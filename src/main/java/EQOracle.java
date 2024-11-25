import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EQOracle {
  private Scanner scanner;
  public EQOracle() {
    scanner = new Scanner(System.in);
  }

  public Integer EquivaleneceQuery() {
    String isEquivalent;
     while (true) {
      System.out.println("Is the hypothesis partition equivalent to the target partition? (yes/no):");
      isEquivalent = scanner.nextLine().trim().toLowerCase();
      if (isEquivalent.equals("yes")) {
        return null;
      } else if (isEquivalent.equals("no")) {
        System.out.println("Please enter a integer:");
        String inputLine = scanner.nextLine();
        return Integer.parseInt(inputLine);
      } else {
        System.out.println("Invalid input. Please enter 'yes' or 'no'.");
      }

    }

  }
}
