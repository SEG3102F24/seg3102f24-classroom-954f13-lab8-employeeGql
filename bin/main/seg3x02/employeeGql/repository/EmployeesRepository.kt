package seg3x02.employeeGql.repository

import org.springframework.data.mongodb.repository.MongoRepository
import seg3x02.employeeGql.entity.Employee

interface EmployeeRepository : MongoRepository<Employee, String>
