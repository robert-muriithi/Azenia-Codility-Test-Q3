/**
 * the task is to implement the following function inside the University class:
 *
 * fun getPaidCoursesWithTheNumbersOfSubscribedStudents(coursesCount: Int): Map<Course, Int> {}
 *
 * The function should return a Map<Course, Int> instance
 * where key s the Course type and its value is the number of students
 * enrolled to that course.
 *
 * The returned Map size should be equal to the number of passed as the
 * courseCount: Int param.
 * The returned map should be sorted descending by the number of the students subscribed to the course.
 * the map instance should contain only paid courses.
 */



fun main() {
    val students = listOf(
        Student(
            subscribedCourses = listOf(
                Course(name = "Maths", isPaid = false, id = 1),
                Course(name = "Arts", isPaid = true, id = 1)
            ), id = 11, name = "John"
        ),
        Student(
            subscribedCourses = listOf(
                Course(name = "History", isPaid = true, id = 1),
                Course(name = "Biology", isPaid = true, id = 1)
            ), id = 12, name = "Jane"
        ),
        Student(
            subscribedCourses = listOf(
                Course(name = "Physics", isPaid = true, id = 1),
                Course(name = "History", isPaid = true, id = 1)
            ), id = 13, name = "Mary"
        ),
    )

    val repository = StudentRepository(students)
    val university = University(repository)
    val result = university.getPaidCoursesWithTheNumbersOfSubscribedStudents(3)
    println(result)
}

class StudentRepository(private val students: List<Student>) : Repository<Student> {
    override fun get(): Iterable<Student> {
        return students
    }
}


class University(private val repository: Repository<Student>) {
    fun getPaidCoursesWithTheNumbersOfSubscribedStudents(coursesCount: Int): Map<Course, Int> =
        repository.get()
            .flatMap { it.subscribedCourses }
            .distinct()
            .filter { it.isPaid }
            .associateWith { course ->
                repository.get().count { it.subscribedCourses.contains(course) }
            }
            .filterValues { it > 0 }
            .toList()
            .sortedByDescending { (_, count) -> count }
            .take(coursesCount)
            .toMap()

}


/*fun getPaidCoursesWithTheNumbersOfSubscribedStudents(coursesCount: Int): Map<Course, Int> {
        val studentIterable = repository.get()
        val paidCourses: List<Course> = studentIterable.flatMap { it.subscribedCourses }
            .distinct()
            .filter { it.isPaid }
        val courseIntMutableMap: MutableMap<Course, Int> = mutableMapOf()
        for (course in paidCourses) {
            val studentCount = studentIterable.count { it.subscribedCourses.contains(course) }
            if (studentCount > 0) {
                courseIntMutableMap[course] = studentCount
            }
        }
        return courseIntMutableMap.toList()
            .sortedByDescending { (_, count) -> count }
            .take(coursesCount)
            .toMap()
    }*/



