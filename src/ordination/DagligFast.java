package ordination;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DagligFast extends Ordination {  // RETTET: Skal arve fra Ordination

    private double morgenDose;
    private double middagDose;
    private double aftenDose;
    private double natDose;


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


    public double getMorgenDose() {
        return morgenDose;
    }

    public double getMiddagDose() {
        return middagDose;
    }

    public double getAftenDose() {
        return aftenDose;
    }

    public double getNatDose() {
        return natDose;
    }

    public double[] getDoser() {
        return new double[]{morgenDose,middagDose,aftenDose,natDose};

    }

}


