package notjoe.tmm.api.material;

/**
 * Represents properties of a MaterialDefinition's fluid.
 */
public class FluidProperties {
    private final int density;
    private final int viscosity;
    private final int luminosity;
    private final int temperature;
    private final boolean bucketAvailable;

    /**
     * Constructs new FluidProperties from given values. Fluid blocks are not created by TMM.
     * @param density Fluid density.
     * @param viscosity Fluid viscosity.
     * @param luminosity Fluid luminosity.
     * @param temperature Fluid temperature.
     * @param bucketAvailable Whether or not a bucket should be available for this fluid.
     */
    public FluidProperties(int density, int viscosity, int luminosity, int temperature, boolean bucketAvailable) {
        this.density = density;
        this.viscosity = viscosity;
        this.luminosity = luminosity;
        this.temperature = temperature;
        this.bucketAvailable = bucketAvailable;
    }

    /**
     * @return Fluid density.
     */
    public int getDensity() {
        return density;
    }

    /**
     * @return Fluid viscosity.
     */
    public int getViscosity() {
        return viscosity;
    }

    /**
     * @return Fluid luminosity.
     */
    public int getLuminosity() {
        return luminosity;
    }

    /**
     * @return Fluid temperature.
     */
    public int getTemperature() {
        return temperature;
    }

    /**
     * @return Whether or not a Universal Bucket should be made available for this fluid.
     */
    public boolean isBucketAvailable() {
        return bucketAvailable;
    }
}
