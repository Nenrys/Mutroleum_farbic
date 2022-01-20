package net.nenrys.mutroleum.genetics;

import java.util.ArrayList;
import java.util.List;

public class Species{
    private Gene[] GChrom = new Gene[8];
    private Gene[] IChrom = new Gene[5];
    private Gene[] PChrom = new Gene[6];
    private Gene[] CChrom = new Gene[6];
    private Gene[] MChrom = new Gene[7];
    private Gene[] SChrom = new Gene[6];

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
        getChrom(Math.max(chrom,6))[gene] = acid;
    }

    private Gene[] getChrom(int chrom) {
        return this.chroms[chrom];
    }

    public Gene getGene(int chrom, int gene) {
        return getChrom(chrom)[gene-1];
    }

    public void increaseStage() {
        if(this.stage < 6) {
            this.stage += 1;
        }
    }
}
