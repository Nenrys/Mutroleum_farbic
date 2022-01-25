package net.nenrys.mutroleum.fluids;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.nenrys.mutroleum.genetics.HasGenesImpl;
import net.nenrys.mutroleum.genetics.Species;
import net.nenrys.mutroleum.genetics.SpeciesColors;

public class DeadMutroleumFluidBlock extends FluidBlock {

    public DeadMutroleumFluidBlock(FlowableFluid fluid, Settings settings) {
        super(fluid, settings);
        this.setDefaultState(this.stateManager.getDefaultState());
    }

    @Override
    public ItemStack tryDrainFluid(WorldAccess world, BlockPos pos, BlockState state) {
        if (state.get(LEVEL) == 0) {
            world.setBlockState(pos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
            HasGenesImpl hasgenes = new HasGenesImpl();
            int col = state.getFluidState().get(DeadMutroleum.COLOR);
            Species species = hasgenes.getSpeciesFromColor(col,true);
            ItemStack itemstack = new ItemStack(this.fluid.getBucketItem());
            hasgenes.addSpeciesToItem(itemstack,species);
            return itemstack;
        }
        return ItemStack.EMPTY;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block,BlockState> stateManager) {
        super.appendProperties(stateManager);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if(!world.isClient()) {
            if (entity instanceof PlayerEntity) {
                ((PlayerEntity) entity).addStatusEffect(
                        new StatusEffectInstance(StatusEffects.POISON,100));
            }
        }
    }
}
