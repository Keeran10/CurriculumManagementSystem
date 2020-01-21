package com.soen490.cms.Services.PdfService;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.soen490.cms.Models.Course;
import com.soen490.cms.Models.DegreeRequirement;
import com.soen490.cms.Models.Request;
import com.soen490.cms.Models.Requisite;
import com.soen490.cms.Services.ImpactAssessmentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

import static com.soen490.cms.Services.PdfService.PdfUtil.*;


@Service
@Log4j2
public class PdfCourse {

    @Autowired
    private ImpactAssessmentService impactAssessmentService;

    @Autowired
    private PdfPreface pdfPreface;

    String o_anti_note = "";
    String c_anti_note = "";


    public void addCoursePage(Document doc, Request request, Course o, Course c){

        doc = pdfPreface.addCoursePreface(doc, request, o, c);

        try {
            addCourseDiffTable(doc, request, o , c);
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }
    }


    /**
     * Adds the course table where the differences between the original and changed course are highlighted.
     * @param doc The document used to write the course preface.
     * @param request The request object containing the course metadata.
     * @param o The original course information.
     * @param c The changed course information.
     * @throws FileNotFoundException
     * @throws DocumentException
     */
    public void addCourseDiffTable(Document doc, Request request, Course o, Course c) throws FileNotFoundException, DocumentException {

        // Creates a table with 2 column.
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);

        float CELL_PADDING = 5f;
        int size = 0;
        String remainder;
        int request_type = request.getRequestType();

        // if this is a course creation, original course should have fields set to null to avoid exceptions
        if(request_type == 1) {
            o = new Course();
            o.setName("");
            o.setNumber(0);
            o.setCredits(0);
            o.setDescription("");
            o.setLevel(0);
            o.setTitle("");
            o.setNote("");
        }
        // if this is a course deletion, changed course should have fields set to null to avoid exceptions
        else if(request_type == 3) {
            c = new Course();
            c.setName("");
            c.setNumber(0);
            c.setCredits(0);
            c.setDescription("");
            c.setLevel(0);
            c.setTitle("");
            c.setNote("");
        }
        else if(request_type != 2){
            log.error("Non-existent course request type: " + request_type);
            return;
        }

        String o_name = o.getName() + " " + o.getNumber();
        String o_title = " " + o.getTitle();
        String o_credits = " (" + o.getCredits() + " credits)";
        String c_name = c.getName() + " " + c.getNumber();
        String c_title = " " + c.getTitle();
        String c_credits = " (" + c.getCredits() + " credits)";

        String o_body = stringifyRequisites(o.getRequisites(), true) + o.getDescription();
        String c_body = stringifyRequisites(c.getRequisites(), false) + c.getDescription();

        String o_note = o.getNote();
        String c_note = c.getNote();

        if(!o_anti_note.equals("")){

            if(!o_note.equals(""))
                o_note = o_note.substring(6);

            o_note = o_anti_note + o_note;
        }
        if(!c_anti_note.equals("")){

            if(!c_note.equals(""))
                c_note = c_note.substring(6);

            c_note = c_anti_note + c_note;
        }

        String rationale = request.getRationale();
        String resource_implications = request.getResourceImplications();

        // static headers
        table.addCell(new PdfPCell(new Phrase("Present Text", times_10_bold))).setPadding(CELL_PADDING);
        table.addCell(new PdfPCell(new Phrase("Proposed Text", times_10_bold))).setPadding(CELL_PADDING);
        table.completeRow();

        // course paragraph
        Paragraph original_paragraph = new Paragraph();
        Paragraph changed_paragraph = new Paragraph();

        // course phrases
        // name and number
        Phrase original_name_phrase = new Phrase();
        Phrase changed_name_phrase = new Phrase();

        // title
        Phrase original_title_phrase = new Phrase();
        Phrase changed_title_phrase = new Phrase();

        // credits
        Phrase original_credits_phrase = new Phrase();
        Phrase changed_credits_phrase = new Phrase();

        // body = chunks of requisites & descriptions
        Phrase original_body_phrase = new Phrase();
        Phrase changed_body_phrase = new Phrase();

        // notes
        Phrase original_note_phrase = new Phrase();
        Phrase changed_note_phrase = new Phrase();

