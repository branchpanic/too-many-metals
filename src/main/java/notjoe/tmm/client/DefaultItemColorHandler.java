package notjoe.tmm.client;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import notjoe.tmm.api.ResourceType;
import notjoe.tmm.api.TMaterial;
import notjoe.tmm.api.TMaterialRegistry;
import notjoe.tmm.common.content.ItemMaterial;

public class DefaultItemColorHandler implements IItemColor {
    @Override
    public int colorMultiplier(ItemStack stack, int tintIndex) {
        if (!(stack.getItem() instanceof ItemMaterial) || tintIndex > 0) {
            return 0xFFFFFF;
        }

        int id = stack.getMetadata();
        TMaterial material = TMaterialRegistry.INSTANCE.getMaterialByID(id);

        if (material != null && !material.getCustomModelLocation(ResourceType.getTypeFromItem(stack)).isPresent()) {
            return material.getRgbColor();
        } else {
            return 0xFFFFFF;
        }
    }
}
