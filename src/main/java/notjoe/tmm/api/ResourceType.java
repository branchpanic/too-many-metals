package notjoe.tmm.api;

import net.minecraft.client.resources.I18n;
import org.apache.commons.lang3.StringUtils;

public enum ResourceType {
    NUGGET("nugget", true),
    DUST("dust", true),
    INGOT("ingot", true),
    GEM("gem", true),

    ORE("ore", false),
    BLOCK("block", false),

    PLATE("plate", true),
    GEAR("gear", true);

    private String oreDictPrefix;
    private boolean item;

    ResourceType(String oreDictPrefix, boolean item) {
        this.oreDictPrefix = oreDictPrefix;
        this.item = item;
    }

    @Override
    public String toString() {
        return StringUtils.capitalize(name().toLowerCase());
    }

    public String getOreDictPrefix() {
        return oreDictPrefix;
    }

    public String getLangFormatString() {
        return "resource.tmm." + oreDictPrefix;
    }

    public String getFormattedDisplayName(String resourceName) {
        return I18n.format(getLangFormatString(), resourceName);
    }

    public boolean isItem() {
        return item;
    }
}
