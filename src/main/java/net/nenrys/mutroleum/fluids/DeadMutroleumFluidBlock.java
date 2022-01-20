package net.nenrys.mutroleum.fluids;

import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DeadMutroleumFluidBlock extends FluidBlock {
    public DeadMutroleumFluidBlock(FlowableFluid fluid, Settings settings) {
        super(fluid, settings);
        setDefaultState(getStateManager().getDefaultState().with(COLOR,7));
    }

    public static final IntProperty COLOR = IntProperty.of("color", 0,15);

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof PlayerEntity) {
            ((PlayerEntity) entity).addStatusEffect(
                    new StatusEffectInstance(StatusEffects.POISON,20));
        }

    }
}
