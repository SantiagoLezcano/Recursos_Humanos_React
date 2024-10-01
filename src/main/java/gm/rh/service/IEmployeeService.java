package gm.rh.service;

import gm.rh.model.Employee;

import java.util.List;

public interface IEmployeeService {

    List<Employee> getEmployees();

    Employee getById(Long id);

    Employee save(Employee employee);

    void delete(Employee employee);
}
