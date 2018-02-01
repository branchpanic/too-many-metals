package notjoe.tmm.api;

/**
 * Represents an immutable TMM Material, generally a new type of metal or gem.
 */
public class TMaterial {
    private final String name;
    private final int rgbColor;

    /**
     * Constructs this TMaterial from given values.
     */
    public TMaterial(String name, int rgbColor) {
        this.name = name;
        this.rgbColor = rgbColor;
    }

    /**
     * @return Name of this TMaterial.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Color (RGB Hex) of this TMaterial.
     */
    public int getRgbColor() {
        return rgbColor;
    }
}
