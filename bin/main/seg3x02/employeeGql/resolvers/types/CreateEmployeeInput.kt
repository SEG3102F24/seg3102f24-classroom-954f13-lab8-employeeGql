package seg3x02.employeeGql.types

data class CreateEmployeeInput(
    val firstName: String,
    val lastName: String,
    val position: String,
    val department: String
)
