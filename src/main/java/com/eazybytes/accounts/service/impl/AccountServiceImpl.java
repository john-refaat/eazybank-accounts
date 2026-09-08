package com.eazybytes.accounts.service.impl;

import com.eazybytes.accounts.dto.AccountDto;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entity.Account;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exceptions.CustomerAlreadyExistsException;
import com.eazybytes.accounts.exceptions.ResourceNotFoundException;
import com.eazybytes.accounts.mapper.AccountMapper;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.AccountNumberGenerator;
import com.eazybytes.accounts.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author: john
 * Date: 8/22/26
 */
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AccountNumberGenerator accountNumberGenerator;


    @Override
    @Transactional
    public void createAccount(CustomerDto customerDto) {
        customerRepository.findByPhoneNumber(customerDto.getPhoneNumber())
                .ifPresent(
                        x -> {throw new CustomerAlreadyExistsException("Customer already exists with phone number: " + customerDto.getPhoneNumber());});
        Customer savedCustomer = customerRepository.save(
                CustomerMapper.mapToCustomer(customerDto, new Customer()));

        Account account = new Account(
                savedCustomer.getCustomerId(),
                accountNumberGenerator.generate(),
                "SAVINGS",
                "123 Main Street, Downtown Branch"
        );  
        accountRepository.save(account);
        
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDto getAccount(String phoneNumber) {
        Customer customer = customerRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "phone number", phoneNumber));
        Account account = accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "customer id", String.valueOf(customer.getCustomerId())));
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccount(AccountMapper.mapToAccountDto(account, new AccountDto()));
        return customerDto;
    }

    @Override
    @Transactional
    public void updateAccount(CustomerDto customerDto) {
        Customer customer = customerRepository.findByPhoneNumber(customerDto.getPhoneNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "phone number", customerDto.getPhoneNumber()));

        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());

        Account account = accountRepository.findByCustomerId(customer.getCustomerId())
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Account",
                                "customer id",
                                String.valueOf(customer.getCustomerId())));
        if(customerDto.getAccount() != null) {
            account.setAccountType(customerDto.getAccount().getAccountType());
            account.setBranchAddress(customerDto.getAccount().getBranchAddress());
        }

        customerRepository.save(customer);
        accountRepository.save(account);
    }

    @Transactional
    @Override
    public void deleteAccount(String phoneNumber) {
        Customer customer = customerRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "phone number", phoneNumber));
        Account account = accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "customer id", String.valueOf(customer.getCustomerId())));
        accountRepository.deleteById(account.getAccountNumber());
        customerRepository.deleteById(customer.getCustomerId());
    }

}
