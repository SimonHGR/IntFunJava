package cocontra;

import java.util.List;

public class TaxPrep {
  public static void calculateTaxes(Taxable t) {}
  public static void calculateBulkTaxes(List<? extends Taxable> lt) {
//    lt.add(new Taxable());
//    lt.add(new Individual());
//    lt.add(new Corporation());
    for (Taxable t : lt) { // assign something from the list to a TAXABLE
    }
  }

  public static void collectJoesClients(List<? super Individual> li) {
    li.add(new Individual());
    li.add(new Individual());
//    for (Individual t : li) {
//
//    }
  }
  public static void main(String[] args) {
    List<Taxable> clients = List.of(
        new Individual(), new Corporation(), new Individual()
    );
    collectJoesClients(clients);
    calculateBulkTaxes(clients);

    List<Individual> joesClients = List.of(
        new Individual(), new Individual()
    );

    collectJoesClients(joesClients);

    calculateBulkTaxes(joesClients);

  }

}
