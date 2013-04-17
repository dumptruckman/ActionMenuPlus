/**
 * Copyright (c) 2013. dumptruckman
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.dumptruckman.minecraft.actionmenu;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Observable;
import java.util.Observer;

/**
 * This class is responsible for handling the current selection of a {@link MenuModel} and is public for reference
 * only.
 * <p/>
 * A {@link MenuModel} may have multiple selectors to track selections on it for different users though a MenuSelector
 * will only ever track one {@link MenuModel}.
 * <p/>
 * A MenuSelector's selection process for {@link #selectPrevious()} and {@link #selectNext()} is determined by the
 * result of {@link #isWrapping()}.  If {@link #isWrapping()} returns true, calling {@link #selectNext()} while on the
 * last selectable item of the model will cause the selection to roll over to the first selectable item, while calling
 * {@link #selectPrevious()} while on the first selectable item of the model will cause the selection to roll over to
 * the last selectable item.  However, if {@link #isWrapping()} returns false, instead of the selection rolling over in
 * the previous circumstances, it will instead remain at the first or last item.
 * <p/>
 * Every selector starts with no current selection.
 */
public final class MenuSelector implements Observer {

    @NotNull
    private final MenuModel model;
    private final boolean wrapping;

    private int index = -1;

    MenuSelector(@NotNull final MenuModel model, final boolean wrapping) {
        this.model = model;
        this.wrapping = wrapping;
        this.model.addObserver(this);
    }

    /**
     * Gets the model this selector is responsible for.
     *
     * @return the model this selector is responsible for.
     */
    @NotNull
    public MenuModel getModel() {
        return model;
    }

    /**
     * Gets the currently selected index of the model.
     *
     * @return the currently selected index of the model.
     */
    public int getSelectedIndex() {
        return index;
    }

    /**
     * Selects the next <em>selectable</em> item in the model, wrapping to the beginning if {@link #isWrapping()}
     * returns true.
     */
    public void selectNext() {
        index++;
        // we need to find the next selectable item
        for (MenuItem selected = getSelectedItem(); // first check the newly selected item
             index < getModel().size() && selected != null; // ensure it is within the bounds of the model
             index++, selected = getSelectedItem()) { // check the next item
            if (selected.isSelectable()) {
                break;
            }
        }
        // wrap to beginning if necessary
        if (isWrapping() && index >= getModel().size()) {
            index = 0;
        }
        validateIndex();
    }

    /**
     * Selects the previous <em>selectable</em> item in the model, wrapping to the end if {@link #isWrapping()}
     * returns true.
     */
    public void selectPrevious() {
        index--;
        // we need to find the previous selectable item
        for (MenuItem selected = getSelectedItem();  // first check the newly selected item
             index >= 0 && selected != null; // ensure it is within the bounds of the model
             index--, selected = getSelectedItem()) { // check the previous item
            if (selected.isSelectable()) {
                break;
            }
        }
        // wrap to end if necessary
        if (isWrapping() && index < 0) {
            index = getModel().size() - 1;
        }
        validateIndex();
    }

    /**
     * Selects the specified index.
     * <p/>
     * If the specified index is outside the bounds of the model or points to a non-selectable item, this will do
     * nothing and the selection index will remain as it was.
     *
     * @param index the index to select.
     */
    public void selectIndex(final int index) {
        if (index >= 0 && index < getModel().size() && getModel().get(index).isSelectable()) {
            this.index = index;
        }
    }

    /**
     * Gets the currently selected menu item if any.
     *
     * @return the currently selected menu item or null if no item selected.
     */
    @Nullable
    public MenuItem getSelectedItem() {
        if (index >= 0 && index < getModel().size()) {
            return model.get(index);
        }
        return null;
    }

    /**
     * Clears the current selection of this selector.
     * <p/>
     * This will cause {@link #getSelectedItem()} to return null.
     */
    public void clearSelection() {
        index = -1;
    }

    /**
     * Gets whether this selector should wrap to the end of the model on {@link #selectPrevious()} and to the
     * beginning of the model on {@link #selectNext()} or if no wrapping should occur.
     *
     * @return true if wrapping should occur, false otherwise.
     */
    public boolean isWrapping() {
        return wrapping;
    }

    /**
     * This should be called when the contents of this selector's model is altered in any way.
     *
     * @param o this should be the same model returned by {@link #getModel()} or this will do nothing.
     * @param arg this argument is ignored.
     */
    @Override
    public void update(final Observable o, final Object arg) {
        if (o == model) {
            validateIndex();
        }
    }

    /**
     * Ensures that the current index is valid.
     */
    private void validateIndex() {
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
