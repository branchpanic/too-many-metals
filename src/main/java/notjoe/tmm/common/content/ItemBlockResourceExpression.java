package notjoe.tmm.common.content;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import notjoe.tmm.api.material.MaterialDefinition;
import notjoe.tmm.api.resource.ResourceType;

public class ItemBlockResourceExpression extends ItemBlock implements ResourceExpression {
    public ItemBlockResourceExpression(BlockResourceExpression block) {
        super(block);
        setRegistryName(block.getRegistryName());
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return block.getLocalizedName();
    }

    @Override
    public MaterialDefinition getMaterialDefinition() {
        return ((BlockResourceExpression) block).getMaterialDefinition();
    }

    @Override
    public ResourceType getResourceType() {
        return ((BlockResourceExpression) block).getResourceType();
    }
}
