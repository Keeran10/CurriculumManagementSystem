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

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.soen490.cms.Controllers.ImpactAssessmentController;
import com.soen490.cms.Services.ImpactAssessmentService;

import java.util.HashMap;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ImpactStatementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ImpactAssessmentService impactAssessmentService;
    
    @Autowired
    private ImpactAssessmentController controller;

    @Test
    public void GetStatementShouldReturnBodyResponse() {
        try {
            Map<String, Object> responseMap = new HashMap();
            responseMap.put("ChangedTitle","Theoretical and computer science");
            responseMap.put("ChangedNumber",362);
            responseMap.put("ChangedCredits",3.5);
        when(impactAssessmentService.getAssessment(1)).thenReturn(responseMap);
            this.mockMvc.perform(get("/ImpactAssessment2?requestId=1")).andDo(print()).andExpect(status().isOk())
                    .andExpect(content().string(containsString("Theoretical and computer science")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void GetStatementShouldReturnErrorMessageForInvalidRequest() {
        try {
            Map<String, Object> responseMap = new HashMap();
            responseMap.put("error","RequestTypeInvalid");
            when(impactAssessmentService.getAssessment(25535896)).thenReturn(responseMap);
            this.mockMvc.perform(get("/ImpactAssessment2?requestId=25535896")).andDo(print()).andExpect(status().isOk())
                    .andExpect(content().string(containsString("error")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void contexLoads(){
        assertThat(controller).isNotNull();
    }


}
