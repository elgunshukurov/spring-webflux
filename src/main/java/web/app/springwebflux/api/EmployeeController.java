package web.app.springwebflux.api;

import web.app.springwebflux.entity.Employee;
import web.app.springwebflux.repo.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @GetMapping("/{id}")
    public Mono<Employee> getById(@PathVariable String id) {
        return employeeRepository.findById(id);
    }

    @GetMapping
    public Flux<Employee> getAll() {
        return employeeRepository.findAll();
    }
}
