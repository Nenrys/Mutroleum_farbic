package net.nenrys.mutroleum.world.ores;

import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.*;
import net.minecraft.world.gen.feature.*;
import net.nenrys.mutroleum.Mutroleum;
import net.nenrys.mutroleum.blocks.ModBlocks;
import org.lwjgl.system.CallbackI;

public class MutroleumOreClass {

    private static final ConfiguredFeature<?, ?> OVERWORLD_MUTROLEUM_ORE_CONFIGURED_FEATURE =
            Feature.ORE.configure(new OreFeatureConfig(
                    OreConfiguredFeatures.STONE_ORE_REPLACEABLES, ModBlocks.ORE_MUTROLEUM.getDefaultState(), 9));

    private static final PlacedFeature OVERWORLD_MUTROLEUM_ORE_PLACED_FEATURE =
            OVERWORLD_MUTROLEUM_ORE_CONFIGURED_FEATURE.withPlacement(
                    CountPlacementModifier.of(10), SquarePlacementModifier.of(),
                    HeightRangePlacementModifier.uniform(YOffset.aboveBottom(12),YOffset.fixed(40)));

    public static void registerOreFeatures() {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
                new Identifier(Mutroleum.MOD_ID, "overworld_mutroleum_ore"),
                OVERWORLD_MUTROLEUM_ORE_CONFIGURED_FEATURE);

        Registry.register(BuiltinRegistries.PLACED_FEATURE,
                new Identifier(Mutroleum.MOD_ID, "overworld_mutroleum_ore"),
                OVERWORLD_MUTROLEUM_ORE_PLACED_FEATURE);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY,
                        new Identifier(Mutroleum.MOD_ID, "overworld_mutroleum_ore")));
    }
}
