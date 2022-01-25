package net.nenrys.mutroleum.fluids;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.nenrys.mutroleum.genetics.SpeciesColors;

public abstract class DeadMutroleum extends ModFluids {

    public static final IntProperty COLOR = IntProperty.of("color", 0,15);

    @Override
    public FluidState getFlowing(int level, boolean falling) {
        return this.getFlowing().getDefaultState()
                .with(LEVEL, level).with(FALLING, falling).with(COLOR,this.getStill().getDefaultState().get(COLOR));
    }

    @Override
    public void onScheduledTick(World world, BlockPos pos, FluidState state) {
        super.onScheduledTick(world,pos,state);
    }

    @Override
    public Item getBucketItem() {
        return ModFluids.DEAD_MUTROLEUM_BUCKET;
    }

    protected static abstract class Flowing extends DeadMutroleum {

        @Override
        protected BlockState toBlockState(FluidState fluidState) {
            // getBlockStateLevel converts the LEVEL_1_8 of the fluid state to the LEVEL_15 the fluid block uses
            return blockProvider().getDefaultState().
                    with(Properties.LEVEL_15, getBlockStateLevel(fluidState));
        }

        protected abstract FlowableFluid stillfluidProvider();
        protected abstract Block blockProvider();

        @Override
        public Fluid getStill() {
            return stillfluidProvider();
        }

        public Flowing() {
            super();
            setDefaultState(getStateManager().getDefaultState().with(COLOR,7));
        }

        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
            builder.add(COLOR);
        }

        @Override
        public int getLevel(FluidState fluidState) {
            return fluidState.get(LEVEL);
        }

