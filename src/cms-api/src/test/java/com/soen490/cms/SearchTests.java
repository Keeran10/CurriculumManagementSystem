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

import com.soen490.cms.Controllers.SearchController;
import com.soen490.cms.Models.User;
import com.soen490.cms.Repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchTests {

    @Autowired
    private SearchController searchController;

    @Test
    public void confirmSearchCourses(){
        assertNotEquals(Collections.EMPTY_LIST, searchController.getCourses());
        assertNotNull(searchController.getCourse("SOEN", 228));
        assertNotNull(searchController.getCourseById(1));

    }
    @Test
    public void confirmSearchDegrees(){
        assertNotNull(searchController.getDegree("Bachelor of Software Engineering (BEng)"));
        assertNotEquals(Collections.EMPTY_LIST, searchController.getDegrees());
    }
    @Test
    public void confirmSearchPrograms(){
        assertNotEquals(Collections.EMPTY_LIST, searchController.getPrograms());
        assertNotNull(searchController.getProgram("Software Engineering"));
    }

    @Test
    public void confirmSearchDepartments(){
        assertNotNull(searchController.getDepartment("Computer Science & Software Engineering"));
        assertNotEquals(Collections.EMPTY_LIST, searchController.getDepartments());
    }

    @Test
    public void confirmSearchFaculties(){
        assertNotEquals(Collections.EMPTY_LIST, searchController.getFaculties());
        assertNotNull(searchController.getFaculty("Gina Cody School of Engineering and Computer Science"));
    }
}
