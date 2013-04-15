package com.dumptruckman.minecraft.actionmenu;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class Menus {

    @NotNull
    public static MenuModel newMenuModel() {
        return new MenuModel(new ArrayList<MenuItem>());
    }

    @NotNull
    public static MenuModel newMenuModel(@NotNull final java.util.Collection<MenuItem> initialContents) {
        return new MenuModel(new ArrayList<MenuItem>(initialContents));
    }

    @NotNull
    public static MenuModel newMenuModel(final int initialCapacity) {
        return new MenuModel(new ArrayList<MenuItem>(initialCapacity));
    }

    @NotNull
    public static MenuItem newMenuItem(@NotNull final String itemText, @Nullable final Action itemAction) {
        return new MenuItem(itemText, itemAction);
    }

    @NotNull
    public static MenuItem newMenuItem(@NotNull final String itemText) {
        return new MenuItem(itemText, null);
    }
}
