package com.banking.accounts.service.impl;

import com.banking.accounts.dto.AccountsDTO;
import com.banking.accounts.dto.CardsDTO;
import com.banking.accounts.dto.CustomerDetailsDTO;
import com.banking.accounts.dto.LoansDTO;
import com.banking.accounts.entity.Accounts;
import com.banking.accounts.entity.Customer;
import com.banking.accounts.exception.ResourceNotFoundException;
import com.banking.accounts.mapper.AccountsMapper;
import com.banking.accounts.mapper.CustomerMapper;
import com.banking.accounts.repository.AccountsRepository;
import com.banking.accounts.repository.CustomerRepository;
import com.banking.accounts.service.ICustomersService;
import com.banking.accounts.service.client.CardsFeignClient;
import com.banking.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements ICustomersService {

    private AccountsRepository accountsRepository;

    private CustomerRepository customerRepository;

    private CardsFeignClient cardsFeignClient;

    private LoansFeignClient loansFeignClient;

    /**
     *
     * @param mobileNumber
     * @return Customer Details based on a a given mobileNumber
     */
    @Override
    public CustomerDetailsDTO fetchCustomerDetails(String mobileNumber, String correlationId) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
            () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
            () -> new ResourceNotFoundException("Accounts", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDTO customerDetailsDTO = CustomerMapper.mapToCustomerDetailsDTO(customer, new CustomerDetailsDTO());
        customerDetailsDTO.setAccountsDTO(AccountsMapper.mapToAccountsDTO(accounts, new AccountsDTO()));

        ResponseEntity<LoansDTO> loansDTOResponseEntity = loansFeignClient.fetchLoanDetails(correlationId, mobileNumber);
        if (loansDTOResponseEntity != null) {
            customerDetailsDTO.setLoansDTO(loansDTOResponseEntity.getBody());
        }

        ResponseEntity<CardsDTO> cardsDTOResponseEntity = cardsFeignClient.fetchCardDetails(correlationId, mobileNumber);
        if (cardsDTOResponseEntity != null) {
            customerDetailsDTO.setCardDTO(cardsDTOResponseEntity.getBody());
        }

        return customerDetailsDTO;

    }

}
