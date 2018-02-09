package notjoe.tmm.api.resource;

/**
 * Default, standard resources provided by TMM. Indirect expressions defined here will have crafting recipes added
 * automatically due to common usage.
 */
public enum Resource implements ResourceType {
    NUGGET(false, true, 1),
    INGOT(false, true, 9),
    GEM(false, true, 9),
    BLOCK(true, true, 81),

    ORE(true, false, 2),
    DUST(false, false, 1),
    PLATE(false, false, 4),
    GEAR(false, false, 4);

    private final boolean block;
    private final boolean directlyUsable;
    private final int value;

    Resource(boolean block, boolean directlyUsable, int value) {
        this.block = block;
        this.directlyUsable = directlyUsable;
        this.value = value;
    }

    @Override
    public boolean isBlock() {
        return block;
    }

    @Override
    public boolean isDirectlyUsable() {
        return directlyUsable;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getOreDictPrefix() {
        return name().toLowerCase();
    }
}
