package controller;

import ordination.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {


    private Controller controller;
    private Laegemiddel laegemiddel;
    private Patient patient;
    @BeforeEach
    void setUp(){
        controller = Controller.getController();
        patient = controller.opretPatient("12323442322-001","Finn",90);
        laegemiddel = controller.opretLaegemiddel("Paracetamol",0.1,0.15,0.15,"mg");

    }

    @Test
    void opretPNOrdination() {
        //Arrange
        setUp();

        //act
        PN pn = controller.opretPNOrdination(
                LocalDate.of(2025, 9, 1),
                LocalDate.of(2025, 9, 5), patient, laegemiddel, 2.0);


        List<Ordination> actual = patient.getOrdinationer();

        //Assert
        assertNotNull(pn);
        assertTrue(patient.getOrdinationer().contains(pn), "Ordination skal tilføjes til patienten");
        assertEquals(0.0, pn.getAntalEnheder(), 0.001);

    }


    @Test
    void opretDagligFastOrdination() {
        setUp();

        DagligFast dagligFast1 = controller.opretDagligFastOrdination(LocalDate.of(2025, 9, 1), LocalDate.of(2025, 9, 2), patient, laegemiddel, 1.0, 1.0, 1.0, 1.0);


        assertNotNull(dagligFast1);
        assertEquals(2.0, dagligFast1.antalDage());
        assertTrue(patient.getOrdinationer().contains(dagligFast1));
    }

    @Test
    void opretDagligSkaevOrdination() {

    }

    @Test
    void ordinationPNAnvendt() {
    }

    @Test
    void anbefaletDosisPrDoegn() {
    }

    @Test
    void antalOrdinationerPrVægtPrLægemiddel() {
    }
}