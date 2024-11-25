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

public class ListForPartition {
  private Collection<Integer> sigmaE;
  private Partition targetPartition;

  public ListForPartition(Collection<Integer> sigmaE, Partition targetPartition) {
    this.sigmaE = sigmaE;
    this.targetPartition = targetPartition;
  }

  public ArrayList<Collection<Integer>> buildListsForPartition() {
    HashSet<Character> outputs = new HashSet<Character>();
    for (Integer input : sigmaE) {
      outputs.add(targetPartition.output(input));
    }
    ArrayList<Character> outputsList = new ArrayList<Character>(outputs);
    ArrayList<Collection<Integer>> lists = new ArrayList<Collection<Integer>>();
    for (int i = 0; i < outputsList.size(); i++) {
      HashSet<Integer> list = new HashSet<Integer>();
      lists.add(list);
    }
    for (Integer input : sigmaE) {
      Character output = targetPartition.output(input);
      for (int i = 0; i < outputsList.size(); i++) {
        if (output.equals(outputsList.get(i))) {
          lists.get(i).add(input);
        }
      }
    }
    return lists;
  }


  public void addCharacter(Integer input) {
    sigmaE.add(input);
  }



}
