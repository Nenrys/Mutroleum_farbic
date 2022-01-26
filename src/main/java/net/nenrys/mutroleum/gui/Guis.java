package net.nenrys.mutroleum.gui;

import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.nenrys.mutroleum.Mutroleum;
import net.nenrys.mutroleum.blocks.ModBlocks;
import net.nenrys.mutroleum.blocks.OrganismChamber;

public class Guis {

    public static ScreenHandlerType<OrganismChamberScreenHandler> ORGANISM_CHAMBER_SCREEN_HANLDER;

    public static void registerGuis() {

        ORGANISM_CHAMBER_SCREEN_HANLDER = ScreenHandlerRegistry
                .registerSimple(new Identifier(Mutroleum.MOD_ID, "organism_chamber"), OrganismChamberScreenHandler::new);
    }

    public static void registerGuisClient() {
        ScreenRegistry.register(Guis.ORGANISM_CHAMBER_SCREEN_HANLDER, OrganismChamberScreen::new);
    }
}
