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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.soen490.cms.Controllers.ImpactAssessmentController;
import com.soen490.cms.Services.ImpactAssessmentService;

import java.util.HashMap;
import java.util.Map;


@RunWith(SpringRunner.class)
@WebMvcTest(ImpactAssessmentController.class)
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
            this.mockMvc.perform(get("/ImpactAssessment?requestId=1")).andDo(print()).andExpect(status().isOk())
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
            this.mockMvc.perform(get("/ImpactAssessment?requestId=25535896")).andDo(print()).andExpect(status().isOk())
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
