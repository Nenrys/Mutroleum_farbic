package net.nenrys.mutroleum.genetics;

public interface IHasGenes {

    Species getSpecies();

    default Gene getGene(int chrom, int gene) {
        return getSpecies().getGene(chrom, gene);
    }

}

