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

package com.soen490.cms;

import com.soen490.cms.Services.PdfService;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class PdfServiceTests {

    @Autowired
    private PdfService pdfService;

    String original = "This string, processed, should be identical to the one produced through a mere eye test.";
    String original_eye_test = "This string~,~ ~processed, ~should be ~identical to ~the ~one~ ~produced~ ~through~ ~a~ ~mere~ eye test.";

    String changed = "This string should also be the same, once processed, as its own eye test string defined below.";
    String changed_eye_test = "This string should ~also ~be the ~same,~ ~once~ ~processed,~ ~as~ ~its~ ~own ~eye test~ string defined below~.";


    /**
     * Assert how the diff tool works
     */
    @Test
    public void generateDiffsTest(){

        String[] result = pdfService.generateDiffs(original, changed);

        assertEquals(result[0], original_eye_test);
        assertEquals(result[1], changed_eye_test);
    }


    /**
     * Confirms that diff tool follows a specific pattern in diff storage
     */
    @Test
    public void confirmDiffPattern() {

        String[] result = pdfService.generateDiffs(original, changed);

        String[] original_processed = result[0].split("~");
        String[] changed_processed = result[1].split("~");

        String[] o_identical_substrings = new String[100];
        String[] o_different_substrings = new String[original_processed.length];

        String[] c_identical_substrings = new String[100];
        String[] c_different_substrings = new String[changed_processed.length];

        int ctr1 = 0;
        int ctr2 = 0;

        for (int i = 0; i < original_processed.length; i++) {

            if (i % 2 == 0)
                o_identical_substrings[ctr1++] = original_processed[i];
            else
                o_different_substrings[ctr2++] = original_processed[i];
        }

        ctr1 = 0;
        ctr2 = 0;

        for (int i = 0; i < changed_processed.length; i++) {

            if (i % 2 == 0)
                c_identical_substrings[ctr1++] = changed_processed[i];
            else
                c_different_substrings[ctr2++] = changed_processed[i];
        }

        boolean isValid = false;

        if (o_identical_substrings.length <= c_identical_substrings.length) {

            for (String c_identical_substring : c_identical_substrings) {

                if(c_identical_substring == null)
                    break;


                for (String o_identical_substring : o_identical_substrings) {

                    if(o_identical_substring == null)
                        break;


                    if (c_identical_substring.contains(o_identical_substring) || o_identical_substring.contains(c_identical_substring)) {

                        isValid = true;

                        break;
                    }
                }

                if (!isValid) assertFalse(true);
            }

            log.info("identical pattern successfully discovered.");
            //assertFalse(false);
        }

        if (o_different_substrings.length <= c_different_substrings.length) {

            for (String c_different_substring : c_different_substrings) {

                if(c_different_substring == null)
                    break;


                for (String o_different_substring : o_different_substrings) {

                    if(o_different_substring == null)
                        break;


                    if (c_different_substring.contains(o_different_substring) || o_different_substring.contains(c_different_substring)) {

                        isValid = true;

                        break;
                    }
                }

                if (!isValid) assertFalse(true);
            }

            log.info("diff pattern successfully discovered.");
            assertFalse(false);
        }

        assertFalse(false);
    }
}
