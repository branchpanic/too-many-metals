package notjoe.tmm.common.integration;

import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.Mod;
import notjoe.tmm.api.TMaterialContentFactory;
import notjoe.tmm.api.TMaterialRegistry;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.tools.TinkerMaterials;

@Mod.EventBusSubscriber
public class TinkersIntegration implements Integrator {

    private void sendSmelteryDefinitions() {

        TMaterialRegistry.INSTANCE.forEachMaterial((id, material) -> {
            if (!material.hasBaseResourceType()) {
                return;
            }

            if (material.isTinkersIntegration()) {
                Material tinkersMaterial = new Material(material.getName(), material.getColorInt());
                TinkerMaterials.materials.add(tinkersMaterial);
                tinkersMaterial.setCraftable(material.isCraftableTinkersPart());
                tinkersMaterial.setCastable(!material.isCraftableTinkersPart());
                tinkersMaterial.setRepresentativeItem(TMaterialContentFactory.INSTANCE.getItemStack(material.getName(), material.getBaseResourceType()));
                TinkerRegistry.addMaterialStats(tinkersMaterial,
                        new HeadMaterialStats(
                                material.getPartDurability(),
                                material.getMiningSpeed(),
                                material.getAttackDamage(),
                                material.getToolHarvestLevel()
                        ),

                        new HandleMaterialStats(
                                material.getHandleMultiplier(),
                                material.getHandleBonus()
                        ),

                        new ExtraMaterialStats(
                                material.getHandleBonus()
                        ),

                        new BowMaterialStats(
                                material.getBowDrawspeed(),
                                material.getBowRange(),
                                material.getBowBonusDamage()
                        ));

                TinkerRegistry.integrate(tinkersMaterial, TMaterialContentFactory.INSTANCE.getFluid(material.getName()), material.getOreDictSuffix());
            } else {
                TinkerRegistry.integrate(TMaterialContentFactory.INSTANCE.getFluid(material.getName()), material.getOreDictSuffix());
            }

        });
    }

    @Override
    public String getIntegrationID() {
        return "tconstruct";
    }

    @Override
    public void doIntegration() {
        sendSmelteryDefinitions();
    }

    @Override
    public LoaderState getTargetState() {
        return LoaderState.PREINITIALIZATION;
    }
}
