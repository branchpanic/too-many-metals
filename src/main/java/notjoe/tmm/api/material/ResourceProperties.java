package notjoe.tmm.api.material;

import io.vavr.collection.Map;
import io.vavr.collection.Vector;
import io.vavr.control.Option;
import notjoe.tmm.api.resource.ResourceType;

/**
 * Represents core properties of a MaterialDefinition, such as how it can be expressed or how it can be alloyed.
 */
public class ResourceProperties {
    private final Vector<ResourceType> availableResourceTypes;
    private final Option<Map<String, Double>> alloyDefinition;
    private final boolean addCraftingRecipes;
    private final float smeltingExperience;

    /**
     * Constructs new ResourceProperties from given values.
     * @param availableResourceTypes A Vector of the ResourceTypes that are valid for this material.
     * @param alloyDefinition An optional alloy definition. Keys represent fluid names, while values are ratios of the
     *                        fluids relative to an output of 1 ingot.
     * @param addCraftingRecipes Whether or not crafting recipes should be automatically added.
     * @param smeltingExperience Experience gained from smelting this material.
     */
    public ResourceProperties(Vector<ResourceType> availableResourceTypes,
                              Option<Map<String, Double>> alloyDefinition,
                              boolean addCraftingRecipes, float smeltingExperience) {
        this.availableResourceTypes = availableResourceTypes;
        this.alloyDefinition = alloyDefinition;
        this.addCraftingRecipes = addCraftingRecipes;
        this.smeltingExperience = smeltingExperience;
    }

    /**
     * @return Valid ResourceTypes for this material.
     */
    public Vector<ResourceType> getAvailableResourceTypes() {
        return availableResourceTypes;
    }

    /**
     * @return Optional alloy definition for this material (see constructor).
     */
    public Option<Map<String, Double>> getAlloyDefinition() {
        return alloyDefinition;
    }

    /**
     * @return Whether or not crafting recipes should be added automatically.
     */
    public boolean shouldAddCraftingRecipes() {
        return addCraftingRecipes;
    }

    /**
     * @return How much experience is gained from smelting an ore of this material.
     */
    public float getSmeltingExperience() {
        return smeltingExperience;
    }
}
