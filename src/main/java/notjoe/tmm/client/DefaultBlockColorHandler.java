package notjoe.tmm.client;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import notjoe.tmm.api.material.MaterialDefinition;
import notjoe.tmm.api.material.VisualProperties;
import notjoe.tmm.common.content.BlockResourceExpression;

import javax.annotation.Nullable;

public class DefaultBlockColorHandler implements IBlockColor {
    @Override
    public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex) {
        if (tintIndex != 0 || (state.getBlock() instanceof BlockResourceExpression)) {
            return 0xFFFFFF;
        }

        BlockResourceExpression blockResourceExpression = (BlockResourceExpression) state.getBlock();
        MaterialDefinition material = blockResourceExpression.getMaterialDefinition();
        VisualProperties visualProperties = material.getVisualProperties();

        if (visualProperties.getCustomModelLocationForResource(blockResourceExpression.getResourceType()).isDefined()) {
            return visualProperties.getColor().getRGB();
        } else {
            return 0xFFFFFF;
        }
    }
}
