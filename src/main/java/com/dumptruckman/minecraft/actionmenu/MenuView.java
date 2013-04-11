package com.dumptruckman.minecraft.actionmenu;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public interface MenuView {

    void showMenu(@NotNull final Menu menu, @NotNull final CommandSender viewer);

    @NotNull
    Menu getMenu();

    void setMenu(@NotNull final Menu menu);
}
