package notjoe.tmm.common;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import notjoe.tmm.api.material.MaterialDefinition;
import notjoe.tmm.common.configuration.ModConfig;
import notjoe.tmm.common.integration.IntegrationHandler;
import notjoe.tmm.common.provider.MaterialDefinitionProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static notjoe.tmm.TooManyMetals.LOGGER;

public class CommonProxy {
    public static IntegrationHandler integrationHandler = new IntegrationHandler();

    private List<MaterialDefinition> loadMaterials(File directory) {
        MaterialDefinitionProvider loader = new MaterialDefinitionProvider(directory);

        try {
            loader.loadData();
        } catch (FileNotFoundException e) {
            LOGGER.error("Configuration directory is missing!");
        }

        loader.registerDefinedMaterials();
        return loader.getDefinedMaterials();
    }

    private void listMaterials(List<MaterialDefinition> materials) {
        LOGGER.info("== TMM registered the following materials:");
        LOGGER.info(String.format("  %-3s%-30s", "ID", "Name"));
        for (MaterialDefinition material : materials) {
            LOGGER.info(String.format("  %-3d%-30s",
                    TMaterialRegistry.INSTANCE.getIDFromName(material.getName()),
                    material.getName()));
        }
        LOGGER.info("== End of registered materials.");
    }

    public void onPreInit(FMLPreInitializationEvent event) {
        File configDirectory = new File(event.getModConfigurationDirectory(), "TooManyMetals");

        List<MaterialDefinition> registeredMaterials = loadMaterials(configDirectory);

        if (ModConfig.logMaterials) {
            listMaterials(registeredMaterials);
        }

        integrationHandler.onPreInit();
    }

    public void onInit(FMLInitializationEvent event) {
        TMaterialRegistry.INSTANCE.registerOreDictEntries();
        integrationHandler.onInit();
    }

    public void onPostInit(FMLPostInitializationEvent event) {

    }
}
