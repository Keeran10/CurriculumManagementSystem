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

package com.soen490.cms.Services;

import com.soen490.cms.Models.Request;
import com.soen490.cms.Repositories.RequestRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@Service
public class ImpactAssessmentService {

    @Autowired
    RequestRepository requestRepository;
    @Autowired
    ImpactAssessmentCourseService impactAssessmentCourseService;

    /**
     * Based on the requestId gets the Impact statement of the specific report
     * case 1: Program Request type
     * case 2: Course Request type
     *
     * @param requestId
     * @return Map<String, Object> Json
     */
    public Map<String, Object> getAssessment(int requestId){
        Map<String, Object> responseMap = new HashMap();
        Request request = requestRepository.findByRequestId(requestId);
        log.info("Looking for the Request with: "+ requestId );

        if(request == null) {
            responseMap.put("error","Request does not exist");
            return responseMap; }

        else{
            log.info("Content of Request "+ request );
            switch (request.getTargetType()){
                //TODO case 1: return "it's a program change request";
                case 2: return impactAssessmentCourseService.getCourseImpact(request);
                default: {
                    responseMap.put("error","invalid Target Type in the Request");
                    return responseMap; }
            }
        }
    }
}
