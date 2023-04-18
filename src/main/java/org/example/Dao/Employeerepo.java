package org.example.Dao;

import org.example.Entity.Employee;

import java.util.Map;

public interface Employeerepo {

     void save(Employee employee);
     Employee findById(final String id);
     Map<String,Employee> findAll();
     void delete(String id);

}
