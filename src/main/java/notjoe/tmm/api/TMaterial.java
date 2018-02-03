package notjoe.tmm.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
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

    @Getter
    int fluidDensity = FluidRegistry.LAVA.getDensity();
    @Getter
    int fluidViscosity = FluidRegistry.LAVA.getViscosity();
    @Getter
    int fluidLuminosity = FluidRegistry.LAVA.getLuminosity();
    @Getter
    int fluidTemperature = FluidRegistry.LAVA.getTemperature();
    @Getter
    private ResourceType[] resourceTypes = new ResourceType[] {
            ResourceType.NUGGET, ResourceType.DUST, ResourceType.INGOT, ResourceType.BLOCK, ResourceType.ORE,
            ResourceType.PLATE, ResourceType.GEAR
    };
    @JsonProperty
    @Getter
    private Map<String, Double> alloyOf;
    @JsonProperty
    private Map<ResourceType, String> customModels;
    @Getter
    private int rgbColor;
    private String oreDictSuffix;
    @Getter
    private boolean addingCompactingRecipes = true;
    @Getter
    private boolean addingPlateRecipes = true;
    @Getter
    private boolean addingGearRecipes = true;
    @Getter
    private boolean bucketAvailable = true;

    public ItemStack createItemStack(ResourceType resourceType, int amount) {
        int meta = TMaterialRegistry.INSTANCE.getIDFromName(name);
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

    public boolean hasResourceType(ResourceType type) {
        return ArrayUtils.contains(resourceTypes, type);
    }

    public boolean hasBaseResourceType() {
        return hasResourceType(ResourceType.INGOT) || hasResourceType(ResourceType.GEM);
    }

    public boolean isAlloy() {
        return alloyOf != null;
    }

    public ResourceType getBaseResourceType() {
        if (hasResourceType(ResourceType.INGOT)) {
            return ResourceType.INGOT;
        } else if (hasResourceType(ResourceType.GEM)) {
            return ResourceType.GEM;
        }

        return null;
    }

    public Optional<String> getCustomModelFor(ResourceType type) {
        if (customModels != null && customModels.containsKey(type)) {
            return Optional.of(customModels.get(type));
        }

        return Optional.empty();
    }

    public String getName() {
        return name.toLowerCase();
    }

    public String getNameCapitalized() {
        return StringUtils.capitalize(name);
    }

    public String getOreDictName(ResourceType type) {
        return type.getOreDictPrefix() + getNameCapitalized();
    }

    public String getOreDictSuffix() {
        if (oreDictSuffix == null) {
            return getNameCapitalized();
        }

        return oreDictSuffix;
    }
}
