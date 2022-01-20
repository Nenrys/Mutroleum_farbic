package net.nenrys.mutroleum.genetics;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.biome.Biome;

@Environment(value= EnvType.CLIENT)
public class SpeciesColors {

    private static final int[] colors1 = {0xf9ffff,0x9c9d97,0x474f52,0x1d1c21}; //White, light gray, gray, black
    private static final int[] colors2 = {0x38caa,0xb02e26,0xc64fbd,0x8932b7}; //Pink, red, magenta, purple
    private static final int[] colors3 = {0x3c44a9,0x3ab3da,0x169c9d,0x5d7c15}; //Blue, light blue, cyan, green
    private static final int [] colors4 = {0x80c71f,0xffd83d,0xf9801d,0x825432}; //Lime, yellow, orange, brown

    @FunctionalInterface
    public interface SpeciesColorResolver {
        int getColor(IHasGenes carrier, boolean prim);
    }

    public static final SpeciesColorResolver SPECIES_COLOR = (carrier,prim) -> {
        Gene options = prim ? carrier.getGene(1, 1): carrier.getGene(2, 1);
        Gene col = prim ? carrier.getGene(1, 2): carrier.getGene(2, 2);
        return switch(options) {
            case A:
                yield colors1[col.ordinal()];
            case T:
                yield colors2[col.ordinal()];
            case C:
                yield colors3[col.ordinal()];
            case G:
                yield colors4[col.ordinal()];
            default:
                yield colors2[3];
        };
    };

    public static final SpeciesColorResolver SPECIES_COLOR_nr = (carrier,prim) -> {
        Gene options = prim ? carrier.getGene(1, 1): carrier.getGene(2, 1);
        Gene col = prim ? carrier.getGene(1, 2): carrier.getGene(2, 2);
        return options.ordinal()*4 + col.ordinal();
        };

    public static int getBlockStateColor(IHasGenes carrier) {
        return SPECIES_COLOR_nr.getColor(carrier,true);
    }

}
