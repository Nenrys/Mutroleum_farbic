package net.nenrys.mutroleum.misc;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;

import net.nenrys.mutroleum.genetics.Gene;
import net.nenrys.mutroleum.genetics.HasGenesImpl;
import net.nenrys.mutroleum.genetics.IHasGenes;
import net.nenrys.mutroleum.genetics.Species;

public class MutroleumCommands<T> {

    @FunctionalInterface
    public interface Icommand {
        void command(CommandContext<ServerCommandSource> context) throws CommandSyntaxException;
    }

    private static void registerCommand(String command, Icommand function) {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> dispatcher.register(CommandManager.literal(command).executes(context -> {
            function.command(context);
            return 1;
        })));
    }

    public static void registerCommands() {
        registerCommand("mu_checkspecies", context -> {
            PlayerEntity player = context.getSource().getPlayer();
            ItemStack itemstack = player.getMainHandStack();
            String spec;
            if (itemstack.hasNbt()) {
                assert itemstack.getNbt() != null;
                if (itemstack.getNbt().contains("species")) {
                    IHasGenes hasGenes = new HasGenesImpl();
                    spec = "Species: " + hasGenes.getStringfromSpecies(hasGenes.getSpeciesFromItem(itemstack));
                } else {
                    spec = "Has no species";
                }
            } else {
                spec = "Has no species";
            }

            player.sendMessage(new LiteralText(spec),false);
        });

        registerCommand("mu_addspecies", context -> {
            PlayerEntity player = context.getSource().getPlayer();
            ItemStack itemstack = player.getMainHandStack();
            IHasGenes hasGenes = new HasGenesImpl();
            Species species = new Species("addspecies");
            species.setGene(0,0, Gene.G);
            species.setGene(0,1,Gene.A);
            hasGenes.addSpeciesToItem(itemstack,species);
        });

        registerCommand("mu_addspecies2", context -> {
            PlayerEntity player = context.getSource().getPlayer();
            ItemStack itemstack = player.getMainHandStack();
            IHasGenes hasGenes = new HasGenesImpl();
            Species species = new Species("addspecies2");
            species.setGene(0,0, Gene.C);
            species.setGene(0,1,Gene.A);
            species.setGene(0,2, Gene.A);
            species.setGene(0,3,Gene.A);
            hasGenes.addSpeciesToItem(itemstack,species);
        });
    }
}
