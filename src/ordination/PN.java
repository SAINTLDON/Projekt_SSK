package ordination;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class PN extends Ordination {

    private double antal;
    private LocalDate startdato;
    private LocalDate slutdato;
    private List<LocalDate> givetDatoer = new ArrayList<>();
    private Laegemiddel laegemiddel;

    public PN(LocalDate startDen, LocalDate slutDen, Patient patient, Laegemiddel laegemiddel, double antal) {
        super(startDen,slutDen,patient,laegemiddel,antal);
    }

    @Override
    public double samletDosis() {
        return givetDatoer.size()*antal;
    }

    @Override
    public double doegnDosis() {
        if (givetDatoer.isEmpty()) return 0.0;

        LocalDate førsteDato = givetDatoer.get(0);
        LocalDate sidsteDato = givetDatoer.get(0);

        for (LocalDate dato : givetDatoer) {
            if (dato.isBefore(førsteDato)) førsteDato = dato;
            if (dato.isAfter(sidsteDato)) sidsteDato = dato;
        }

        long dage = ChronoUnit.DAYS.between(førsteDato, sidsteDato) + 1; // +1 for inkl. første og sidste dag
        return givetDatoer.size() * antal / dage;
    }
    public boolean givDosis(LocalDate givesDen) {
        if (!givesDen.isBefore(getStartDen()) && !givesDen.isAfter(getSlutDen())) {
            givetDatoer.add(givesDen);
            return true;
        }
        return false;
    }

    @Override
    public String getType() {
        return "";
    }
    public int getAntalGangeGivet() {
        return givetDatoer.size();
    }

    public double getAntalEnheder() {
        return antal;
    }

    public List<LocalDate> getGivetDatoer() {
        return new ArrayList<>(givetDatoer);
    }
}



