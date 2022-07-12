package streamstuff;

import java.util.List;
import java.util.function.Predicate;

public class Student {
  private String name;
  private double gpa;
  private List<String> courses;

  private Student(String name, double gpa, List<String> courses) {
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
    return List.copyOf(courses);
  }

  public Student withGpa(double newGpa) {
    if (!isValid(this.name, newGpa, this.courses)) {
      throw new IllegalArgumentException("Bad new Gpa propose");
    }
    return new Student(this.name, newGpa, this.courses);
  }

  public Student withName(String name) {
    return new Student(name, this.gpa, this.courses);
  }
  public static Student of(String name, double gpa, String ... courses) {
    var courseList = List.of(courses);
    if (!isValid(name, gpa, courseList)) {
      throw new IllegalArgumentException("bad Student args");
    }

    return new Student(name, gpa, courseList);
  }

  public static boolean isValid(String name, double gpa, List<String> courses) {
    return name != null & name.length() > 0
        && gpa >= 0 && gpa <= 5 && courses != null;
  }

//  private static final Predicate<Student> smartPred = s -> s.getGpa() > 3.5;
//  public static Predicate<Student> getSmartPredicate() {
//    return smartPred;
//  }

  public static Predicate<Student> getSmartPredicate(/*final */double threshold) {
//    threshold++;
    double [] t = { threshold };
    t[0]++;
    // can create BEHAVIOR that is dependendent on the argument to
    // this "behavior factory"
    return s -> s.gpa > threshold/*++*/; // "closure" of threshold
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
