package notjoe.tmm.common.content;

import io.vavr.control.Option;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import notjoe.tmm.TooManyMetals;
import notjoe.tmm.api.material.MaterialDefinition;
import notjoe.tmm.api.resource.ResourceType;
import notjoe.tmm.common.TabResources;
import org.jetbrains.annotations.NotNull;

public class ItemResourceExpression extends Item implements ResourceExpression {
    private final ResourceType resourceType;
    private final MaterialDefinition materialDefinition;
    private final String internalName;

    public ItemResourceExpression(MaterialDefinition materialDefinition, ResourceType resourceType) {
        this.resourceType = resourceType;
        this.materialDefinition = materialDefinition;
        internalName = materialDefinition.getName() + "_" + resourceType.toString();

        setUnlocalizedName("tmm." + internalName);
        setRegistryName(TooManyMetals.MODID, internalName);

        setMaxDamage(0);
        setCreativeTab(TabResources.CREATIVE_TAB);
    }

    @Override
    public MaterialDefinition getMaterialDefinition() {
        return materialDefinition;
    }

    @Override
    public ResourceType getResourceType() {
        return resourceType;
    }

    @NotNull
    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return I18n.format(getResourceType().getTranslationKey(), getMaterialDefinition().getNameCapitalized());
    }

    public ModelResourceLocation getModelResourceLocation() {
        Option<String> customModel = getMaterialDefinition().getVisualProperties().getCustomModelLocationForResource(getResourceType());

        if (customModel.isDefined()) {
            return new ModelResourceLocation(customModel.get(), "inventory");
        } else {
            return new ModelResourceLocation(new ResourceLocation(TooManyMetals.MODID, internalName), "inventory");
        }
    }
}
