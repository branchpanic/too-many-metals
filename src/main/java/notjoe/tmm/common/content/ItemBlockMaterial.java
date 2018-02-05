package notjoe.tmm.common.content;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockMaterial extends ItemBlock {
    public ItemBlockMaterial(Block block) {
        super(block);
        setRegistryName(block.getRegistryName());
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return block.getLocalizedName();
    }
}
