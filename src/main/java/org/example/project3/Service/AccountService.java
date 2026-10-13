package org.example.project3.Service;

import lombok.RequiredArgsConstructor;
import org.example.project3.API.ApiException;
import org.example.project3.Model.Account;
import org.example.project3.Model.Customer;
import org.example.project3.Repository.AccountRepository;
import org.example.project3.Repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public void CreateAccount(Integer customer_id,Account account){
        Customer customer = customerRepository.findCustomerById(customer_id);
        if(customer ==null){
            throw new ApiException("customer not found");
        }

        account.setBalance(0.0);
        account.setIsActive(false);
        account.setCustomer(customer);

        customer.getAccounts().add(account);
        accountRepository.save(account);
        customerRepository.save(customer);
    }


    public void ActiveAccount(Integer account_id){
        Account account = accountRepository.findAccountById(account_id);
        if(account==null){
            throw new ApiException("account not found");
        }
        account.setIsActive(true);
        accountRepository.save(account);
    }

    public Set<Account> getAccounts(Integer customer_id){
        Customer customer = customerRepository.findCustomerById(customer_id);
        if(customer==null){
            throw new ApiException("customer not found");
        }
        Set<Account> accounts = customer.getAccounts();
        if(accounts.isEmpty()){
            throw new ApiException("you dont have account");
        }
        return accounts;
    }

    public Account getAccountById(Integer customer_id,Integer account_id){
        Customer customer = customerRepository.findCustomerById(customer_id);
        if(customer==null){
            throw new ApiException("customer not found");
        }
        Account account =accountRepository.findAccountByCustomerAndId(customer,account_id);
        if(account==null){
            throw new ApiException("account not found");
        }

        return account;
    }

    public List<Account> getAccounts(){
        List<Account> accounts = accountRepository.findAll();
        if(accounts.isEmpty()){
            throw new ApiException("there is no accounts in the system");
        }
        return accounts;
    }

    public void deposit(Integer customer_id,Integer account_id,Double amount){
        Customer customer = customerRepository.findCustomerById(customer_id);
        if(customer==null){
            throw new ApiException("customer not found");
        }

        Account account = accountRepository.findAccountByCustomerAndId(customer,account_id);
        if(account==null){
            throw new ApiException("account not found");
        }

        if (amount == null || amount <= 0) {
            throw new ApiException("amount must be greater than zero");
        }

        if (!account.getIsActive()) {
            throw new ApiException("account is not active");
        }
        account.setBalance(account.getBalance()+amount);
        accountRepository.save(account);
    }

    public void withdrew(Integer customer_id,Integer account_id,Double amount){
        Customer customer = customerRepository.findCustomerById(customer_id);
        if(customer==null){
            throw new ApiException("customer not found");
        }

        Account account = accountRepository.findAccountByCustomerAndId(customer,account_id);
        if(account==null){
            throw new ApiException("account not found");
        }

        if (amount == null || amount <= 0) {
            throw new ApiException("amount must be greater than zero");
        }

        if (!account.getIsActive()) {
            throw new ApiException("account is not active");
        }

        if(amount>account.getBalance()){
            throw new ApiException("you dont have this balance");
        }

        account.setBalance(account.getBalance()-amount);
        accountRepository.save(account);
    }

    public void transfer(Integer customer_id,Integer account_id,Double amount,String AccountNumber ){
        Customer customer = customerRepository.findCustomerById(customer_id);
        if(customer==null){
            throw new ApiException("customer not found");
        }

        Account account = accountRepository.findAccountByCustomerAndId(customer,account_id);
        if(account==null){
            throw new ApiException("account not found");
        }

        Account toAccount=accountRepository.findAccountByAccountNumber(AccountNumber);
        if(toAccount==null){
            throw new ApiException("to account not found");
        }

        if (amount == null || amount <= 0) {
            throw new ApiException("amount must be greater than zero");
        }

        if (!account.getIsActive()) {
            throw new ApiException("account is not active");
        }

        if (account.getId().equals(toAccount.getId())) {
            throw new ApiException("cannot transfer to the same account");
        }

        if (!toAccount.getIsActive()) {
            throw new ApiException("to account is not active");
        }

        if(amount>account.getBalance()){
            throw new ApiException("you dont have this balance");
        }
        account.setBalance(account.getBalance()-amount);
        toAccount.setBalance(toAccount.getBalance()+amount);

        accountRepository.save(account);
        accountRepository.save(toAccount);
    }

    public void blockAccount(Integer account_id){
        Account account = accountRepository.findAccountById(account_id);
        if(account==null){
            throw new ApiException("account not found");
        }

        account.setIsActive(false);
        accountRepository.save(account);
    }
}
