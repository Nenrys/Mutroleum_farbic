package net.nenrys.mutroleum.genetics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Species {
    private Gene[] GChrom = {Gene.N, Gene.N, Gene.N, Gene.N, Gene.N, Gene.N, Gene.N, Gene.N};
    private Gene[] IChrom = {Gene.N, Gene.N, Gene.N, Gene.N, Gene.N};
    private Gene[] PChrom = {Gene.N, Gene.N, Gene.N, Gene.N, Gene.N, Gene.N};
    private Gene[] CChrom = {Gene.N, Gene.N, Gene.N, Gene.N, Gene.N, Gene.N};
    private Gene[] MChrom = {Gene.N, Gene.N, Gene.N, Gene.N, Gene.N, Gene.N, Gene.N};
    private Gene[] SChrom = {Gene.N, Gene.N, Gene.N, Gene.N, Gene.N, Gene.N};

    public static final int[] Chromlen = {8, 5, 6, 6, 7, 6};

    private Gene[][] chroms = {GChrom, IChrom, PChrom, CChrom, MChrom, SChrom};

    private final String NAME;
    private int stage;

    public Species(String name) {
        this.NAME = name;
        this.stage = 1;
    }

    public Species() {
        this("__default__");
    }

    public void setGene(int chrom, int gene, Gene acid) {
        //getChrom(Math.max(chrom,6))[gene] = acid;
        this.chroms[chrom][gene] = acid;
    }

    private Gene[] getChrom(int chrom) {
        return this.chroms[chrom];
    }

    public Gene getGene(int chrom, int gene) {
        return getChrom(chrom)[gene];
    }

    public void increaseStage() {
        if (this.stage < 6) {
            this.stage += 1;
        }
    }

    public String getName() {
        return this.NAME;
    }

    public ArrayList<Gene> getGenes() {
        ArrayList<Gene> genes = new ArrayList<>();

        for (Gene[] chrom : chroms) {
            genes.addAll(Arrays.asList(chrom));
        }
        return genes;
    }

    public static Gene char2Gene(char c) {
        return switch (c) {
            case 'A':
                yield Gene.A;
            case 'T':
                yield Gene.T;
            case 'C':
                yield Gene.C;
            case 'G':
                yield Gene.G;
            default:
                yield Gene.N;
        };
    }
}
