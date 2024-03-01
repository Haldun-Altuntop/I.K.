package arc.haldun.ik.applicationform.elements;

public enum SocialAssurance {
    SSK(0),
    BAGKUR(1),
    EMEKLI_SANDIGI(2),
    YESIL_KART(3);

    private final int id;

    SocialAssurance(int id) {
        this.id = id;
    }

    public static SocialAssurance findById(int id) {

        SocialAssurance[] socialAssurances = SocialAssurance.values();

        for (SocialAssurance socialAssurance : socialAssurances) {
            if (socialAssurance.id == id) return socialAssurance;
        }
        throw new IllegalArgumentException("Geçersiz id: " + id);
    }
}