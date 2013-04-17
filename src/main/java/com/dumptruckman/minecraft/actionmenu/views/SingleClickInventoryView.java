package com.dumptruckman.minecraft.actionmenu.views;

import com.dumptruckman.minecraft.actionmenu.Menu;
import com.dumptruckman.minecraft.actionmenu.MenuItem;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class SingleClickInventoryView implements InventoryView {

    @NotNull
    private final Inventory inventory;

    public SingleClickInventoryView(@NotNull final Inventory inventory) {
        this.inventory = inventory;
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public void indexSelected(@NotNull final Menu menu, @NotNull final Player player, final int index) {
        final MenuItem item = menu.getItem(index);
        if (item != null && !item.getText().isEmpty()) {
            if (item.isSelectable()) {
                player.sendMessage(item.getText());
            } else {
                player.sendMessage(ChatColor.GRAY.toString() + ChatColor.ITALIC + "(Not Selectable) "
                        + ChatColor.RESET + item.getText());
            }
        }
    }

    @Override
    public void updateView(@NotNull final Menu menu, @NotNull final Player viewer) {
        final Inventory inv = getInventory();
        for (int i = 0; i < inv.getSize() && i < menu.getModel().size(); i++) {
            final MenuItem item = menu.getItem(i);
            if  (item != null && item.getItemStack() != null) {
                inv.setItem(i, item.getItemStack());
            } else {
                inv.clear(i);
            }
        }
        viewer.openInventory(getInventory());
    }
}
