/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.dumptruckman.minecraft.actionmenu;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * An object which can display a {@link Menu}.
 * <p/>
 * This interface allows a Menu to be displayed on a wide variety of outputs.  (Signs, chat, scoreboard, inventories,
 * etc.)
 * <p/>
 * Something important to note about MenuView is that it is not automated in any way.  Anytime something changes in the
 * menu it is up to the user of the view to call {@link #updateView(Menu, org.bukkit.entity.Player)} in order to show
 * changes done on the menu.  These changes include altering the current selection, adding/removing menu items, or
 * altering the properties of a menu item.
 */
public interface MenuView {

    /**
     * Tells the view that it needs to display or re-display the given menu.
     * <p/>
     * Typically this only needs to be done when changes to the menu, including selection change, or the menu's items
     * occur.
     *
     * @param menu the menu to display on this view.
     * @param viewer the player viewing the menu.
     */
    void updateView(@NotNull final Menu menu, @NotNull final Player viewer);
}
