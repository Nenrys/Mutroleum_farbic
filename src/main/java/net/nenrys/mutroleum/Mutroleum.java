package net.nenrys.mutroleum;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.nenrys.mutroleum.blocks.ModBlocks;
import net.nenrys.mutroleum.blocks.blockentities.BlockEntities;
import net.nenrys.mutroleum.fluids.ModFluids;
import net.nenrys.mutroleum.gui.Guis;
import net.nenrys.mutroleum.items.ModItems;
import net.nenrys.mutroleum.misc.MutroleumCommands;
import net.nenrys.mutroleum.world.ores.MutroleumOreClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Mutroleum implements ModInitializer {

	public static final String MOD_ID = "mutroleum";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	public static final ItemGroup MUTROLEUM_GROUP =
			FabricItemGroupBuilder.build(
					new Identifier(MOD_ID, "mutroleum_group"), () -> new ItemStack(ModItems.P_MUTROLEUM));

	@Override
	public void onInitialize() {


		LOGGER.info("Initializing");
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		MutroleumOreClass.registerOreFeatures();
		ModFluids.registerFluids();
		MutroleumCommands.registerCommands();
		BlockEntities.registerBlockEntities();
		Guis.registerGuis();
	}
}
