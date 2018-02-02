package notjoe.tmm.common;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import notjoe.tmm.TooManyMetals;
import notjoe.tmm.api.ResourceType;
import notjoe.tmm.api.TMaterialRegistry;
import notjoe.tmm.common.content.ItemMaterial;
import notjoe.tmm.common.content.ModContent;

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
                new ItemMaterial(ResourceType.GEM),
                new ItemMaterial(ResourceType.PLATE),
                new ItemMaterial(ResourceType.GEAR)
        );
        LOGGER.info("Item registering finished.");
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        LOGGER.info("Recipe registering started.");
        TMaterialRegistry.INSTANCE.registerVanillaRecipes(event.getRegistry());
        LOGGER.info("Recipe registering finished.");
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerModels(ModelRegistryEvent event) {
        ModContent.RESOURCE_NUGGET.registerModels();
        ModContent.RESOURCE_DUST.registerModels();
        ModContent.RESOURCE_INGOT.registerModels();
        ModContent.RESOURCE_GEM.registerModels();
        ModContent.RESOURCE_PLATE.registerModels();
        ModContent.RESOURCE_GEAR.registerModels();
    }
}
