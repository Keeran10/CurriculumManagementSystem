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
package com.soen490.cms.Models;

import lombok.Data;

import java.math.BigInteger;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Data
class Revision {

    private int id;
    private int rev;
    private String rev_type;
    private String modified_date;
    private String modified_by;

    Revision(int id, int rev, int revtype, BigInteger timestamp, User user){

        this.id = id;
        this.rev = rev;

        if(revtype == 0)
            this.rev_type = "create";
        else if(revtype == 1)
            this.rev_type = "update";
        else if(revtype == 2)
            this.rev_type = "remove";

        Instant instant = Instant.ofEpochMilli(timestamp.longValue());
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.modified_date = fmt.format(instant.atZone(ZoneId.systemDefault()));

        if(user != null)
            this.modified_by = user.getEmail() + " (" + user.getFirstName() + " " + user.getLastName() + ")";
        else
            this.modified_by = "N/A";
    }
}
