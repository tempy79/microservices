package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomerDto;

public interface ICustomrService {

    CustomerDto fetchCustomer(String mobileNmber);
}
