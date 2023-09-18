data class Student(val id: Int, val name: String, val subscribedCourses: List<Course>)
data class Course(val id: Int, val name: String, val isPaid: Boolean)
