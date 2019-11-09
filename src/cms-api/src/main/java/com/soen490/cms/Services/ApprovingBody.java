package com.soen490.cms.Services;

import com.soen490.cms.Models.RequestPackage;

public interface ApprovingBody {

    public RequestPackage sendPackage(int requestPackageId);
    public void receivePackage(RequestPackage requestPackage);
}
