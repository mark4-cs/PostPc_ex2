package test.android.exercise.mini.calculator.app;

import android.exercise.mini.calculator.app.SimpleCalculatorImpl;

import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.*;

public class SimpleCalculatorImplTest {

  @Test
  public void when_noInputGiven_then_outputShouldBe0(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    assertEquals("0", calculatorUnderTest.output());
  }

  @Test
  public void when_inputIsPlus_then_outputShouldBe0Plus(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertPlus();
    assertEquals("0+", calculatorUnderTest.output());
  }


  @Test
  public void when_inputIsMinus_then_outputShouldBeCorrect(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertMinus();
    String expected = "0-"; // TODO: decide the expected output when having a single minus
    assertEquals(expected, calculatorUnderTest.output());
  }

  @Test
  public void when_callingInsertDigitWithIllegalNumber_then_exceptionShouldBeThrown(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    try {
      calculatorUnderTest.insertDigit(357);
      fail("should throw an exception and not reach this line");
    } catch (RuntimeException e) {
      // good :)
    }
  }


  @Test
  public void when_callingDeleteLast_then_lastOutputShouldBeDeleted(){
    // todo: implement test
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertMinus(); /*"0-"*/
    calculatorUnderTest.insertPlus(); /*"0-"*/
    calculatorUnderTest.insertMinus();  /*"0-"*/
    calculatorUnderTest.insertDigit(2); /*"0-2"*/
    calculatorUnderTest.insertDigit(4); /*"0-24"*/
    calculatorUnderTest.insertMinus();  /*"0-24-"*/
    calculatorUnderTest.insertPlus(); /*"0-24-"*/
    calculatorUnderTest.insertDigit(3);   /*"0-24-3"*/
    assertEquals("-24-3", calculatorUnderTest.output());
    calculatorUnderTest.deleteLast();
    assertEquals("-24-", calculatorUnderTest.output());
    calculatorUnderTest.deleteLast();
    assertEquals("-24", calculatorUnderTest.output());
    calculatorUnderTest.deleteLast();
    assertEquals("-2", calculatorUnderTest.output());
    calculatorUnderTest.deleteLast();
    assertEquals("0-", calculatorUnderTest.output());
    calculatorUnderTest.deleteLast();
    assertEquals("0", calculatorUnderTest.output());
    calculatorUnderTest.deleteLast();
    assertEquals("0", calculatorUnderTest.output());
  }

  @Test
  public void when_callingClear_then_outputShouldBeCleared(){
    // todo: implement test
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertMinus(); /*"0-"*/
    calculatorUnderTest.insertPlus(); /*"0-"*/
    calculatorUnderTest.insertMinus();  /*"0-"*/
    calculatorUnderTest.insertDigit(2); /*"0-2"*/
    calculatorUnderTest.insertDigit(4); /*"0-24"*/
    calculatorUnderTest.insertMinus();  /*"0-24-"*/
    calculatorUnderTest.insertPlus(); /*"0-24-"*/
    calculatorUnderTest.insertDigit(3);   /*"0-24-3"*/
    assertEquals("-24-3", calculatorUnderTest.output());
    calculatorUnderTest.clear();
    assertEquals("0", calculatorUnderTest.output());
    calculatorUnderTest.clear();
    assertEquals("0", calculatorUnderTest.output());
  }

  @Test
  public void when_savingState_should_loadThatStateCorrectly(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    // give some input
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(7);

    // save current state
    Serializable savedState = calculatorUnderTest.saveState();
    assertNotNull(savedState);

    // call `clear` and make sure calculator cleared
    calculatorUnderTest.clear();
    assertEquals("0", calculatorUnderTest.output());

    // load the saved state and make sure state was loaded correctly
    calculatorUnderTest.loadState(savedState);
    assertEquals("5+7", calculatorUnderTest.output());
  }

