package net.nenrys.mutroleum.genetics;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

import java.util.ArrayList;

public interface IHasGenes {

    Species getSpecies();

    default Gene getGene(int chrom, int gene) {
        return getSpecies().getGene(chrom,gene);
    }

    default void addSpeciesToItem(ItemStack stack, Species species) {
        StringBuilder stringtoput = new StringBuilder(species.getName() + "__");
        NbtCompound nbtCompound = stack.getOrCreateNbt();
        ArrayList<Gene> genes = species.getGenes();
        for (Gene gene : genes) {
            stringtoput.append(gene.toString());
        }

        nbtCompound.putString("species", stringtoput.toString());
    }

    default String getStringfromSpecies(Species species) {
        StringBuilder stringtoput = new StringBuilder(species.getName() + "__");
        ArrayList<Gene> genes = species.getGenes();
        for (Gene gene : genes) {
            stringtoput.append(gene.toString());
        }
        return stringtoput.toString();
    }

    default Species getSpeciesFromColor(int col, boolean prim) {
        Species species = new Species("unknown");
        int primorsec = prim ? 0 : 2;
        Gene opgene = switch(col) {
            case 0,1,2,3: yield Gene.A;
            case 4,5,6,7: yield Gene.T;
            case 8,9,10,11: yield Gene.C;
            case 12,13,14,15: yield Gene.G;
            default: yield Gene.N;
        };
        Gene colgene = switch(col%4) {
            case 0: yield Gene.A;
            case 1: yield Gene.T;
            case 2: yield Gene.C;
            case 3: yield Gene.G;
            default: yield Gene.N;
        };
        species.setGene(0,primorsec,opgene);
        species.setGene(0,1+primorsec,colgene);
        return species;
    }

    default Species getSpeciesFromItem(ItemStack stack) {
        if (!stack.hasNbt()){
            return new Species("Error species, bad1");
        }
        assert stack.getNbt() != null;
        if (!stack.getNbt().contains("species")) {
            return new Species("Error Species, bad2");
        }
        String speciesstring = stack.getNbt().getString("species");
        int nameindex = speciesstring.indexOf("__");
        String name = speciesstring.substring(0, nameindex);
        Species species = new Species(name);
        String genestring = speciesstring.substring(nameindex + 2);
        int geneindex = 0;
        for (int i = 0; i<6;i++) {
            for (int j = 0; j < Species.Chromlen[i];j++) {
                species.setGene(i,j,Species.char2Gene(genestring.charAt(geneindex)));
                geneindex += 1;
            }
        }
        return species;
    }
}
