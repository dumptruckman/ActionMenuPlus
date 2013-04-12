package com.dumptruckman.minecraft.actionmenu;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MenuItem {

    @NotNull
    public static MenuItem createItem(@NotNull final String text) {
        return new MenuItem(text, null);
    }

    @NotNull
    public static MenuItem createItem(@NotNull final String text, @Nullable final Action action) {
        return new MenuItem(text, action);
    }

    @Nullable
    private Action itemAction;

    private boolean selectable = true;
    @NotNull
    private String text;

    protected MenuItem(@NotNull final String text, @Nullable final Action action) {
        this.text = text;
        this.itemAction = action;
    }

    public void performAction(@NotNull final Player user) {
        if (getAction() != null) {
            getAction().performAction(user);
        }
    }

    public Action getAction() {
        return itemAction;
    }

    public void setAction(@NotNull final Action action) {
        this.itemAction = action;
    }

    public boolean isSelectable() {
        return selectable;
    }

    public void setSelectable(final boolean selectable) {
        this.selectable = selectable;
    }

    @NotNull
    public String getText() {
        return text;
    }

    public void setText(@NotNull final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "text='" + text + '\'' +
                '}';
    }
}
