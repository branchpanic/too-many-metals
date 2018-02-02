package notjoe.tmm.client;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import notjoe.tmm.api.TMaterial;
import notjoe.tmm.api.TMaterialRegistry;
import notjoe.tmm.common.content.ItemMaterial;

public class DefaultItemColorHandler implements IItemColor {
    @Override
    public int colorMultiplier(ItemStack stack, int tintIndex) {
        if (!(stack.getItem() instanceof ItemMaterial)) {
            return 0;
        }

        int id = stack.getMetadata();
        TMaterial material = TMaterialRegistry.INSTANCE.getMaterialByID(id);

        if (material != null && material.getCustomModelLocation() == null) {
            return material.getRgbColor();
        } else {
            return 0;
        }
    }
}
