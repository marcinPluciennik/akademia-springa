package springcourse.kursspringboot2testy;

import org.junit.Assert;
import org.junit.Test;


public class CalculatorTests {

    @Test
    public void shouldAddTwoNumbers(){
        //Given
        Calculator calculator = new Calculator();

        //Then
        Assert.assertEquals(calculator.add(10,15), 25);
        Assert.assertEquals(calculator.add(-10,15), 5);
        Assert.assertEquals(calculator.add(-2,-2), -4);
    }

    @Test
    public void shouldNoAddTwoNumbers(){
        //Given
        Calculator calculator = new Calculator();

        //Then
        Assert.assertNotEquals(calculator.add(10,20), 25);
    }

    @Test
    public void shouldDivideTwoNumbers() {
        //Given
        Calculator calculator = new Calculator();

        //Then
        Assert.assertEquals(calculator.divide(100,10), 10, 0);
    }

    @Test(expected = ArithmeticException.class)
    public void shouldNotDivideByZero() {
        //Given
        Calculator calculator = new Calculator();

        //Then
        Assert.assertNotEquals(calculator.divide(100,0), 10);
    }
}
