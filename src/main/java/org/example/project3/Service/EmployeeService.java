package org.example.project3.Service;

import lombok.RequiredArgsConstructor;
import org.example.project3.API.ApiException;
import org.example.project3.DTO.EmployeeDTO;
import org.example.project3.Model.Employee;
import org.example.project3.Model.User;
import org.example.project3.Repository.AuthRepository;
import org.example.project3.Repository.EmployeeRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final AuthRepository authRepository;

    public void EmployeeRegister(EmployeeDTO employeeDTO){
        String hash = new BCryptPasswordEncoder().encode(employeeDTO.getPassword());
        User user = new User();
        user.setUsername(employeeDTO.getUsername());
        user.setPassword(hash);
        user.setName(employeeDTO.getName());
        user.setEmail(employeeDTO.getEmail());
        user.setRole("EMPLOYEE");


        Employee employee = new Employee();
        employee.setPosition(employeeDTO.getPosition());
        employee.setSalary(employeeDTO.getSalary());
        employee.setUser(user);

        user.setEmployee(employee);

        authRepository.save(user);
        employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployee(){
        List<Employee> employees = employeeRepository.findAll();
        if(employees.isEmpty()){
            throw new ApiException("there is no employees in the system");
        }
        return employees;
    }

    public void updateEmployee(Integer employee_id, EmployeeDTO employeeDTO){
        Employee oldEmployee = employeeRepository.findEmployeeById(employee_id);
        if(oldEmployee == null){
            throw new ApiException("employee not found");
        }

        User user = oldEmployee.getUser();
        user.setEmail(employeeDTO.getEmail());

        oldEmployee.setPosition(employeeDTO.getPosition());
        oldEmployee.setSalary(employeeDTO.getSalary());

        authRepository.save(user);
        employeeRepository.save(oldEmployee);
    }


    public void deleteEmployee(Integer employee_id){
        Employee employee = employeeRepository.findEmployeeById(employee_id);
        if(employee == null){
            throw new ApiException("employee not found");
        }

        User user = employee.getUser();
        if(user==null){
            throw new ApiException("user not found");
        }

        authRepository.delete(user);
    }
}
