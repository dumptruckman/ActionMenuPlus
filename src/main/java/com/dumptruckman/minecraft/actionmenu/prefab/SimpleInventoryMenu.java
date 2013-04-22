package com.dumptruckman.minecraft.actionmenu.prefab;

import com.dumptruckman.minecraft.actionmenu.Menu;
import com.dumptruckman.minecraft.actionmenu.MenuModel;
import com.dumptruckman.minecraft.actionmenu.views.InventoryView;
import com.dumptruckman.minecraft.actionmenu.views.SingleClickInventoryView;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class SimpleInventoryMenu extends SingleViewMenu implements InventoryView {

    public SimpleInventoryMenu(@NotNull final Plugin plugin, @NotNull final String title, final int inventorySize) {
        super(title, new SingleClickInventoryView(plugin, Bukkit.createInventory(null, inventorySize, title)), new MenuModel(), true, true);
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        return ((InventoryView) getView()).getInventory();
    }

    @Override
    public void indexSelected(@NotNull final Menu menu, @NotNull final Player player, final int index) {
        ((InventoryView) getView()).indexSelected(menu, player, index);
    }
}