        @Override
        public boolean isStill(FluidState fluidState) {
            return false;
        }
    }
    protected static abstract class Still extends DeadMutroleum {

        protected abstract int stateConstructor();
        public Still() {
            super();
            setDefaultState(getStateManager().getDefaultState().with(COLOR,stateConstructor()));
        }

        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(COLOR);
        }

        @Override
        public int getLevel(FluidState state) {
            return 8;
        }

        @Override
        public boolean isStill(FluidState fluidState) {
            return true;
        }
    }

    public static class StillWhite extends Still{
        @Override
        protected int stateConstructor() {
            return 0;
        }
        @Override
        public Fluid getStill() {
            return ModFluids.STILL_DEAD_MUTROLEUM_WHITE;
        }
        @Override
        protected BlockState toBlockState(FluidState fluidState) {
            // getBlockStateLevel converts the LEVEL_1_8 of the fluid state to the LEVEL_15 the fluid block uses
            return ModFluids.DEAD_MUTROLEUM_BLOCK_WHITE.getDefaultState().
                    with(Properties.LEVEL_15, getBlockStateLevel(fluidState));
        }
        @Override
        public Fluid getFlowing() {
            return ModFluids.FLOWING_DEAD_MUTROLEUM_WHITE;
        }
    }
    public static class StillLightGray extends Still{
        @Override
        protected int stateConstructor() {
            return 1;
        }
        @Override
        public Fluid getStill() {
            return ModFluids.STILL_DEAD_MUTROLEUM_LIGHTGRAY;
        }
        @Override
        protected BlockState toBlockState(FluidState fluidState) {
            // getBlockStateLevel converts the LEVEL_1_8 of the fluid state to the LEVEL_15 the fluid block uses
            return ModFluids.DEAD_MUTROLEUM_BLOCK_LIGHTGRAY.getDefaultState().
                    with(Properties.LEVEL_15, getBlockStateLevel(fluidState));
        }
        @Override
        public Fluid getFlowing() {
            return ModFluids.FLOWING_DEAD_MUTROLEUM_LIGHTGRAY;
        }
    }
    public static class StillGray extends Still{

        @Override
        public Fluid getStill() {
            return ModFluids.STILL_DEAD_MUTROLEUM_GRAY;
        }
        @Override
        protected int stateConstructor() {
            return 2;
        }
        @Override
        protected BlockState toBlockState(FluidState fluidState) {
            // getBlockStateLevel converts the LEVEL_1_8 of the fluid state to the LEVEL_15 the fluid block uses
            return ModFluids.DEAD_MUTROLEUM_BLOCK_GRAY.getDefaultState().
                    with(Properties.LEVEL_15, getBlockStateLevel(fluidState));
        }
        @Override
        public Fluid getFlowing() {
            return ModFluids.FLOWING_DEAD_MUTROLEUM_GRAY;
        }
    }
    public static class StillBlack extends Still{
        @Override
        protected int stateConstructor() {
        return 3;
    }
        @Override
        public Fluid getStill() {
            return ModFluids.STILL_DEAD_MUTROLEUM_BLACK;
        }
        @Override
        protected BlockState toBlockState(FluidState fluidState) {
            // getBlockStateLevel converts the LEVEL_1_8 of the fluid state to the LEVEL_15 the fluid block uses
            return ModFluids.DEAD_MUTROLEUM_BLOCK_BLACK.getDefaultState().
                    with(Properties.LEVEL_15, getBlockStateLevel(fluidState));
        }
        @Override
        public Fluid getFlowing() {
            return ModFluids.FLOWING_DEAD_MUTROLEUM_BLACK;
        }
    }
    public static class StillPink extends Still{
        @Override
        protected int stateConstructor() {
            return 4;
        }
        @Override
        public Fluid getStill() {
            return ModFluids.STILL_DEAD_MUTROLEUM_PINK;
        }
        @Override
        protected BlockState toBlockState(FluidState fluidState) {
            // getBlockStateLevel converts the LEVEL_1_8 of the fluid state to the LEVEL_15 the fluid block uses
            return ModFluids.DEAD_MUTROLEUM_BLOCK_PINK.getDefaultState().
                    with(Properties.LEVEL_15, getBlockStateLevel(fluidState));
        }
        @Override
        public Fluid getFlowing() {
            return ModFluids.FLOWING_DEAD_MUTROLEUM_PINK;
        }
    }
    public static class StillRed extends Still{
        @Override
        protected BlockState toBlockState(FluidState fluidState) {
            // getBlockStateLevel converts the LEVEL_1_8 of the fluid state to the LEVEL_15 the fluid block uses
            return ModFluids.DEAD_MUTROLEUM_BLOCK_RED.getDefaultState().
                    with(Properties.LEVEL_15, getBlockStateLevel(fluidState));
        }
        @Override
        protected int stateConstructor() {
            return 5;
        }
        @Override
        public Fluid getStill() {
            return ModFluids.STILL_DEAD_MUTROLEUM_RED;
        }
        @Override
        public Fluid getFlowing() {
            return ModFluids.FLOWING_DEAD_MUTROLEUM_RED;
        }
    }
    public static class StillMagenta extends Still{
        @Override
        protected int stateConstructor() {
            return 6;
        }
        @Override
        public Fluid getStill() {
            return ModFluids.STILL_DEAD_MUTROLEUM_MAGENTA;
        }
        @Override
        protected BlockState toBlockState(FluidState fluidState) {
            // getBlockStateLevel converts the LEVEL_1_8 of the fluid state to the LEVEL_15 the fluid block uses
            return ModFluids.DEAD_MUTROLEUM_BLOCK_MAGENTA.getDefaultState().
                    with(Properties.LEVEL_15, getBlockStateLevel(fluidState));
        }
        @Override
        public Fluid getFlowing() {
            return ModFluids.FLOWING_DEAD_MUTROLEUM_MAGENTA;
        }
    }
    public static class StillPurple extends Still{
        @Override
        protected int stateConstructor() {
            return 7;
        }
        @Override
        public Fluid getStill() {
            return ModFluids.STILL_DEAD_MUTROLEUM_PURPLE;
        }
        @Override
        protected BlockState toBlockState(FluidState fluidState) {
            // getBlockStateLevel converts the LEVEL_1_8 of the fluid state to the LEVEL_15 the fluid block uses
            return ModFluids.DEAD_MUTROLEUM_BLOCK_PURPLE.getDefaultState().
                    with(Properties.LEVEL_15, getBlockStateLevel(fluidState));
        }
        @Override
        public Fluid getFlowing() {
            return ModFluids.FLOWING_DEAD_MUTROLEUM_PURPLE;
        }
    }
    public static class StillBlue extends Still{
        @Override
        protected int stateConstructor() {
            return 8;
        }
        @Override
        public Fluid getStill() {
            return ModFluids.STILL_DEAD_MUTROLEUM_BLUE;
        }
        @Override
        protected BlockState toBlockState(FluidState fluidState) {
            // getBlockStateLevel converts the LEVEL_1_8 of the fluid state to the LEVEL_15 the fluid block uses
            return ModFluids.DEAD_MUTROLEUM_BLOCK_BLUE.getDefaultState().
                    with(Properties.LEVEL_15, getBlockStateLevel(fluidState));
        }
        @Override
        public Fluid getFlowing() {
            return ModFluids.FLOWING_DEAD_MUTROLEUM_BLUE;
        }
    }
    public static class StillLightBlue extends Still{
        @Override
        protected int stateConstructor() {
            return 9;
        }
        @Override
        public Fluid getStill() {
            return ModFluids.STILL_DEAD_MUTROLEUM_LIGHTBLUE;
        }
        @Override
        protected BlockState toBlockState(FluidState fluidState) {
            // getBlockStateLevel converts the LEVEL_1_8 of the fluid state to the LEVEL_15 the fluid block uses
            return ModFluids.DEAD_MUTROLEUM_BLOCK_BLUE.getDefaultState().
                    with(Properties.LEVEL_15, getBlockStateLevel(fluidState));
        }
        @Override
        public Fluid getFlowing() {
            return ModFluids.FLOWING_DEAD_MUTROLEUM_LIGHTBLUE;
        }
    }
    public static class StillCyan extends Still{
        @Override
        protected int stateConstructor() {
            return 10;
        }
        @Override
        public Fluid getStill() {
            return ModFluids.STILL_DEAD_MUTROLEUM_CYAN;
        }
        @Override
        protected BlockState toBlockState(FluidState fluidState) {
            // getBlockStateLevel converts the LEVEL_1_8 of the fluid state to the LEVEL_15 the fluid block uses
            return ModFluids.DEAD_MUTROLEUM_BLOCK_CYAN.getDefaultState().
                    with(Properties.LEVEL_15, getBlockStateLevel(fluidState));
        }
        @Override
        public Fluid getFlowing() {
            return ModFluids.FLOWING_DEAD_MUTROLEUM_CYAN;
        }
    }
    public static class StillGreen extends Still{@Override
    protected int stateConstructor() {
        return 11;
    }
        @Override
        public Fluid getStill() {
            return ModFluids.STILL_DEAD_MUTROLEUM_GREEN;
        }
        @Override
        protected BlockState toBlockState(FluidState fluidState) {
            // getBlockStateLevel converts the LEVEL_1_8 of the fluid state to the LEVEL_15 the fluid block uses
            return ModFluids.DEAD_MUTROLEUM_BLOCK_GREEN.getDefaultState().
                    with(Properties.LEVEL_15, getBlockStateLevel(fluidState));
        }
        @Override
        public Fluid getFlowing() {
            return ModFluids.FLOWING_DEAD_MUTROLEUM_GREEN;
        }
    }
    public static class StillLime extends Still{
        @Override
        protected int stateConstructor() {
            return 12;
        }
        @Override
        public Fluid getStill() {
            return ModFluids.STILL_DEAD_MUTROLEUM_LIME;
        }
        @Override
        protected BlockState toBlockState(FluidState fluidState) {
            // getBlockStateLevel converts the LEVEL_1_8 of the fluid state to the LEVEL_15 the fluid block uses
            return ModFluids.DEAD_MUTROLEUM_BLOCK_LIME.getDefaultState().
                    with(Properties.LEVEL_15, getBlockStateLevel(fluidState));
        }
        @Override
        public Fluid getFlowing() {
            return ModFluids.FLOWING_DEAD_MUTROLEUM_LIME;
        }
    }
    public static class StillYellow extends Still{
        @Override
        protected int stateConstructor() {
            return 13;
        }
        @Override
        public Fluid getStill() {
            return ModFluids.STILL_DEAD_MUTROLEUM_YELLOW;
        }
        @Override
        protected BlockState toBlockState(FluidState fluidState) {
            // getBlockStateLevel converts the LEVEL_1_8 of the fluid state to the LEVEL_15 the fluid block uses
            return ModFluids.DEAD_MUTROLEUM_BLOCK_YELLOW.getDefaultState().
                    with(Properties.LEVEL_15, getBlockStateLevel(fluidState));
        }
        @Override
        public Fluid getFlowing() {
            return ModFluids.FLOWING_DEAD_MUTROLEUM_YELLOW;
        }
    }
    public static class StillOrange extends Still{
        @Override
        protected int stateConstructor() {
            return 14;
        }
        @Override
        public Fluid getStill() {
            return ModFluids.STILL_DEAD_MUTROLEUM_ORANGE;
        }
        @Override
        protected BlockState toBlockState(FluidState fluidState) {
            // getBlockStateLevel converts the LEVEL_1_8 of the fluid state to the LEVEL_15 the fluid block uses
            return ModFluids.DEAD_MUTROLEUM_BLOCK_ORANGE.getDefaultState().
                    with(Properties.LEVEL_15, getBlockStateLevel(fluidState));
        }
        @Override
        public Fluid getFlowing() {
            return ModFluids.FLOWING_DEAD_MUTROLEUM_ORANGE;
        }
    }
    public static class StillBrown extends Still{
        @Override
        protected int stateConstructor() {
            return 15;
        }
        @Override
        public Fluid getStill() {
            return ModFluids.STILL_DEAD_MUTROLEUM_BROWN;
        }
        @Override
        protected BlockState toBlockState(FluidState fluidState) {
            // getBlockStateLevel converts the LEVEL_1_8 of the fluid state to the LEVEL_15 the fluid block uses
            return ModFluids.DEAD_MUTROLEUM_BLOCK_BROWN.getDefaultState().
                    with(Properties.LEVEL_15, getBlockStateLevel(fluidState));
        }
        @Override
        public Fluid getFlowing() {
            return ModFluids.FLOWING_DEAD_MUTROLEUM_BROWN;
        }
    }

    public static class FlowingWhite extends Flowing{
        @Override
        protected FlowableFluid stillfluidProvider() {
            return ModFluids.STILL_DEAD_MUTROLEUM_WHITE;
        }

        @Override
        protected Block blockProvider() {
            return ModFluids.DEAD_MUTROLEUM_BLOCK_WHITE;
        }
        @Override
        public Fluid getFlowing() {
            return ModFluids.FLOWING_DEAD_MUTROLEUM_WHITE;
        }
    }
    public static class FlowingLightGray extends Flowing{
        @Override
        protected FlowableFluid stillfluidProvider() {
            return ModFluids.STILL_DEAD_MUTROLEUM_LIGHTGRAY;
        }

        @Override
        protected Block blockProvider() {
            return ModFluids.DEAD_MUTROLEUM_BLOCK_LIGHTGRAY;
        }
        @Override
        public Fluid getFlowing() {
            return ModFluids.FLOWING_DEAD_MUTROLEUM_LIGHTGRAY;
        }
    }
    public static class FlowingGray extends Flowing{
        @Override
        protected FlowableFluid stillfluidProvider() {
            return ModFluids.STILL_DEAD_MUTROLEUM_GRAY;
        }

        @Override
        protected Block blockProvider() {
            return ModFluids.DEAD_MUTROLEUM_BLOCK_GRAY;
        }
        @Override
        public Fluid getFlowing() {
            return ModFluids.FLOWING_DEAD_MUTROLEUM_GRAY;
        }
    }
    public static class FlowingBlack extends Flowing{
        @Override
        protected FlowableFluid stillfluidProvider() {
            return ModFluids.STILL_DEAD_MUTROLEUM_BLACK;
        }

        @Override
        protected Block blockProvider() {
            return ModFluids.DEAD_MUTROLEUM_BLOCK_BLACK;
        }
        @Override
        public Fluid getFlowing() {
            return ModFluids.FLOWING_DEAD_MUTROLEUM_BLACK;
        }
    }
    public static class FlowingPink extends Flowing{
        @Override
        protected FlowableFluid stillfluidProvider() {
            return ModFluids.STILL_DEAD_MUTROLEUM_PINK;
        }

        @Override
        protected Block blockProvider() {
            return ModFluids.DEAD_MUTROLEUM_BLOCK_PINK;
        }
        @Override
        public Fluid getFlowing() {
            return ModFluids.FLOWING_DEAD_MUTROLEUM_PINK;
        }
    }
    public static class FlowingRed extends Flowing{
        @Override
        protected FlowableFluid stillfluidProvider() {
            return ModFluids.STILL_DEAD_MUTROLEUM_RED;
        }

        @Override
        protected Block blockProvider() {
            return ModFluids.DEAD_MUTROLEUM_BLOCK_RED;
        }
        @Override
        public Fluid getFlowing() {
            return ModFluids.FLOWING_DEAD_MUTROLEUM_RED;
        }
    }
    public static class FlowingMagenta extends Flowing{
        @Override
        protected FlowableFluid stillfluidProvider() {
            return ModFluids.STILL_DEAD_MUTROLEUM_MAGENTA;
        }

        @Override
        protected Block blockProvider() {
            return ModFluids.DEAD_MUTROLEUM_BLOCK_MAGENTA;
        }
        @Override
        public Fluid getFlowing() {
            return ModFluids.FLOWING_DEAD_MUTROLEUM_MAGENTA;
        }
    }
    public static class FlowingPurple extends Flowing{
        @Override
        protected FlowableFluid stillfluidProvider() {
            return ModFluids.STILL_DEAD_MUTROLEUM_PURPLE;
        }

        @Override
        protected Block blockProvider() {
            return ModFluids.DEAD_MUTROLEUM_BLOCK_PURPLE;
        }
        @Override
        public Fluid getFlowing() {
            return ModFluids.FLOWING_DEAD_MUTROLEUM_PURPLE;
        }
    }
    public static class FlowingBlue extends Flowing{
        @Override
        protected FlowableFluid stillfluidProvider() {
            return ModFluids.STILL_DEAD_MUTROLEUM_BLUE;
        }

        @Override
        protected Block blockProvider() {
            return ModFluids.DEAD_MUTROLEUM_BLOCK_BLUE;
        }
        @Override
        public Fluid getFlowing() {
            return ModFluids.FLOWING_DEAD_MUTROLEUM_BLUE;
        }
    }
    public static class FlowingLightBlue extends Flowing{
        @Override
        protected FlowableFluid stillfluidProvider() {
            return ModFluids.STILL_DEAD_MUTROLEUM_LIGHTBLUE;
        }

        @Override
        protected Block blockProvider() {
            return ModFluids.DEAD_MUTROLEUM_BLOCK_LIGHTBLUE;
        }
        @Override
        public Fluid getFlowing() {
            return ModFluids.FLOWING_DEAD_MUTROLEUM_LIGHTBLUE;
        }
    }
    public static class FlowingCyan extends Flowing{
        @Override
        protected FlowableFluid stillfluidProvider() {
            return ModFluids.STILL_DEAD_MUTROLEUM_CYAN;
        }

        @Override
        protected Block blockProvider() {
            return ModFluids.DEAD_MUTROLEUM_BLOCK_CYAN;
        }
        @Override
        public Fluid getFlowing() {
            return ModFluids.FLOWING_DEAD_MUTROLEUM_CYAN;
        }
    }
    public static class FlowingGreen extends Flowing{
        @Override
        protected FlowableFluid stillfluidProvider() {
            return ModFluids.STILL_DEAD_MUTROLEUM_GREEN;
        }

        @Override
        protected Block blockProvider() {
            return ModFluids.DEAD_MUTROLEUM_BLOCK_GREEN;
        }
        @Override
        public Fluid getFlowing() {
            return ModFluids.FLOWING_DEAD_MUTROLEUM_GREEN;
        }
    }
    public static class FlowingLime extends Flowing{
        @Override
        protected FlowableFluid stillfluidProvider() {
            return ModFluids.STILL_DEAD_MUTROLEUM_LIME;
        }

        @Override
        protected Block blockProvider() {
            return ModFluids.DEAD_MUTROLEUM_BLOCK_LIME;
        }
        @Override
        public Fluid getFlowing() {
            return ModFluids.FLOWING_DEAD_MUTROLEUM_LIME;
        }
    }
    public static class FlowingYellow extends Flowing{
        @Override
        protected FlowableFluid stillfluidProvider() {
            return ModFluids.STILL_DEAD_MUTROLEUM_YELLOW;
        }

        @Override
        protected Block blockProvider() {
            return ModFluids.DEAD_MUTROLEUM_BLOCK_YELLOW;
        }
        @Override
        public Fluid getFlowing() {
            return ModFluids.FLOWING_DEAD_MUTROLEUM_YELLOW;
        }
    }
    public static class FlowingOrange extends Flowing{
        @Override
        protected FlowableFluid stillfluidProvider() {
            return ModFluids.STILL_DEAD_MUTROLEUM_ORANGE;
        }

        @Override
        protected Block blockProvider() {
            return ModFluids.DEAD_MUTROLEUM_BLOCK_ORANGE;
        }
        @Override
        public Fluid getFlowing() {
            return ModFluids.FLOWING_DEAD_MUTROLEUM_ORANGE;
        }
    }
    public static class FlowingBrown extends Flowing{
        @Override
        protected FlowableFluid stillfluidProvider() {
            return ModFluids.STILL_DEAD_MUTROLEUM_BROWN;
        }

        @Override
        protected Block blockProvider() {
            return ModFluids.DEAD_MUTROLEUM_BLOCK_BROWN;
        }
        @Override
        public Fluid getFlowing() {
            return ModFluids.FLOWING_DEAD_MUTROLEUM_BROWN;
        }
    }
}