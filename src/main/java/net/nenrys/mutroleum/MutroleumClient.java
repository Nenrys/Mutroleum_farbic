package net.nenrys.mutroleum;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import net.nenrys.mutroleum.fluids.DeadMutroleum;
import net.nenrys.mutroleum.fluids.ModFluids;
import net.nenrys.mutroleum.genetics.SpeciesColors;
import net.nenrys.mutroleum.gui.Guis;
import net.nenrys.mutroleum.items.ColorItems;
import org.jetbrains.annotations.Nullable;


public class MutroleumClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        registerMutroleumFluids();

        ColorItems.registerColor();

        Guis.registerGuisClient();
    }

    private static class MutroleumFluidRenderHandler extends SimpleFluidRenderHandler {
        public MutroleumFluidRenderHandler(Identifier stillTexture, Identifier flowingTexture, int tint) {
            super(stillTexture, flowingTexture, tint);
        }

        @Override
        public int getFluidColor(@Nullable BlockRenderView view, @Nullable BlockPos pos, FluidState state) {
            return SpeciesColors.getColorFromState(state.get(DeadMutroleum.COLOR));
        }

    }

    public void registerFluidMutroleum(FlowableFluid stillfluid, FlowableFluid flowingfluid) {
        FluidRenderHandlerRegistry.INSTANCE.register(stillfluid,
                flowingfluid,
                new MutroleumFluidRenderHandler(new Identifier("minecraft:block/water_still"),
                        new Identifier("minecraft:block/water_flow"), 0x8932b7));

        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(),
                stillfluid, flowingfluid);
    }

    public void registerMutroleumFluids() {
        registerFluidMutroleum(ModFluids.STILL_DEAD_MUTROLEUM_WHITE, ModFluids.FLOWING_DEAD_MUTROLEUM_WHITE);
        registerFluidMutroleum(ModFluids.STILL_DEAD_MUTROLEUM_LIGHTGRAY,ModFluids.FLOWING_DEAD_MUTROLEUM_LIGHTGRAY);
        registerFluidMutroleum(ModFluids.STILL_DEAD_MUTROLEUM_GRAY,ModFluids.FLOWING_DEAD_MUTROLEUM_GRAY);
        registerFluidMutroleum(ModFluids.STILL_DEAD_MUTROLEUM_BLACK, ModFluids.FLOWING_DEAD_MUTROLEUM_BLACK);
        registerFluidMutroleum(ModFluids.STILL_DEAD_MUTROLEUM_PINK, ModFluids.FLOWING_DEAD_MUTROLEUM_PINK);
        registerFluidMutroleum(ModFluids.STILL_DEAD_MUTROLEUM_RED, ModFluids.FLOWING_DEAD_MUTROLEUM_RED);
        registerFluidMutroleum(ModFluids.STILL_DEAD_MUTROLEUM_MAGENTA, ModFluids.FLOWING_DEAD_MUTROLEUM_MAGENTA);
        registerFluidMutroleum(ModFluids.STILL_DEAD_MUTROLEUM_PURPLE, ModFluids.FLOWING_DEAD_MUTROLEUM_PURPLE);
        registerFluidMutroleum(ModFluids.STILL_DEAD_MUTROLEUM_BLUE, ModFluids.FLOWING_DEAD_MUTROLEUM_BLUE);
        registerFluidMutroleum(ModFluids.STILL_DEAD_MUTROLEUM_LIGHTBLUE, ModFluids.FLOWING_DEAD_MUTROLEUM_LIGHTBLUE);
        registerFluidMutroleum(ModFluids.STILL_DEAD_MUTROLEUM_CYAN, ModFluids.FLOWING_DEAD_MUTROLEUM_CYAN);
        registerFluidMutroleum(ModFluids.STILL_DEAD_MUTROLEUM_GREEN, ModFluids.FLOWING_DEAD_MUTROLEUM_GREEN);
        registerFluidMutroleum(ModFluids.STILL_DEAD_MUTROLEUM_LIME, ModFluids.FLOWING_DEAD_MUTROLEUM_LIME);
        registerFluidMutroleum(ModFluids.STILL_DEAD_MUTROLEUM_YELLOW, ModFluids.FLOWING_DEAD_MUTROLEUM_YELLOW);
        registerFluidMutroleum(ModFluids.STILL_DEAD_MUTROLEUM_ORANGE, ModFluids.FLOWING_DEAD_MUTROLEUM_ORANGE);
        registerFluidMutroleum(ModFluids.STILL_DEAD_MUTROLEUM_BROWN, ModFluids.FLOWING_DEAD_MUTROLEUM_BROWN);
    }
}
