import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Collection;
import java.util.Collections;


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
  private ArrayList<IntPred> hypothesisPreds;

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

  public Integer getSymbolImage(Integer input) {
    Character output = targetPartition.output(input);
    for (Integer e : sigmaE) {
      if (output.equals(targetPartition.output(e))) {
        return e;
      }
    }
    return null;
  }

  public Integer getMinimumSymbolicImage(Integer input) {
    ArrayList<Integer> sigmaEList = new ArrayList<Integer>(sigmaE);
    Collections.sort(sigmaEList);
    Character output = targetPartition.output(input);
    for (Integer e : sigmaEList) {
      if (output.equals(targetPartition.output(e))) {
        return e;
      }
    }
    return null;
  }


  public void addCharacter(Integer input) {
    sigmaE.add(input);
  }

  public Integer getEdge(Integer low, Integer high) {
    Character lowOutput = targetPartition.output(low);
    Integer result = low;

    while (low <= high) {
      int mid = low + (high - low) / 2;
      Character midOutput = targetPartition.output(mid);

      if (midOutput.equals(lowOutput)) {
        result = mid; // 等価な出力を持つ最大の整数を更新
        low = mid + 1; // さらに右側を探索
      } else {
        high = mid - 1; // 左側を探索
      }
    }

    return result;
  }



}
