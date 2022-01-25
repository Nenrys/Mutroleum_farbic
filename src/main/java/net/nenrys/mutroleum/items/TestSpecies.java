package net.nenrys.mutroleum.items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.nenrys.mutroleum.genetics.IHasGenes;
import net.nenrys.mutroleum.genetics.Species;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TestSpecies extends Item implements IHasGenes {

	Species species = new Species("Testspecies");

	public TestSpecies(Settings settings) {
		super(settings);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		ItemStack itemstack = user.getStackInHand(hand);

		if (world.isClient()) {
			if (itemstack.hasNbt()) {
				assert itemstack.getNbt() != null;
				if (itemstack.getNbt().contains("species")) {
					user.sendMessage(new LiteralText("Testing with species: " +this.getStringfromSpecies(this.getSpeciesFromItem(itemstack))), false);
					return TypedActionResult.success(itemstack);
				}
			}
			user.sendMessage(new LiteralText("No species on item"), false);
		}
		return TypedActionResult.pass(itemstack);
	}

	@Override
	public ItemStack getDefaultStack() {
		ItemStack stack = new ItemStack(this);
		this.addSpeciesToItem(stack, new Species("getdefaultstack"));
		return stack;
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
		tooltip.add(new LiteralText(this.getSpeciesFromItem(stack).getName()));
	}


	@Override
	public Species getSpecies() {
		return this.getSpeciesFromItem(this.getDefaultStack());
	}


	@Override
	public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
		if (this.isIn(group)) {
			ItemStack stack = new ItemStack(this);
			Species species1 = new Species("Testspeciesappend");
			this.addSpeciesToItem(stack, species1);
			stacks.add(stack);
		}
	}


}