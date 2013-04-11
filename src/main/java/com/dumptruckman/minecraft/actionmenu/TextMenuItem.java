package com.dumptruckman.minecraft.actionmenu;

import org.jetbrains.annotations.NotNull;

public interface TextMenuItem extends MenuItem {

    @NotNull
    String getText();

    void setText(@NotNull final String text);
}
