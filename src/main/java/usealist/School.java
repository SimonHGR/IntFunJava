package usealist;

import students.Student;

import java.util.List;

public class School {
  public static void showAllStudents(List<Student> ls) {
    for (Student s : ls) {
      System.out.println("> " + s);
    }
  }

  public static void showSmartStudents(List<Student> ls) {
    for (Student s : ls) {
      if (s.getGpa() > 3.0) {
        System.out.println(">> " + s);
      }
    }
  }

  public static void main(String[] args) {
    List<Student> roster = List.of(
        Student.of("Fred", 3.2, "Math", "Physics"),
        Student.of("Jim", 2.5, "Journalism"),
        Student.of("Sheila", 3.9,
            "Math", "Physics", "Astrophysics", "Quantum Mechanics")
    );

    showAllStudents(roster);
    showSmartStudents(roster);

  }
}
