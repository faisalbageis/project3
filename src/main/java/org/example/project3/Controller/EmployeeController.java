package org.example.project3.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.project3.DTO.EmployeeDTO;
import org.example.project3.Model.Employee;
import org.example.project3.Model.User;
import org.example.project3.Service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity<?> EmployeeRegister(@Valid @RequestBody EmployeeDTO employeeDTO){
        employeeService.EmployeeRegister(employeeDTO);
        return ResponseEntity.status(200).body("employee registered successfully");
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllEmployee(){
        return ResponseEntity.status(200).body(employeeService.getAllEmployee());
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateEmployee(@AuthenticationPrincipal User user, @Valid @RequestBody EmployeeDTO employeeDTO){
        employeeService.updateEmployee(user.getId(), employeeDTO);
        return ResponseEntity.status(200).body("employee updated successfully");
    }

    @DeleteMapping("/delete/{employee_id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Integer employee_id){
        employeeService.deleteEmployee(employee_id);
        return ResponseEntity.status(200).body("employee deleted successfully");
    }
}
