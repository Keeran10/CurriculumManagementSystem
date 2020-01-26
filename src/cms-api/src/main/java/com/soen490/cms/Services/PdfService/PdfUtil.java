// MIT License

// Copyright (c) 2019 teamCMS

// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:

// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.

// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.
package com.soen490.cms.Services.PdfService;

import com.github.difflib.algorithm.DiffException;
import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;
import com.itextpdf.text.*;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;

public class PdfUtil {

    // preface fonts
    public static final Font times_10 = new Font(Font.FontFamily.TIMES_ROMAN, 11);
    public static final Font times_10_bold = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD);
    // table fonts
    static final Font arial_9 = FontFactory.getFont("Arial", 9, BaseColor.BLACK);
    public static final Font arial_10 = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
    static final Font arial_9_bold = FontFactory.getFont("Arial", 9, Font.BOLD);
    static final Font arial_10_bold = FontFactory.getFont("Arial", 10, Font.BOLD);
    static final Font arial_9_italic = FontFactory.getFont("Arial", 9, Font.ITALIC);
    static final Font arial_10_italic = FontFactory.getFont("Arial", 10, Font.ITALIC);
    static final Font arial_10_bold_italic = FontFactory.getFont("Arial", 10, Font.BOLDITALIC);
    // table column names share same font
    public static final Font column_font = arial_10;
    // diffs fonts
    // for credits & description removals
    static final Font arial_10_red = FontFactory.getFont
            ("Arial", 10, BaseColor.RED);

    // for credits & description add-ons
    static final Font arial_10_blue = FontFactory.getFont
            ("Arial", 10, BaseColor.BLUE);

    // this + chunk.setUnderline(0.1f, 3f) for name & number removal (strikethrough)
    static final Font arial_10_red_bold = FontFactory.getFont
            ("Arial", 10, Font.BOLD, BaseColor.RED);

    // this + chunk.setUnderline(0.1f, -1f) for name & number add-ons (underline)
    static final Font arial_10_blue_bold = FontFactory.getFont
            ("Arial", 10, Font.BOLD, BaseColor.BLUE);

    // this + chunk.setUnderline(0.1f, 3f) for title removal (strikethrough)
    static final Font arial_10_red_bold_italic = FontFactory.getFont
            ("Arial", 10, Font.BOLDITALIC, BaseColor.RED);

    // this + chunk.setUnderline(0.1f, -1f) for title add-ons (underline)
    static final Font arial_10_blue_bold_italic = FontFactory.getFont
            ("Arial", 10, Font.BOLDITALIC, BaseColor.BLUE);

    // this + chunk.setUnderline(0.1f, 3f) for note removal (strikethrough)
    static final Font arial_10_red_italic = FontFactory.getFont
            ("Arial", 10, Font.ITALIC, BaseColor.RED);

    // this + chunk.setUnderline(0.1f, -1f) for note add-ons (underline)
    static final Font arial_10_blue_italic = FontFactory.getFont
            ("Arial", 10, Font.ITALIC, BaseColor.BLUE);


    /**
     * Surrounds substrings with "~" to differentiate the original string from the changed string.
     * @param original The string of the original text.
     * @param changed The string of the changed text.
     * @return An array of strings with the appended "~".
     */
    public static String[] generateDiffs(String original, String changed){

        String[] processed_strings = new String[2];

        DiffRowGenerator generator = DiffRowGenerator.create()
                .showInlineDiffs(true)
                .inlineDiffByWord(true)
                .oldTag(f -> "~")
                .newTag(f -> "~")
                .build();

        try {
            List<DiffRow> rows = generator.generateDiffRows(
                    Collections.singletonList(original),
                    Collections.singletonList(changed));

            processed_strings[0] = rows.get(0).getOldLine();
            processed_strings[1] = rows.get(0).getNewLine();

        } catch (DiffException e) {
            e.printStackTrace();
        }

        return processed_strings;
    }


    /**
     * Uses the strings from generateDiffs to highlight substrings with differentiating fonts and colors. This is
     * then written to the document course table.
     * @param original_phrase The original section to be written to the document.
     * @param changed_phrase The changed section to be written to the document.
     * @param o The original string to be differentiated, highlighted and added to the original phrase.
     * @param c The changed string to be differentiated, highlighted and added to the changed phrase.
     * @param type The section in question - 1: name&number, 2: title, 3: credits, 4: description and 5: note
     */
    public static void processDifferences(Phrase original_phrase, Phrase changed_phrase, String o, String c, int type) {

        int ctr = 0;
        String[] processed = generateDiffs(o, c);
        String[] o_partitions = processed[0].split("~");
        String[] c_partitions = processed[1].split("~");

        // compose original phrase
        for(String partition : o_partitions){

            partition = partition.trim();

            if(ctr % 2 != 0 && !partition.equals("")){

                if(type == 1)
                    original_phrase.add(new Chunk(partition + " ",
                            arial_10_red_bold).setUnderline(0.1f, 3f));

                if(type == 2)
                    original_phrase.add(new Chunk(partition + " ",
                            arial_10_red_bold_italic).setUnderline(0.1f, 3f));

                if(type == 3) {
                    original_phrase.add(new Chunk(partition,
                            arial_10_red).setUnderline(0.1f, 3f));
                }

                if(type == 4) {

                    if(StringUtils.isNumeric(partition))
                        original_phrase.add(new Chunk(partition,
                                arial_10_red).setUnderline(0.1f, 3f));
                    else
                        original_phrase.add(new Chunk(partition + " ",
                                arial_10_red).setUnderline(0.1f, 3f));
                }

                if(type == 5)
                    original_phrase.add(new Chunk(partition + " ",
                            arial_10_red_italic).setUnderline(0.1f, 3f));

                if(type == 6) {
                    original_phrase.add(new Chunk(partition + " ",
                            arial_10_red).setUnderline(0.1f, 3f));
                }
            }
            else if(!partition.equals("")){

                if(type == 1) {
                    original_phrase.add(new Chunk(partition + " ", arial_10_bold));
                }

                if(type == 2) {
                    original_phrase.add(new Chunk(partition + " ", arial_10_bold_italic));
                }

                if(type == 3) {

                    if(partition.equals("credits)"))
                        original_phrase.add(new Chunk(" " + partition, arial_10));

                    else
                        original_phrase.add(new Chunk(partition, arial_10));
                }

                if(type == 4) {
                    original_phrase.add(new Chunk(partition + " ", arial_10));
                }

                if(type == 5) {
                    original_phrase.add(new Chunk(partition + " ", arial_10_italic));
                }

                if(type == 6) {
                    original_phrase.add(new Chunk(partition + " ", arial_10));
                }

            }

            ctr++;
        }

        // reset counter
        ctr = 0;

        // compose changed phrase
        for(String partition : c_partitions){

            partition = partition.trim();

            if(ctr % 2 != 0 && !partition.equals("")){

                if(type == 1)
                    changed_phrase.add(new Chunk(partition + " ",
                            arial_10_blue_bold).setUnderline(0.1f, -1f));

                if(type == 2)
                    changed_phrase.add(new Chunk(partition + " ",
                            arial_10_blue_bold_italic).setUnderline(0.1f, -1f));

                if(type == 3) {
                    changed_phrase.add(new Chunk(partition,
                            arial_10_blue).setUnderline(0.1f, -1f));
                }

                if(type == 4) {

                    if(StringUtils.isNumeric(partition))
                        changed_phrase.add(new Chunk(partition,
                                arial_10_blue).setUnderline(0.1f, -1f));
                    else
                        changed_phrase.add(new Chunk(partition + " ",
                                arial_10_blue).setUnderline(0.1f, -1f));
                }

                if(type == 5)
                    changed_phrase.add(new Chunk(partition + " ",
                            arial_10_blue_italic).setUnderline(0.1f, -1f));

                if(type == 6) {
                    changed_phrase.add(new Chunk(partition + " ",
                            arial_10_blue).setUnderline(0.1f, -1f));
                }

            }
            else if(!partition.equals("")){

                if(type == 1) {
                    changed_phrase.add(new Chunk(partition + " ", arial_10_bold));
                }

                if(type == 2) {
                    changed_phrase.add(new Chunk(partition + " ", arial_10_bold_italic));
                }

                if(type == 3) {

                    if(partition.equals("credits)"))
                        changed_phrase.add(new Chunk(" " + partition, arial_10));

                    else
                        changed_phrase.add(new Chunk(partition, arial_10));
                }

                if(type == 4) {
                    changed_phrase.add(new Chunk(partition + " ", arial_10));
                }

                if(type == 5) {
                    changed_phrase.add(new Chunk(partition + " ", arial_10_italic));
                }

                if(type == 6) {
                    changed_phrase.add(new Chunk(partition + " ", arial_10));
                }
            }

            ctr++;
        }

    }


    /**
     * Handles program string differences which are slightly distinct from the course diff method.
     * @param o
     * @param c
     * @param present
     * @param proposed
     */
    public static void processProgramDifference(Paragraph o, Paragraph c, String present, String proposed){

        String[] processed = generateDiffs(present, proposed);
        String[] o_partitions = processed[0].split("~");
        String[] c_partitions = processed[1].split("~");
        int ctr = 0;


        for(String partition : o_partitions){

            if(ctr % 2 != 0 && !partition.equals("")){
                o.add(new Chunk(partition, arial_10_red).setUnderline(0.1f, 3f));
            }
            else if(!partition.equals("")){
                o.add(new Chunk(partition, arial_10));
            }

            ctr++;
        }

        ctr = 0;

        for(String partition : c_partitions){

            if(ctr % 2 != 0 && !partition.equals("")){
                c.add(new Chunk(partition, arial_10_blue).setUnderline(0.1f, -1f));
            }
            else if(!partition.equals("")){
                c.add(new Chunk(partition, arial_10));
            }

            ctr++;
        }

    }

}
