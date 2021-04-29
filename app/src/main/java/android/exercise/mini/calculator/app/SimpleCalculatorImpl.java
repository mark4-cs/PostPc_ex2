package android.exercise.mini.calculator.app;

import android.text.TextUtils;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class SimpleCalculatorImpl implements SimpleCalculator {

  // todo: add fields as needed
  private List<String> input = new ArrayList<String>(){{add("0");}};

  @Override
  public String output() {
    // todo: return output based on the current state
    String s = this.input.toString().replaceAll("\\[|\\]", "").replaceAll(", ","");
    if (s.length() > 2 && s.charAt(0) == '0' && s.charAt(1) == '-'){return s.substring(1);}
    return s;

  }

  @Override
  public void insertDigit(int digit) {
    // todo: insert a digit
    if (digit < 0 || digit > 9){throw new InvalidParameterException();}
    this.input.add(Integer.toString(digit));
    if (this.input.size() > 1 && this.input.get(0).equals("0") && !this.input.get(1).equals("-")){this.input.remove(0);}  /*delete extra 0*/
  }

  @Override
  public void insertPlus() {
    // todo: insert a plus
    if (this.input.get(this.input.size() - 1).equals("+") || this.input.get(this.input.size() - 1).equals("-")){return;}
    this.input.add("+");
  }

  @Override
  public void insertMinus() {
    // todo: insert a minus
    if (this.input.get(this.input.size() - 1).equals("+") || this.input.get(this.input.size() - 1).equals("-")){return;}
    this.input.add("-");
  }

  @Override
  public void insertEquals() {
    // todo: calculate the equation. after calling `insertEquals()`, the output should be the result
    //  e.g. given input "14+3", calling `insertEquals()`, and calling `output()`, output should be "17"
    String a = this.input.toString().replaceAll("\\[|\\]", "").replaceAll(", ","");
    String[] operators = a.split("[0-9]+");
    String[] digits = a.split("[+-]");
    int result = Integer.parseInt(digits[0]);
    for(int i=1; i<digits.length; i++){
      if(operators[i].equals("+"))
        result += Integer.parseInt(digits[i]);
      else
        result -= Integer.parseInt(digits[i]);
    }
    this.input.clear();
    if (result < 0){result = -result; this.input.add("0"); this.input.add("-");}
    String digitTokens = Integer.toString(result);
    for(int i = 0; i < digitTokens.length(); i++){this.input.add(String.valueOf(digitTokens.charAt(i)));}
  }

  @Override
  public void deleteLast() {
    // todo: delete the last input (digit, plus or minus)
    //  e.g.
    //  if input was "12+3" and called `deleteLast()`, then delete the "3"
    //  if input was "12+" and called `deleteLast()`, then delete the "+"
    //  if no input was given, then there is nothing to do here
    this.input.remove(input.size() - 1);
    if (this.input.isEmpty()){this.input.add("0");}
  }

  @Override
  public void clear() {
    // todo: clear everything (same as no-input was never given)
    this.input.clear();
    this.input.add("0");
  }

  @Override
  public Serializable saveState() {
    CalculatorState state = new CalculatorState();
    state.input = new ArrayList<>(this.input);
    // todo: insert all data to the state, so in the future we can load from this state
    return state;
  }

  @Override
  public void loadState(Serializable prevState) {
    if (!(prevState instanceof CalculatorState)) {
      return; // ignore
    }
    CalculatorState casted = (CalculatorState) prevState;
    // todo: use the CalculatorState to load
    this.input = new ArrayList<>(casted.input);
  }

  private static class CalculatorState implements Serializable {
    /*
    TODO: add fields to this class that will store the calculator state
    all fields must only be from the types:
    - primitives (e.g. int, boolean, etc)
    - String
    - ArrayList<> where the type is a primitive or a String
    - HashMap<> where the types are primitives or a String
     */
    private List<String> input;
  }
}
