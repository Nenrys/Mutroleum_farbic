package net.nenrys.mutroleum.items;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtil;
import net.nenrys.mutroleum.fluids.ModFluids;
import net.nenrys.mutroleum.genetics.HasGenesImpl;
import net.nenrys.mutroleum.genetics.SpeciesColors;

@Environment(value= EnvType.CLIENT)
public class ColorItems {

    public static void registerColor(){
        ColorProviderRegistry.ITEM.register((item, tintIndex) -> {
            HasGenesImpl hasgenes = new HasGenesImpl();
            int color = -1;
            if (tintIndex == 0) {color = SpeciesColors.getColorFromSpecies(hasgenes.getSpeciesFromItem(item))[0];}
            if (tintIndex == 1) {color = SpeciesColors.getColorFromSpecies(hasgenes.getSpeciesFromItem(item))[1];}
            return color;
        }, ModItems.TESTSPECIES);

        ColorProviderRegistry.ITEM.register((item, tintIndex) -> {
            HasGenesImpl hasgenes = new HasGenesImpl();
            int color = -1;
            if (tintIndex == 1) {color = SpeciesColors.getColorFromSpecies(hasgenes.getSpeciesFromItem(item))[0];}
            return color;
        }, ModFluids.DEAD_MUTROLEUM_BUCKET);
    }

}
