package arc.haldun.ik.applicationform.elements;

public enum Residence {
    RENT(0),
    POSSESION(1),
    FAMILYS(2);

    private final int i;

    Residence(int i) {
        this.i = i;
    }

    public int getId() {
        return i;
    }

    public static Residence findById(int id) {

        Residence[] residences = Residence.values();

        for (Residence r : residences) {
            if (r.getId() == id) return r;
        }

        throw new IllegalArgumentException("Geçersiz numara: " + id);
    }
}