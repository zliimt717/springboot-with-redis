package org.example.Controller;

import org.example.Entity.Employee;
import org.example.Service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/redis/employee")
public class EmployeeController {
    private static final Logger LOG= LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeService service;

    // Save a new employee
    // Url - http://localhost:8080/api/redis/employee
    @PostMapping
    public String save(@RequestBody final Employee employee){
        LOG.info("Saving the new employee to the redis");
        service.save(employee);
        return "Successfully added. Employee with id= "+employee.getId();
    }

    // Get all employees
    @GetMapping("/getall")
    public  Map<String, Employee> findAll(){
        LOG.info("Fetching all employees from the redis.");
        final Map<String, Employee> employeeMap=service.findAll();
        return employeeMap;
    }

    // Get employee by id
    @GetMapping("/get/{id}")
    public Employee findById(@PathVariable("id") final String id){
        LOG.info("Fetching employee with id= "+id);
        return service.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String,Employee> delete(@PathVariable("id") final String id){
        LOG.info("Deleting employee with id= :"+id);
        // Delete the employee
        service.delete(id);
        // Returning the all employee(post the deleted one)
        return findAll();
    }
}
