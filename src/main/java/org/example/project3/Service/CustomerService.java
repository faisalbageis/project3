package org.example.project3.Service;

import lombok.RequiredArgsConstructor;
import org.example.project3.API.ApiException;
import org.example.project3.DTO.CustomerDTO;
import org.example.project3.Model.Customer;
import org.example.project3.Model.User;
import org.example.project3.Repository.AuthRepository;
import org.example.project3.Repository.CustomerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AuthRepository authRepository;

    public void CustomerRegister(CustomerDTO customerDTO){
        String hash = new BCryptPasswordEncoder().encode(customerDTO.getPassword());

        User user = new User();
        user.setUsername(customerDTO.getUsername());
        user.setPassword(hash);
        user.setName(customerDTO.getName());
        user.setEmail(customerDTO.getEmail());
        user.setRole("CUSTOMER");

        Customer customer = new Customer();
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setUser(user);

        user.setCustomer(customer);

        authRepository.save(user);
        customerRepository.save(customer);
    }

    public List<Customer> getAllCustomer(){
        List<Customer> customers = customerRepository.findAll();
        if(customers.isEmpty()){
            throw new ApiException("there is no customers in the system");
        }
        return customers;
    }

    public void updateCustomer(Integer customer_id, CustomerDTO customerDTO){
        Customer oldCustomer = customerRepository.findCustomerById(customer_id);
        if(oldCustomer == null){
            throw new ApiException("customer not found");
        }

        User user = oldCustomer.getUser();
        user.setEmail(customerDTO.getEmail());

        oldCustomer.setPhoneNumber(customerDTO.getPhoneNumber());

        authRepository.save(user);
        customerRepository.save(oldCustomer);
    }

    public void deleteCustomer(Integer customer_id){
        Customer customer = customerRepository.findCustomerById(customer_id);
        if(customer == null){
            throw new ApiException("customer not found");
        }
        User user = customer.getUser();
        if(user==null){
            throw new ApiException("user not found");
        }

        authRepository.delete(user);
    }

}
