package notjoe.tmm.api;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.minecraftforge.registries.IForgeRegistry;
import notjoe.tmm.TooManyMetals;
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

    private IRecipe createCompactingRecipe(String name, int required, String input, ItemStack output) {
        String[] inputs = new String[9];
        ResourceLocation resourceLocation = new ResourceLocation(TooManyMetals.MODID, name);
        Arrays.fill(inputs, input);
        return new ShapelessOreRecipe(
                resourceLocation,
                output,
                inputs
        ).setRegistryName(resourceLocation);
    }

    private IRecipe createUnCompactingRecipe(String name, int yield, String input, ItemStack output) {
        ItemStack adjustedOutput = output.copy();
        ResourceLocation resourceLocation = new ResourceLocation(TooManyMetals.MODID, name);
        adjustedOutput.setCount(yield);
        return new ShapelessOreRecipe(
                resourceLocation,
                adjustedOutput,
                input
        ).setRegistryName(resourceLocation);
    }

    public void registerVanillaRecipes(IForgeRegistry<IRecipe> recipeRegistry) {
        forEachMaterial((id, material) -> {
            ResourceType primaryResourceType = null;

            if (material.hasResourceType(ResourceType.INGOT)) {
                primaryResourceType = ResourceType.INGOT;
            } else if (material.hasResourceType(ResourceType.GEM)) {
                primaryResourceType = ResourceType.GEM;
            }

            if (primaryResourceType == null) {
                return;
            }

            String primaryResourceOreDict = material.getOreDictName(primaryResourceType);

            if (material.hasResourceType(ResourceType.NUGGET) && material.isRegisterCompactingRecipes()) {
                recipeRegistry.registerAll(
                        createCompactingRecipe(material.getName() + "_nugget_to_primary", 9, material.getOreDictName(ResourceType.NUGGET), material.createItemStack(primaryResourceType)),
                        createUnCompactingRecipe(material.getName() + "_primary_to_nugget", 9, primaryResourceOreDict, material.createItemStack(ResourceType.NUGGET))
                );
            }

            if (material.hasResourceType(ResourceType.GEAR) && material.isRegisterGearRecipe()) {
                ResourceLocation resourceLocation = new ResourceLocation(TooManyMetals.MODID, material.getName() + "_primary_to_gear");
                ItemStack materialGear = material.createItemStack(ResourceType.GEAR);
                recipeRegistry.registerAll(
                        new ShapedOreRecipe(
                                resourceLocation,
                                materialGear,
                                " x ",
                                "xix",
                                " x ",
                                'x', primaryResourceOreDict,
                                'i', "ingotIron"
                        ).setRegistryName(resourceLocation)
                );
            }

            if (material.hasResourceType(ResourceType.PLATE) && material.isRegisterPlateRecipe()) {
                ResourceLocation resourceLocation = new ResourceLocation(TooManyMetals.MODID, material.getName() + "_primary_to_plate");
                ItemStack materialPlate = material.createItemStack(ResourceType.PLATE);
                recipeRegistry.registerAll(
                        new ShapedOreRecipe(
                                resourceLocation,
                                materialPlate,
                                "xx",
                                "xx",
                                'x', primaryResourceOreDict
                        ).setRegistryName(resourceLocation)
                );
            }
        });
    }
}
