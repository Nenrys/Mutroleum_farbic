package net.nenrys.mutroleum.blocks;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.EnchantingTableBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.*;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.Nameable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.nenrys.mutroleum.blocks.blockentities.OrganismChamberEntity;
import net.nenrys.mutroleum.gui.OrganismChamberScreenHandler;
import org.jetbrains.annotations.Nullable;

public class OrganismChamber extends BlockWithEntity {
	public OrganismChamber(Settings settings) {
		super(settings);
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new OrganismChamberEntity(pos, state);
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		if (!world.isClient()) {
			NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);

			if (screenHandlerFactory != null) {
				player.openHandledScreen(screenHandlerFactory);
			}
		}

		return ActionResult.SUCCESS;
	}

	@Override
	public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
		if (state.getBlock() != newState.getBlock()) {
			BlockEntity blockEntity = world.getBlockEntity(pos);
			if (blockEntity instanceof OrganismChamberEntity) {
				ItemScatterer.spawn(world, pos, (Inventory) blockEntity);
				// update comparators
				world.updateComparators(pos,this);
			}
			super.onStateReplaced(state, world, pos, newState, moved);
		}
	}

	@Override
	@Nullable
	public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		assert blockEntity != null;
		Text text = ((Nameable)(blockEntity)).getDisplayName();
		if (blockEntity instanceof OrganismChamberEntity) {
			return new SimpleNamedScreenHandlerFactory((syncId, inventory, player) -> new OrganismChamberScreenHandler(syncId, inventory, ScreenHandlerContext.create(world, pos)), text);
		}
		return null;
	}

	@Override
	public boolean hasComparatorOutput(BlockState state) {
		return false;
	}

	@Override
	public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
		return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
	}
}