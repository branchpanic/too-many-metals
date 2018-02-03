package notjoe.tmm.common;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import notjoe.tmm.api.TMaterial;
import notjoe.tmm.api.TMaterialRegistry;
import notjoe.tmm.common.configuration.MaterialLoader;
import notjoe.tmm.common.configuration.ModConfig;
import notjoe.tmm.common.integration.IntegrationHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static notjoe.tmm.TooManyMetals.LOGGER;

public class CommonProxy {
    private List<TMaterial> loadMaterials(File directory) {
        MaterialLoader loader = new MaterialLoader(directory);

        try {
            loader.loadData();
        } catch (FileNotFoundException e) {
            LOGGER.error("Configuration directory is missing!");
        }

        loader.registerDefinedMaterials();
        return loader.getDefinedMaterials();
    }

    private void listMaterials(List<TMaterial> materials) {
        LOGGER.info("== TMM registered the following materials:");
        LOGGER.info(String.format("  %-3s%-30s", "ID", "Name"));
        for (TMaterial material : materials) {
            LOGGER.info(String.format("  %-3d%-30s",
                    TMaterialRegistry.INSTANCE.getIDFromName(material.getName()),
                    material.getName()));
        }
        LOGGER.info("== End of registered materials.");
    }

    public void onPreInit(FMLPreInitializationEvent event) {
        File configDirectory = new File(event.getModConfigurationDirectory(), "TooManyMetals");

        List<TMaterial> registeredMaterials = loadMaterials(configDirectory);

        if (ModConfig.logMaterials) {
            listMaterials(registeredMaterials);
        }

        TMaterialRegistry.INSTANCE.createFluids();
        IntegrationHandler.onPreInit();
    }

    public void onInit(FMLInitializationEvent event) {

    }

    public void onPostInit(FMLPostInitializationEvent event) {
        TMaterialRegistry.INSTANCE.registerOreDictEntries();
    }
}
