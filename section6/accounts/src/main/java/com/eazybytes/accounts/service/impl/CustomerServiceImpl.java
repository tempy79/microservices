package com.eazybytes.accounts.service.impl;

import com.eazybytes.accounts.dto.AccountsDto;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.ResourceNotFoundException;
import com.eazybytes.accounts.mapper.AccountsMapper;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.ICustomrService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomrService {

    private CustomerRepository customerRepository;
    private AccountsRepository accountsRepository;

    @Override
    public CustomerDto fetchCustomer(String mobileNmber) {
        Customer customerByMobileNumber = customerRepository.findByMobileNumber(mobileNmber).orElseThrow(() -> new ResourceNotFoundException("not found"));

        Accounts accountByCustomerId = accountsRepository.findByCustomerId(customerByMobileNumber.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("not found")
        );

        CustomerDto customerDto = CustomerMapper.maptoCustomerDTO(customerByMobileNumber, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountDto(accountByCustomerId, new AccountsDto()));
        return customerDto;
    }
}
