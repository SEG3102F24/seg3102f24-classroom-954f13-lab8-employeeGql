package seg3x02.employeeGql.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "employees")
data class Employee(
    @Id
    var employeeId: String? = null,
    var firstName: String,
    var lastName: String,
    var position: String,
    var department: String
)
