package notjoe.tmm.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import net.minecraftforge.fluids.FluidRegistry;
import notjoe.tmm.TooManyMetals;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
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

    private String rgbColor;
    private String oreDictSuffix;

    @Getter
    private boolean addingCompactingRecipes = true;
    @Getter
    private boolean addingPlateRecipes = true;
    @Getter
    private boolean addingGearRecipes = true;
    @Getter
    private boolean bucketAvailable = true;

    @Getter
    private int oreMiningLevel = 1;
    @Getter
    private float hardness;
    @Getter
    private float resistance;

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

    public int getRgbColor() {
        try {
            return new Color(Integer.parseUnsignedInt(rgbColor.substring(Math.max(0, rgbColor.length() - 6)), 16)).getRGB();
        } catch (Exception e) {
            TooManyMetals.LOGGER.warn("Invalid color format for " + getName() + "!");
            return 0;
        }
    }
}
