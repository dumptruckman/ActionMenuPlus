package com.dumptruckman.minecraft.actionmenu;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public abstract class MenuView {

    @NotNull
    private Menu menu;

    protected MenuView(@NotNull final Menu menu) {
        this.menu = menu;
    }

    public abstract void showMenu(@NotNull final Menu menu, @NotNull final Player viewer);

    @NotNull
    public Menu getMenu() {
        return menu;
    }

    void setMenu(@NotNull final Menu menu) {
        this.menu = menu;
    }
}
