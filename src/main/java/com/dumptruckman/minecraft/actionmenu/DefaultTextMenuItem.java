package com.dumptruckman.minecraft.actionmenu;

import org.jetbrains.annotations.NotNull;

class DefaultTextMenuItem extends DefaultMenuItem implements TextMenuItem {

    @NotNull
    private String text;

    public DefaultTextMenuItem(@NotNull final String text) {
        this.text = text;
    }

    @NotNull
    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(@NotNull final String text) {
        this.text = text;
    }
}
