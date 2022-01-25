package net.nenrys.mutroleum.genetics;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;

@Environment(value= EnvType.CLIENT)
public class SpeciesColors {

    private static final int[] colors1 = {0xf9ffff,0x9c9d97,0x474f52,0x1d1c21}; //White, light gray, gray, black
    private static final int[] colors2 = {0x38caa,0xb02e26,0xc64fbd,0x8932b7}; //Pink, red, magenta, purple
    private static final int[] colors3 = {0x3c44a9,0x3ab3da,0x169c9d,0x5d7c15}; //Blue, light blue, cyan, green
    private static final int [] colors4 = {0x80c71f,0xffd83d,0xf9801d,0x825432}; //Lime, yellow, orange, brown

    private static final int[][] allcolors = {colors1,colors2,colors3,colors4};

    public static final int DEFAULTCOLOR = 7;

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

    public static int getColorFromState(int col) {
        return allcolors[col/4][col%4];
    }

    public static final SpeciesColorResolver SPECIES_COLOR_nr = (carrier,prim) -> {
        Gene options = prim ? carrier.getGene(0, 0): carrier.getGene(0, 1);
        Gene col = prim ? carrier.getGene(0, 3): carrier.getGene(0, 4);
        if (options == Gene.N) {
            return DEFAULTCOLOR;
        } else {
            return options.ordinal()*4 + col.ordinal();
            }
        };


    public static int[] getColorFromSpecies(Species species) {
        Gene primop = species.getGene(0,0);
        Gene primcol = species.getGene(0,1);
        Gene secop = species.getGene(0,2);
        Gene seccol = species.getGene(0,3);

        int prim = primcol != Gene.N ? switch(primop) {
            case A:
                yield colors1[primcol.ordinal()];
            case T:
                yield colors2[primcol.ordinal()];
            case C:
                yield colors3[primcol.ordinal()];
            case G:
                yield colors4[primcol.ordinal()];
            default:
                yield colors2[3];
        } : colors2[3];

        int sec = seccol != Gene.N ? switch(secop) {
            case A:
                yield colors1[seccol.ordinal()];
            case T:
                yield colors2[seccol.ordinal()];
            case C:
                yield colors3[seccol.ordinal()];
            case G:
                yield colors4[seccol.ordinal()];
            default:
                yield colors2[3];
        }: colors2[3];
        return new int[]{prim, sec};
    }
    public static int getStatefromColor(int color) {
        for (int[] colors : allcolors) {
            for (int i = 0; i < colors.length; i++) {
                if (color == colors[i]) {
                    return i;
                }
            }
        } return DEFAULTCOLOR;
    }

    public static int[] getColorIntFromSpecies(Species species) {
        Gene primop = species.getGene(0, 0);
        Gene primcol = species.getGene(0, 1);
        Gene secop = species.getGene(0, 2);
        Gene seccol = species.getGene(0, 3);

        int retint1;
        int retint2;

        if (primop == Gene.N || primcol == Gene.N) {
            retint1 = 7;
        } else {
            retint1 = primop.ordinal() * 4 + primcol.ordinal();
        }

        if (secop == Gene.N || seccol == Gene.N) {
            retint2 = 7;
        } else {
            retint2 = primop.ordinal() * 4 + primcol.ordinal();
        }
        return new int[]{retint1, retint2};
    }
}
