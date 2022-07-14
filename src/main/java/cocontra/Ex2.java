package cocontra;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Ex2 {

  public static <F, E extends F> F[] getAsArray(List<E> items, Class<F> cl) {
    F[] res = (F[]) Array.newInstance(cl, items.size());
    int idx = 0;
    for (E e : items) {
      res[idx++] = e;
//      e = res[idx]; // NOPE can assign E to F, but can't expect the reverse
    }
    return res;
  }

  public static void main(String[] args) {
    List<String> names = List.of("Fred", "Jim", "Sheila");
    CharSequence[] sa = getAsArray(names, CharSequence.class);
    System.out.println("Array is " + Arrays.toString(sa));
    System.out.println("Array type is " + sa.getClass().getName());

//        List<CharSequence> lcs = List<String>();
//    lcs.add(new String());
//    lcs.add(new StringBuilder());
  }
}
