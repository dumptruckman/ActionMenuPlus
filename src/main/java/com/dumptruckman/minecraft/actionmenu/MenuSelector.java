package com.dumptruckman.minecraft.actionmenu;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Observable;
import java.util.Observer;

class MenuSelector implements Observer {

    @NotNull
    private final MenuModel model;

    private int index = 0;

    MenuSelector(@NotNull final MenuModel model) {
        this.model = model;
        this.model.addObserver(this);
    }

    @NotNull
    MenuModel getModel() {
        return model;
    }

    int getSelectedIndex() {
        return index;
    }

    void selectNext() {

    }

    void selectPrevious() {

    }

    @Nullable
    MenuItem getSelectedItem() {
        return null;
    }

    boolean isWrapping() {
        return true;
    }

    @Override
    public void update(final Observable o, final Object arg) {
        if (o == model) {

        }
    }
}
