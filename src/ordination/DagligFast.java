package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class DagligFast extends Ordination {  //

    private double morgenDose;
    private double middagDose;
    private double aftenDose;
    private double natDose;
    Patient patient;


    public DagligFast(LocalDate startdato, LocalDate slutdato, Patient patient, Laegemiddel laegemiddel, double morgenDose, double middagDose, double aftenDose, double natDose) {
        super(startdato, slutdato, patient, laegemiddel, 0);
        this.morgenDose = morgenDose;
        this.middagDose = middagDose;
        this.aftenDose = aftenDose;
        this.natDose = natDose;
    }

    @Override
    public double doegnDosis() {
        return morgenDose + middagDose + aftenDose + natDose;
    }

    @Override
    public String getType() {
        return "";
    }

    @Override
    public double samletDosis() {
        long antalDage = ChronoUnit.DAYS.between(getStartDen(),getSlutDen());
        return doegnDosis() * antalDage;
    }

    public Dosis[] getDoser() {
        return new Dosis[]{
                new Dosis(LocalTime.of(8,0), morgenDose),
                new Dosis(LocalTime.of(12,0), middagDose),
                new Dosis(LocalTime.of(18,0), aftenDose),
                new Dosis(LocalTime.of(22,0), natDose)
        };
    }


}




