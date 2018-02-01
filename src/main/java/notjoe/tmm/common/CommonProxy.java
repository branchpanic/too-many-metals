package notjoe.tmm.common;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import notjoe.tmm.api.TMaterial;
import notjoe.tmm.api.TMaterialRegistry;
import notjoe.tmm.common.configuration.MaterialLoader;
import notjoe.tmm.common.configuration.ModConfig;

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

        return loader.getDefinedMaterials();
    }

    private void listMaterials(List<TMaterial> materials) {
        LOGGER.info("== TMM registered the following materials:");
        LOGGER.info(String.format("  %-3s%-30s", "ID", "Name"));
        for (TMaterial material : materials) {
            LOGGER.info(String.format("  %-3d%-30s",
                    TMaterialRegistry.INSTANCE.nameToID(material.getName()),
                    material.getName()));
        }
        LOGGER.info("== End of registered materials.");
    }

    public void onPreInit(FMLPreInitializationEvent event) {
        File configDirectory = event.getModConfigurationDirectory();

        if (!configDirectory.exists()) {
            if (!configDirectory.mkdir()) {
                LOGGER.warn("Couldn't create the configuration directory-- try to make it manually!");
            }
        }

        List<TMaterial> registeredMaterials = loadMaterials(event.getModConfigurationDirectory());

        if (ModConfig.logMaterials) {
            listMaterials(registeredMaterials);
        }
    }
}
