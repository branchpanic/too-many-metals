package notjoe.tmm.common.integration;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import notjoe.tmm.api.TMaterialRegistry;

import java.util.Map;

public class TinkersIntegration {
    public static void sendSmelteryDefinitions() {
        TMaterialRegistry.INSTANCE.forEachMaterial((id, material) -> {
            NBTTagCompound compound = new NBTTagCompound();
            compound.setString("fluid", material.getName());
            compound.setString("ore", material.getOreDictSuffix());
            compound.setBoolean("toolforge", true);

            FMLInterModComms.sendMessage("tconstruct", "integrateSmeltery", compound);

            if (material.isAlloy()) {
                NBTTagList tagList = new NBTTagList();

                NBTTagCompound outputFluid = new NBTTagCompound();
                outputFluid.setString("FluidName", material.getName());
                outputFluid.setInteger("Amount", 144);
                tagList.appendTag(outputFluid);

                Map<String, Double> alloyDefinition = material.getAlloyOf();

                for (String fluidName : alloyDefinition.keySet()) {
                    NBTTagCompound component = new NBTTagCompound();
                    component.setString("FluidName", fluidName);
                    component.setInteger("Amount", (int) (144 * alloyDefinition.get(fluidName)));
                    tagList.appendTag(outputFluid);
                }

                NBTTagCompound alloyMessage = new NBTTagCompound();
                alloyMessage.setTag("alloy", tagList);

                FMLInterModComms.sendMessage("tconstruct", "alloy", alloyMessage);
            }
        });
    }
}
