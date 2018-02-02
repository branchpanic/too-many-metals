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
import notjoe.tmm.common.content.MaterialItem;
import notjoe.tmm.common.content.ModContent;
import notjoe.tmm.common.content.ResourceType;

@Mod.EventBusSubscriber(modid = TooManyMetals.MODID)
public class RegistrationEvents {
    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> blockRegistryEvent) {
        TooManyMetals.LOGGER.info("Registering blocks");
    }

    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> itemRegistryEvent) {
        itemRegistryEvent.getRegistry().registerAll(
                new MaterialItem(ResourceType.NUGGET),
                new MaterialItem(ResourceType.INGOT),
                new MaterialItem(ResourceType.GEM)
        );
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerModels(ModelRegistryEvent event) {
        ((MaterialItem) ModContent.RESOURCE_NUGGET).registerModels(TMaterialRegistry.INSTANCE.getMaterialsByID());
        ((MaterialItem) ModContent.RESOURCE_INGOT).registerModels(TMaterialRegistry.INSTANCE.getMaterialsByID());
        ((MaterialItem) ModContent.RESOURCE_GEM).registerModels(TMaterialRegistry.INSTANCE.getMaterialsByID());
    }
}
