package notjoe.tmm.common.content;

import org.apache.commons.lang3.StringUtils;

public enum ResourceType {
    NUGGET("nugget"),
    DUST("dust"),
    INGOT("ingot"),
    GEM("gem"),

    ORE("ore"),
    BLOCK("block");

    private String oreDictPrefix;
    ResourceType(String oreDictPrefix) {
        this.oreDictPrefix = oreDictPrefix;
    }

    @Override
    public String toString() {
        return StringUtils.capitalize(name().toLowerCase());
    }

    public String getOreDictPrefix() {
        return oreDictPrefix;
    }
}