  @Test
  public void when_savingStateFromFirstCalculator_should_loadStateCorrectlyFromSecondCalculator(){
    SimpleCalculatorImpl firstCalculator = new SimpleCalculatorImpl();
    SimpleCalculatorImpl secondCalculator = new SimpleCalculatorImpl();
    firstCalculator.insertMinus(); /*"0-"*/
    firstCalculator.insertPlus(); /*"0-"*/
    firstCalculator.insertMinus();  /*"0-"*/
    firstCalculator.insertDigit(2); /*"0-2"*/
    firstCalculator.insertDigit(4); /*"0-24"*/
    firstCalculator.insertMinus();  /*"0-24-"*/
    firstCalculator.insertPlus(); /*"0-24-"*/
    firstCalculator.insertDigit(3);   /*"0-24-3"*/
    Serializable prevState = firstCalculator.saveState();
    assertNotNull(prevState);
    assertEquals("-24-3", firstCalculator.output());
    secondCalculator.loadState(prevState);
    assertEquals("-24-3", secondCalculator.output());
    firstCalculator.clear();
    assertEquals("-24-3", secondCalculator.output());
    // TODO: implement the test based on this method's name.
    //  you can get inspiration from the test method `when_savingState_should_loadThatStateCorrectly()`
  }

  // TODO:
  //  the existing tests are not enough since they only test simple use-cases with small inputs.
  //  write at least 10 methods to test correct behavior with complicated inputs or use-cases.
  //  examples:
  //  - given input "5+7-13<DeleteLast>25", expected output is "5+17-125"
  //  - given input "9<Clear>12<Clear>8-7=", expected output is "1"
  //  - given input "8-7=+4=-1=", expected output is "4"
  //  - given input "999-888-222=-333", expected output is "-111-333"
  //  - with 2 calculators, give them different inputs, then save state on first calculator and load the state into second calculator, make sure state loaded well
  //  etc etc.
  //  feel free to be creative in your tests!

  @Test
  public void delete_sequence_scenario_one(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.deleteLast();
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertDigit(5);
    assertEquals("5+7-125", calculatorUnderTest.output());
  }

  @Test
  public void clear_sequence_scenario_one(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.clear();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.clear();
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertEquals();
    assertEquals("1", calculatorUnderTest.output());
  }

  @Test
  public void calc_sequence_scenario_one(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(4);
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertEquals();
    assertEquals("4", calculatorUnderTest.output());
  }

  @Test
  public void calc_sequence_scenario_two(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.insertDigit(3);
    assertEquals("-111-333", calculatorUnderTest.output());
  }

  @Test
  public void load_save_sequence_two_calcs(){
    SimpleCalculatorImpl firstCalculator = new SimpleCalculatorImpl();
    SimpleCalculatorImpl secondCalculator = new SimpleCalculatorImpl();
    firstCalculator.insertMinus();
    firstCalculator.insertDigit(3);
    secondCalculator.insertDigit(4);
    secondCalculator.loadState(firstCalculator.saveState());
    assertEquals("-3", secondCalculator.output());
  }

  @Test
  public void multiple_minus(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertMinus();
    assertEquals("0-", calculatorUnderTest.output());
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertMinus();
    assertEquals("0-", calculatorUnderTest.output());
  }

  @Test
  public void multiple_plus(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertPlus();
    assertEquals("0+", calculatorUnderTest.output());
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertPlus();
    assertEquals("0+", calculatorUnderTest.output());
  }

  @Test
  public void multiple_plus_and_minus(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertPlus();
    assertEquals("0-", calculatorUnderTest.output());
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertPlus();
    assertEquals("0+", calculatorUnderTest.output());
  }

  @Test
  public void delete_sequence_scenario_two(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.deleteLast();
    calculatorUnderTest.insertDigit(2);
    assertEquals("55+77-12", calculatorUnderTest.output());
  }

  @Test
  public void delete_sequence_scenario_three(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.insertDigit(4);
    calculatorUnderTest.insertEquals();
    assertEquals("-1", calculatorUnderTest.output());
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertEquals();
    assertEquals("0", calculatorUnderTest.output());
  }
}