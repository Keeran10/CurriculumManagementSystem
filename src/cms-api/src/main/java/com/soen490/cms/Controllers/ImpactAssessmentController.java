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

package com.soen490.cms.Controllers;

import com.soen490.cms.Services.ImpactAssessmentService;
import lombok.extern.log4j.Log4j2;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Log4j2
@RestController
@CrossOrigin(origins = ControllerConfiguration.ENDPOINT_URL)
public class ImpactAssessmentController {

    @Autowired
    ImpactAssessmentService impactAssessmentService;


    /**
     * endpoint of: /ImpactAssessment
     * takes as parameter the request Id
     *
     * @param requestId
     * @return Map<String, Object> Impact report object
     */
    @GetMapping(value = "/ImpactAssessment2")
    public Map<String, Object> getImpactAssessment(@RequestParam int requestId){

        log.info("Getting the Impact of Request Package: Id = ", requestId);

        Map<String, Object> impact = impactAssessmentService.getAssessment(requestId);

        impact.put("RequestId", requestId);

        return impact;
    }

    @PostMapping(value="/ImpactAssessment", consumes = "application/json")
    public Map<String, Object> getImpactAssessment2(@Valid @RequestBody String requestForm, BindingResult bindingResult) throws JSONException{
        log.info("Getting the Impact of the following JSON: " + requestForm);
        return impactAssessmentService.getCourseImpact(requestForm);
    }
}
