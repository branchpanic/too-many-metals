package notjoe.tmm.common;

import io.vavr.collection.Vector;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import notjoe.tmm.api.material.MaterialDefinition;
import notjoe.tmm.api.resource.ResourceType;

import javax.inject.Inject;

import static notjoe.tmm.TooManyMetals.LOGGER;

public class RegistrationEvents {
    private Vector<MaterialDefinition> materialDefinitions;

    @Inject
    public RegistrationEvents(Vector<MaterialDefinition> materialDefinitions) {
        this.materialDefinitions = materialDefinitions;
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        LOGGER.info("Recipe registering started.");


        LOGGER.info("Recipe registering finished.");
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerModels(ModelRegistryEvent event) {
        LOGGER.info("Model registering started.");

        LOGGER.info("Model registering finished.");
    }

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> blockRegistryEvent) {
        LOGGER.info("Block registering started.");

        IForgeRegistry<Block> blockRegistry = blockRegistryEvent.getRegistry();

        for (MaterialDefinition materialDefinition : materialDefinitions) {
            materialDefinition.getResourceProperties().getAvailableResourceTypes()
                    .filter(ResourceType::isBlock)
                    .forEach(resourceType -> blockRegistry.register(materialDefinition.createBlock(resourceType).get()));
        }

        LOGGER.info("Block registering finished.");
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> itemRegistryEvent) {
        LOGGER.info("Item registering started.");

        IForgeRegistry<Item> blockRegistry = itemRegistryEvent.getRegistry();

        for (MaterialDefinition materialDefinition : materialDefinitions) {
            materialDefinition.getResourceProperties().getAvailableResourceTypes()
                    .filter(ResourceType::isBlock)
                    .forEach(resourceType -> blockRegistry.register(materialDefinition.createItem(resourceType).get()));
        }

        LOGGER.info("Item registering finished.");
    }
}
