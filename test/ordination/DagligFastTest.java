package ordination;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DagligFastTest {

    @Test
    void testDoegnDosis() {
       Patient patient = new Patient("85","Jens",80);
       Laegemiddel lm = new Laegemiddel("Jens",25,3,3,"l");
        DagligFast df = new DagligFast(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 1, 3),
                patient,
                lm,
                1.0, 2.0, 3.0, 4.0 // morgen, middag, aften, nat
        );

        // Summen af doser pr. døgn = 1+2+3+4 = 10
        assertEquals(10.0, df.doegnDosis(), 0.001);
    }
}