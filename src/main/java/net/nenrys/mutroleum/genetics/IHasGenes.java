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
        NbtCompound nbtCompound = stack.getOrCreateNbt();

        String stringtoput = getStringfromSpecies(species);
        nbtCompound.putString("species", stringtoput);
    }

    default String getStringfromSpecies(Species species) {
        StringBuilder stringtoput = new StringBuilder("_n_" + species.getName() + "_s_");
        stringtoput.append(species.getStage());
        stringtoput.append("_g_");
        ArrayList<Gene> genes = species.getGenes();
        for (Gene gene : genes) {
            stringtoput.append(gene.toString());
        }
        stringtoput.append("_a_");
        int[] attrs = species.getAttributes();
        for (int attr:attrs) {
            stringtoput.append(attr);
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

        int nameindex = speciesstring.indexOf("_n_");
        int stageindex = speciesstring.indexOf("_s_");
        int geneindex = speciesstring.indexOf("_g_");
        int attrsindex = speciesstring.indexOf("_a_");

        String name = speciesstring.substring(nameindex + 3, stageindex);
        Species species = new Species(name);

        int stage = Integer.parseInt(speciesstring.substring(stageindex+3,geneindex));
        species.setStage(stage);

        String genestring = speciesstring.substring(geneindex+3,attrsindex);
        int genenr = 0;
        for (int i = 0; i<6;i++) {
            for (int j = 0; j < Species.Chromlen[i];j++) {
                species.setGene(i,j,Species.char2Gene(genestring.charAt(genenr)));
                genenr += 1;
            }
        }

        int reg = Integer.parseInt(speciesstring.substring(attrsindex+3)) -1;
        int pow = Integer.parseInt(speciesstring.substring(attrsindex+4)) -1;
        int dom = Integer.parseInt(speciesstring.substring(attrsindex+5)) -1;
        int pro = Integer.parseInt(speciesstring.substring(attrsindex+6)) -1;
        species.addReg(reg);
        species.addPow(pow);
        species.addDom(dom);
        species.addPro(pro);

        return species;
    }
}
