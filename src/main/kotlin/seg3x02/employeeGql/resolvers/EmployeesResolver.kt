package seg3x02.employeeGql.resolvers

import org.springframework.stereotype.Controller
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.MutationMapping
import seg3x02.employeeGql.entity.Employee
import seg3x02.employeeGql.repository.EmployeesRepository
import seg3x02.employeeGql.resolvers.types.CreateEmployeeInput

@Controller
class EmployeesResolver @Autowired constructor(
    private val employeesRepository: EmployeesRepository
){

@QueryMapping
    fun employees(): List<Employee> 
    {
        return employeesRepository.findAll()
    }

    @QueryMapping
    fun employeeById(@Argument id: String): Employee? 
    {
        return employeesRepository.findById(id).orElse(null)
    }

    @MutationMapping
    fun addEmployee(@Argument input: CreateEmployeeInput): Employee 
    {
        val employee = Employee(
            name = input.name ?: "",
            dateOfBirth = input.dateOfBirth ?: "",
            city = input.city ?: "",
            salary = input.salary ?: 0.0f,
            gender = input.gender,
            email = input.email
        )
        return employeesRepository.save(employee)
    }
}