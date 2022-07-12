package superiterable;

import java.util.List;
import java.util.function.Consumer;

class StringPrinter implements Consumer<String> {
  @Override
  public void accept(String s) {
    System.out.println(s);
  }
}
class StringPrettyPrinter implements Consumer<String> {
  @Override
  public void accept(String s) {
    System.out.println("pretty! " + s);
  }
}

public class UseSuperIterable {
  public static void main(String[] args) {
    SuperIterable<String> names = new SuperIterable<>(
        List.of("Fred", "Jim", "Sheila")
    );

    for (String s : names) {
      System.out.println("> " + s);
    }
//    System.out.println("------------");
//    Consumer<String> printer = new StringPrinter();
//    names.toEvery(printer);
//    System.out.println("------------");
//    Consumer<String> prettyPrinter = new StringPrettyPrinter();
//    names.toEvery(prettyPrinter);
//    System.out.println("------------");
//    Consumer<String> prettyPrinter2 = new Consumer<String>() {
//      @Override
//      public void accept(String s) {
//        System.out.println("anonymous inner: " + s);
//      }
//    };
//    names.toEvery(prettyPrinter2);
//    System.out.println("------------");
//    names.toEvery(new Consumer<String>() {
//      @Override
//      public void accept(String s) {
//        System.out.println("anonymous inner: " + s);
//      }
//    });
    System.out.println("------------");
    // Consumer of String is an interface (that's a rule for what's next)
    // If that interface declares EXACTLY ONE abstract method
    // AND we only wish to implement that abstract method (and NOT OTHERWISE)
    // "OBVIOUSLY" the one abstract method's name can be determined by
    // inspection
    // and the "method body" that we provide must be compatible
    // with that abstract method (argument count and types, and return type)
//    names.toEvery(/*new Consumer<String>() {*/
//      /*@Override
//      public void accept*/(String s) -> {
//        System.out.println("anonymous inner: " + s);
//      }
//    /*}*/);
//    names.toEvery( (String s) -> {
//        System.out.println("anonymous inner: " + s);
//      } );

    // (String s, Integer i) or (s, i) BUT NOT (String s, i) nor (s, Integer i)
    // if type inference is possible (since Java 11 I think :) :
    // (var s, var i) or (s, i) BUT NOT (var s, i)
    //    nor (s, var i) nor (String s, var i)
    //    var is permitted to allow annotation (@NotNull var s)

    // block lambda
//    names.toEvery( (s) -> {
//        System.out.println("anonymous inner: " + s);
//      } );

    // Uniquely for a single "unadorned" argument, we can leave out the parens
    // note, zero args: () -> ...
//    names.toEvery( s -> {
//        System.out.println("anonymous inner: " + s);
//      } );

    // "expression lambda", where body is simply a "return expression"
    // or single void expression, can leave out everthing in the body
    // except that expression (i.e. no return, or terminating semicolon)
//    names.toEvery( s -> System.out.println("lambda: " + s) );

//    names
//        .filter(s -> s.length() > 3)
////        .map(s -> { return s.toUpperCase();})
//        .map(s -> s.toUpperCase())
//        .toEvery(s -> System.out.println("more lambda: " + s));

    names
        .filter(s -> s.length() > 3)
        .map(s -> s.toUpperCase())
        .forEach(s -> System.out.println("more lambda: " + s));
  }
}
