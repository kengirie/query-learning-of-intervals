import java.util.Collection;
import java.util.ArrayList;

import theory.intervals.BoundedIntegerSolver;
import theory.intervals.IntPred;

import org.sat4j.specs.TimeoutException;

public class Learner {
  private Partition targetPartition;
  private Collection<Integer> initialSigmaE;
  private BoundedIntegerSolver solver;

  public Learner(Partition targetPartition, Collection<Integer> initialSigmaE, BoundedIntegerSolver solver) {
    this.targetPartition = targetPartition;
    this.initialSigmaE = initialSigmaE;
    this.solver = solver;
  }

  public void learn() throws TimeoutException {
    ListForPartition listForPartition = new ListForPartition(initialSigmaE, targetPartition);
    EQOracle eqOracle = new EQOracle();
    while (true) {
      ArrayList<Collection<Integer>> lists = listForPartition.buildListsForPartition();
      ArrayList<IntPred> preds = solver.GetSeparatingPredicates(lists, Long.MAX_VALUE);
      System.out.println("your hypothesis partition is:" + preds);
      Integer cex = eqOracle.EquivaleneceQuery();
      if (cex == null) {
        System.out.println("The learner has learned the target partition:" + preds);
        break;
      }
      else {
        System.out.println("counterexample is:" + cex);
        listForPartition.addCharacter(cex);
      }
    }
  }

}
