package notjoe.tmm.api.material;

import io.vavr.collection.Map;
import io.vavr.collection.Vector;
import io.vavr.control.Option;
import notjoe.tmm.api.resource.ResourceType;

public class ResourceProperties {
    private final Vector<ResourceType> availableResourceTypes;
    private final Option<Map<String, Double>> alloyDefinition;
    private final boolean addCraftingRecipes;
    private final float smeltingExperience;

    public ResourceProperties(Vector<ResourceType> availableResourceTypes,
                              Option<Map<String, Double>> alloyDefinition,
                              boolean addCraftingRecipes, float smeltingExperience) {
        this.availableResourceTypes = availableResourceTypes;
        this.alloyDefinition = alloyDefinition;
        this.addCraftingRecipes = addCraftingRecipes;
        this.smeltingExperience = smeltingExperience;
    }

    public Vector<ResourceType> getAvailableResourceTypes() {
        return availableResourceTypes;
    }

    public Option<Map<String, Double>> getAlloyDefinition() {
        return alloyDefinition;
    }

    public boolean shouldAddCraftingRecipes() {
        return addCraftingRecipes;
    }

    public float getSmeltingExperience() {
        return smeltingExperience;
    }
}
