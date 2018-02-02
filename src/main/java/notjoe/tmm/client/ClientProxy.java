package notjoe.tmm.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import notjoe.tmm.common.CommonProxy;
import notjoe.tmm.common.content.ModContent;

public class ClientProxy extends CommonProxy {
    @Override
    public void onInit(FMLInitializationEvent event) {
        super.onInit(event);

        ItemColors itemColors = Minecraft.getMinecraft().getItemColors();
        itemColors.registerItemColorHandler(new DefaultItemColorHandler(), ModContent.RESOURCE_NUGGET,
                ModContent.RESOURCE_INGOT, ModContent.RESOURCE_GEM, ModContent.RESOURCE_DUST,
                ModContent.RESOURCE_GEAR, ModContent.RESOURCE_PLATE);
    }
}
