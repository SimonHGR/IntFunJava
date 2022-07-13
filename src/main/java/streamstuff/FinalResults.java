package streamstuff;

import java.util.List;
import java.util.stream.Collectors;

public class FinalResults {
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

    var res = roster.stream()
        // more sensibly
//        .flatMap(s -> s.getCourses().stream())
        .map(Student::getCourses)
        .flatMap(List::stream)
        .collect(Collectors.toSet());
    System.out.println(res);
    res.forEach(System.out::println);
    System.out.println("-------------------------");
    roster.stream()
        .collect(Collectors.groupingBy(FinalResults::getLetterGrade))
        .entrySet().stream()
        .forEach(System.out::println);

    System.out.println("-------------------------");
    roster.stream()
        .collect(Collectors.groupingBy(FinalResults::getLetterGrade,
            Collectors.mapping(s -> s.getName(),
                Collectors.joining( ", ", "Students with this grade are: ", ""))))
        .entrySet().stream()
        .forEach(System.out::println);

    System.out.println("-------------------------");
    roster.stream()
        .collect(Collectors.groupingBy(FinalResults::getLetterGrade,
                Collectors.counting()))
        .entrySet().stream()
        .map(e -> "There are " + e.getValue()
            + " students with grade " + e.getKey())
        .forEach(System.out::println);

  }

  public static String getLetterGrade(Student s) {
    double grade = s.getGpa();
    if (grade > 3.5) return "A";
    if (grade > 3) return "B";
    if (grade > 2) return "C";
    if (grade > 1) return "D";
    return "E";
  }
}
