package streamstuff;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class LabExample {
  public static void main(String[] args) {
    List<Student> roster = List.of(
        Student.of("Spider-man", 4.0, "Adv Physics", "Adv Gym"),
        Student.of("Iron Man", 5.0, "Adv Physics", "Adv Engineering", "Adv AI"),
        Student.of("Hulk", 0.5, "Gym"),
        Student.of("Bruce Banner", 5.0, "Adv Physics"),
        Student.of("Groot", 0.5, "Botany", "Intro to English as a Second Language"),
        Student.of("Loki", 4.0, "Adv Theater", "Ethics"),
        Student.of("Thor", 2.5, "Gym", "History of Asguard"),
        Student.of("Captian America", 3.0, "Gym", "History of U.S.A.", "Physics"),
        Student.of("Ant Man", 2.8, "Into to Physics", "Theater"),
        Student.of("Wasp", 3.3, "Physics", "Martial Arts"),
        Student.of("Shuri", 5.0, "Adv Physics", "Adv Engineering", "Adv AI"),
        Student.of("Black Panther", 3.5, "Gym", "Martial Arts", "History of Waconda")
    );

//    Stream<Student> rs = roster.stream();
//    rs.forEach(s -> System.out.println(s));
//    rs.forEach(s -> System.out.println(s));

    roster.stream()
        .forEach(s -> System.out.println(s));
    System.out.println("-------------------------");
    roster.stream()
        .filter(s -> s.getGpa() > 3.5)
        .forEach(s -> System.out.println(s));
    System.out.println("-------------------------");
    roster.stream()
        .filter(s -> s.getGpa() <= 3.5)
        .forEach(s -> System.out.println(s));
    System.out.println("-------------------------");
    roster.stream()
        .filter(s -> s.getGpa() > 3.5)
        .map(s -> "Student " + s.getName() + " is smart and takes " + s.getCourses())
        .forEach(s -> System.out.println(s));
    System.out.println("-------------------------");
    roster.stream()
        .filter(s -> s.getGpa() <= 3.5)
        .map(s -> "Student " + s.getName() + " is not smart and takes " + s.getCourses())
        .forEach(s -> System.out.println(s));
    System.out.println("-------------------------");
    System.out.println("There are " +
        roster.stream()
            .filter(s -> s.getName().toUpperCase().charAt(0) > 'M')
            .count() + " students with 2nd half names");
    System.out.println("-------------------------");
    roster.stream()
        .filter(s -> s.getGpa() > 3.5)
//        .sorted((s1, s2) -> Double.compare(s2.getGpa(), s1.getGpa()))
        .sorted((s1, s2) ->
            Integer.compare(
                s2.getCourses().size(),
                s1.getCourses().size()))
        .forEach(s -> System.out.println(s));
    System.out.println("-------------------------");
    boolean someoneIsSmart = roster.stream()
        .filter(s -> s.getName().length() > 4)
        .anyMatch(s -> s.getGpa() > 3.5);
    System.out.println("Is a student with a long name smart? " + someoneIsSmart);

    Object obj = (Predicate<Student> & Serializable) s -> s.getGpa() > 3.5;
    System.out.println("obj is a " + obj.getClass().getName());
    Method[] methods = obj.getClass().getMethods();
    for (Method m : methods) {
      System.out.println("> " + m);
    }
  }
}
