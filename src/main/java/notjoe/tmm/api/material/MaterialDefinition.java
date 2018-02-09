package notjoe.tmm.api.material;

import io.vavr.Tuple;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import io.vavr.control.Option;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import notjoe.tmm.api.resource.ResourceType;
import notjoe.tmm.common.content.BlockResourceExpression;
import notjoe.tmm.common.content.ItemResourceExpression;
import org.apache.commons.lang3.StringUtils;

/**
 * Represents a TMM Material, generally a new type of metal or gem.
 */
public class MaterialDefinition {
    private final String name;

    private final FluidProperties fluidProperties;
    private final WorldProperties worldProperties;
    private final VisualProperties visualProperties;
    private final ResourceProperties resourceProperties;

    private final Map<ResourceType, Item> itemResources;
    private final Map<ResourceType, Block> blockResources;

    public MaterialDefinition(String name, FluidProperties fluidProperties, WorldProperties worldProperties,
                              VisualProperties visualProperties, ResourceProperties resourceProperties) {
        this.name = name;
        this.fluidProperties = fluidProperties;
        this.worldProperties = worldProperties;
        this.visualProperties = visualProperties;
        this.resourceProperties = resourceProperties;

        blockResources = HashMap.ofEntries(
                resourceProperties.getAvailableResourceTypes()
                        .filter(ResourceType::isBlock)
                        .map(resourceType ->
                                Tuple.of(resourceType, createBlock(resourceType).get())));

        itemResources = HashMap.ofEntries(
                resourceProperties.getAvailableResourceTypes()
                        .filter(resourceType -> !resourceType.isBlock())
                        .map(resourceType ->
                                Tuple.of(resourceType, createItem(resourceType).get())));
    }

    public String getName() {
        return name.toLowerCase();
    }

    public String getNameCapitalized() {
        return StringUtils.capitalize(name);
    }

    public FluidProperties getFluidProperties() {
        return fluidProperties;
    }

    public WorldProperties getWorldProperties() {
        return worldProperties;
    }

    public VisualProperties getVisualProperties() {
        return visualProperties;
    }

    public ResourceProperties getResourceProperties() {
        return resourceProperties;
    }

    public Map<ResourceType, Item> getItemResources() {
        return itemResources;
    }

    public Map<ResourceType, Block> getBlockResources() {
        return blockResources;
    }

    public ItemStack createItemStack(ResourceType resourceType, int amount) {
        if (resourceType.isBlock()) {
            return new ItemStack(createBlock(resourceType).get(), 1);
        }

        return new ItemStack(createItem(resourceType).get(), 1);
    }

    public Option<Item> createItem(ResourceType resourceType) {
        if (!resourceType.isBlock()) {
            return Option.of(new ItemResourceExpression(this, resourceType));
        }

        return Option.none();
    }

    public Option<Block> createBlock(ResourceType resourceType) {
        if (resourceType.isBlock()) {
            return Option.of(new BlockResourceExpression(this, resourceType));
        }

        return Option.none();
    }
}
