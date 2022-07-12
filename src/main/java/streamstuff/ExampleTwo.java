package streamstuff;

import java.util.List;
import java.util.function.Predicate;

public class ExampleTwo {

  public static <E> Predicate<E> negate(Predicate<E> ps) {
    return s -> !ps.test(s);
  }

  public static <E> Predicate<E> and(Predicate<E> first, Predicate<E> second) {
    return e -> first.test(e) && second.test(e);
  }

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

    roster.stream()
//        .filter(s -> s.getGpa() < 4.5)
//        .filter(negate(Student.getSmartPredicate(4.5)))
//        .filter(Student.getSmartPredicate(2.5))
//        .filter(and(
//            Student.getSmartPredicate(2.5),
//            negate(Student.getSmartPredicate(4.5))))
        .filter(
            Student.getSmartPredicate(2.5)
                .and(Student.getSmartPredicate(4.5).negate()))
        .forEach(s -> System.out.println(s));

    /*
    Monroe, Alice
    Monroe, Zeb
    Smith, Adam
    Smith, Bert
    Smith, Susan
    Tribb, Fred
    Ulvaeus, Felicity


     */

  }
}
