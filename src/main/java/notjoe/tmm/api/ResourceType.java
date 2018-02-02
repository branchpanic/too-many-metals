package notjoe.tmm.api;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import notjoe.tmm.common.content.ModContent;
import org.apache.commons.lang3.StringUtils;

public enum ResourceType {
    NUGGET("nugget"),
    DUST("dust"),
    INGOT("ingot"),
    GEM("gem"),

    ORE("ore"),
    BLOCK("block"),

    PLATE("plate"),
    GEAR("gear");

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

    public String getLangFormatString() {
        return "resource.tmm." + oreDictPrefix;
    }

    public String getFormattedDisplayName(String resourceName) {
        return I18n.format(getLangFormatString(), resourceName);
    }

    public static ResourceType getTypeFromItem(ItemStack stack) {
        Item item = stack.getItem();
        if (item == ModContent.RESOURCE_NUGGET) {
            return NUGGET;
        } else if (item == ModContent.RESOURCE_DUST) {
            return DUST;
        } else if (item == ModContent.RESOURCE_INGOT) {
            return INGOT;
        } else if (item == ModContent.RESOURCE_GEM) {
            return GEM;
        } else if (item == ModContent.RESOURCE_PLATE) {
            return PLATE;
        } else if (item == ModContent.RESOURCE_GEAR) {
            return GEAR;
        }

        // TODO: Blocks

        return null;
    }
}
