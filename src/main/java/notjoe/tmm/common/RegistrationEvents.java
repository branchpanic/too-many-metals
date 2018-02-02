package notjoe.tmm.common;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import notjoe.tmm.TooManyMetals;
import notjoe.tmm.api.TMaterialRegistry;
import notjoe.tmm.common.content.*;

import static notjoe.tmm.TooManyMetals.LOGGER;

@Mod.EventBusSubscriber(modid = TooManyMetals.MODID)
public class RegistrationEvents {
    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> blockRegistryEvent) {
        LOGGER.info("Block registering started.");
        blockRegistryEvent.getRegistry().registerAll(

        );
        LOGGER.info("Block registering finished.");
    }

    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> itemRegistryEvent) {
        LOGGER.info("Item registering started.");
        itemRegistryEvent.getRegistry().registerAll(
                new ItemMaterial(ResourceType.NUGGET),
                new ItemMaterial(ResourceType.DUST),
                new ItemMaterial(ResourceType.INGOT),
                new ItemMaterial(ResourceType.GEM)
        );
        LOGGER.info("Item registering finished.");
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerModels(ModelRegistryEvent event) {
        ((ItemMaterial) ModContent.RESOURCE_NUGGET).registerModels(TMaterialRegistry.INSTANCE.getMaterialsByID());
        ((ItemMaterial) ModContent.RESOURCE_DUST).registerModels(TMaterialRegistry.INSTANCE.getMaterialsByID());
        ((ItemMaterial) ModContent.RESOURCE_INGOT).registerModels(TMaterialRegistry.INSTANCE.getMaterialsByID());
        ((ItemMaterial) ModContent.RESOURCE_GEM).registerModels(TMaterialRegistry.INSTANCE.getMaterialsByID());
    }
}
