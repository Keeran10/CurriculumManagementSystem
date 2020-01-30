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

import com.soen490.cms.Models.DegreeRequirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DegreeRequirementRepository extends JpaRepository<DegreeRequirement, Integer> {

    @Query(value = "SELECT degree_id FROM degree_requirement WHERE core=?", nativeQuery = true)
    List<Integer> findDegreeByCore(String core);

    @Query(value = "SELECT course_id FROM degree_requirement WHERE core=?", nativeQuery = true)
    List<Integer> findCoursesByCore(String core);

    @Query(value = "SELECT dr.course_id FROM degree_requirement dr INNER JOIN course c ON c.id=dr.course_id" +
            "WHERE dr.core=? AND c.is_active=0", nativeQuery = true)
    List<Integer> findChangedCoursesByCore(String core);

    @Query(value = "SELECT * FROM degree_requirement WHERE course_id=?", nativeQuery = true)
    List<DegreeRequirement> findByCourseId(int id);

    @Query(value = "SELECT * FROM degree_requirement WHERE id=?", nativeQuery = true)
    DegreeRequirement findById(int id);

    @Query(value = "SELECT * FROM degree_requirement WHERE core=?", nativeQuery = true)
    List<DegreeRequirement> findByCore(String core);


}
