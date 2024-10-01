package gm.rh.controller;

import gm.rh.exception.NoFoundException;
import gm.rh.model.Employee;
import gm.rh.service.IEmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rh-app")
@CrossOrigin(value = "http://localhost:3000")
public class EmployeeController {

    private static final Logger logger =
            LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("/employee")
    public List<Employee> getAll(){
        var employees= employeeService.getEmployees();
        employees.forEach((employee -> logger.info(employee.toString())));
        return employees;
    }

    @PostMapping("/employee")
    public Employee addEmployee(@RequestBody Employee employee){
        logger.info("Empleado a agregar: {}", employee);
        return employeeService.save(employee);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id){
        Employee employee=employeeService.getById(id);
        if(null == employee) throw new NoFoundException("El id no existe" + id);

        return ResponseEntity.ok(employee);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,
                                                   @RequestBody Employee employeeReceived){
        Employee employee =employeeService.getById(id);
        if(null == employee)
            throw new NoFoundException("El id no existe" + id);
        employee.setName(employeeReceived.getName());
        employee.setDepartment(employeeReceived.getDepartment());
        employee.setSalary(employeeReceived.getSalary());

        employeeService.save(employee);

        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Map<String,Boolean>> delete(@PathVariable Long id){
        Employee employee=employeeService.getById(id);
        if(null== employee)
            throw new NoFoundException("El id no existe" + id);
        employeeService.delete(employee);

        //Json{"eliminado":true}
        Map<String,Boolean> response =new HashMap<>();
        response.put("eliminado",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
