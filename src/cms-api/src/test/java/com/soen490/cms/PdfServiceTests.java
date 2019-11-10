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
