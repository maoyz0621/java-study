/**
 * Copyright 2020 Inc.
 **/
package com.myz.ajax.itextpdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * 加水印的实现类
 *
 * @author maoyz0621 on 2020/9/20
 * @version v1.0
 */
public class Watermark extends PdfPageEventHelper {

    Font FONT = new Font(Font.FontFamily.HELVETICA, 30, Font.BOLD, new GrayColor(0.95f));

    /**
     * 水印内容
     */
    private String waterContent;

    public Watermark() {
    }

    public Watermark(String waterContent) {
        this.waterContent = waterContent;
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                ColumnText.showTextAligned(writer.getDirectContentUnder(),
                        Element.ALIGN_CENTER,
                        new Phrase(this.waterContent == null ? "HELLO WORLD" : this.waterContent, FONT),
                        (50.5f + i * 350),
                        (40.0f + j * 150),
                        writer.getPageNumber() % 2 == 1 ? 45 : -45);
            }
        }
    }
}