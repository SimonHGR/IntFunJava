package students;

import java.util.Arrays;
import java.util.List;

public class Student {
  private String name;
  private double gpa;
  private List<String> courses; // Set??

  private Student(String name, double gpa, List<String> courses) {
    this.name = name;
    this.gpa = gpa;
    this.courses = courses;
  }

  // if caller calls this as:
  // String [] courses = {"Math", "Physics"};
  // Student.of("X", 1.0, courses);
  // then the caller could still MUTATE THE ARRAY, and that
  // will cause mutation of the Arrays.asList represneation
  // e.g. courses[0] = "Frederick"...
  public static Student of(String name, double gpa, String ... courses) {
    // Java 8 does not have List.of
//    return new Student(name, gpa, Arrays.asList(courses));
    return new Student(name, gpa, Arrays.asList(courses.clone()));
    // Java 9 gives List.of -> unmodifiable List
    // consider List.of(new StringBuilder("Hello"));
//    return new Student(name, gpa, List.of(courses));
  }
}
