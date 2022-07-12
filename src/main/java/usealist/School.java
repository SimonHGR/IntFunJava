package usealist;

import students.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

// this interface is essentially equivalent to
// Predicate<E>
interface Criterion<E> {
  boolean test(E s);
}

class SmartStudent implements Criterion<Student> {
  private double threshold;

  public SmartStudent(double threshold) {
    this.threshold = threshold;
  }

  @Override
  public boolean test(Student s) {
    return s.getGpa() > threshold;
  }
}

class EnthusiasticStudent implements Criterion<Student> {

  @Override
  public boolean test(Student s) {
    return s.getCourses().size() > 1;
  }
}


class UpperCaser implements UnaryOperator<Student> {

  @Override
  public Student apply(Student s) {
    return s.withName(s.getName().toUpperCase());
  }
}
public class School {
  public static void showAllStudents(Iterable<?> ls) {
//    ls.add(new Object());
//    ls.add("Bad!");
    for (Object s : ls) {
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
//  public static List<Student> getSmartStudents(
//      List<Student> ls, double threshold) {
//    List<Student> results = new ArrayList<>();
//    for (Student s : ls) {
//      if (s.getGpa() > threshold) {
//        results.add(s);
//      }
//    }
//    return results;
//  }
  // "command" pattern -- pass an argument for the behavior it contains
  // more than for the data it contains
//  public static List<Student> getStudentsByCriterion(
//      Iterable<Student> ls, Criterion<Student> crit) {
//  public static <E> List<E> getStudentsByCriterion(
  public static <E> List<E> getByCriterion(
      Iterable<E> ls, Criterion<E> crit) {
    List<E> results = new ArrayList<>();
    for (E s : ls) {
      if (crit.test(s)) {
        results.add(s);
      }
    }
    return results;
  }

  public static <E> List<E> updateWith(
      Iterable<E> in, UnaryOperator<E> operation) {
    List<E> results = new ArrayList<>();
    for (E s : in) {
      results.add(operation.apply(s));
    }
    return results;
  }

//  public static List<Student> updateWith(
//      Iterable<Student> in, UnaryOperator<Student> operation) {
//    List<Student> results = new ArrayList<>();
//    for (Student s : in) {
//      results.add(operation.apply(s));
//    }
//    return results;
//  }
//
//  public static List<Student> getEnthusiasticStudents(
//      List<Student> ls, int threshold) {
//    List<Student> results = new ArrayList<>();
//    for (Student s : ls) {
//      if (s.getCourses().size() > threshold) {
//        results.add(s);
//      }
//    }
//    return results;
//  }

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
//    showAllStudents(getSmartStudents(roster, 3.5));

    showAllStudents(getByCriterion(roster, new SmartStudent(3.5)));
    System.out.println("Marketing-smart:");
//    School.threshold = 2.0;
//    showSmartStudents(roster);
//    showSmartStudents(roster, 2.0);
    showAllStudents(getByCriterion(roster, new SmartStudent(2.0)));

    System.out.println("Enthusiastic:");
//    showAllStudents(getEnthusiasticStudents(roster, 1));
    showAllStudents(getByCriterion(roster, new EnthusiasticStudent()));

    System.out.println("Uppercase names");
    showAllStudents(updateWith(roster, new UpperCaser()));

  }
}
