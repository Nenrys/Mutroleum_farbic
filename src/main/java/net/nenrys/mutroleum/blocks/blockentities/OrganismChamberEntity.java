package net.nenrys.mutroleum.blocks.blockentities;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.nenrys.mutroleum.gui.ImplementedInventory;
import net.nenrys.mutroleum.gui.OrganismChamberScreenHandler;
import org.jetbrains.annotations.Nullable;

public class OrganismChamberEntity extends BlockEntity implements NamedScreenHandlerFactory {
    //private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);

    public OrganismChamberEntity(BlockPos pos, BlockState state) {
        super(BlockEntities.ORGANISM_CHAMBER_ENTITY, pos, state);
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new OrganismChamberScreenHandler(syncId,inv);
    }

    @Override
    public Text getDisplayName() {
        return new TranslatableText(getCachedState().getBlock().getTranslationKey());
    }

/*
    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

 */
}
