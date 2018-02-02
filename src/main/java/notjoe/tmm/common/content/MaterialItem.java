package notjoe.tmm.common.content;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;
import notjoe.tmm.TooManyMetals;
import notjoe.tmm.api.TMaterial;
import notjoe.tmm.api.TMaterialRegistry;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static notjoe.tmm.TooManyMetals.LOGGER;

public class MaterialItem extends Item {
    private final ResourceType resourceType;

    public MaterialItem(ResourceType resourceType) {
        this.resourceType = resourceType;

        String internalName = "resource_" + resourceType.toString();

        setUnlocalizedName("tmm." + internalName);
        setRegistryName(TooManyMetals.MODID, internalName);
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void registerModels(List<TMaterial> materials) {
        for (int i = 0; i < materials.size(); i++) {
            TMaterial material = materials.get(i);
            if (ArrayUtils.contains(material.getResourceTypes(), resourceType)) {
                String customModelLocation = material.getCustomModel();

                System.out.println("Registering models");

                ModelResourceLocation modelResourceLocation = new ModelResourceLocation("minecraft:iron_ingot",
                        "inventory");

                if (customModelLocation != null) {
                    System.out.println("There is a custom model");
                    modelResourceLocation = new ModelResourceLocation(customModelLocation, "inventory");
                }

                ModelLoader.setCustomModelResourceLocation(this, i, modelResourceLocation);
            }
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        TMaterial material = TMaterialRegistry.INSTANCE.getMaterialByID(stack.getMetadata());

        if (material != null) {
            return material.getName() + " " + StringUtils.capitalize(resourceType.toString());
        } else {
            return super.getItemStackDisplayName(stack);
        }
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (tab == getCreativeTab()) {
            TMaterialRegistry.INSTANCE.forEachMaterial((id, material) -> {
                if (ArrayUtils.contains(material.getResourceTypes(), resourceType)) {
                    items.add(new ItemStack(this, 1, id));
                }
            });
        }
    }
}
