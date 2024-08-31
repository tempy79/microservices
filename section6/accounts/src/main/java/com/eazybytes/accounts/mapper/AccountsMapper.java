package com.eazybytes.accounts.mapper;

import com.eazybytes.accounts.dto.AccountsDto;
import com.eazybytes.accounts.entity.Accounts;

public class AccountsMapper {


    public static AccountsDto mapToAccountDto(Accounts accounts, AccountsDto accountsDto){
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        accountsDto.setAccountType(accounts.getAccountType());
        return accountsDto;
    }

    public static Accounts mapToAccounts(AccountsDto accountsDto, Accounts accounts){

        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;

    }

}
