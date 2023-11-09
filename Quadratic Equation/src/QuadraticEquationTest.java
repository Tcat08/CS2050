public class QuadraticEquationTest {
    /*
     * CS2050 - Computer Science II - Fall 2023
     * Instructor: Thyago Mota
     * Student:
     * Description: Homework 01 - QuadraticEquationTest
     */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
    class QuadraticEquationTest {
        @Test
        public void testNonQuadraticEquation() {
            assertThrows(Exception.class, () -> new QuadraticEquation(0, 1, 1));
        }
        @Test
        public void testQuadraticEquationNoRoots() {
            try {
                QuadraticEquation qe = new QuadraticEquation(1, -3, 4);
                assertEquals(1, qe.getA(), QuadraticEquation.PRECISION);
                assertEquals(-3, qe.getB(), QuadraticEquation.PRECISION);
                assertEquals(4, qe.getC(), QuadraticEquation.PRECISION);
                assertFalse(qe.hasRoots());
                assertThrows(Exception.class, () -> qe.firstRoot());
                assertThrows(Exception.class, () -> qe.secondRoot());
            }
            catch (Exception ex) {
            }
        }
        @Test
        public void testQuadraticEquationYesRoots() {
            try {
                QuadraticEquation qe = new QuadraticEquation(1, -7, 10);
                assertEquals(1, qe.getA(), QuadraticEquation.PRECISION);
                assertEquals(-7, qe.getB(), QuadraticEquation.PRECISION);
                assertEquals(10, qe.getC(), QuadraticEquation.PRECISION);
                assertTrue(qe.hasRoots());
                assertEquals(5, qe.firstRoot());
                assertEquals(2, qe.secondRoot());
            }
            catch (Exception ex) {
            }
        }
    }

}
