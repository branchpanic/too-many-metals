package notjoe.tmm.client;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import notjoe.tmm.api.TMaterial;
import notjoe.tmm.common.content.BlockMaterial;
import notjoe.tmm.common.content.ItemBlockMaterial;
import notjoe.tmm.common.content.ItemMaterial;

public class DefaultItemColorHandler implements IItemColor {
    @Override
    public int colorMultiplier(ItemStack stack, int tintIndex) {
        if (tintIndex > 0) {
            return 0xFFFFFF;
        }

        Item stackItem = stack.getItem();
        if (stackItem instanceof ItemMaterial) {
            return getItemColor((ItemMaterial) stackItem);
        } else if (stackItem instanceof ItemBlockMaterial) {
            return getBlockColor((ItemBlockMaterial) stackItem);
        }

        return 0xFFFFFF;
    }

    public int getItemColor(ItemMaterial item) {
        TMaterial material = item.getTMaterial();
        if (material != null && !material.getCustomModelFor(item.getResourceType()).isPresent()) {
            return material.getRgbColor();
        } else {
            return 0xFFFFFF;
        }
    }

    public int getBlockColor(ItemBlockMaterial item) {
        BlockMaterial blockMaterial = (BlockMaterial) item.getBlock();
        TMaterial material = blockMaterial.getTMaterial();
        if (material != null && !material.getCustomModelFor(blockMaterial.getResourceType()).isPresent()) {
            return material.getRgbColor();
        } else {
            return 0xFFFFFF;
        }
    }
}
