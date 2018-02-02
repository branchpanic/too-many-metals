package notjoe.tmm.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class TabResources extends CreativeTabs {
    public static final TabResources CREATIVE_TAB = new TabResources();

    public TabResources() {
        super("tmm.resources");
    }

    @Override
    public boolean hasSearchBar() {
        return true;
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(Items.DIAMOND);
    }
}
