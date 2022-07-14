package mutatingaverage;

import java.util.OptionalDouble;
import java.util.concurrent.ThreadLocalRandom;

class Average {
//  public static long totalCount = 0;
  private double sum;
  public long count;

  public Average() {} // default is zero, zero

  private static Average theSingleton = new Average();
  public static Average getSingleton() {
    return theSingleton;
  }
  public void include(double d) {
    sum += d;
    count++;
//    totalCount++;
  }

  public void merge(Average other) {
    System.out.println("In merge: " + Thread.currentThread().getName());
    this.sum += other.sum;
    this.count += other.count;
  }

  public OptionalDouble get() {
    if (count > 0) {
      return OptionalDouble.of(sum / count);
    } else {
      return OptionalDouble.empty();
    }
  }
}

public class Averager {
  public static void main(String[] args) {
    long start = System.nanoTime();
    ThreadLocalRandom.current().doubles(6_000_000_000L, -1.0, 1.0)
        .parallel()
        .collect(() -> new Average() /*() -> Average.getSingleton()*/,
            (a, d) -> a.include(d),
            (aFinal, a) -> aFinal.merge(a))
        .get()
        .ifPresentOrElse(System.out::println,
            () -> System.out.println("No data"));
    long time = System.nanoTime() - start;
    System.out.printf("Time taken: %7.3f\n", (time / 1_000_000_000.0));
//    System.out.println("Total count is " + Average.totalCount);
    System.out.println("Total count is " + Average.getSingleton().count);
  }
}
