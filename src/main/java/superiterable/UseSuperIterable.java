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
    // IFF that interface declares EXACTLY ONE abstract method
    // AND we only wish to implement that abstract method
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
    names.toEvery( (String s) -> {
        System.out.println("anonymous inner: " + s);
      } );



    //    names.filter(...).map(...).toEvery(...)
  }
}
