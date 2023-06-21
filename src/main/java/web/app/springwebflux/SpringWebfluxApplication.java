package web.app.springwebflux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import web.app.springwebflux.entity.Employee;
import web.app.springwebflux.repo.EmployeeRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
public class SpringWebfluxApplication {

	@Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringWebfluxApplication.class, args);
	}

	@EventListener(ApplicationStartedEvent.class)
	public void appStart() {
		if (employeeRepository.count().block() == 0) {
			IntStream.range(0, 100)
					.mapToObj(this::generate)
					.map(employeeRepository::save)
					.collect(Collectors.toList())
					.forEach(item -> item.subscribe(System.out::println));
		}
	}

	private Employee generate(int i) {
		return Employee.builder()
				.name("Name" + i)
				.lastname("Lastname"+i)
				.birthDate(LocalDate.now())
				.build();
	}

}
