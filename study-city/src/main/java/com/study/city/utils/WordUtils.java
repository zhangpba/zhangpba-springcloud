package com.study.city.utils;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.Bookmark;
import org.apache.poi.hwpf.usermodel.Bookmarks;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhangpba
 * @date 2021-04-29
 */
public class WordUtils {
    /**
     * 根据模板写入word
     *
     * @param tempPath   模板路径
     * @param targetPath 目标路径
     */
    public static void writeDoc(String tempPath, String targetPath) {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(tempPath);
            HWPFDocument doc = new HWPFDocument(is);
            Range range = doc.getRange();
            // 把range范围内的${reportDate}替换为当前的日期
            range.replaceText("${reportDate}", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            range.replaceText("${appleAmt}", "100.00");
            range.replaceText("${bananaAmt}", "200.00");
            range.replaceText("${totalAmt}", "300.00");
            range.replaceText("${title}", "黄金价格");
            os = new FileOutputStream(targetPath);
            // 把doc输出到输出流中
            doc.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭输入流
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 关闭输出流
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 一 读取word内容(根据WordExtractor读取)
     *
     * @param fileName 文件路径+文件名
     * @return
     */
    public static String readDoc(String fileName) {
        String buffer = "";
        FileInputStream is = null;
        OPCPackage opcPackage = null;
        try {
            if (fileName.endsWith(".doc")) {
                is = new FileInputStream(fileName);
                WordExtractor extractor = new WordExtractor(is);
                buffer = extractor.getText();
                // 输出word文档所有的文本
                System.out.println(extractor.getText());
                System.out.println(extractor.getTextFromPieces());
                // 输出页眉的内容
                System.out.println("页眉：" + extractor.getHeaderText());
                // 输出页脚的内容
                System.out.println("页脚：" + extractor.getFooterText());
                // 输出当前word文档的元数据信息，包括作者、文档的修改时间等。
                System.out.println(extractor.getMetadataTextExtractor().getText());
                // 获取各个段落的文本
                String paraTexts[] = extractor.getParagraphText();
                for (int i = 0; i < paraTexts.length; i++) {
                    System.out.println("Paragraph " + (i + 1) + " : " + paraTexts[i]);
                }
            } else if (fileName.endsWith("docx")) {
                opcPackage = POIXMLDocument.openPackage(fileName);
                POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
                buffer = extractor.getText();
            } else {
                System.out.println("此文件不是word文件！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭输入流
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (opcPackage != null) {
                try {
                    opcPackage.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return buffer;
    }

    /**
     * 二 HWPFDocument读文件
     *
     * @return
     */
    public static String readDocByHwfd(String fileName) {
        InputStream is = null;
        try {
            is = new FileInputStream(fileName);
            HWPFDocument doc = new HWPFDocument(is);
            // 输出书签信息
            printInfo((Range) doc.getBookmarks());
            // 输出文本
            System.out.println(doc.getDocumentText());
            Range range = doc.getRange();
            // insertInfo(range);
            printInfo(range);
            // 读表格
            readTable(range);
            // 读列表
            readList(range);
            // 删除range
            Range r = new Range(2, 5, doc);
            r.delete();//在内存中进行删除，如果需要保存到文件中需要再把它写回文件
            // 把当前HWPFDocument写到输出流中
//            doc.write(new FileOutputStream("D:\\test.doc"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭输入流
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 输出书签信息
     *
     * @param bookmarks
     */
    private static void printInfo(Bookmarks bookmarks) {
        int count = bookmarks.getBookmarksCount();
        System.out.println("书签数量：" + count);
        Bookmark bookmark;
        for (int i = 0; i < count; i++) {
            bookmark = bookmarks.getBookmark(i);
            System.out.println("书签" + (i + 1) + "的名称是：" + bookmark.getName());
            System.out.println("开始位置：" + bookmark.getStart());
            System.out.println("结束位置：" + bookmark.getEnd());
        }
    }

    /**
     * 读表格
     * 每一个回车符代表一个段落，所以对于表格而言，每一个单元格至少包含一个段落，每行结束都是一个段落。
     *
     * @param range
     */
    private static void readTable(Range range) {
        //遍历range范围内的table。
        TableIterator tableIter = new TableIterator(range);
        Table table;
        TableRow row;
        TableCell cell;
        while (tableIter.hasNext()) {
            table = tableIter.next();
            int rowNum = table.numRows();
            for (int j = 0; j < rowNum; j++) {
                row = table.getRow(j);
                int cellNum = row.numCells();
                for (int k = 0; k < cellNum; k++) {
                    cell = row.getCell(k);
                    //输出单元格的文本
                    System.out.println(cell.text().trim());
                }
            }
        }
    }

    /**
     * 读列表
     *
     * @param range
     */
    private static void readList(Range range) {
        int num = range.numParagraphs();
        Paragraph para;
        for (int i = 0; i < num; i++) {
            para = range.getParagraph(i);
            if (para.isInTable()) {
                System.out.println("读列表: " + para.text());
            }
        }
    }

    /**
     * 输出Range
     *
     * @param range
     */
    private static void printInfo(Range range) {
        //获取段落数
        int paraNum = range.numParagraphs();
        System.out.println(paraNum);
        for (int i = 0; i < paraNum; i++) {
            insertInfo(range.getParagraph(i));
            System.out.println("段落" + (i + 1) + "：" + range.getParagraph(i).text());
            if (i == (paraNum - 1)) {
                insertInfo(range.getParagraph(i));
            }
        }
        int secNum = range.numSections();
        System.out.println(secNum);
        Section section;
        for (int i = 0; i < secNum; i++) {
            section = range.getSection(i);
            System.out.println(section.getMarginLeft());
            System.out.println(section.getMarginRight());
            System.out.println(section.getMarginTop());
            System.out.println(section.getMarginBottom());
            System.out.println(section.getPageHeight());
            System.out.println(section.text());
        }
    }

    /**
     * 插入内容到Range，这里只会写到内存中
     *
     * @param range
     */
    private static void insertInfo(Range range) {
        range.insertAfter("Hello");
    }

    /**
     * 输出当前word的一些信息
     *
     * @param info
     */
    private static void printInfo(SummaryInformation info) {
        // 作者
        System.out.println("作者:" + info.getAuthor());
        // 字符统计
        System.out.println("字符统计:" + info.getCharCount());
        // 页数
        System.out.println("页数:" + info.getPageCount());
        // 标题
        System.out.println("标题:" + info.getTitle());
        // 主题
        System.out.println("主题:" + info.getSubject());
    }
}
