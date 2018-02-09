package notjoe.tmm.common.content;

import io.vavr.control.Option;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.ResourceLocation;
import notjoe.tmm.TooManyMetals;
import notjoe.tmm.api.material.MaterialDefinition;
import notjoe.tmm.api.material.WorldProperties;
import notjoe.tmm.api.resource.Resource;
import notjoe.tmm.api.resource.ResourceType;
import notjoe.tmm.common.TabResources;
import org.jetbrains.annotations.NotNull;

public class BlockResourceExpression extends Block implements ResourceExpression {
    private final MaterialDefinition materialDefinition;
    private final ResourceType resourceType;
    private final String internalName;

    public BlockResourceExpression(MaterialDefinition materialDefinition, ResourceType resourceType) {
        super(Material.IRON);
        this.materialDefinition = materialDefinition;
        this.resourceType = resourceType;
        internalName = materialDefinition.getName() + "_" + resourceType.getOreDictPrefix();

        setUnlocalizedName("tmm." + internalName);
        setRegistryName(TooManyMetals.MODID, internalName);

        setCreativeTab(TabResources.CREATIVE_TAB);

        WorldProperties worldProperties = materialDefinition.getWorldProperties();

        setHardness(worldProperties.getBlockHardness());
        setResistance(worldProperties.getBlockResistance());
        setHarvestLevel("pickaxe", worldProperties.getBlockMiningLevel());
    }

    @NotNull
    @Override
    public BlockRenderLayer getBlockLayer() {
        if (getResourceType() == Resource.ORE) {
            return BlockRenderLayer.CUTOUT;
        } else {
            return super.getBlockLayer();
        }
    }

    @NotNull
    @Override
    public String getLocalizedName() {
        return I18n.format(getResourceType().getTranslationKey(), getMaterialDefinition().getNameCapitalized());
    }

    @Override
    public ResourceType getResourceType() {
        return resourceType;
    }

    @Override
    public MaterialDefinition getMaterialDefinition() {
        return materialDefinition;
    }

    public ModelResourceLocation getModelResourceLocation() {
        Option<String> customModel = getMaterialDefinition().getVisualProperties().getCustomModelLocationForResource(getResourceType());

        if (customModel.isDefined()) {
            return new ModelResourceLocation(customModel.get(), "inventory");
        } else {
            return new ModelResourceLocation(new ResourceLocation(TooManyMetals.MODID, internalName), "inventory");
        }
    }

    public IStateMapper getCustomStateMapper() {
        return new StateMapperBase() {
            @NotNull
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return BlockResourceExpression.this.getModelResourceLocation();
            }
        };
    }
}
