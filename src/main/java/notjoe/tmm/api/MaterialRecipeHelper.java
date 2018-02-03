package notjoe.tmm.api;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import notjoe.tmm.TooManyMetals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaterialRecipeHelper {
    private TMaterial input;

    public MaterialRecipeHelper(TMaterial input) {
        this.input = input;
    }

    public IRecipe getCompressionRecipe(ResourceType small, ResourceType large) {
        ResourceLocation name = new ResourceLocation(TooManyMetals.MODID, input.getName() + "_" + small.toString() + "_to_" + large.toString());
        return new ShapelessOreRecipe(
                name,
                input.createItemStack(large),
                NonNullList.withSize(9, input.getOreDictName(small)).toArray()
        ).setRegistryName(name);
    }

    public IRecipe getDecompressionRecipe(ResourceType small, ResourceType large) {
        ResourceLocation name = new ResourceLocation(TooManyMetals.MODID, input.getName() + "_" + large.toString() + "_to_" + small.toString());
        return new ShapelessOreRecipe(
                name,
                input.createItemStack(small, 9),
                input.getOreDictName(large)
        ).setRegistryName(name);
    }

    public IRecipe getGearRecipe() {
        ResourceLocation name = new ResourceLocation(TooManyMetals.MODID, input.getName() + "_gear");
        return new ShapedOreRecipe(
                name,
                input.createItemStack(ResourceType.GEAR),
                " x ",
                "xix",
                " x ",
                'x', input.getOreDictName(input.getBaseResourceType()),
                'i', "ingotIron"
        ).setRegistryName(name);
    }

    public IRecipe getPlateRecipe() {
        ResourceLocation name = new ResourceLocation(TooManyMetals.MODID, input.getName() + "_plate");
        return new ShapedOreRecipe(
                name,
                input.createItemStack(ResourceType.PLATE),
                "xx",
                "xx",
                'x', input.getOreDictName(input.getBaseResourceType())
        ).setRegistryName(name);
    }

    public List<IRecipe> getPossibleCraftingRecipes() {
        if (!input.hasBaseResourceType()) {
            return Collections.emptyList();
        }

        List<IRecipe> recipes = new ArrayList<>();
        ResourceType baseResourceType = input.getBaseResourceType();

        if (input.isAddingCompactingRecipes()) {
            if (input.hasResourceType(ResourceType.NUGGET)) {
                recipes.add(getCompressionRecipe(ResourceType.NUGGET, baseResourceType));
                recipes.add(getDecompressionRecipe(ResourceType.NUGGET, baseResourceType));
            }
        }

        if (input.isAddingGearRecipes()) {
            recipes.add(getGearRecipe());
        }

        if (input.isAddingPlateRecipes()) {
            recipes.add(getPlateRecipe());
        }

        return recipes;
    }
}
