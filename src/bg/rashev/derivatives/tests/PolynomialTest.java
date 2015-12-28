package bg.rashev.derivatives.tests;

import bg.rashev.derivatives.Polynomial;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class PolynomialTest {

    @Test
    public void testSimplify() throws Exception {
        Polynomial expected = new Polynomial("x^2+1");
        Polynomial actual = new Polynomial("1+x^2");
        assertEquals(expected, actual.simplify());

        expected = new Polynomial("3*x^2");
        actual = new Polynomial("2*x^2 + x^2");
        assertEquals(expected, actual.simplify());

        expected = new Polynomial("3");
        actual = new Polynomial("3");
        assertEquals(expected, actual.simplify());

        expected = new Polynomial("4*x^2");
        actual = new Polynomial("2*x^2 + 2*x^2");
        assertEquals(expected, actual.simplify());

    }

    @Test
    public void testDerive() throws Exception {
        Polynomial expected = new Polynomial("6*x^2+1");
        Polynomial actual = new Polynomial("2*x^3+x");
        assertEquals(expected, actual.derive());

        expected = new Polynomial("0");
        actual = new Polynomial("1");
        assertEquals(expected, actual.derive());

        expected = new Polynomial("4*x^3+30*x^2");
        actual = new Polynomial("x^4+10*x^3");
        assertEquals(expected, actual.derive());

        expected = new Polynomial("6*x");
        actual = new Polynomial("3*x^2");
        assertEquals(expected, actual.derive());

    }

    @Test
    public void testDeriveToString() throws Exception {
        String polynomial = "2*x^3+x";
        String derivative = "6*x^2+1";
        Polynomial actual = new Polynomial(polynomial);
        assertEquals(derivative, actual.derive().toString());

        polynomial = "1";
        derivative = "0";
        actual = new Polynomial(polynomial);
        assertEquals(derivative, actual.derive().toString());

        polynomial = "x^4+10*x^3";
        derivative = "4*x^3+30*x^2";
        actual = new Polynomial(polynomial);
        assertEquals(derivative, actual.derive().toString());

        polynomial = "1+x^2";
        derivative = "2*x";
        actual = new Polynomial(polynomial);
        assertEquals(derivative, actual.derive().toString());

        polynomial = "2*x^2+x^2";
        derivative = "6*x";
        actual = new Polynomial(polynomial);
        assertEquals(derivative, actual.derive().toString());
    }
}