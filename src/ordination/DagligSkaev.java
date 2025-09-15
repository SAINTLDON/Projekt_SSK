package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class DagligSkaev extends Ordination {
    private List<Dosis> doser;

    public DagligSkaev(LocalDate startDen, LocalDate slutDen, Patient patient, Laegemiddel laegemiddel, List<Dosis> doser) {
        super(startDen, slutDen, patient, laegemiddel,0);
        this.doser = doser;
    }


    public List<Dosis> getDoser() {
        return doser;
    }

    public void opretDosis(LocalTime tid, double antal) {
        doser.add(new Dosis(tid, antal));
    }

    @Override
    public double samletDosis() {
        return doegnDosis() * antalDage();
    }

    @Override
    public double doegnDosis() {
        double dosertotal = 0;
        for (Dosis dosis : doser) {
            dosertotal += dosis.getAntal();


        }
        return dosertotal;
    }


    @Override
    public String getType() {
        return "DagligtSkaev";
    }
}
