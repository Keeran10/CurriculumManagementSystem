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

package com.soen490.cms.Repositories;

import com.soen490.cms.Models.Course;
import com.soen490.cms.Models.Degree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface DegreeRepository extends JpaRepository<Degree, Integer>{
    Degree findByName(String name);

    @Query(value = "SELECT * FROM degree d INNER JOIN degree_requirement ON d.id = degree_requirement.degree_id WHERE degree_requirement.course_id=?1 AND core LIKE '% Core'", nativeQuery = true)
    Collection<Degree> findDegreeByRequiredCourseId(int course_id);

    @Query(value = "SELECT * FROM degree d INNER JOIN degree_requirement ON d.id = degree_requirement.degree_id WHERE degree_requirement.course_id=?1 AND core NOT LIKE '% Electives'", nativeQuery = true)
    Collection<Degree> findDegreeByElectiveCourseId(int course_id);


    @Query(value = "SELECT core FROM degree_requirement INNER JOIN course c ON c.id = degree_requirement.course_id AND c.id=?1", nativeQuery = true)
    Collection<String> findProgramCoreCourseId(int course_id);

    @Query(value = "SELECT SUM(c.credits) FROM course c INNER JOIN degree_requirement ON c.id = degree_requirement.course_id WHERE degree_requirement.core=?1 AND c.is_active=1 ", nativeQuery = true)
    Double findCreditsTotalOfCoreProgram(String name);

}
