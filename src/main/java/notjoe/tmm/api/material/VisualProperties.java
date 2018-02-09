package notjoe.tmm.api.material;

import io.vavr.collection.Map;
import io.vavr.control.Option;
import notjoe.tmm.api.resource.ResourceType;

import java.awt.*;

public class VisualProperties {
    private final Color color;
    private final Option<Map<ResourceType, String>> customModelLocations;

    public VisualProperties(Color color, Option<Map<ResourceType, String>> customModelLocations) {
        this.color = color;
        this.customModelLocations = customModelLocations;
    }

    public Color getColor() {
        return color;
    }

    public Option<Map<ResourceType, String>> getCustomModelLocations() {
        return customModelLocations;
    }

    public Option<String> getCustomModelLocationForResource(ResourceType resourceType) {
        if (!customModelLocations.isDefined()) {
            return Option.none();
        }

        Map<ResourceType, String> customModelMap = getCustomModelLocations().get();
        return customModelMap.get(resourceType);
    }
}
