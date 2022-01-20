package net.nenrys.mutroleum.fluids;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.*;
import net.nenrys.mutroleum.Mutroleum;
import net.nenrys.mutroleum.items.DeadMutroleumBucket;

import java.util.Random;

public abstract class ModFluids extends FlowableFluid {

    public static String MOD_ID = Mutroleum.MOD_ID;

    public static FlowableFluid STILL_DEAD_MUTROLEUM;
    public static FlowableFluid FLOWING_DEAD_MUTROLEUM;
    public static Item DEAD_MUTROLEUM_BUCKET;
    public static Block DEAD_MUTROLEUM_BLOCK;

    @Override
    public boolean matchesType(Fluid fluid) {
        return fluid == getStill() || fluid == getFlowing();
    }

    @Override
    protected boolean isInfinite() {
        return false;
    }

    @Override
    protected void beforeBreakingBlock(WorldAccess world, BlockPos pos, BlockState state) {
        final BlockEntity blockEntity = state.hasBlockEntity() ? world.getBlockEntity(pos) : null;
        Block.dropStacks(state, world, pos, blockEntity);
    }

    @Override
    protected boolean canBeReplacedWith(FluidState fluidState, BlockView blockView,
                                        BlockPos blockPos, Fluid fluid, Direction direction) {
        return false;
    }

    @Override
    protected int getFlowSpeed(WorldView worldView) {
        return 4;
    }

    @Override
    protected int getLevelDecreasePerBlock(WorldView worldView) {
        return 1;
    }

    @Override
    public int getTickRate(WorldView worldView) {
        return 5;
    }

    @Override
    protected float getBlastResistance() {
        return 100.0F;
    }

    public static void registerFluids() {

        Mutroleum.LOGGER.info("Registring mod fluids for " + Mutroleum.MOD_ID);

        ModFluids.STILL_DEAD_MUTROLEUM = Registry.register(Registry.FLUID,
                new Identifier(MOD_ID, "dead_mutroleum"), new DeadMutroleum.Still());
        ModFluids.FLOWING_DEAD_MUTROLEUM = Registry.register(Registry.FLUID,
                new Identifier(MOD_ID, "flowing_dead_mutroleum"), new DeadMutroleum.Flowing());
        ModFluids.DEAD_MUTROLEUM_BUCKET = Registry.register(Registry.ITEM,
                new Identifier(MOD_ID, "dead_mutroleum_bucket"),
                new DeadMutroleumBucket(ModFluids.STILL_DEAD_MUTROLEUM,
                        new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1)
                                .group(Mutroleum.MUTROLEUM_GROUP)));

        ModFluids.DEAD_MUTROLEUM_BLOCK = Registry.register(Registry.BLOCK,
                new Identifier(MOD_ID, "dead_mutroleum"),
                new DeadMutroleumFluidBlock(ModFluids.STILL_DEAD_MUTROLEUM, FabricBlockSettings.copy(Blocks.WATER)));
    }
}
