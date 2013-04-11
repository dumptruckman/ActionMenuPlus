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
        index++;
        if (isWrapping() && index >= getModel().size()) {
            index = 0;
        }
        verifySelection();
    }

    void selectPrevious() {
        index--;
        if (isWrapping() && index < 0) {
            index = getModel().size() - 1;
        }
        verifySelection();
    }

    void selectIndex(final int index) {
        if (index >= 0 && index < getModel().size() && getModel().get(index).isSelectable()) {
            this.index = index;
        }
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
            verifySelection();
        }
    }

    private void verifySelection() {
        int size = getModel().size();
        if (index >= 0 && index < size && getModel().get(index).isSelectable()) {
            // selection is fine
            return;
        }
        for (int offset = 1, // index offset
                low = index - offset, // next highest index
                high = index + offset; // next lowest index
             low >= 0 || high < size; // stop if both high and low are outside of the range of the model
             offset++, // Move to the next offset
                low = index - offset,
                high = index + offset) {
            if (low >= 0 && getModel().get(low).isSelectable()) {
                // Found the next lowest (valid) index to be selectable
                index = low;
                return;
            }
            if (high < size && getModel().get(high).isSelectable()) {
                // Found the next highest (valid) index to be selectable
                index = high;
                return;
            }
        }
        // Couldn't find a valid selectable index
        index = -1;
    }
}
