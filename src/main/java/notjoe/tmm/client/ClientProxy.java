package notjoe.tmm.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import notjoe.tmm.api.TMaterialContentFactory;
import notjoe.tmm.common.CommonProxy;
import notjoe.tmm.common.content.BlockMaterial;
import notjoe.tmm.common.content.ItemMaterial;

public class ClientProxy extends CommonProxy {
    @Override
    public void onInit(FMLInitializationEvent event) {
        super.onInit(event);

        ItemColors itemColors = Minecraft.getMinecraft().getItemColors();
        itemColors.registerItemColorHandler(new DefaultItemColorHandler(), TMaterialContentFactory.INSTANCE.getItems().toArray(new ItemMaterial[]{}));
        itemColors.registerItemColorHandler(new DefaultItemColorHandler(), TMaterialContentFactory.INSTANCE.getBlocks().toArray(new BlockMaterial[]{}));

        BlockColors blockColors = Minecraft.getMinecraft().getBlockColors();
        blockColors.registerBlockColorHandler(new DefaultBlockColorHandler(), TMaterialContentFactory.INSTANCE.getBlocks().toArray(new BlockMaterial[]{}));
    }
}
