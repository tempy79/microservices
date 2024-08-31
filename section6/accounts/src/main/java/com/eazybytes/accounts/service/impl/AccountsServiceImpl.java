package com.eazybytes.accounts.service.impl;

import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.dto.AccountsDto;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.CustomerAlreadyExistsException;
import com.eazybytes.accounts.exception.ResourceNotFoundException;
import com.eazybytes.accounts.mapper.AccountsMapper;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createCustomer(CustomerDto customerDto) {

        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> byMobileNumber = customerRepository.findByMobileNumber(customer.getMobileNumber());
        if(byMobileNumber.isPresent()){
            throw new CustomerAlreadyExistsException("customer already exist");
        }
        customer.setCreatedBy("Anonimus");
        customer.setCreatedAt(LocalDateTime.now());
        Customer customerSaved = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(customerSaved));
    }

    @Override
    public boolean updateCustomer(CustomerDto customerDto) {
        AccountsDto accountsDto = customerDto.getAccountsDto();
        boolean isUpdate = false;
        if(accountsDto != null){
            Accounts acconts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(() -> new ResourceNotFoundException("not found"));
            AccountsMapper.mapToAccounts(accountsDto, acconts);
            accountsRepository.save(acconts);
            Long custoerId = acconts.getCustomerId();
            Customer customerById = customerRepository.findById(custoerId).orElseThrow(() -> new ResourceNotFoundException("not found"));
            CustomerMapper.mapToCustomer(customerDto, customerById);
            customerRepository.save(customerById);
            isUpdate = true;
        }

        return isUpdate;
    }

    @Override
    public boolean deleteCustomer(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("not found"));
        accountsRepository.deleteAccountsByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }


    private Accounts createNewAccount(Customer customer){
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setCreatedBy("Anonimus");
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }
}
