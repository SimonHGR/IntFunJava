package fpaverage;

import java.util.OptionalDouble;
import java.util.concurrent.ThreadLocalRandom;

record Average(double sum, long count) {
  public Average merge(Average other) {
    return new Average(this.sum + other.sum(),  this.count + other.count);
  }

  public OptionalDouble get() {
    if (count != 0) {
      return OptionalDouble.of(sum / count);
    } else {
      return OptionalDouble.empty();
    }
  }
}

public class Averager {
  // minimal "look at the compilations" flag
  // -XX:+PrintCompilation

  public static void main(String[] args) {
//    var av = new Average(10, 4);
//    var av2 = new Average(10, 4);
//    System.out.println(av);
//    System.out.println(av.equals(av2));

    long start = System.nanoTime();

    ThreadLocalRandom.current().doubles(2_000_000_000, -1.0, +1.0)
        .parallel() // TLAB "thread local allocation buffer"
        .mapToObj(d -> new Average(d, 1))
//        .reduce((a1, a2) -> a1.merge(a2))
        .reduce(new Average(0, 0), Average::merge)
        .get()
        .ifPresentOrElse(System.out::println,
            () -> System.out.println("No data!"));

//        .reduce(Average::merge)
//        .ifPresentOrElse(opt -> System.out.println(opt.get()),
//            () -> System.out.println("No data!"));

    long totalTime = System.nanoTime() - start;
    System.out.printf("elapsed time is %7.3f\n", (totalTime / 1_000_000_000.0));
  }
}
