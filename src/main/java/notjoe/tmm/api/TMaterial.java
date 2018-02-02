package notjoe.tmm.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.minecraft.item.ItemStack;
import notjoe.tmm.common.content.ModContent;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Optional;

/**
 * Represents a TMM Material, generally a new type of metal or gem.
 */
public class TMaterial {
    private String name;
    private int rgbColor;
    private ResourceType[] resourceTypes = new ResourceType[] {
            ResourceType.NUGGET, ResourceType.DUST, ResourceType.INGOT, ResourceType.BLOCK, ResourceType.ORE,
            ResourceType.PLATE, ResourceType.GEAR
    };
    private String oreDictSuffix;

    @JsonProperty
    private Map<String, Integer> alloyDefinitions;

    @JsonProperty
    private Map<ResourceType, String> customModelLocations;

    private boolean registerCompactingRecipes = true;
    private boolean registerPlateRecipe = true;
    private boolean registerGearRecipe = true;

    public ItemStack createItemStack(ResourceType resourceType, int amount) {
        int meta = TMaterialRegistry.INSTANCE.nameToID(name);
        if (ArrayUtils.contains(resourceTypes, resourceType)) {
            switch (resourceType) {
                case NUGGET:
                    return new ItemStack(ModContent.RESOURCE_NUGGET, amount, meta);
                case DUST:
                    return new ItemStack(ModContent.RESOURCE_DUST, amount, meta);
                case INGOT:
                    return new ItemStack(ModContent.RESOURCE_INGOT, amount, meta);
                case GEM:
                    return new ItemStack(ModContent.RESOURCE_GEM, amount, meta);
                case GEAR:
                    return new ItemStack(ModContent.RESOURCE_GEAR, amount, meta);
                case PLATE:
                    return new ItemStack(ModContent.RESOURCE_PLATE, amount, meta);
                // TODO: Implement block resources
                case ORE:
                case BLOCK:
                default:
                    break;
            }
        }

        return ItemStack.EMPTY;
    }

    public ItemStack createItemStack(ResourceType resourceType) {
        return createItemStack(resourceType, 1);
    }

    /**
     * @return Name of this TMaterial.
     */
    public String getName() {
        return name.toLowerCase();
    }

    public String getNameCapitalized() {
        return StringUtils.capitalize(name);
    }

    public boolean hasResourceType(ResourceType type) {
        return ArrayUtils.contains(resourceTypes, type);
    }

    public String getOreDictName(ResourceType type) {
        return type.getOreDictPrefix() + getNameCapitalized();
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

    public Optional<String> getCustomModelLocation(ResourceType type) {
        if (customModelLocations != null && customModelLocations.containsKey(type)) {
            return Optional.of(customModelLocations.get(type));
        }

        return Optional.empty();
    }

    public boolean isRegisterCompactingRecipes() {
        return registerCompactingRecipes;
    }

    public boolean isRegisterPlateRecipe() {
        return registerPlateRecipe;
    }

    public boolean isRegisterGearRecipe() {
        return registerGearRecipe;
    }
}
