import java.io.*;
import java.util.*;

public class RPNStacker {

  public static boolean isNumeric(String strNum) {
    if (strNum == null) {
      return false;
    }
    try {
      Double.parseDouble(strNum);
    } catch (NumberFormatException nfe) {
      return false;
    }
    return true;
  }

  public static double operation(String operator, Stack<Double> stack) {
    try {
      double b = stack.pop();
      double a = stack.pop();
      switch (operator) {
        case "+":
          return a + b;
        case "-":
          return a - b;
        case "/":
          return a / b;
        case "*":
          return a * b;
        default:
          return a + b;
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return 0.00;
    }
  }

  public static void main(String args[]) throws IOException {
    BufferedReader br = null;
    Stack<Double> stack = new Stack<Double>();
    try {
      FileInputStream stream = new FileInputStream("file.stk");
      InputStreamReader reader = new InputStreamReader(stream);
      br = new BufferedReader(reader);
      String linha = br.readLine();
      while (linha != null) {
        if (isNumeric(linha)) {
          stack.push(Double.parseDouble((linha)));
        } else {
          stack.push(operation(linha, stack));
        }
        linha = br.readLine();
      }
      System.out.println(stack.pop());
    } finally {
      br.close();
    }
  }
}
