package notjoe.tmm.common;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import notjoe.tmm.TooManyMetals;
import notjoe.tmm.api.TMaterialContentFactory;
import notjoe.tmm.api.TMaterialRegistry;
import notjoe.tmm.common.content.BlockMaterial;
import notjoe.tmm.common.content.ItemBlockMaterial;
import notjoe.tmm.common.content.ItemMaterial;

import java.util.stream.Collectors;

import static notjoe.tmm.TooManyMetals.LOGGER;

@Mod.EventBusSubscriber(modid = TooManyMetals.MODID)
public class RegistrationEvents {
    private static TMaterialContentFactory contentFactory = TMaterialContentFactory.INSTANCE;

    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> blockRegistryEvent) {
        LOGGER.info("Block registering started.");
        blockRegistryEvent.getRegistry().registerAll(contentFactory.getBlocks().toArray(new BlockMaterial[]{}));
        LOGGER.info("Block registering finished.");
    }

    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> itemRegistryEvent) {
        LOGGER.info("Item registering started.");
        itemRegistryEvent.getRegistry().registerAll(contentFactory.getItems().toArray(new ItemMaterial[]{}));
        itemRegistryEvent.getRegistry().registerAll(contentFactory.getBlocks()
                .stream()
                .map(ItemBlockMaterial::new)
                .collect(Collectors.toList())
                .toArray(new ItemBlock[]{}));
        LOGGER.info("Item registering finished.");
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        LOGGER.info("Recipe registering started.");
        TMaterialRegistry.INSTANCE.registerCraftingRecipes(event.getRegistry());
        LOGGER.info("Recipe registering finished.");
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerModels(ModelRegistryEvent event) {
        contentFactory.getItems().forEach(ItemMaterial::registerModels);
        contentFactory.getBlocks().forEach(blockMaterial -> {
            blockMaterial.registerModels();
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockMaterial), 0, blockMaterial.getModelResourceLocation());
        });
    }
}
