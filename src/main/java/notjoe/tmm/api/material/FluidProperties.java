package notjoe.tmm.api.material;

public class FluidProperties {
    private final int density;
    private final int viscosity;
    private final int luminosity;
    private final int temperature;
    private final boolean bucketAvailable;

    public FluidProperties(int density, int viscosity, int luminosity, int temperature, boolean bucketAvailable) {
        this.density = density;
        this.viscosity = viscosity;
        this.luminosity = luminosity;
        this.temperature = temperature;
        this.bucketAvailable = bucketAvailable;
    }

    public int getDensity() {
        return density;
    }

    public int getViscosity() {
        return viscosity;
    }

    public int getLuminosity() {
        return luminosity;
    }

    public int getTemperature() {
        return temperature;
    }

    public boolean isBucketAvailable() {
        return bucketAvailable;
    }
}
