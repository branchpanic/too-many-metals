package notjoe.tmm.client;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import notjoe.tmm.api.TMaterial;
import notjoe.tmm.common.content.BlockMaterial;

import javax.annotation.Nullable;

public class DefaultBlockColorHandler implements IBlockColor {
    @Override
    public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex) {
        if (tintIndex != 0) {
            return 0xFFFFFF;
        }

        Block block = state.getBlock();

        if (!(block instanceof BlockMaterial)) {
            return 0xFFFFFF;
        }

        BlockMaterial blockMaterial = (BlockMaterial) block;
        TMaterial material = blockMaterial.getTMaterial();
        if (material != null && !material.getCustomModelFor(blockMaterial.getResourceType()).isPresent()) {
            return material.getRgbColor();
        } else {
            return 0xFFFFFF;
        }
    }
}
