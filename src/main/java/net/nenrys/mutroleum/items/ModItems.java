package net.nenrys.mutroleum.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nenrys.mutroleum.Mutroleum;

public class ModItems {

    //__0 simple items, comment reference for python (don't remove)
    public static final Item P_MUTROLEUM = new Item(new FabricItemSettings().group(ItemGroup.MISC));
    public static final Item C_MUTROLEUM = new Item(new FabricItemSettings().group(ItemGroup.MISC));

    private static void registerItem(String name, Item item) {
        Registry.register(Registry.ITEM, new Identifier(Mutroleum.MOD_ID, name), item);
    }

    private static void registerItems() {
        //__1 registering all items, also entrypoint for python (don't remove)
        registerItem("p_mutroleum", P_MUTROLEUM);
        registerItem("c_mutroleum", C_MUTROLEUM);
    }

    public static void registerModItems() {
        Mutroleum.LOGGER.info("Registring mod items for " + Mutroleum.MOD_ID);

        registerItems();
    }
}
