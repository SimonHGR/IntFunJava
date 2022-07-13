package superiterable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class SuperIterable<E> implements Iterable<E> {
  private Iterable<E> self;

  public SuperIterable(Iterable<E> self) {
    this.self = self;
  }

  public SuperIterable<E> filter(/*@NotNull SuperIterable<E> this, */Predicate<E> pred) {
    List<E> results = new ArrayList<>();
    for (E e : this.self) {
      if (pred.test(e)) {
        results.add(e);
      }
    }
    return new SuperIterable<>(results);
  }

  public <F> SuperIterable<F> map(Function<E, F> op) {
    List<F> results = new ArrayList<>();
    for (E e : this.self) {
      results.add(op.apply(e));
    }
    return new SuperIterable<>(results);
  }

  public <F> SuperIterable<F> flatMap(Function<E, SuperIterable<F>> op) {
    List<F> results = new ArrayList<>();
    for (E e : this.self) {
      SuperIterable<F> manyF = op.apply(e);
      for (F f : manyF) {
        results.add(f);
      }
    }
    return new SuperIterable<>(results);
  }

  // this is equivalent (mostly) to forEach, which is
  // an integral part of Iterable (and some others)
//  public void toEvery(Consumer<E> op) {
//    for (E e : this.self) {
//      op.accept(e);
//    }
//  }

  @Override
  public Iterator<E> iterator() {
    return self.iterator();
  }
}
