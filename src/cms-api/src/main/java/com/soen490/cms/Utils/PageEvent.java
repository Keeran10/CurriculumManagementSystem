package com.soen490.cms.Utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;


public class PageEvent extends PdfPageEventHelper {

    private static int page_number = 1;
    private static Font arial_10 = FontFactory.getFont("Arial", 10, BaseColor.BLACK);

    public void onStartPage(PdfWriter writer, Document doc){

        //Rectangle rect = writer.getBoxSize("corner-box");
        //ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_CENTER, new Phrase
                //("Top Left"), rect.getLeft() + 20, rect.getTop() - 50, 0);
        //ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_CENTER, new Phrase
                //((new Date()).toString()), rect.getRight() - 50, rect.getTop() - 50, 0);
    }


    public void onEndPage(PdfWriter writer, Document doc){

        Rectangle rect = writer.getBoxSize("corner-box");
        //ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_CENTER, new Phrase
               //("Bottom Left"), rect.getLeft() + 50, rect.getBottom() + 10, 0);
        Phrase page_n = new Phrase();
        page_n.add(new Chunk(String.valueOf(page_number++), arial_10));

        ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_CENTER, page_n, rect.getRight() - 15, rect.getBottom() + 10, 0);
    }
}
