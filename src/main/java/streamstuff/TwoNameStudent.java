package streamstuff;

import java.util.Comparator;
import java.util.List;

public class TwoNameStudent {
  private String first;
  private String last;

  public TwoNameStudent(String first, String last) {
    this.first = first;
    this.last = last;
  }

  public String getFirst() {
    return first;
  }

  public String getLast() {
    return last;
  }

  @Override
  public String toString() {
    return "TwoNameStudent{" +
        "first='" + first + '\'' +
        ", last='" + last + '\'' +
        '}';
  }

  public static void main(String[] args) {
    List<TwoNameStudent> lts = List.of(
        new TwoNameStudent("Fred", "Smith"),
        new TwoNameStudent("Alice", "Smith"),
        new TwoNameStudent("Susan", "Jones"),
        new TwoNameStudent("Bill", "Smith"),
        new TwoNameStudent("Tony", "Smith"),
        new TwoNameStudent("Tony", "Jones"),
        new TwoNameStudent("Felicity", "Jones"),
        new TwoNameStudent("Mary", "Smith")
        );
    lts.stream()
        .sorted(Comparator.comparing(s -> s.getLast()))
        .forEach(s -> System.out.println(s));
  }
}
