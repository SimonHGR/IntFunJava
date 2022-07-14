package exceptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

@FunctionalInterface
interface ExFunction<A, R> {
  R apply(A a) throws Throwable;

  static <A, R> Function<A, Optional<R>> wrap(ExFunction<A, R> op) {
    return a -> {
      try {
        return Optional.of(op.apply(a));
      } catch (Throwable e) {
        return Optional.empty();
      }
    };
  }
}

public class StreamFileProcessing {
//  public static Optional<Stream<String>> getLines(String path) {
//    try {
//      return Optional.of(Files.lines(Path.of(path)));
//    } catch (IOException ioe) {
////      throw new RuntimeException(ioe);
//      // we can't express the problem!!!!
//      // look at VAVR--provides Either<F,S>
//      return Optional.empty();
//    }
//  }
  public static void main(String[] args) {
    Consumer<Optional<?>> reporter = x -> {
      if (x.isEmpty()) System.out.println("There was a problem");
    };

      Stream.of("a.txt", "b.txt", "c.txt")
//          .flatMap(fn -> getLines(fn))
//          .map(fn -> getLines(fn))
          .map(ExFunction.wrap(fn -> Files.lines(Path.of(fn))))
          .peek(reporter)
          .filter(Optional::isPresent)
          .flatMap(opt -> opt.get())
          .forEach(System.out::println);
  }
}
