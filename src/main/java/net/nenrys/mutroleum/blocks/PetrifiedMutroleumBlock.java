package net.nenrys.mutroleum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import java.util.Random;

public class PetrifiedMutroleumBlock extends Block {
    public PetrifiedMutroleumBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(HEATED, false));
    }

    public static final BooleanProperty HEATED = BooleanProperty.of("heated");

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        //world.setBlockState(pos, (BlockState)state.with(HEATED, isLavaNearby(world, pos)));
        world.setBlockState(pos, state.with(HEATED, true));
    }

    // only testing, will be removed
    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {

        world.setBlockState(pos, state.with(HEATED, true));
        super.onSteppedOn(world, pos, state, entity);
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