        if(request.getRequestType() == 1){

            changed_name_phrase.add(new Chunk(c_name, arial_10_bold));
            changed_title_phrase.add(new Chunk(c_title, arial_10_bold_italic));
            changed_credits_phrase.add(new Chunk(c_credits, arial_10));
            changed_body_phrase.add(new Chunk(c_body, arial_10));
            changed_note_phrase.add(new Chunk(c_note, arial_10_italic));

        }
        else if(request.getRequestType() == 2) {

            processDifferences(original_name_phrase, changed_name_phrase, o_name, c_name, 1);
            processDifferences(original_title_phrase, changed_title_phrase, o_title, c_title, 2);
            processDifferences(original_credits_phrase, changed_credits_phrase, o_credits, c_credits, 3);
            processDifferences(original_body_phrase, changed_body_phrase, o_body, c_body, 4);
            processDifferences(original_note_phrase, changed_note_phrase, o_note, c_note, 5);

        }
        else if(request.getRequestType() == 3){

            original_name_phrase.add(new Chunk(o_name, arial_10_bold));
            original_title_phrase.add(new Chunk(o_title, arial_10_bold_italic));
            original_credits_phrase.add(new Chunk(o_credits, arial_10));
            original_body_phrase.add(new Chunk(o_body, arial_10));
            original_note_phrase.add(new Chunk(o_note, arial_10_italic));

        }
        // ...

        // add all course phrases to paragraph
        original_paragraph.add(original_name_phrase);
        original_paragraph.add(original_title_phrase);
        original_paragraph.add(original_credits_phrase);
        original_paragraph.add(Chunk.NEWLINE);
        original_paragraph.add(original_body_phrase);
        original_paragraph.add(Chunk.NEWLINE);
        original_paragraph.add(original_note_phrase);

        changed_paragraph.add(changed_name_phrase);
        changed_paragraph.add(changed_title_phrase);
        changed_paragraph.add(changed_credits_phrase);
        changed_paragraph.add(Chunk.NEWLINE);
        changed_paragraph.add(changed_body_phrase);
        changed_paragraph.add(Chunk.NEWLINE);
        changed_paragraph.add(changed_note_phrase);

        // once all course details are done
        table.addCell(new PdfPCell(original_paragraph)).setPadding(CELL_PADDING);
        table.addCell(new PdfPCell(changed_paragraph)).setPadding(CELL_PADDING);
        table.completeRow();

        // add rationale cell which spans 2 columns
        Phrase rationale_phrase = new Phrase();

        rationale_phrase.add(new Chunk("Rationale:", column_font).setUnderline(0.1f, -1f));
        rationale_phrase.add(Chunk.NEWLINE);
        rationale_phrase.add(Chunk.NEWLINE);

        if(rationale != null && !rationale.equals(""))
            rationale_phrase.add(new Chunk(rationale, arial_10));
        else
            rationale_phrase.add(new Chunk("None.", arial_10));

        PdfPCell rationale_cell = new PdfPCell(rationale_phrase);
        rationale_cell.setColspan(2);
        table.addCell(rationale_cell).setPadding(CELL_PADDING);
        table.completeRow();

        // add resource implications cell which spans 2 columns
        Phrase resource_phrase = new Phrase();

        resource_phrase.add(new Chunk("Resource Implications:", column_font).setUnderline(0.1f, -1f));
        resource_phrase.add(Chunk.NEWLINE);
        resource_phrase.add(Chunk.NEWLINE);

        if(resource_implications != null && !resource_implications.equals(""))
            resource_phrase.add(new Chunk(resource_implications, arial_10));
        else
            resource_phrase.add(new Chunk("None.", arial_10));

        PdfPCell resource_cell = new PdfPCell(resource_phrase);
        resource_cell.setColspan(2);
        table.addCell(resource_cell).setPadding(CELL_PADDING);
        table.completeRow();

        // add impact report cell
        table.addCell(addCourseImpactReport(request)).setPadding(CELL_PADDING);

