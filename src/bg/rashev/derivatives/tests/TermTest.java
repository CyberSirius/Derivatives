package bg.rashev.derivatives.tests;

import bg.rashev.derivatives.Term;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class TermTest {

    @Test
    public void testDerive() throws Exception {
        Term testTerm = new Term(0, 0);
        Term derivedTestTerm = new Term(0, 0);
        assertEquals(testTerm, derivedTestTerm.derive());

        testTerm.setCoefficient(6);
        testTerm.setPower(2);
        derivedTestTerm.setCoefficient(2);
        derivedTestTerm.setPower(3);
        assertEquals(testTerm, derivedTestTerm.derive());

        testTerm.setCoefficient(4);
        testTerm.setPower(3);
        derivedTestTerm.setCoefficient(1);
        derivedTestTerm.setPower(4);
        derivedTestTerm.derive();
        assertEquals(testTerm, derivedTestTerm.derive());

        testTerm.setCoefficient(0);
        testTerm.setPower(0);
        derivedTestTerm.setCoefficient(4);
        derivedTestTerm.setPower(0);
        assertEquals(testTerm, derivedTestTerm.derive());
    }

    @Test
    public void testToString() throws Exception {
        Term testTerm = new Term(5, 4);
        assertEquals("5*x^4", testTerm.toString());

        testTerm.setCoefficient(4);
        testTerm.setPower(1);
        assertEquals("4*x", testTerm.toString());

        testTerm.setCoefficient(4);
        testTerm.setPower(0);
        assertEquals("4", testTerm.toString());

        testTerm.setCoefficient(1);
        testTerm.setPower(4);
        assertEquals("x^4", testTerm.toString());

        testTerm.setCoefficient(1);
        testTerm.setPower(1);
        assertEquals("x", testTerm.toString());

        testTerm.setCoefficient(0);
        testTerm.setPower(0);
        assertEquals("0", testTerm.toString());

    }
}