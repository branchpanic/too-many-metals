package notjoe.tmm.common.content;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;
import notjoe.tmm.TooManyMetals;
import notjoe.tmm.api.ResourceType;
import notjoe.tmm.api.TMaterial;
import notjoe.tmm.api.TMaterialRegistry;
import notjoe.tmm.common.TabResources;
import org.apache.commons.lang3.ArrayUtils;

import java.util.List;
import java.util.Optional;

public class ItemMaterial extends Item {
    private final ResourceType resourceType;

    public ItemMaterial(ResourceType resourceType) {
        this.resourceType = resourceType;

        String internalName = "resource_" + resourceType.toString();

        setUnlocalizedName("tmm." + internalName);
        setRegistryName(TooManyMetals.MODID, internalName);

        setMaxDamage(0);
        setHasSubtypes(true);
        setCreativeTab(TabResources.CREATIVE_TAB);
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void registerModels() {
        List<TMaterial> materials = TMaterialRegistry.INSTANCE.getMaterialsByID();
        for (int i = 0; i < materials.size(); i++) {
            TMaterial material = materials.get(i);
            if (ArrayUtils.contains(material.getResourceTypes(), resourceType)) {
                ModelResourceLocation modelResourceLocation = new ModelResourceLocation(getRegistryName(),
                        "inventory");

                Optional<String> customModelLocation = material.getCustomModelLocation(resourceType);
                if (customModelLocation.isPresent()) {
                    modelResourceLocation = new ModelResourceLocation(customModelLocation.get(), "inventory");
                }

                ModelLoader.setCustomModelResourceLocation(this, i, modelResourceLocation);
            }
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        TMaterial material = TMaterialRegistry.INSTANCE.getMaterialByID(stack.getMetadata());

        if (material != null) {
            return resourceType.getFormattedDisplayName(material.getNameCapitalized());
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
