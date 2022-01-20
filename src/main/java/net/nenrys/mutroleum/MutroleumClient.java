package net.nenrys.mutroleum;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import net.nenrys.mutroleum.fluids.ModFluids;

public class MutroleumClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_DEAD_MUTROLEUM,
                ModFluids.FLOWING_DEAD_MUTROLEUM,
                new SimpleFluidRenderHandler(new Identifier("minecraft:block/water_still"),
                        new Identifier("minecraft:block/water_flow"), 0x8932b7));

        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(),
                ModFluids.STILL_DEAD_MUTROLEUM, ModFluids.FLOWING_DEAD_MUTROLEUM);
    }
}