        try {
            doc.add(table);
        }catch(DocumentException e){
            e.printStackTrace();
        }
    }


    /**
     * Aggregates a collection of requisites into a string identical to those displayed on the academic calendar.
     * @param requisites The requisites of a given course.
     * @return A formatted string of requisites.
     */
    private String stringifyRequisites(Collection<Requisite> requisites, boolean original) {

        StringBuilder r;
        int ctr = 0;
        int ctr_anti = 0;
        int ctr2_anti = 0;
        int ctr_co = 0;
        int ctr2_co = 0;
        int ctr_pre = 0;
        String anti_store = "";
        StringBuilder co_store = new StringBuilder();

        if(!requisites.isEmpty())
            r = new StringBuilder("Prerequisite: ");
        else
            return "";

        boolean equivalent_next = true;

        for(Requisite requisite : requisites){

            if(requisite.getType().equals("antirequisite"))
                ctr_anti++;
            if(requisite.getType().equals("corequisite"))
                ctr_co++;
            if(requisite.getType().equals("prerequisite"))
                ctr_pre++;
        }

        for(Requisite requisite : requisites){

            String type = requisite.getType();
            String name_number;

            if(requisite.getNumber() == 0)
                name_number= requisite.getName() + " ";
            else
                name_number= requisite.getName() + " " + requisite.getNumber();


            if(type.equals("prerequisite")) {
                if(ctr == 0) {
                    r.append(name_number);
                    ctr++;
                }
                else
                    r.append("; ").append(name_number);
            }
            else if(type.equals("equivalent") && equivalent_next) {
                r.append("; ").append(name_number).append(" or ");
                equivalent_next = false;
            }
            else if(type.equals("equivalent") && !equivalent_next){
                r.append(name_number);
                equivalent_next = true;
            }
            else if(type.equals("corequisite")) {

                if(ctr_co == 1)
                    co_store = new StringBuilder(name_number + " previously or concurrently");

                else if(ctr_co == 2 && ctr2_co == 0)
                    co_store = new StringBuilder(name_number);

                else if(ctr_co == 2 && ctr2_co == 1)
                    co_store.append(" and ").append(name_number).append(" previously or concurrently");

                else if(ctr_co > 2 && ctr2_co == 0)
                    co_store = new StringBuilder(name_number);

                else if(ctr_co > 2 && ctr_co != ctr2_co + 1)
                    co_store.append(", ").append(name_number);

                else co_store.append(" and ").append(name_number).append("  previously or concurrently");

                ctr2_co++;

            }
            else if(type.equals("antirequisite")){

                if(ctr_anti == 1)
                    anti_store = name_number;

                else if(ctr_anti == 2 && ctr2_anti == 0)
                    anti_store = name_number;

                else if(ctr_anti == 2 && ctr2_anti == 1)
                    anti_store = anti_store + " or " + name_number;

                else if(ctr_anti > 2 && ctr2_anti == 0)
                    anti_store = name_number;

                else if(ctr_anti != ctr2_anti + 1)
                    anti_store = anti_store + ", " + name_number;

                else anti_store = anti_store + " or " + name_number;

                ctr2_anti++;
            }
        }

        if (original && ctr_anti > 0)
            o_anti_note = "NOTE: Students who have received credit for " + anti_store +
                    " may not take this course for credit.";
        else if (!original && ctr_anti > 0)
            c_anti_note = "NOTE: Students who have received credit for " + anti_store +
                    " may not take this course for credit.";

        if(ctr_co > 0 && ctr_pre > 0)
            r.append("; ").append(co_store);

        if(ctr_co > 0 && ctr_pre == 0)
            r.append(co_store);

        r.append(". ");

        return r.toString();
    }


    /**
     * Creates a table cell with the impact statement report from the given request.
     * @param request The request object used to generate impact statements.
     * @return the last course diff table cell with the impact statements report.
     */
    private PdfPCell addCourseImpactReport(Request request) {

        Map<String, Object> impact_report = impactAssessmentService.getAssessment(request.getId());

        Iterator it = impact_report.entrySet().iterator();

        boolean isNone = true;

        Paragraph report_paragraph = new Paragraph();
        report_paragraph.setTabSettings(new TabSettings(25f));
        report_paragraph.add(new Chunk("Impact Statements Report:", column_font).setUnderline(0.1f, -1f));
        report_paragraph.add(Chunk.NEWLINE);

        String key;

        while(it.hasNext()){

            Map.Entry pair = (Map.Entry) it.next();

            key = pair.getKey().toString();

            if(key.equals("DegreeCourseRequiredImpact")) {

                Map<String, Object> original_removed_added_updated = (Map<String, Object>) pair.getValue();

                ArrayList<Map<String, Object>> original = (ArrayList) original_removed_added_updated.get("original");
                ArrayList<Map<String, Object>> removed = (ArrayList) original_removed_added_updated.get("removed");
                ArrayList<Map<String, Object>> added = (ArrayList) original_removed_added_updated.get("added");
                ArrayList<Map<String, Object>> updated = (ArrayList) original_removed_added_updated.get("updated");

                if(original != null) {

                    if (0 < original.size()) {

                        report_paragraph.add(Chunk.NEWLINE);

                        for (int i = 0; i < original.size(); i++) {

                            Iterator it_u = original.get(i).entrySet().iterator();

                            while (it_u.hasNext()) {

                                Map.Entry updated_pair = (Map.Entry) it_u.next();

                                String impact_line = ("Original degree to be modified: " +
                                        updated_pair.getKey() + " (" + updated_pair.getValue() + " credits).");


                                report_paragraph.add(new Chunk(impact_line, arial_10));
                                report_paragraph.add(Chunk.NEWLINE);
                                isNone = false;
                            }
                        }
                        if (updated != null) {
                            if (0 < updated.size()) {

                                for (int i = 0; i < updated.size(); i++) {

                                    Iterator it_u = updated.get(i).entrySet().iterator();

                                    while (it_u.hasNext()) {

                                        Map.Entry updated_pair = (Map.Entry) it_u.next();

                                        String impact_line = ("- Degree credits will be updated to become: " +
                                                updated_pair.getKey() + " (" + updated_pair.getValue() + " credits).");

                                        report_paragraph.add(Chunk.TABBING);
                                        report_paragraph.add(new Chunk(impact_line, arial_10));
                                        report_paragraph.add(Chunk.NEWLINE);
                                    }
                                }
                            }
                        }
                        if (removed != null) {
                            if (0 < removed.size()) {

                                for (int i = 0; i < removed.size(); i++) {

                                    Iterator it_u = removed.get(i).entrySet().iterator();

                                    while (it_u.hasNext()) {

                                        Map.Entry updated_pair = (Map.Entry) it_u.next();

                                        String impact_line = ("- Degree credits will be removed to become: " +
                                                updated_pair.getKey() + " (" + updated_pair.getValue() + " credits).");

                                        report_paragraph.add(Chunk.TABBING);
                                        report_paragraph.add(new Chunk(impact_line, arial_10));
                                        report_paragraph.add(Chunk.NEWLINE);
                                    }
                                }
                            }
                        }
                        if (added != null) {
                            if (0 < added.size()) {

                                for (int i = 0; i < added.size(); i++) {

                                    Iterator it_u = added.get(i).entrySet().iterator();

                                    while (it_u.hasNext()) {

                                        Map.Entry updated_pair = (Map.Entry) it_u.next();

                                        String impact_line = ("- Degree credits will be added to become: " +
                                                updated_pair.getKey() + " (" + updated_pair.getValue() + " credits).");

                                        report_paragraph.add(Chunk.TABBING);
                                        report_paragraph.add(new Chunk(impact_line, arial_10));
                                        report_paragraph.add(Chunk.NEWLINE);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if(key.equals("ProgramImpact")) {

                Map<String, Object> original_removed_added_updated = (Map<String, Object>) pair.getValue();

                ArrayList<Map<String, Object>> original = (ArrayList) original_removed_added_updated.get("original");
                ArrayList<Map<String, Object>> removed = (ArrayList) original_removed_added_updated.get("removed");
                ArrayList<Map<String, Object>> added = (ArrayList) original_removed_added_updated.get("added");
                ArrayList<Map<String, Object>> updated = (ArrayList) original_removed_added_updated.get("updated");

                if (original != null) {

                    double temp_credits = 0;

                    if (0 < original.size()) {

                        for (int i = 0; i < original.size(); i++) {

                            Iterator it_u = original.get(i).entrySet().iterator();

                            while (it_u.hasNext()) {

                                Map.Entry updated_pair = (Map.Entry) it_u.next();

                                if(updated_pair.getKey().equals("") && Double.valueOf(updated_pair.getValue().toString()) == 0){
                                    continue;
                                }

                                String impact_line = ("Core section to be modified: " +
                                        updated_pair.getKey() + " (" + updated_pair.getValue() + " credits).");

                                temp_credits = (double) updated_pair.getValue();

                                report_paragraph.add(Chunk.NEWLINE);
                                report_paragraph.add(new Chunk(impact_line, arial_10));
                                report_paragraph.add(Chunk.NEWLINE);
                                isNone = false;
                            }
                        }
                        if(updated != null) {
                            if (0 < updated.size()) {

                                for (int i = 0; i < updated.size(); i++) {

                                    Iterator it_u = updated.get(i).entrySet().iterator();

                                    while (it_u.hasNext()) {

                                        Map.Entry updated_pair = (Map.Entry) it_u.next();

                                        if(updated_pair.getKey().equals("") && Double.valueOf(updated_pair.getValue().toString()) == 0){
                                            continue;
                                        }

                                        String impact_line;

                                        if(Double.valueOf(updated_pair.getValue().toString()) == 0) {
                                            impact_line = ("- Course will be migrated to: " + updated_pair.getKey() + ".");
                                        }
                                        else if(Double.valueOf(updated_pair.getValue().toString()) > temp_credits){
                                            impact_line = ("- Core credits will be added to become: " +
                                                    updated_pair.getKey() + " (" + updated_pair.getValue() + " credits).");
                                        }
                                        else{
                                            impact_line = ("- Core credits will be removed to become: " +
                                                    updated_pair.getKey() + " (" + updated_pair.getValue() + " credits).");
                                        }

                                        report_paragraph.add(Chunk.TABBING);
                                        report_paragraph.add(new Chunk(impact_line, arial_10));
                                        report_paragraph.add(Chunk.NEWLINE);
                                    }
                                }
                            }
                        }
                        if(removed != null) {
                            if (0 < removed.size()) {

                                for (int i = 0; i < removed.size(); i++) {

                                    Iterator it_u = removed.get(i).entrySet().iterator();

                                    while (it_u.hasNext()) {

                                        Map.Entry updated_pair = (Map.Entry) it_u.next();

                                        if(updated_pair.getKey().equals("") && Double.valueOf(updated_pair.getValue().toString()) == 0){
                                            continue;
                                        }

                                        String impact_line;

                                        if(Double.valueOf(updated_pair.getValue().toString()) == 0) {
                                            impact_line = ("- Course will be removed from: " + updated_pair.getKey() + ".");
                                        }
                                        else {
                                            impact_line = ("- Core credits will be added to become: " +
                                                    updated_pair.getKey() + " (" + updated_pair.getValue() + " credits).");
                                        }

                                        report_paragraph.add(Chunk.TABBING);
                                        report_paragraph.add(new Chunk(impact_line, arial_10));
                                        report_paragraph.add(Chunk.NEWLINE);
                                    }
                                }
                            }
                        }
                        if(added != null) {
                            if (0 < added.size()) {

                                for (int i = 0; i < added.size(); i++) {

                                    Iterator it_u = added.get(i).entrySet().iterator();

                                    while (it_u.hasNext()) {

                                        Map.Entry updated_pair = (Map.Entry) it_u.next();

                                        if(updated_pair.getKey().equals("") && Double.valueOf(updated_pair.getValue().toString()) == 0){
                                            continue;
                                        }

                                        String impact_line;

                                        if(Double.valueOf(updated_pair.getValue().toString()) == 0) {
                                            impact_line = ("- Course will be migrated to: " + updated_pair.getKey() + ".");
                                        }
                                        else {
                                            impact_line = ("- Core credits will be added to become: " +
                                                    updated_pair.getKey() + " (" + updated_pair.getValue() + " credits).");
                                        }

                                        report_paragraph.add(Chunk.TABBING);
                                        report_paragraph.add(new Chunk(impact_line, arial_10));
                                        report_paragraph.add(Chunk.NEWLINE);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if(isNone)
            report_paragraph.add(new Chunk("None.", arial_10));

        PdfPCell report = new PdfPCell(report_paragraph);
        report.setColspan(2);

        return report;
    }
}
