import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Collection;

import org.sat4j.specs.TimeoutException;
import learning.symbolicmealy.LexicographicallyMinimalOracle;
import learning.symbolicmealy.SymbolicMealyAutomatonLearner;
import learning.symbolicmealy.SymbolicMealyAutomatonOracle;
import symbolicmealy.SMAInputMove;
import symbolicmealy.SymbolicMealyAutomaton;

import automata.sfa.SFA;
import automata.sfa.SFAMove;
import theory.BooleanAlgebra;
import theory.ProductAlgebra;
import theory.cartesian.CartesianProduct;
import theory.intervals.BoundedIntegerSolver;
import theory.intervals.IntPred;
import theory.intervals.IntegerSolver;
import utilities.Pair;

import org.sat4j.specs.TimeoutException;

import theory.intervals.RealPred;
import theory.intervals.RealSolver;
import theory.intervals.DoubleSolver;
import theory.intervals.DoublePred;
import theory.intervals.BoundedDoubleSolver;

public class App {
  public static void main(String[] args) throws TimeoutException {
    BoundedIntegerSolver solver = new BoundedIntegerSolver(0, 100);
    IntPred p = new IntPred(0, 10);
    ArrayList<Collection<Integer>> sets = new ArrayList<Collection<Integer>>();
    sets.add(new HashSet<Integer>(Arrays.asList(0, 40)));
    sets.add(new HashSet<Integer>(Arrays.asList(30)));
    ArrayList<IntPred> preds = solver.GetSeparatingPredicates(sets, Long.MAX_VALUE);
    ArrayList<Character> outputs = new ArrayList<Character>(Arrays.asList('a', 'b'));
    Partition partition = new Partition(preds, outputs);
    ListForPartition listForPartition = new ListForPartition(Arrays.asList(0, 59), partition);
    System.out.println(listForPartition.getEdge(0, 33));
    System.out.println(listForPartition.getSymbolImage(34));

    ArrayList<Collection<Integer>> lists = listForPartition.buildListsForPartition();
    ArrayList<IntPred> preds2 = solver.GetSeparatingPredicates(lists, Long.MAX_VALUE);
    System.out.println(preds2);
    System.out.println(lists);
    System.out.println(partition);
    System.out.println(partition.output(34));
    System.out.println(preds);
    UserInputEQOracle eqOracle = new UserInputEQOracle();
    //System.out.println(eqOracle.EquivaleneceQuery());
    ArrayList<Integer> initialSigmaE = new ArrayList<Integer>(Arrays.asList(0));
    Learner learner = new Learner(partition, initialSigmaE, solver);
    //learner.learn();
  }


}
