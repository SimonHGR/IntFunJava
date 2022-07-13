package streamstuff;

import java.util.Comparator;
import java.util.List;

public class TwoNameStudent {
  public static <E> Comparator<E> thenComparing(
      Comparator<E> first, Comparator<E> second) {
    return (e1, e2) -> {
      int v1 = first.compare(e1, e2);
      if (v1 != 0) return v1;
      else return second.compare(e1, e2);
    };
  }

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
    Comparator<TwoNameStudent> ctns = Comparator.comparing(s -> s.getLast());
//    Comparator<TwoNameStudent> ctnsrev = ctns.reversed();
//    Comparator<TwoNameStudent> ctnsBothNames =
//        ctns.thenComparing(s -> s.getFirst());

    lts.stream()
        .sorted(Comparator
            .comparing(TwoNameStudent::getLast)
            .thenComparing(TwoNameStudent::getFirst))
//        .sorted(Comparator
//            .<TwoNameStudent, String>comparing(s -> s.getLast())
//            .thenComparing(s -> s.getFirst()))
//        .sorted(Comparator
//            .comparing((TwoNameStudent s) -> s.getLast())
//            .thenComparing(s -> s.getFirst()))
//        .sorted(ctns.thenComparing(s -> s.getFirst()))
//        .forEach(s -> System.out.println(s));
        .forEach(System.out::println);


//    List<String> ls;
  }
}
