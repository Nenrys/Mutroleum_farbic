package net.nenrys.mutroleum.blocks.blockentities;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nenrys.mutroleum.Mutroleum;
import net.nenrys.mutroleum.blocks.ModBlocks;

public class BlockEntities {

    public static BlockEntityType<OrganismChamberEntity> ORGANISM_CHAMBER_ENTITY;

    public static void registerBlockEntities() {
        ORGANISM_CHAMBER_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "mutroleum:organism_chamber_entity",
                FabricBlockEntityTypeBuilder.create(OrganismChamberEntity::new, ModBlocks.ORGANISMCHAMBER).build(null));
    }
}
