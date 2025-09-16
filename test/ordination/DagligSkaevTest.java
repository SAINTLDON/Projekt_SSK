package ordination;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DagligSkaevTest {

    @Test
    void samletDosis() {

        Patient patient = new Patient("121256-0512", "Jane", 67.0);
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 2.0, "mg");
        List<Dosis> doser = new ArrayList<>();
        doser.add(new Dosis(LocalTime.of(9, 0), 1.0));
        doser.add(new Dosis(LocalTime.of(20, 0), 2.0));

        // Per dag = 3.0
        // Antal dage = 3 (inkl. start og slut)
        DagligSkaev dagligSkaev = new DagligSkaev(
                LocalDate.of(2025, 9, 1),
                LocalDate.of(2025, 9, 3), patient, laegemiddel, doser);

        // Act
        double actual = dagligSkaev.samletDosis();

        // Assert
        assertEquals(9.0, actual, 0.001, "Samlet dosis skal være doegnDosis * antal dage");
    }

    @Test
    void doegnDosis() {
        Patient patient = new Patient("121256-0512", "Jane", 67.0);
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 2.0, "mg");
        List<Dosis> doser = new ArrayList<>();
        doser.add(new Dosis(LocalTime.of(8, 0), 0.5));
        doser.add(new Dosis(LocalTime.of(12, 0), 1.5));
        doser.add(new Dosis(LocalTime.of(22, 0), 2.0));

        DagligSkaev dagligSkaev = new DagligSkaev(
                LocalDate.of(2025, 9, 1),
                LocalDate.of(2025, 9, 1),
                patient,
                laegemiddel,
                doser
        );

        // Act
        double actual = dagligSkaev.doegnDosis();

        // Assert
        assertEquals(4.0, actual, 0.001, "Døgn-dosis skal være summen af alle doser på en dag");
    }
    }
