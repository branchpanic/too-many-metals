package notjoe.tmm.api.resource;

import notjoe.tmm.TooManyMetals;

/**
 * Represents a physical form of a Material.
 */
public interface ResourceType {
    /**
     * @return Whether or not this ResourceType represents a block.
     */
    boolean isBlock();

    /**
     * A "direct form" of a material is a form that has not been processed and is directly usable. An example of a
     * directly usable form of iron would be an ingot, while a plate or ore would not be directly usable.
     * <p>
     * Resources which are directly usable will automatically have crafting recipes generated for them. The only
     * indirect resources that will automatically get resources are defined in {@link Resource}.
     *
     * @return Whether or not this ResourceType is a direct form of the material it represents.
     */
    boolean isDirectlyUsable();

    /**
     * @return How many units of the material are represented by this resource (nugget = 1).
     */
    int getValue();

    /**
     * @return The Ore Dictionary prefix of this resource, i.e. "ingot" or "gem".
     */
    String getOreDictPrefix();

    /**
     * @return The I18n translation key for this resource, used when localizing display names. Ideally should be
     * formattable with a material name.
     */
    default String getTranslationKey() {
        return "resource." + TooManyMetals.MODID + "." + getOreDictPrefix();
    }
}
