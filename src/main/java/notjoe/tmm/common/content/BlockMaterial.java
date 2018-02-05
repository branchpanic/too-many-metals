package notjoe.tmm.common.content;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import notjoe.tmm.TooManyMetals;
import notjoe.tmm.api.ResourceType;
import notjoe.tmm.api.TMaterial;
import notjoe.tmm.common.TabResources;

import java.util.Optional;

public class BlockMaterial extends Block {
    private final TMaterial tMaterial;
    private final ResourceType resourceType;

    public BlockMaterial(TMaterial tMaterial, ResourceType resourceType) {
        super(Material.IRON);
        this.tMaterial = tMaterial;
        this.resourceType = resourceType;

        String internalName = tMaterial.getName() + "_" + resourceType.toString();

        setUnlocalizedName("tmm." + internalName);
        setRegistryName(TooManyMetals.MODID, internalName);

        setCreativeTab(TabResources.CREATIVE_TAB);
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public TMaterial getTMaterial() {
        return tMaterial;
    }

    public ModelResourceLocation getModelResourceLocation() {
        ModelResourceLocation modelResourceLocation = new ModelResourceLocation(new ResourceLocation(TooManyMetals.MODID, "resource_" + resourceType.toString()), "inventory");

        Optional<String> customModelLocation = tMaterial.getCustomModelFor(resourceType);
        if (customModelLocation.isPresent()) {
            modelResourceLocation = new ModelResourceLocation(customModelLocation.get(), "inventory");
        }

        return modelResourceLocation;
    }

    public void registerModels() {
        ModelLoader.setCustomStateMapper(this, new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return BlockMaterial.this.getModelResourceLocation();
            }
        });
    }

    @Override
    public String getLocalizedName() {
        return resourceType.getFormattedDisplayName(tMaterial.getNameCapitalized());
    }


}
