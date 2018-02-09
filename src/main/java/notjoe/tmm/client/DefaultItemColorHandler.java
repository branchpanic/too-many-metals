package notjoe.tmm.client;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import notjoe.tmm.api.material.VisualProperties;
import notjoe.tmm.common.content.ResourceExpression;

public class DefaultItemColorHandler implements IItemColor {
    @Override
    public int colorMultiplier(ItemStack stack, int tintIndex) {
        if (tintIndex > 0) {
            return 0xFFFFFF;
        }

        Item stackItem = stack.getItem();
        if (stackItem instanceof ResourceExpression) {
            ResourceExpression resourceExpression = (ResourceExpression) stackItem;
            VisualProperties properties = resourceExpression.getMaterialDefinition().getVisualProperties();
            if (!properties.getCustomModelLocationForResource(resourceExpression.getResourceType()).isDefined()) {
                return properties.getColor().getRGB();
            }
        }

        return 0xFFFFFF;
    }
}
