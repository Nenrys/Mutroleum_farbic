package net.nenrys.mutroleum.blocks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;
import net.nenrys.mutroleum.Mutroleum;

public class ModBlocks {

    //__0 simple blocks, comment reference for python (don't remove)
	public static final Block ORGANISMCHAMBER = new OrganismChamber(
			FabricBlockSettings.of(Material.STONE).strength(3.0f));
	public static final Block MUTRATOR = new Mutrator(
			FabricBlockSettings.of(Material.STONE).strength(3.0f));
	public static final Block WAVELENGTH_DETECTOR = new WavelengthDetector(
			FabricBlockSettings.of(Material.STONE).strength(3.0f));
	public static final Block ORE_MUTROLEUM = new OreBlock(AbstractBlock.Settings.of(
            Material.STONE).requiresTool().strength(3.0f, 3.0f), UniformIntProvider.create(3, 7));
    public static final Block P_MUTROLEUM_BLOCK = new PetrifiedMutroleumBlock(
            FabricBlockSettings.of(Material.AMETHYST).strength(2.0f).requiresTool().ticksRandomly());

    //register a single block
    private static void registerBlock(String name, Block block) {
        Registry.register(Registry.BLOCK, new Identifier(Mutroleum.MOD_ID, name), block);
    }
    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registry.ITEM, new Identifier(Mutroleum.MOD_ID, name), new BlockItem(
                block, new FabricItemSettings().group(Mutroleum.MUTROLEUM_GROUP)));
    }
    private static void registerBlockandItem(String name, Block block) {
        registerBlock(name, block);
        registerBlockItem(name, block);
    }

    //register all blocks
    private static void registerBlocks() {
        //__1 register simple blocks, entrypoint for python (don't remove)
		registerBlockandItem("organismchamber", ORGANISMCHAMBER);
		registerBlockandItem("mutrator", MUTRATOR);
		registerBlockandItem("wavelength_detector", WAVELENGTH_DETECTOR);
		registerBlockandItem("ore_mutroleum", ORE_MUTROLEUM);
        registerBlockandItem("p_mutroleum_block", P_MUTROLEUM_BLOCK);
    }

    public static void registerModBlocks() {
        Mutroleum.LOGGER.info("Registring mod blocks for " + Mutroleum.MOD_ID);

        registerBlocks();
    }
}
