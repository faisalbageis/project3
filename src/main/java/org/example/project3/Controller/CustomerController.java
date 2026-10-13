package org.example.project3.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.project3.DTO.CustomerDTO;
import org.example.project3.Model.Customer;
import org.example.project3.Model.User;
import org.example.project3.Service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;


    @PostMapping("/register")
    public ResponseEntity<?> CustomerRegister(@Valid @RequestBody CustomerDTO customerDTO){
        customerService.CustomerRegister(customerDTO);
        return ResponseEntity.status(200).body("customer registered successfully");
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllCustomer(){
        return ResponseEntity.status(200).body(customerService.getAllCustomer());
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCustomer(@AuthenticationPrincipal User user, @Valid @RequestBody CustomerDTO customerDTO){
        customerService.updateCustomer(user.getId(), customerDTO);
        return ResponseEntity.status(200).body("customer updated successfully");
    }

    @DeleteMapping("/delete/{customer_id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Integer customer_id){
        customerService.deleteCustomer(customer_id);
        return ResponseEntity.status(200).body("customer deleted successfully");
    }

}
