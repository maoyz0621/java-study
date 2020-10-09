1.新建document对象，可通过一下三种任意一种
Document document =new Document(); // 默认页面大小是A4
Document document =new Document(PageSize.A4); // 指定页面大小为A4
Document document =new Document(PageSize.A4,50,50,30,20); // 指定页面大小为A4，且自定义页边距(marginLeft、marginRight、marginTop、marginBottom)
其中页面大小PageSize也可自定义大小，例：new Document(new Rectangle(400, 500));

2.建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
创建 PdfWriter 对象 第一个参数是对文档对象的引用，第二个参数是文件的实际名称，在该名称中还会给出其输出路径
PdfWriter writer =PdfWriter.getInstance(document,new FileOutputStream(filePath));

3.打开文档
写入数据之前要打开文档
document.open();

4.向文档中添加内容
document.add();

5.关闭文档
document.close();