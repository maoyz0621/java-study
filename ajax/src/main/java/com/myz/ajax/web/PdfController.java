/**
 * Copyright 2020 Inc.
 **/
package com.myz.ajax.web;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.myz.ajax.itextpdf.PdfReport;
import com.myz.ajax.pdf.ColumnVo;
import com.myz.ajax.pdf.TableVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author maoyz0621 on 2020/9/15
 * @version v1.0
 */
@RestController
@RequestMapping("/pdf")
public class PdfController {

    /**
     * @param
     * @return
     */

    @GetMapping(value = "/download")
    public void download(HttpServletResponse response) {
        try {
            List<TableVo> tables = new ArrayList<>();
            TableVo tableVo = new TableVo();
            tableVo.setTableComment("aaaa");
            tableVo.setTableName("asdasd");

            ColumnVo vo1 = new ColumnVo();
            vo1.setColumnComment("aaa");
            vo1.setColumnKey("asdas");
            vo1.setColumnName("asdasd");
            vo1.setColumnType("asdfsfsdaf");
            vo1.setIsNullable("adasdasdas");
            List<ColumnVo> list = new ArrayList<>();
            list.add(vo1);
            tableVo.setColumns(list);
            tables.add(tableVo);


            PdfDocument pdfDoc = new PdfDocument(new PdfWriter("DatabaseDesign.pdf"));
            // 构建文档对象
            Document doc = new Document(pdfDoc);
            // TextFooterEventHandler eh = new TextFooterEventHandler();
            // pdfDoc.addEventHandler(PdfDocumentEvent.END_PAGE, eh);
            // 中文字体
            PdfFont sysFont = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
            Paragraph paragraph = new Paragraph();
            paragraph.add("数据库设计文档").setFont(sysFont).setBold().setFontSize(20).setTextAlignment(TextAlignment.CENTER);
            doc.add(paragraph);
            int num = 0;
            for (TableVo vo : tables) {
                num++;
                // 段落
                doc.add(new Paragraph(""));
                String title = num + "  表名：" + vo.getTableName() + "   表注释：" + vo.getTableComment();
                doc.add(new Paragraph(title).setFont(sysFont).setBold());
                // 构建表格以100%的宽度
                Table table = new Table(5).setWidth(UnitValue.createPercentValue(100));

                // 第一行
                table.addCell(new Cell().add(new Paragraph("列名")).setFont(sysFont)
                        .setBackgroundColor(new DeviceRgb(221, 234, 238)));
                table.addCell(new Cell().add(new Paragraph("数据类型")).setFont(sysFont)
                        .setBackgroundColor(new DeviceRgb(221, 234, 238)));
                table.addCell(new Cell().add(new Paragraph("约束")).setFont(sysFont)
                        .setBackgroundColor(new DeviceRgb(221, 234, 238)));
                table.addCell(new Cell().add(new Paragraph("允许空")).setFont(sysFont)
                        .setBackgroundColor(new DeviceRgb(221, 234, 238)));
                table.addCell(new Cell().add(new Paragraph("备注")).setFont(sysFont)
                        .setBackgroundColor(new DeviceRgb(221, 234, 238)));

                // 第二行
                for (ColumnVo col : vo.getColumns()) {
                    table.addCell(new Cell().add(new Paragraph(col.getColumnName())).setFont(sysFont));
                    table.addCell(new Cell().add(new Paragraph(col.getColumnType())).setFont(sysFont));
                    table.addCell(new Cell().add(new Paragraph(col.getColumnKey())).setFont(sysFont));
                    table.addCell(new Cell().add(new Paragraph(col.getIsNullable())).setFont(sysFont));
                    table.addCell(new Cell().add(new Paragraph(col.getColumnComment())).setFont(sysFont));
                }

                // 将表格添加入文档并页面居中
                doc.add(table.setHorizontalAlignment(HorizontalAlignment.CENTER));
            }
            doc.close();
            // return "文件路径-" + System.getProperty("user.dir") + "\\DatabaseDesign.pdf";
        } catch (Exception e) {
            e.printStackTrace();
        }

        File file = new File("DatabaseDesign.pdf");
        if (!file.exists()) {
            return;
        }
        // 设置格式
        try {
            response.reset();
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("utf-8");
            response.setContentLength((int) file.length());
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("DatabaseDesign.pdf", "UTF-8"));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        // 文件写出
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));) {
            byte[] buff = new byte[1024];
            OutputStream os = response.getOutputStream();
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (Exception e) {
            // return "下载失败";
        } finally {
            // 删除服务器文件
            boolean result = false;
            int tryCount = 0;
            while (!result && tryCount++ < 10) {
                System.gc();
                result = file.delete();
            }

        }
    }

    @GetMapping(value = "/download1")
    public void download1(HttpServletResponse response) {
        try {
            //PdfReport.gen(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}