package cocontra;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Ex1 {
  public static <E, F> List<F> map(List<E> in,
//        Function<e is assignable to caller type ?, F> op) {
        // "super" means "is assignable from"
        // ? super represents "contravariance"
        // ? extends represents "covariance"
        // function arguments should generally be ? super (contravariant)
        // function returns should generally be ? extends (covariant)
        // PECS producer->extends consumer->super
        Function<? super E, ? extends F> op) {
    List<F> out = new ArrayList<>();
//    for (E e : in) {
//      out.add(op.apply(e));
//    }

    in.forEach(e -> out.add(op.apply(e)));
    return out;
  }

  public static void main(String[] args) {
    List<String> names = List.of("Fred", "Jim", "Sheila");
    List<StringBuilder> names2 = List.of(new StringBuilder("Alice"),
        new StringBuilder("Janice"),
        new StringBuilder("Sheila"));

    System.out.println("String instanceof CharSeqeuence? "
        + ("" instanceof String));
    System.out.println("StringBuilder instanceof CharSeqeuence? "
        + (new StringBuilder() instanceof StringBuilder));

    Function<String, Integer> len = s -> s.length();
    Function<StringBuilder, Integer> len2 = s -> s.length();
    Function<CharSequence, Integer> len3 = s -> s.length();
    var res = len3.apply("");
    List<Object> nameLengths = map(names, len3);
    System.out.println("lengths: " + nameLengths);
    List<Integer> name2Lengths = map(names2, len2);
    System.out.println("lengths 2: " + name2Lengths);


//    Function<String, String> upc = s -> s.toUpperCase();
//    List<String> shoutNames = map(names, upc);
//    System.out.println("original " + names);
//    System.out.println("   upper " + shoutNames);


  }
}
