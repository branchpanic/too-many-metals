package notjoe.tmm.api;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.function.BiConsumer;

/**
 * Main registrar for all registeredMaterials defined through TooManyMetals. Maps material definitions by name and numerical ID.
 */
public enum TMaterialRegistry {
    INSTANCE;

    private Map<String, TMaterial> registeredMaterials;

    public Map<String, TMaterial> getRegisteredMaterials() {
        if (registeredMaterials == null) {
            registeredMaterials = new LinkedHashMap<>();
        }

        return registeredMaterials;
    }

    public List<TMaterial> getMaterialsByID() {
        return new ArrayList<>(registeredMaterials.values());
    }

    public void registerAllMaterials(Collection<TMaterial> materials) {
        materials.forEach(this::registerMaterial);
    }

    public void registerMaterial(TMaterial material) {
        getRegisteredMaterials().put(material.getName(), material);
    }

    public TMaterial getMaterial(String name) {
        return registeredMaterials.get(name);
    }

    public TMaterial getMaterialByID(int id) {
        return getMaterialsByID().get(id);
    }

    public void forEachMaterial(BiConsumer<Integer, TMaterial> action) {
        List<TMaterial> materials = getMaterialsByID();
        for (int i = 0; i < materials.size(); i++) {
            action.accept(i, materials.get(i));
        }
    }

    public int nameToID(String materialName) {
        List<String> names = new ArrayList<>(registeredMaterials.keySet());
        return names.indexOf(materialName);
    }

    public ItemStack getStackFromDefinition(String name, ResourceType resourceType) {
        return getMaterial(name).createItemStack(resourceType);
    }

    public void registerOreDictEntries() {
        forEachMaterial((id, material) -> {
            for (ResourceType type : material.getResourceTypes()) {
                OreDictionary.registerOre(
                        type.getOreDictPrefix() + StringUtils.capitalize(material.getName()),
                        material.createItemStack(type)
                );
            }
        });
    }
}
