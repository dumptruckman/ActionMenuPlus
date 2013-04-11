package com.dumptruckman.minecraft.actionmenu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.jetbrains.annotations.NotNull;

public class BookMenuView extends MenuView {

    final ItemStack bookItem;
    final BookMeta bookMeta;
    final int pageNumber;

    public BookMenuView(@NotNull final Menu menu, @NotNull final ItemStack item, final int pageNumber) {
        super(menu);
        if (item.getType() != Material.WRITTEN_BOOK) {
            throw new IllegalArgumentException("Item must be written book!");
        }
        bookItem = item;
        bookMeta = (BookMeta) item.getItemMeta();
        this.pageNumber = pageNumber;
    }

    @Override
    public void showMenu(@NotNull final Menu menu, @NotNull final Player viewer) {


        bookItem.setItemMeta(bookMeta);
    }
}
