package com.soen490.cms.Utils;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.stream.Stream;

public class GeneratePdf {

    private Document doc;

    GeneratePdf(){ doc = new Document(); }

    private PdfPTable addCourseTable(String file_name) {

        // Creates a table with 2 column.
        PdfPTable table = new PdfPTable(2);

        String original_header = "abc", changed_header = "cba";
        String original_note = "aaa", changed_note = "bbb";
        String rationale = "None.";
        String resource_implications = "None.";

        try {
            // package.pdf
            PdfWriter.getInstance(doc, new FileOutputStream(file_name));

            // fonts
            Font bold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            Font italic = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.ITALIC);

            // static headers
            table.addCell(new PdfPCell(new Phrase("Present Text", bold)));
            table.addCell(new PdfPCell(new Phrase("Proposed Text", bold)));
            table.completeRow();

            // dynamic course cells
            Phrase original = new Phrase();
            Phrase changed = new Phrase();

            original.add(new Chunk(original_header, bold));
            changed.add(new Chunk(changed_header, bold));
            // keep adding chunks
            // ...

            table.addCell(new PdfPCell(original));
            table.addCell(new PdfPCell(changed));
            table.completeRow();

            PdfPCell rationale_cell = new PdfPCell(new Phrase(rationale));
            rationale_cell.setColspan(2);

            table.addCell(rationale_cell);
            table.completeRow();

            PdfPCell resource_cell = new PdfPCell(new Phrase(resource_implications));
            rationale_cell.setColspan(2);

            table.addCell(resource_cell);
            table.completeRow();



            // Creates another row that only have to columns.
            // The cell 5 and cell 6 width will span two columns
            // in width.
            PdfPCell cell5 = new PdfPCell(new Phrase("Cell 5"));
            cell5.setColspan(2);
            PdfPCell cell6 = new PdfPCell(new Phrase("Cell 6"));
            cell6.setColspan(2);
            table.addCell(cell5);
            table.addCell(cell6);
            table.completeRow();

            // Adds table to the doc
            doc.open();
            doc.add(table);
        } catch (DocumentException | FileNotFoundException e){
            e.printStackTrace();
        } finally{
            doc.close();
        }

        return table;
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("column header 1", "column header 2", "column header 3")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }
}
