package students;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class VIPStudent extends Student {
  VIPStudent(String name, double gpa, List<String> courses) {
    super(name, gpa, courses);
  }
}

public class Student {
  private String name;
  private double gpa;
  private List<String> courses; // Set??

  /*private*/ Student(String name, double gpa, List<String> courses) {
    this.name = name;
    this.gpa = gpa;
    this.courses = courses;
  }

  public String getName() {
    return name;
  }

  public double getGpa() {
    return gpa;
  }

  public List<String> getCourses() {
    // safe if courses is List.of, NOT if it's Arrays.asList
//    return courses;
    // creates an "unmodifiable" list if the original was
    // not already unmodifiable, but returns the original
    // if it was, in fact, unmodifiable
    return List.copyOf(courses);

    // "proxy" pattern--wrapper which rejects changes around
    // a list that might have accepted changes
//    return Collections.unmodifiableList(courses);

  }

  public Student withGpa(double newGpa) {
    if (!isValid(this.name, newGpa, this.courses)) {
      throw new IllegalArgumentException("Bad new Gpa propose");
    }
//    this.gpa = gpa; // NO NO NO!!! we want an immutable object
    return new Student(this.name, newGpa, this.courses);
  }

  public Student withName(String name) {
    return new Student(name, this.gpa, this.courses);
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
//    return new Student(name, gpa, Arrays.asList(courses.clone()));
    // Java 9 gives List.of -> unmodifiable List
    // consider List.of(new StringBuilder("Hello"));
    var courseList = List.of(courses);
    if (!isValid(name, gpa, courseList)) {
      throw new IllegalArgumentException("bad Student args");
    }

    if (gpa < 3.5) {
      return new Student(name, gpa, courseList);
    } else {
      return new VIPStudent(name, gpa, courseList);
    }
  }

  public static Student of(String name, double gpa, List<String> courses) {
    if (!isValid(name, gpa, courses)) {
      throw new IllegalArgumentException("bad Student args");
    }
    return new Student(name, gpa, courses);
  }

  public static boolean isValid(String name, double gpa, List<String> courses) {
    return name != null & name.length() > 0
        && gpa >= 0 && gpa < 5 && courses != null;
  }

  @Override
  public String toString() {
    return "Student{" +
        "name='" + name + '\'' +
        ", gpa=" + gpa +
        ", courses=" + courses +
        '}';
  }
}
