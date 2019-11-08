package com.soen490.cms;

import com.soen490.cms.Services.PdfService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PdfServiceTests {

    @Autowired
    private PdfService pdfService;

    @Test
    public void generateDiffsTest(){

        String original = "This string, processed, should be identical to the one produced through a mere eye test.";
        String original_eye_test = "This string~,~ ~processed, ~should be ~identical to ~the ~one~ ~produced~ ~through~ ~a~ ~mere~ eye test.";

        String changed = "This string should also be the same, once processed, as its own eye test string defined below.";
        String changed_eye_test = "This string should ~also ~be the ~same,~ ~once~ ~processed,~ ~as~ ~its~ ~own ~eye test~ string defined below~.";

        String[] result = pdfService.generateDiffs(original, changed);

        assertEquals(result[0], original_eye_test);
        assertEquals(result[1], changed_eye_test);
    }
}
