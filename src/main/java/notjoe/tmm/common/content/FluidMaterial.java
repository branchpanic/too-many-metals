package notjoe.tmm.common.content;

import lombok.Getter;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import notjoe.tmm.api.TMaterial;

public class FluidMaterial extends Fluid {
    public static final ResourceLocation STILL_TEXTURE = new ResourceLocation("tmm:blocks/lava_still");
    public static final ResourceLocation FLOW_TEXTURE = new ResourceLocation("tmm:blocks/lava_flow");

    @Getter
    private final TMaterial material;

    public FluidMaterial(TMaterial material) {
        super(material.getName(), STILL_TEXTURE, FLOW_TEXTURE, material.getColor());
        this.material = material;
        setDensity(material.getFluidDensity());
        setLuminosity(material.getFluidLuminosity());
        setTemperature(material.getFluidTemperature());
        setViscosity(material.getFluidViscosity());

        FluidRegistry.registerFluid(this);
        if (material.isBucketAvailable()) {
            FluidRegistry.addBucketForFluid(this);
        }
    }

    @Override
    public String getLocalizedName(FluidStack stack) {
        return I18n.format("tmm.fluid", material.getNameCapitalized());
    }
}
