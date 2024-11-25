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

public class Partition {
  private ArrayList<IntPred> preds;
  private ArrayList<Character> outputs;

  public Partition(ArrayList<IntPred> preds, ArrayList<Character> outputs) {
    this.preds = preds;
    this.outputs = outputs;
  }

  public Character output(Integer input) {
    for (int i = 0; i < preds.size(); i++) {
      if (preds.get(i).isSatisfiedBy(input)) {
        return outputs.get(i);
      }
    }
    return null;
  }
  @Override
  public String toString() {
    String ret = "";
    for (int i = 0; i < preds.size(); i++) {
      ret += preds.get(i) + " -> " + outputs.get(i) + "\n";
    }
    return ret;
  }

}
