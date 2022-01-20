package net.nenrys.mutroleum.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidFillable;
import net.minecraft.block.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BucketItem;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.nenrys.mutroleum.fluids.DeadMutroleumFluidBlock;
import net.nenrys.mutroleum.genetics.IHasGenes;
import net.nenrys.mutroleum.genetics.Species;
import net.nenrys.mutroleum.genetics.SpeciesColors;
import org.jetbrains.annotations.Nullable;

public class DeadMutroleumBucket extends BucketItem implements IHasGenes {

    private final Fluid fluid;
    public Species species = new Species();
    private BlockState statetoset;

    public DeadMutroleumBucket(Fluid fluid, Settings settings) {
        super(fluid, settings);
        this.fluid = fluid;
        updateState();
    }

    private void updateState() {
        statetoset = fluid.getDefaultState().getBlockState().with(
                DeadMutroleumFluidBlock.COLOR, SpeciesColors.getBlockStateColor(this));
    }

    @Override
    public boolean placeFluid(@Nullable PlayerEntity player, World world, BlockPos pos, @Nullable BlockHitResult hitResult) {
        updateState();
        boolean bl2;
        if (!(fluid instanceof FlowableFluid)) {
            return false;
        }
        BlockState blockState = world.getBlockState(pos);
        Block block = blockState.getBlock();
        Material material = blockState.getMaterial();
        boolean bl = blockState.canBucketPlace(this.fluid);
        boolean bl3 = bl2 = blockState.isAir() || bl || block instanceof FluidFillable && ((FluidFillable)((Object)block)).canFillWithFluid(world, pos, blockState, this.fluid);
        if (!bl2) {
            return hitResult != null && this.placeFluid(player, world, hitResult.getBlockPos().offset(hitResult.getSide()), null);
        }
        if (world.getDimension().isUltrawarm() && this.fluid.isIn(FluidTags.WATER)) {
            int i = pos.getX();
            int j = pos.getY();
            int k = pos.getZ();
            world.playSound(player, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5f, 2.6f + (world.random.nextFloat() - world.random.nextFloat()) * 0.8f);
            for (int l = 0; l < 8; ++l) {
                world.addParticle(ParticleTypes.LARGE_SMOKE, (double)i + Math.random(), (double)j + Math.random(), (double)k + Math.random(), 0.0, 0.0, 0.0);
            }
            return true;
        }
        if (block instanceof FluidFillable && this.fluid == Fluids.WATER) {
            ((FluidFillable)((Object)block)).tryFillWithFluid(world, pos, blockState, ((FlowableFluid)this.fluid).getStill(false));
            this.playEmptyingSound(player, world, pos);
            return true;
        }
        if (!world.isClient && bl && !material.isLiquid()) {
            world.breakBlock(pos, true);
        }
        if (world.setBlockState(pos, statetoset, Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD) || blockState.getFluidState().isStill()) {
            this.playEmptyingSound(player, world, pos);
            return true;
        }
        return false;
    }

    @Override
    public Species getSpecies() {
        return this.species;
    }
}
