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

    public static FlowableFluid FLOWING_DEAD_MUTROLEUM_WHITE;
    public static FlowableFluid FLOWING_DEAD_MUTROLEUM_LIGHTGRAY;
    public static FlowableFluid FLOWING_DEAD_MUTROLEUM_GRAY;
    public static FlowableFluid FLOWING_DEAD_MUTROLEUM_BLACK;
    public static FlowableFluid FLOWING_DEAD_MUTROLEUM_PINK;
    public static FlowableFluid FLOWING_DEAD_MUTROLEUM_RED;
    public static FlowableFluid FLOWING_DEAD_MUTROLEUM_MAGENTA;
    public static FlowableFluid FLOWING_DEAD_MUTROLEUM_PURPLE;
    public static FlowableFluid FLOWING_DEAD_MUTROLEUM_BLUE;
    public static FlowableFluid FLOWING_DEAD_MUTROLEUM_LIGHTBLUE;
    public static FlowableFluid FLOWING_DEAD_MUTROLEUM_CYAN;
    public static FlowableFluid FLOWING_DEAD_MUTROLEUM_GREEN;
    public static FlowableFluid FLOWING_DEAD_MUTROLEUM_LIME;
    public static FlowableFluid FLOWING_DEAD_MUTROLEUM_YELLOW;
    public static FlowableFluid FLOWING_DEAD_MUTROLEUM_ORANGE;
    public static FlowableFluid FLOWING_DEAD_MUTROLEUM_BROWN;

    public static FlowableFluid STILL_DEAD_MUTROLEUM_WHITE;
    public static FlowableFluid STILL_DEAD_MUTROLEUM_LIGHTGRAY;
    public static FlowableFluid STILL_DEAD_MUTROLEUM_GRAY;
    public static FlowableFluid STILL_DEAD_MUTROLEUM_BLACK;
    public static FlowableFluid STILL_DEAD_MUTROLEUM_PINK;
    public static FlowableFluid STILL_DEAD_MUTROLEUM_RED;
    public static FlowableFluid STILL_DEAD_MUTROLEUM_MAGENTA;
    public static FlowableFluid STILL_DEAD_MUTROLEUM_PURPLE;
    public static FlowableFluid STILL_DEAD_MUTROLEUM_BLUE;
    public static FlowableFluid STILL_DEAD_MUTROLEUM_LIGHTBLUE;
    public static FlowableFluid STILL_DEAD_MUTROLEUM_CYAN;
    public static FlowableFluid STILL_DEAD_MUTROLEUM_GREEN;
    public static FlowableFluid STILL_DEAD_MUTROLEUM_LIME;
    public static FlowableFluid STILL_DEAD_MUTROLEUM_YELLOW;
    public static FlowableFluid STILL_DEAD_MUTROLEUM_ORANGE;
    public static FlowableFluid STILL_DEAD_MUTROLEUM_BROWN;

    public static Block DEAD_MUTROLEUM_BLOCK_WHITE;
    public static Block DEAD_MUTROLEUM_BLOCK_LIGHTGRAY;
    public static Block DEAD_MUTROLEUM_BLOCK_GRAY;
    public static Block DEAD_MUTROLEUM_BLOCK_BLACK;
    public static Block DEAD_MUTROLEUM_BLOCK_PINK;
    public static Block DEAD_MUTROLEUM_BLOCK_RED;
    public static Block DEAD_MUTROLEUM_BLOCK_MAGENTA;
    public static Block DEAD_MUTROLEUM_BLOCK_PURPLE;
    public static Block DEAD_MUTROLEUM_BLOCK_BLUE;
    public static Block DEAD_MUTROLEUM_BLOCK_LIGHTBLUE;
    public static Block DEAD_MUTROLEUM_BLOCK_CYAN;
    public static Block DEAD_MUTROLEUM_BLOCK_GREEN;
    public static Block DEAD_MUTROLEUM_BLOCK_LIME;
    public static Block DEAD_MUTROLEUM_BLOCK_YELLOW;
    public static Block DEAD_MUTROLEUM_BLOCK_ORANGE;
    public static Block DEAD_MUTROLEUM_BLOCK_BROWN;


    public static Item DEAD_MUTROLEUM_BUCKET;

    private static FlowableFluid[] flowfluidarray;

    @Override
    public boolean matchesType(Fluid fluid) {
        return fluid == getStill() || fluid == getFlowing();
    }

    public static FlowableFluid getFluidfromint(int col) {
        return flowfluidarray[col];
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

        registterDMFlowing();

        registerDMStill();

        registerDMBlocks();

        ModFluids.DEAD_MUTROLEUM_BUCKET = Registry.register(Registry.ITEM,
                new Identifier(MOD_ID, "dead_mutroleum_bucket"),
                new DeadMutroleumBucket(ModFluids.STILL_DEAD_MUTROLEUM_PURPLE,
                        new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1)
                                .group(Mutroleum.MUTROLEUM_GROUP)));

        flowfluidarray = new FlowableFluid[]{STILL_DEAD_MUTROLEUM_WHITE, STILL_DEAD_MUTROLEUM_LIGHTGRAY,
                STILL_DEAD_MUTROLEUM_GRAY, STILL_DEAD_MUTROLEUM_BLACK, STILL_DEAD_MUTROLEUM_PINK, STILL_DEAD_MUTROLEUM_RED,
                STILL_DEAD_MUTROLEUM_MAGENTA, STILL_DEAD_MUTROLEUM_PURPLE, STILL_DEAD_MUTROLEUM_BLUE,
                STILL_DEAD_MUTROLEUM_LIGHTBLUE, STILL_DEAD_MUTROLEUM_CYAN, STILL_DEAD_MUTROLEUM_GREEN,
                STILL_DEAD_MUTROLEUM_LIME, STILL_DEAD_MUTROLEUM_YELLOW, STILL_DEAD_MUTROLEUM_ORANGE,
                STILL_DEAD_MUTROLEUM_BROWN};
    }
    private static void registterDMFlowing() {
        FLOWING_DEAD_MUTROLEUM_WHITE = Registry.register(Registry.FLUID,
                new Identifier(MOD_ID, "flowing_dead_mutroleum_white"), new DeadMutroleum.FlowingWhite());
        FLOWING_DEAD_MUTROLEUM_LIGHTGRAY = Registry.register(Registry.FLUID,
                new Identifier(MOD_ID, "flowing_dead_mutroleum_lightgray"), new DeadMutroleum.FlowingLightGray());
        FLOWING_DEAD_MUTROLEUM_GRAY = Registry.register(Registry.FLUID,
                new Identifier(MOD_ID, "flowing_dead_mutroleum_gray"), new DeadMutroleum.FlowingGray());
        FLOWING_DEAD_MUTROLEUM_BLACK = Registry.register(Registry.FLUID,
                new Identifier(MOD_ID, "flowing_dead_mutroleum_black"), new DeadMutroleum.FlowingBlack());
        FLOWING_DEAD_MUTROLEUM_PINK = Registry.register(Registry.FLUID,
                new Identifier(MOD_ID, "flowing_dead_mutroleum_pink"), new DeadMutroleum.FlowingPink());
        FLOWING_DEAD_MUTROLEUM_RED = Registry.register(Registry.FLUID,
                new Identifier(MOD_ID, "flowing_dead_mutroleum_red"), new DeadMutroleum.FlowingRed());
        FLOWING_DEAD_MUTROLEUM_MAGENTA = Registry.register(Registry.FLUID,
                new Identifier(MOD_ID, "flowing_dead_mutroleum_magenta"), new DeadMutroleum.FlowingMagenta());
        FLOWING_DEAD_MUTROLEUM_PURPLE = Registry.register(Registry.FLUID,
                new Identifier(MOD_ID, "flowing_dead_mutroleum_purple"), new DeadMutroleum.FlowingPurple());
        FLOWING_DEAD_MUTROLEUM_BLUE = Registry.register(Registry.FLUID,
                new Identifier(MOD_ID, "flowing_dead_mutroleum_blue"), new DeadMutroleum.FlowingBlue());
        FLOWING_DEAD_MUTROLEUM_LIGHTBLUE = Registry.register(Registry.FLUID,
                new Identifier(MOD_ID, "flowing_dead_mutroleum_lightblue"), new DeadMutroleum.FlowingLightBlue());
        FLOWING_DEAD_MUTROLEUM_CYAN = Registry.register(Registry.FLUID,
                new Identifier(MOD_ID, "flowing_dead_mutroleum_cyan"), new DeadMutroleum.FlowingCyan());
        FLOWING_DEAD_MUTROLEUM_GREEN = Registry.register(Registry.FLUID,
                new Identifier(MOD_ID, "flowing_dead_mutroleum_green"), new DeadMutroleum.FlowingGreen());
        FLOWING_DEAD_MUTROLEUM_LIME = Registry.register(Registry.FLUID,
                new Identifier(MOD_ID, "flowing_dead_mutroleum_lime"), new DeadMutroleum.FlowingLime());
        FLOWING_DEAD_MUTROLEUM_YELLOW = Registry.register(Registry.FLUID,
                new Identifier(MOD_ID, "flowing_dead_mutroleum_yellow"), new DeadMutroleum.FlowingYellow());
        FLOWING_DEAD_MUTROLEUM_ORANGE = Registry.register(Registry.FLUID,
                new Identifier(MOD_ID, "flowing_dead_mutroleum_orange"), new DeadMutroleum.FlowingOrange());
        FLOWING_DEAD_MUTROLEUM_BROWN = Registry.register(Registry.FLUID,
                new Identifier(MOD_ID, "flowing_dead_mutroleum_brown"), new DeadMutroleum.FlowingBrown());
    }

    private static void registerDMBlocks() {
        DEAD_MUTROLEUM_BLOCK_WHITE = Registry.register(Registry.BLOCK,
                new Identifier(MOD_ID, "dead_mutroleum_white"),
                new DeadMutroleumFluidBlock(ModFluids.STILL_DEAD_MUTROLEUM_WHITE, FabricBlockSettings.copy(Blocks.WATER)));
        DEAD_MUTROLEUM_BLOCK_LIGHTGRAY = Registry.register(Registry.BLOCK,
                new Identifier(MOD_ID, "dead_mutroleum_lightgray"),
                new DeadMutroleumFluidBlock(ModFluids.STILL_DEAD_MUTROLEUM_LIGHTGRAY, FabricBlockSettings.copy(Blocks.WATER)));
        DEAD_MUTROLEUM_BLOCK_GRAY = Registry.register(Registry.BLOCK,
                new Identifier(MOD_ID, "dead_mutroleum_gray"),
                new DeadMutroleumFluidBlock(ModFluids.STILL_DEAD_MUTROLEUM_GRAY, FabricBlockSettings.copy(Blocks.WATER)));
        DEAD_MUTROLEUM_BLOCK_BLACK= Registry.register(Registry.BLOCK,
                new Identifier(MOD_ID, "dead_mutroleum_black"),
                new DeadMutroleumFluidBlock(ModFluids.STILL_DEAD_MUTROLEUM_BLACK, FabricBlockSettings.copy(Blocks.WATER)));
        DEAD_MUTROLEUM_BLOCK_PINK= Registry.register(Registry.BLOCK,
                new Identifier(MOD_ID, "dead_mutroleum_pink"),
                new DeadMutroleumFluidBlock(ModFluids.STILL_DEAD_MUTROLEUM_PINK, FabricBlockSettings.copy(Blocks.WATER)));
        DEAD_MUTROLEUM_BLOCK_RED= Registry.register(Registry.BLOCK,
                new Identifier(MOD_ID, "dead_mutroleum_red"),
                new DeadMutroleumFluidBlock(ModFluids.STILL_DEAD_MUTROLEUM_RED, FabricBlockSettings.copy(Blocks.WATER)));
        DEAD_MUTROLEUM_BLOCK_MAGENTA= Registry.register(Registry.BLOCK,
                new Identifier(MOD_ID, "dead_mutroleum_magenta"),
                new DeadMutroleumFluidBlock(ModFluids.STILL_DEAD_MUTROLEUM_MAGENTA, FabricBlockSettings.copy(Blocks.WATER)));
        DEAD_MUTROLEUM_BLOCK_PURPLE= Registry.register(Registry.BLOCK,
                new Identifier(MOD_ID, "dead_mutroleum_purple"),
                new DeadMutroleumFluidBlock(ModFluids.STILL_DEAD_MUTROLEUM_PURPLE, FabricBlockSettings.copy(Blocks.WATER)));
        DEAD_MUTROLEUM_BLOCK_BLUE= Registry.register(Registry.BLOCK,
                new Identifier(MOD_ID, "dead_mutroleum_blue"),
                new DeadMutroleumFluidBlock(ModFluids.STILL_DEAD_MUTROLEUM_BLUE, FabricBlockSettings.copy(Blocks.WATER)));
        DEAD_MUTROLEUM_BLOCK_LIGHTBLUE= Registry.register(Registry.BLOCK,
                new Identifier(MOD_ID, "dead_mutroleum_lightblue"),
                new DeadMutroleumFluidBlock(ModFluids.STILL_DEAD_MUTROLEUM_LIGHTBLUE, FabricBlockSettings.copy(Blocks.WATER)));
        DEAD_MUTROLEUM_BLOCK_CYAN= Registry.register(Registry.BLOCK,
                new Identifier(MOD_ID, "dead_mutroleum_cyan"),
                new DeadMutroleumFluidBlock(ModFluids.STILL_DEAD_MUTROLEUM_CYAN, FabricBlockSettings.copy(Blocks.WATER)));
        DEAD_MUTROLEUM_BLOCK_GREEN= Registry.register(Registry.BLOCK,
                new Identifier(MOD_ID, "dead_mutroleum_green"),
                new DeadMutroleumFluidBlock(ModFluids.STILL_DEAD_MUTROLEUM_GREEN, FabricBlockSettings.copy(Blocks.WATER)));
        DEAD_MUTROLEUM_BLOCK_LIME= Registry.register(Registry.BLOCK,
                new Identifier(MOD_ID, "dead_mutroleum_lime"),
                new DeadMutroleumFluidBlock(ModFluids.STILL_DEAD_MUTROLEUM_LIME, FabricBlockSettings.copy(Blocks.WATER)));
        DEAD_MUTROLEUM_BLOCK_YELLOW= Registry.register(Registry.BLOCK,
                new Identifier(MOD_ID, "dead_mutroleum_yellow"),
                new DeadMutroleumFluidBlock(ModFluids.STILL_DEAD_MUTROLEUM_YELLOW, FabricBlockSettings.copy(Blocks.WATER)));
        DEAD_MUTROLEUM_BLOCK_ORANGE= Registry.register(Registry.BLOCK,
                new Identifier(MOD_ID, "dead_mutroleum_orange"),
                new DeadMutroleumFluidBlock(ModFluids.STILL_DEAD_MUTROLEUM_ORANGE, FabricBlockSettings.copy(Blocks.WATER)));
        DEAD_MUTROLEUM_BLOCK_BROWN= Registry.register(Registry.BLOCK,
                new Identifier(MOD_ID, "dead_mutroleum_brown"),
                new DeadMutroleumFluidBlock(ModFluids.STILL_DEAD_MUTROLEUM_BROWN, FabricBlockSettings.copy(Blocks.WATER)));
    }

    private static void registerDMStill() {
        STILL_DEAD_MUTROLEUM_WHITE = Registry.register(Registry.FLUID,
                new Identifier(MOD_ID, "still_dead_mutroleum_white"), new DeadMutroleum.StillWhite());
        STILL_DEAD_MUTROLEUM_LIGHTGRAY = Registry.register(Registry.FLUID,
                new Identifier(MOD_ID, "still_dead_mutroleum_lightgray"), new DeadMutroleum.StillLightGray());
        STILL_DEAD_MUTROLEUM_GRAY = Registry.register(Registry.FLUID,
                new Identifier(MOD_ID, "still_dead_mutroleum_gray"), new DeadMutroleum.StillGray());
        STILL_DEAD_MUTROLEUM_BLACK = Registry.register(Registry.FLUID,
                new Identifier(MOD_ID, "still_dead_mutroleum_black"), new DeadMutroleum.StillBlack());
        STILL_DEAD_MUTROLEUM_PINK = Registry.register(Registry.FLUID,
                new Identifier(MOD_ID, "still_dead_mutroleum_pink"), new DeadMutroleum.StillPink());
        STILL_DEAD_MUTROLEUM_RED = Registry.register(Registry.FLUID,
                new Identifier(MOD_ID, "still_dead_mutroleum_red"), new DeadMutroleum.StillRed());
        STILL_DEAD_MUTROLEUM_MAGENTA = Registry.register(Registry.FLUID,
                new Identifier(MOD_ID, "still_dead_mutroleum_magenta"), new DeadMutroleum.StillMagenta());
        STILL_DEAD_MUTROLEUM_PURPLE = Registry.register(Registry.FLUID,
                new Identifier(MOD_ID, "still_dead_mutroleum_purple"), new DeadMutroleum.StillPurple());
        STILL_DEAD_MUTROLEUM_BLUE = Registry.register(Registry.FLUID,
                new Identifier(MOD_ID, "still_dead_mutroleum_blue"), new DeadMutroleum.StillBlue());
        STILL_DEAD_MUTROLEUM_LIGHTBLUE = Registry.register(Registry.FLUID,
                new Identifier(MOD_ID, "still_dead_mutroleum_lightblue"), new DeadMutroleum.StillLightBlue());
        STILL_DEAD_MUTROLEUM_CYAN = Registry.register(Registry.FLUID,
                new Identifier(MOD_ID, "still_dead_mutroleum_cyan"), new DeadMutroleum.StillCyan());
        STILL_DEAD_MUTROLEUM_GREEN = Registry.register(Registry.FLUID,
                new Identifier(MOD_ID, "still_dead_mutroleum_green"), new DeadMutroleum.StillGreen());
        STILL_DEAD_MUTROLEUM_LIME = Registry.register(Registry.FLUID,
                new Identifier(MOD_ID, "still_dead_mutroleum_lime"), new DeadMutroleum.StillLime());
        STILL_DEAD_MUTROLEUM_YELLOW = Registry.register(Registry.FLUID,
                new Identifier(MOD_ID, "still_dead_mutroleum_yellow"), new DeadMutroleum.StillYellow());
        STILL_DEAD_MUTROLEUM_ORANGE = Registry.register(Registry.FLUID,
                new Identifier(MOD_ID, "still_dead_mutroleum_orange"), new DeadMutroleum.StillOrange());
        STILL_DEAD_MUTROLEUM_BROWN = Registry.register(Registry.FLUID,
                new Identifier(MOD_ID, "still_dead_mutroleum_brown"), new DeadMutroleum.StillBrown());
    }
}
