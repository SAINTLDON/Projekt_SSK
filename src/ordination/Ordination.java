package ordination;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class Ordination {
    private LocalDate startDen;
    private LocalDate slutDen;
    private Patient patient;
    private Laegemiddel laegemiddel;

    // TODO Link til Laegemiddel
    // TODO constructor (med specifikation)


    public Ordination(LocalDate startDen, LocalDate slutDen, Patient patient, Laegemiddel laegemiddel, double antal) {
        this.startDen = startDen;
        this.slutDen = slutDen;
        this.patient = patient;
        this.laegemiddel = laegemiddel;

        if (slutDen.isBefore(startDen)){
            throw new IllegalArgumentException("Slutdato kan ikek starte førr");
        }
        if (patient == null || laegemiddel == null){
            throw new IllegalArgumentException("Patient og lægemiddel skal være fyldt ud");
        }
    }


    public Patient getPatient() {
        return patient;
    }

    public Laegemiddel getLaegemiddel() {
        return laegemiddel;
    }

    public LocalDate getStartDen() {
        return startDen;
    }	

    public LocalDate getSlutDen() {
        return slutDen;
    }

    /**
     * Antal hele dage mellem startdato og slutdato. Begge dage inklusive.
     * @return antal dage ordinationen gælder for
     */
    public int antalDage() {
        return (int) ChronoUnit.DAYS.between(startDen, slutDen) + 1;
    }

    @Override
    public String toString() {
        return startDen.toString();
    }

    /**
     * Returnerer den totale dosis der er givet i den periode ordinationen er gyldig
     * @return
     */
    public abstract double samletDosis();


    /**
     * Returnerer den gennemsnitlige dosis givet pr dag i den periode ordinationen er gyldig
     * @return
     */
    public abstract double doegnDosis();


    /**
     * Returnerer ordinationstypen som en String
     * @return
     */
    public abstract String getType();


}
