package net.nenrys.mutroleum.blocks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nenrys.mutroleum.Mutroleum;
import net.nenrys.mutroleum.items.ModItems;

public class ModBlocks {

    //__0 simple blocks, comment reference for python (don't remove)
    public static final Block P_MUTROLEUM_BLOCK = new Block(
            FabricBlockSettings.of(Material.AMETHYST).strength(2.0f));

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
        registerBlockandItem("p_mutroleum_block", P_MUTROLEUM_BLOCK);
    }

    public static void registerModItems() {
        Mutroleum.LOGGER.info("Registring mod blocks for " + Mutroleum.MOD_ID);

        registerBlocks();
    }
}
