package notjoe.tmm.api.material;

import io.vavr.collection.Map;
import io.vavr.control.Option;
import notjoe.tmm.api.resource.ResourceType;

import java.awt.*;

/**
 * Represents visual, primarily client-side properties of a MaterialDefinition.
 */
public class VisualProperties {
    private final Color color;
    private final Option<Map<ResourceType, String>> customModelLocations;

    /**
     * Constructs new VisualProperties from given values.
     * @param color Color of the material. Does not apply to expressions with custom models.
     * @param customModelLocations Optional map of resource expressions to model locations.
     */
    public VisualProperties(Color color, Option<Map<ResourceType, String>> customModelLocations) {
        this.color = color;
        this.customModelLocations = customModelLocations;
    }

    /**
     * @return Color of this material.
     */
    public Color getColor() {
        return color;
    }

    /**
     * @return Optional map of custom model locations (see constructor).
     */
    public Option<Map<ResourceType, String>> getCustomModelLocations() {
        return customModelLocations;
    }

    /**
     * Gets the custom model location for a resource, if it exists.
     * @param resourceType ResourceType to get a model for.
     * @return Custom model location as a String or none if it is not defined.
     */
    public Option<String> getCustomModelLocationForResource(ResourceType resourceType) {
        if (!customModelLocations.isDefined()) {
            return Option.none();
        }

        Map<ResourceType, String> customModelMap = getCustomModelLocations().get();
        return customModelMap.get(resourceType);
    }
}
