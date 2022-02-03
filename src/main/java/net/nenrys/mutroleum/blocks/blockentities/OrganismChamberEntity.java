package net.nenrys.mutroleum.blocks.blockentities;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Nameable;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.nenrys.mutroleum.gui.ImplementedInventory;
import net.nenrys.mutroleum.gui.OrganismChamberScreenHandler;
import org.jetbrains.annotations.Nullable;

public class OrganismChamberEntity extends BlockEntity implements NamedScreenHandlerFactory, Nameable {
    //private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);

    private Text customName;

    public OrganismChamberEntity(BlockPos pos, BlockState state) {
        super(BlockEntities.ORGANISM_CHAMBER_ENTITY, pos, state);
    }

    @Override
    public Text getName() {
        if (this.customName != null) {
            return this.customName;
        }
        return this.getDisplayName();
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (nbt.contains("CustomName", 8)) {
            this.customName = Text.Serializer.fromJson(nbt.getString("CustomName"));
        }
    }

    @Override
    public Text getDisplayName() {
        return new TranslatableText(getCachedState().getBlock().getTranslationKey());
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new OrganismChamberScreenHandler(syncId, inv);
    }

/*
    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

 */
}
