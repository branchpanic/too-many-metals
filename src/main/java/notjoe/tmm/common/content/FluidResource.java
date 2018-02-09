package notjoe.tmm.common.content;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import notjoe.tmm.api.material.FluidProperties;
import notjoe.tmm.api.material.MaterialDefinition;

public class FluidResource extends Fluid {
    private static final ResourceLocation STILL_TEXTURE = new ResourceLocation("tmm:blocks/lava_still");
    private static final ResourceLocation FLOW_TEXTURE = new ResourceLocation("tmm:blocks/lava_flow");

    private final MaterialDefinition materialDefinition;

    public FluidResource(MaterialDefinition materialDefinition) {
        super(materialDefinition.getName(), STILL_TEXTURE, FLOW_TEXTURE, materialDefinition.getVisualProperties().getColor().getRGB());
        this.materialDefinition = materialDefinition;

        FluidProperties fluidProperties = materialDefinition.getFluidProperties();
        setDensity(fluidProperties.getDensity());
        setLuminosity(fluidProperties.getLuminosity());
        setTemperature(fluidProperties.getTemperature());
        setViscosity(fluidProperties.getViscosity());

        FluidRegistry.registerFluid(this);
        if (fluidProperties.isBucketAvailable()) {
            FluidRegistry.addBucketForFluid(this);
        }
    }

    @Override
    public String getLocalizedName(FluidStack stack) {
        return I18n.format("tmm.fluid", materialDefinition.getNameCapitalized());
    }
}
