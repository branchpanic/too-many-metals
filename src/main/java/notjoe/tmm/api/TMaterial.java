package notjoe.tmm.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.minecraft.item.ItemStack;
import notjoe.tmm.common.content.ModContent;
import notjoe.tmm.common.content.ResourceType;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Map;

/**
 * Represents a TMM Material, generally a new type of metal or gem.
 */
public class TMaterial {
    private String name;
    private int rgbColor;
    private ResourceType[] resourceTypes = new ResourceType[] {
            ResourceType.NUGGET, ResourceType.DUST, ResourceType.INGOT, ResourceType.BLOCK, ResourceType.ORE
    };
    private String customModelLocation;
    private String oreDictSuffix;

    @JsonProperty
    private Map<String, Integer> alloyDefinitions;

    public ItemStack createItemStack(ResourceType resourceType) {
        int meta = TMaterialRegistry.INSTANCE.nameToID(name);
        if (ArrayUtils.contains(resourceTypes, resourceType)) {
            switch (resourceType) {
                case NUGGET:
                    return new ItemStack(ModContent.RESOURCE_NUGGET, 1, meta);
                case DUST:
                    return new ItemStack(ModContent.RESOURCE_DUST, 1, meta);
                case INGOT:
                    return new ItemStack(ModContent.RESOURCE_INGOT, 1, meta);
                case GEM:
                    return new ItemStack(ModContent.RESOURCE_GEM, 1, meta);
                // TODO: Implement block resources
                case ORE:
                case BLOCK:
                default:
                    break;
            }
        }

        return ItemStack.EMPTY;
    }

    /**
     * @return Name of this TMaterial.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Color (RGB Hex) of this TMaterial.
     */
    public int getRgbColor() {
        return rgbColor;
    }

    public ResourceType[] getResourceTypes() {
        return resourceTypes;
    }

    public String getCustomModelLocation() {
        return customModelLocation;
    }
}
