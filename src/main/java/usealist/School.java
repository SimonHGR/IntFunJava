package usealist;

import students.Student;

import java.util.ArrayList;
import java.util.List;

public class School {
  public static void showAllStudents(List<Student> ls) {
    for (Student s : ls) {
      System.out.println("> " + s);
    }
  }

//  private static double threshold = 3.25;
//  public static void showSmartStudents(List<Student> ls/*, double threshold*/) {
//    for (Student s : ls) {
//      if (s.getGpa() > threshold) {
//        System.out.println(">> " + s);
//      }
//    }
//  }
//
  public static List<Student> getSmartStudents(
      List<Student> ls, double threshold) {
    List<Student> results = new ArrayList<>();
    for (Student s : ls) {
      if (s.getGpa() > threshold) {
        results.add(s);
      }
    }
    return results;
  }

  public static List<Student> getEnthusiasticStudents(
      List<Student> ls, int threshold) {
    List<Student> results = new ArrayList<>();
    for (Student s : ls) {
      if (s.getCourses().size() > threshold) {
        results.add(s);
      }
    }
    return results;
  }

  public static void main(String[] args) {
    List<Student> roster = List.of(
        Student.of("Fred", 3.2, "Math", "Physics"),
        Student.of("Jim", 2.5, "Journalism"),
        Student.of("Sheila", 3.9,
            "Math", "Physics", "Astrophysics", "Quantum Mechanics")
    );

    showAllStudents(roster);
    System.out.println("Prof-smart:");
//    School.threshold = 3.5;
//    showSmartStudents(roster);
//    showSmartStudents(roster, 3.0);

    showAllStudents(getSmartStudents(roster, 3.5));
    System.out.println("Marketing-smart:");
//    School.threshold = 2.0;
//    showSmartStudents(roster);
//    showSmartStudents(roster, 2.0);
    showAllStudents(getSmartStudents(roster, 2.0));

    System.out.println("Enthusiastic:");
    showAllStudents(getEnthusiasticStudents(roster, 1));


  }
}
