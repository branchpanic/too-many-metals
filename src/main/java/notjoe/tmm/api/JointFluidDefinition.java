package notjoe.tmm.api;

import lombok.Getter;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import slimeknights.tconstruct.library.fluid.FluidColored;

public class JointFluidDefinition {
    @Getter
    private TMaterial material;

    @Getter
    private Fluid fluid;

    @Getter
    @Deprecated // Not yet implemented
    private Block block;

    public JointFluidDefinition(TMaterial material) {
        this.material = material;
        this.fluid = new FluidColored(material.getName(), material.getRgbColor()) {
            @Override
            public String getLocalizedName(FluidStack stack) {
                return I18n.format("tmm.fluid", material.getNameCapitalized());
            }
        };

        fluid.setDensity(material.getFluidDensity())
                .setViscosity(material.getFluidViscosity())
                .setLuminosity(material.getFluidLuminosity())
                .setTemperature(material.getFluidTemperature());

        FluidRegistry.registerFluid(fluid);
        if (material.isBucketAvailable()) {
            FluidRegistry.addBucketForFluid(fluid);
        }
    }
}
