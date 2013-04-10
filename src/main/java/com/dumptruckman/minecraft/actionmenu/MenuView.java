package com.dumptruckman.minecraft.actionmenu;

import org.jetbrains.annotations.NotNull;

public interface MenuView {

    void displayMenu(@NotNull final Menu menu);

    @NotNull
    Menu getMenu();

    void setMenu(@NotNull final Menu menu);
}
