package streamstuff;

import java.util.List;

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
  }
}
