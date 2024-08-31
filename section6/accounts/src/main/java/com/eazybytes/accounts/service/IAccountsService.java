package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomerDto;
import org.springframework.stereotype.Service;


public interface IAccountsService {

    /**
     *
     * @param customerDto
     */
    void createCustomer(CustomerDto customerDto);

    boolean updateCustomer(CustomerDto customerDto);

    boolean deleteCustomer(String mobileNumber);
}
