package com.dumptruckman.minecraft.actionmenu.views;

import com.dumptruckman.minecraft.actionmenu.Menu;
import com.dumptruckman.minecraft.actionmenu.MenuItem;
import com.dumptruckman.minecraft.actionmenu.MenuView;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.jetbrains.annotations.NotNull;

public class BookMenuView implements MenuView {

    final ItemStack bookItem;
    final BookMeta bookMeta;
    final int pageNumber;

    public BookMenuView(@NotNull final ItemStack item, final int pageNumber) {
        if (item.getType() != Material.WRITTEN_BOOK) {
            throw new IllegalArgumentException("Item must be written book!");
        }
        bookItem = item;
        bookMeta = (BookMeta) item.getItemMeta();
        this.pageNumber = pageNumber;
    }

    @Override
    public void updateView(@NotNull final Menu menu, @NotNull final Player viewer) {
        final StringBuilder builder = new StringBuilder();
        builder.append(menu.getText());
        for (int i = 0; i < menu.getModel().size(); i++) {
            if (builder.length() != 0) {
                builder.append("\n");
            }
            final MenuItem item = menu.getModel().get(i);
            if (menu.getSelectedIndex(viewer) == i) {
                builder.append(ChatColor.BLACK).append("> ");
            } else {
                builder.append("  ");
            }
            if (!item.isSelectable()) {
                builder.append(ChatColor.GRAY);
            } else if (menu.getSelectedIndex(viewer) != i) {
                builder.append(ChatColor.BLACK);
            }
            builder.append(item.getText());
        }
        bookMeta.setPage(pageNumber, builder.toString());
        bookItem.setItemMeta(bookMeta);
    }
}
