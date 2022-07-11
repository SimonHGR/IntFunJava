package usealist;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UseAList {

  public static void breakAList(List l) {
    l.add("Alice");
    l.add(0, LocalDate.now());
  }

  public static void main(String[] args) {
    List<LocalDate> dates = new ArrayList();
    dates.add(LocalDate.now());
    List<String> names = Collections.checkedList(
//        new ArrayList<String>(List.of(LocalDate.now(), "Bill")), String.class);
        new ArrayList<>(List.of(/*LocalDate.now(), */"Bill")), String.class);
    names.add("Fred");
    names.add("Sheila");
//    names.add(LocalDate.now());
    System.out.println(names);

//    breakAList(names);
//    String firstName = (String)names.get(0);
    String firstName = names.get(0);
    System.out.println("first name was " + firstName);
  }
}
