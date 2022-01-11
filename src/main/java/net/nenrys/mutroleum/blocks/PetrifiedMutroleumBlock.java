package net.nenrys.mutroleum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class PetrifiedMutroleumBlock extends Block {
    public PetrifiedMutroleumBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(HEATED, false));
    }

    public static final BooleanProperty HEATED = BooleanProperty.of("heated");

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        world.setBlockState(pos, state.with(HEATED, isLavaNearby(world,pos)));
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
        super.afterBreak(world, player, pos, state, blockEntity, stack);
        if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 0) {
            if (world.getDimension().isUltrawarm()) {
                world.removeBlock(pos, false);
                return;
            }
            //removed stuff from iceblock, may need to put back
            if (state.get(HEATED)) {
                return;
            }

            world.setBlockState(pos, Blocks.WATER.getDefaultState());
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block,BlockState> stateManager) {
        stateManager.add(HEATED);
    }

    private static boolean isLavaNearby(WorldView world, BlockPos pos) {
        for (BlockPos blockPos : BlockPos.iterate(pos.add(-1, -1, -1), pos.add(1, 1, 1))) {
            if (!world.getFluidState(blockPos).isIn(FluidTags.LAVA)) continue;
            return true;
        }
        return false;
    }
}
