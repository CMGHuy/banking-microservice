package com.banking.accounts.service;

import com.banking.accounts.dto.CustomerDTO;

public interface IAccountsService {

    /**
     *
     * @param customerDTO - CustomerDTO object
     */
    void createAccount(CustomerDTO customerDTO);

    /**
     * This method fetches customer account details using mobile number
     * @param mobileNumber - customer mobile number
     * @return CustomerDTO object
     */
    CustomerDTO fetchAccount(String mobileNumber);

    /**
     * This method updates existing customer account details
     * @param customerDTO - CustomerDTO object
     * @return true if customer account updated successfully else false
     */
    boolean updateAccount(CustomerDTO customerDTO);


    /**
     * This method deletes existing customer account details
     * @param mobileNumber - customer mobile number
     * @return true if customer account deleted successfully else false
     */
    boolean deleteAccount(String mobileNumber);

}
