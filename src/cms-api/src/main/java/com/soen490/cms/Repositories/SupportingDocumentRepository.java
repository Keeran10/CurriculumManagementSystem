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

import com.soen490.cms.Models.SupportingDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface SupportingDocumentRepository extends JpaRepository<SupportingDocument, Integer>{

    @Query(value = "SELECT * FROM supporting_document WHERE id=?", nativeQuery = true)
    SupportingDocument findBySupportingDocumentId(int id);

    @Query(value = "SELECT * FROM supporting_document LEFT JOIN request_package ON " +
            "request_package.id = supporting_document.package_id WHERE supporting_document.package_id=?", nativeQuery = true)
    List<SupportingDocument> findByPackage(int package_id);

    @Query(value = "SELECT * FROM supporting_document WHERE target_type=?1 AND target_id=?2", nativeQuery = true)
    List<SupportingDocument> findByTarget(String target_type, int target_id);

    @Query(value = "SELECT * FROM supporting_document WHERE id=?", nativeQuery = true)
    SupportingDocument findById(int id);
}
