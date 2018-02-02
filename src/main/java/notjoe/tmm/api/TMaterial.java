package notjoe.tmm.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import notjoe.tmm.common.content.ResourceType;

import java.awt.*;

/**
 * Represents an immutable TMM Material, generally a new type of metal or gem.
 */
public class TMaterial {
    private String name;
    private int rgbColor;

    private ResourceType[] resourceTypes = new ResourceType[] {
            ResourceType.NUGGET, ResourceType.INGOT, ResourceType.BLOCK
    };

    private String customModel;

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

    public String getCustomModel() {
        return customModel;
    }
}
