package notjoe.tmm.common.integration;

import cofh.api.util.ThermalExpansionHelper;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.LoaderState;
import notjoe.tmm.api.TMaterialContentFactory;
import notjoe.tmm.api.TMaterialRegistry;

public class ThermalExpansionIntegration implements Integrator {
    private static final TMaterialContentFactory contentFactory = TMaterialContentFactory.INSTANCE;

    @Override
    public String getIntegrationID() {
        return "thermalexpansion";
    }

    @Override
    public void doIntegration() {
        TMaterialRegistry.INSTANCE.forEachMaterial((id, material) -> {
            if (!material.hasBaseResourceType() || !material.isThermalExpansionIntegration()) {
                return;
            }

            ThermalExpansionHelper.addCrucibleRecipe(material.getProcessingEnergy(),
                    contentFactory.getItemStack(material.getName(), material.getBaseResourceType()),
                    new FluidStack(contentFactory.getFluid(material.getName()), 144));
        });
    }

    @Override
    public LoaderState getTargetState() {
        return LoaderState.INITIALIZATION;
    }
}
