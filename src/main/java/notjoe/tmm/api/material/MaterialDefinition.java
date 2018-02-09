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
 * Represents a material family defined through TMM. Contains information about everything related to the material as
 * well as methods to express it as Items, Blocks, or ItemStacks.
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

    /**
     * @return The ResourceLocation-safe (all lowercase) name of this material.
     */
    public String getName() {
        return name.toLowerCase();
    }

    /**
     * @return The display name of this material (title case).
     */
    public String getNameCapitalized() {
        return StringUtils.capitalize(name);
    }

    /**
     * @return The {@link FluidProperties} of this material.
     */
    public FluidProperties getFluidProperties() {
        return fluidProperties;
    }

    /**
     * @return The {@link WorldProperties} of this material.
     */
    public WorldProperties getWorldProperties() {
        return worldProperties;
    }

    /**
     * @return The {@link VisualProperties} of this material.
     */
    public VisualProperties getVisualProperties() {
        return visualProperties;
    }

    /**
     * @return The {@link ResourceProperties} of this material.
     */
    public ResourceProperties getResourceProperties() {
        return resourceProperties;
    }

    /**
     * @return Items by ResourceTypes of this material.
     */
    public Map<ResourceType, Item> getItemResources() {
        return itemResources;
    }

    /**
     * @return Blocks defined by ResourceTypes of this material.
     */
    public Map<ResourceType, Block> getBlockResources() {
        return blockResources;
    }

    /**
     * Creates an ItemStack of a block or item that expresses this material.
     *
     * @param resourceType Resource type to create an ItemStack of.
     * @param amount Desired count of items/blocks in the ItemStack.
     * @return ItemStack of this MaterialDefinition expressed as the given ResourceType.
     */
    public ItemStack createItemStack(ResourceType resourceType, int amount) {
        if (resourceType.isBlock()) {
            return new ItemStack(createBlock(resourceType).get(), 1);
        }

        return new ItemStack(createItem(resourceType).get(), 1);
    }

    /**
     * Creates an Item expressing this MaterialDefinition based on a given ResourceType.
     * @param resourceType ResourceType to express.
     * @return An Option of the Item if the ResourceType defines an item, otherwise none.
     */
    public Option<Item> createItem(ResourceType resourceType) {
        if (!resourceType.isBlock()) {
            return Option.of(new ItemResourceExpression(this, resourceType));
        }

        return Option.none();
    }

    /**
     * Creates Block expressing this MaterialDefinition based on a given ResourceType.
     * @param resourceType ResourceType to express.
     * @return An Option of the Block if the ResourceType defines an block, otherwise none.
     */
    public Option<Block> createBlock(ResourceType resourceType) {
        if (resourceType.isBlock()) {
            return Option.of(new BlockResourceExpression(this, resourceType));
        }

        return Option.none();
    }
}
