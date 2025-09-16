package ordination;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class DagligFastTest {

    @Test
    void testGetDoser() {
        Patient patient = new Patient("121256-0512","Jane",67.0);
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre",0.1,0.15,2.0,"mg");


        DagligFast dagligFast = new DagligFast(LocalDate.of(2025,9,1),LocalDate.of(2025,9,1),patient,laegemiddel,1,2,1,1);

        //Act
        Dosis[] doser = dagligFast.getDoser();
        assertNotNull(doser, "getDoser skal returnere et array");
        assertEquals(4, doser.length, "Der skal være præcis 4 doser i arrayet");



        //Assert
        assertNotNull(doser, "getDoser skal returnere et array");
        assertEquals(4, doser.length, "Der skal være præcis 4 doser i arrayet");

        assertEquals(LocalTime.of(8, 0), doser[0].getTid());
        assertEquals(1, doser[0].getAntal(), 0.001);

        assertEquals(LocalTime.of(12, 0), doser[1].getTid());
        assertEquals(2, doser[1].getAntal(), 0.001);

        assertEquals(LocalTime.of(18, 0), doser[2].getTid());
        assertEquals(1, doser[2].getAntal(), 0.001);

        assertEquals(LocalTime.of(22, 0), doser[3].getTid());
        assertEquals(1, doser[3].getAntal(), 0.001);

    }

    @Test
    void doegnDosis() {
        // Arrange
        Patient patient = new Patient("070985-1153", "Finn", 90.0);
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 0.1, 0.15, 2.0, "mg");

        // doser: morgen=1, middag=2, aften=1, nat=1 → i alt 5 per døgn
        DagligFast dagligFast = new DagligFast(
                LocalDate.of(2025, 9, 1),
                LocalDate.of(2025, 9, 3), // 3 dage
                patient,
                laegemiddel,
                1, 2, 1, 1
        );

        // Act
        double actual = dagligFast.doegnDosis();

        // Assert
        assertEquals(5.0, actual, 0.001, "Døgn-dosis skal være summen af alle doser pr. dag");
    }

    @Test
    void samletDosis() {
        // Arrange
        Patient patient = new Patient("050972-1233", "Hans", 110.0);
        Laegemiddel laegemiddel = new Laegemiddel("Fucidin", 0.1, 0.15, 2.0, "mg");

        // doser: morgen=1, middag=2, aften=1, nat=1 → i alt 5 per døgn
        // periode: 3 dage
        DagligFast dagligFast = new DagligFast(
                LocalDate.of(2025, 9, 1),
                LocalDate.of(2025, 9, 3),
                patient,
                laegemiddel,
                1, 2, 1, 1
        );

        // Act
        double actual = dagligFast.samletDosis();

        // Assert
        assertEquals(10.0, actual, 0.001, "Samlet dosis skal være døgn-dosis * antal dage");
    }
    }
