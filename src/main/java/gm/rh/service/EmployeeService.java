package gm.rh.service;

import gm.rh.model.Employee;
import gm.rh.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService{

    @Autowired
    private IEmployeeRepository repository;

    /**
     * @return
     */
    @Override
    public List<Employee> getEmployees() {
        return repository.findAll();
    }

    /**
     * @return
     */
    @Override
    public Employee getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * @param employee
     * @return
     */
    @Override
    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    /**
     * @param employee
     */
    @Override
    public void delete(Employee employee) {
        repository.delete(employee);
    }
}
