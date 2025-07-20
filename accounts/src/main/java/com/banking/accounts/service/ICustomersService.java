package com.banking.accounts.service;

import com.banking.accounts.dto.CustomerDetailsDTO;

public interface ICustomersService {

    /**
     *
     * @param mobileNumber
     * @return Customer Details based on a given mobileNumber
     */
    CustomerDetailsDTO fetchCustomerDetails(String mobileNumber, String correlationId);

}
