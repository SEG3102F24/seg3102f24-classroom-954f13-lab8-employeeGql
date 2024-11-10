package seg3x02.employeeGql.resolvers

import org.springframework.stereotype.Controller
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.Argument
import seg3x02.employeeGql.entity.Employee
import seg3x02.employeeGql.repository.EmployeeRepository
import seg3x02.employeeGql.types.CreateEmployeeInput
import java.util.*


@Controller
class EmployeesResolver(private val employeeRepository: EmployeeRepository) {

    @QueryMapping
    fun employees(): List<Employee> {
        return employeeRepository.findAll()
    }

    @QueryMapping
    fun employeeById(@Argument employeeId: String): Employee? {
        return employeeRepository.findById(employeeId).orElse(null)
    }

    @MutationMapping
    fun newEmployee(@Argument createEmployeeInput: CreateEmployeeInput): Employee {
        val employee = Employee(
            employeeId = UUID.randomUUID().toString(),
            firstName = createEmployeeInput.firstName,
            lastName = createEmployeeInput.lastName,
            position = createEmployeeInput.position,
            department = createEmployeeInput.department
        )
        return employeeRepository.save(employee)
    }

    @MutationMapping
    fun deleteEmployee(@Argument employeeId: String): Boolean {
        return try {
            employeeRepository.deleteById(employeeId)
            true
        } catch (e: Exception) {
            false
        }
    }

    @MutationMapping
    fun updateEmployee(@Argument employeeId: String, @Argument createEmployeeInput: CreateEmployeeInput): Employee? {
        val existingEmployee = employeeRepository.findById(employeeId).orElse(null) ?: return null
        existingEmployee.firstName = createEmployeeInput.firstName
        existingEmployee.lastName = createEmployeeInput.lastName
        existingEmployee.position = createEmployeeInput.position
        existingEmployee.department = createEmployeeInput.department
        return employeeRepository.save(existingEmployee)
    }
}
