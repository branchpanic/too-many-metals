package notjoe.tmm.api;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
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
        return new ArrayList<>(getRegisteredMaterials().values());
    }

    public void registerAllMaterials(Collection<TMaterial> materials) {
        materials.forEach(this::registerMaterial);
    }

    public void registerMaterial(TMaterial material) {
        getRegisteredMaterials().put(material.getName(), material);
        TMaterialContentFactory.INSTANCE.registerMaterial(material);
    }

    public TMaterial getMaterial(String name) {
        return getRegisteredMaterials().get(name);
    }

    public TMaterial getMaterial(int id) {
        return getMaterialsByID().get(id);
    }

    public void forEachMaterial(BiConsumer<Integer, TMaterial> action) {
        List<TMaterial> materials = getMaterialsByID();
        for (int i = 0; i < materials.size(); i++) {
            action.accept(i, materials.get(i));
        }
    }

    public int getIDFromName(String materialName) {
        List<String> names = new ArrayList<>(getRegisteredMaterials().keySet());
        return names.indexOf(materialName);
    }

    public void registerOreDictEntries() {
        forEachMaterial((id, material) -> {
            for (ResourceType type : material.getResourceTypes()) {
                OreDictionary.registerOre(
                        type.getOreDictPrefix() + StringUtils.capitalize(material.getName()),
                        TMaterialContentFactory.INSTANCE.getItemStack(material.getName(), type)
                );
            }
        });
    }

    public void registerCraftingRecipes(IForgeRegistry<IRecipe> recipeRegistry) {
        forEachMaterial((id, material) -> {
            MaterialRecipeHelper recipeHelper = new MaterialRecipeHelper(material);
            recipeRegistry.registerAll(recipeHelper.getPossibleCraftingRecipes().toArray(new IRecipe[]{}));
        });
    }
}
