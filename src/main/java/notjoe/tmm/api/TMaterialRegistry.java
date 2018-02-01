package notjoe.tmm.api;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.*;

/**
 * Main registrar for all materials defined through TooManyMetals. Maps material definitions by name and numerical ID.
 */
public enum TMaterialRegistry {
    INSTANCE;

    private Map<String, TMaterial> materials;

    public Map<String, TMaterial> getMaterials() {
        if (materials == null) {
            materials = new LinkedHashMap<>();
        }

        return materials;
    }

    public List<TMaterial> getMaterialsByID() {
        return new ArrayList<>(materials.values());
    }

    public void registerAllMaterials(Collection<TMaterial> materials) {
        materials.forEach(this::registerMaterial);
    }

    public void registerMaterial(TMaterial material) {
        getMaterials().put(material.getName(), material);
    }

    public TMaterial getMaterial(String name) {
        return materials.get(name);
    }

    public TMaterial getMaterialByID(int id) {
        return getMaterialsByID().get(id);
    }

    public int nameToID(String materialName) {
        List<String> names = new ArrayList<>(materials.keySet());
        return names.indexOf(materialName);
    }
}
