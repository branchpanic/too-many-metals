package notjoe.tmm.common.content;

public enum ResourceType {
    NUGGET(1),
    INGOT(9),
    GEM(9),
    BLOCK(81);

    public final int amount;

    ResourceType(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
