package notjoe.tmm.common.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import notjoe.tmm.TooManyMetals;
import notjoe.tmm.api.TMaterial;
import notjoe.tmm.api.TMaterialRegistry;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialLoader {
    private List<TMaterial> definedMaterials;
    private File definitionDirectory;

    public MaterialLoader(File definitionDirectory) {
        definedMaterials = new ArrayList<>();
        this.definitionDirectory = definitionDirectory;
    }

    public void loadData() throws FileNotFoundException {
        if (!definitionDirectory.isDirectory()) {
            return;
        }

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        for (File definitionFile : definitionDirectory.listFiles()) {
            if (definitionFile.getName().endsWith("yml")) {
                try {
                    definedMaterials.add(mapper.readValue(definitionFile, TMaterial.class));
                } catch (IOException e) {
                    TooManyMetals.LOGGER.error("Failed to load material definition: " + definitionFile.getName());
                    TooManyMetals.LOGGER.error("Try making sure that your YAML syntax is correct and that all" +
                            "necessary values are fulfilled.");
                }
            }
        }
    }

    public void registerDefinedMaterials() {
        TMaterialRegistry.INSTANCE.registerAllMaterials(definedMaterials);
    }

    public List<TMaterial> getDefinedMaterials() {
        return definedMaterials;
    }
}
