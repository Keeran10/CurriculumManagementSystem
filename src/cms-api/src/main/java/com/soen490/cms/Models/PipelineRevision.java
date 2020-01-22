package com.soen490.cms.Models;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigInteger;

@EqualsAndHashCode(callSuper = true)
@Data
public class PipelineRevision extends Revision{

    String from_position;
    String to_position;

    public PipelineRevision(int id, int rev, int revtype, BigInteger timestamp, User user, String to_position){
        super(id, rev, revtype, timestamp, user);
        this.from_position = user.getUserType();
        this.to_position = to_position;
    }
}
