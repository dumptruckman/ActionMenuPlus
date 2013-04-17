/**
 * Copyright (c) 2013. dumptruckman
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.dumptruckman.minecraft.actionmenu;

import org.bukkit.Effect;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.Image;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * A menu, which contains a menu model (the menu items) and manages the selection of menu items.
 * <p/>
 * This is the main class for menus in ActionMenuPlus.  This and {@link MenuView} are the only classes you must track
 * externally.
 * <p/>
 * This menu is backed by a {@link MenuModel} which contains all of the menu items and functions similarly to an
 * {@link java.util.ArrayList}.
 * <p/>
 * All of the methods for manipulating the selection of the menu are available here.  Additionally, some simple methods
 * exist here for manipulating the model of the menu.  For more advanced model operations refer to the model returned
 * by {@link #getModel()}.
 * <p/>
 * As Menu extends {@link MenuItem} it is important to note that a Menu may be added to the contents of another menu!
 * Anyone implementing a {@link MenuView} should take this into considering as you may want to display a nested menu
 * entirely differently.
 */
public class Menu extends MenuItem implements Iterable<MenuItem> {

    private final boolean multiSelection;
    private final boolean wrapSelections;
    @NotNull
    private final Map<Player, MenuSelector> menuSelectorMap;
    @NotNull
    private MenuModel model;

    /**
     * Creates a new Menu with the given menu item text and an empty model.
     * <p/>
     * This Menu will have a single selection for all users and the selector will wrap around the contents of the menu.
     * See {@link com.dumptruckman.minecraft.actionmenu.MenuSelector#isWrapping()} for details on selection wrapping.
     *
     * @param title the text of this menu which can act as it's title or what it will appear as in other menus.
     */
    @NotNull
    public static Menu newMenu(@NotNull final String title) {
        return new Menu(title);
    }

    /**
     * Creates a new Menu with the given menu item text and model.
     * <p/>
     * This Menu will have a single selection for all users and the selector will wrap around the contents of the menu.
     * See {@link com.dumptruckman.minecraft.actionmenu.MenuSelector#isWrapping()} for details on selection wrapping.
     *
     * @param title the text of this menu which can act as it's title or what it will appear as in other menus.
     * @param model the model to use for the menu which holds the menu items.
     */
    @NotNull
    public static Menu newMenu(@NotNull final String title, @NotNull final MenuModel model) {
        return new Menu(title, model);
    }

    /**
     * Creates a new Menu with the given menu item text and an empty model.
     * <p/>
     * This Menu will have a unique selection for each user and the selector will wrap around the contents of the menu.
     * See {@link com.dumptruckman.minecraft.actionmenu.MenuSelector#isWrapping()} for details on selection wrapping.
     *
     * @param title the text of this menu which can act as it's title or what it will appear as in other menus.
     */
    @NotNull
    public static Menu newMultiSelectorMenu(@NotNull final String title) {
        return new Menu(title, new MenuModel(), true, true);
    }

    /**
     * Creates a new Menu with the given menu item text and model.
     * <p/>
     * This Menu will have a unique selection for each user and the selector will wrap around the contents of the menu.
     * See {@link com.dumptruckman.minecraft.actionmenu.MenuSelector#isWrapping()} for details on selection wrapping.
     *
     * @param title the text of this menu which can act as it's title or what it will appear as in other menus.
     * @param model the model to use for the menu which holds the menu items.
     */
    @NotNull
    public static Menu newMultiSelectorMenu(@NotNull final String title, @NotNull final MenuModel model) {
        return new Menu(title, model, true, true);
    }

    /**
     * Creates a new Menu with the given menu item text and an empty model.
     * <p/>
     * This Menu will have a single selection for all users and the selector will not wrap around the contents of the
     * menu. See {@link com.dumptruckman.minecraft.actionmenu.MenuSelector#isWrapping()} for details on selection
     * wrapping.
     *
     * @param title the text of this menu which can act as it's title or what it will appear as in other menus.
     */
    @NotNull
    public static Menu newNoWrapMenu(@NotNull final String title) {
        return new Menu(title, new MenuModel(), false, false);
    }

    /**
     * Creates a new Menu with the given menu item text and model.
     * <p/>
     * This Menu will have a unique selection for each user and the selector will not wrap around the contents of the
     * menu. See {@link com.dumptruckman.minecraft.actionmenu.MenuSelector#isWrapping()} for details on selection
     * wrapping.
     *
     * @param title the text of this menu which can act as it's title or what it will appear as in other menus.
     * @param model the model to use for the menu which holds the menu items.
     */
    @NotNull
    public static Menu newNoWrapMenu(@NotNull final String title, @NotNull final MenuModel model) {
        return new Menu(title, model, false, false);
    }

    /**
     * Creates a new Menu with the given menu item text and an empty model.
     * <p/>
     * This Menu will have a unique selection for each user and the selector will not wrap around the contents of the
     * menu. See {@link com.dumptruckman.minecraft.actionmenu.MenuSelector#isWrapping()} for details on selection
     * wrapping.
     *
     * @param title the text of this menu which can act as it's title or what it will appear as in other menus.
     */
    @NotNull
    public static Menu newNoWrapMultiSelectorMenu(@NotNull final String title) {
        return new Menu(title, new MenuModel(), true, false);
    }

    /**
     * Creates a new Menu with the given menu item text and model.
     * <p/>
     * This Menu will have a single selection for all users and the selector will not wrap around the contents of the
     * menu. See {@link com.dumptruckman.minecraft.actionmenu.MenuSelector#isWrapping()} for details on selection
     * wrapping.
     *
     * @param title the text of this menu which can act as it's title or what it will appear as in other menus.
     * @param model the model to use for the menu which holds the menu items.
     */
    @NotNull
    public static Menu newNoWrapMultiSelectorMenu(@NotNull final String title, @NotNull final MenuModel model) {
        return new Menu(title, model, true, false);
    }

    /**
     * Constructs a Menu with the given menu item text and an empty model.
     * <p/>
     * This Menu will have a single selection for all users and the selector will wrap around the contents of the menu.
     * See {@link com.dumptruckman.minecraft.actionmenu.MenuSelector#isWrapping()} for details on selection wrapping.
     *
     * @param title the text of this menu which can act as it's title or what it will appear as in other menus.
     */
    protected Menu(@NotNull final String title) {
        this(title, new MenuModel());
    }

    /**
     * Constructs a Menu with the given menu item text and model.
     * <p/>
     * This Menu will have a single selection for all users and the selector will wrap around the contents of the menu.
     * See {@link com.dumptruckman.minecraft.actionmenu.MenuSelector#isWrapping()} for details on selection wrapping.
     *
     * @param title the text of this menu which can act as it's title or what it will appear as in other menus.
     * @param model the model to use for the menu which holds the menu items.
     */
    protected Menu(@NotNull final String title, @NotNull final MenuModel model) {
        this(title, model, false, true);
    }

    /**
     * Constructs a Menu with the given menu item text and model.
     * <p/>
     * With this constructor you may specify whether or not to use a single selection for all users or a unique
     * selection for each user.  You may also specify whether the menu selection should wrap around the menu contents.
     * See {@link com.dumptruckman.minecraft.actionmenu.MenuSelector#isWrapping()} for details on selection wrapping.
     *
     * @param title the text of this menu which can act as it's title or what it will appear as in other menus.
     * @param model the model to use for the menu which holds the menu items.
     * @param multiSelection true to use a unique selection for every menu user or false to use a single selection for
     *                       all users.
     * @param wrapSelections true to enable selection wrapping. See {@link MenuSelector#isWrapping()} for details on
     *                       selection wrapping.
     */
    protected Menu(@NotNull final String title, @NotNull final MenuModel model,
                   final boolean multiSelection, final boolean wrapSelections) {
        super(title);
        this.model = model;
        this.multiSelection = multiSelection;
        this.wrapSelections = wrapSelections;
        if (!multiSelection) {
            menuSelectorMap = new HashMap<Player, MenuSelector>(1);
        } else {
            menuSelectorMap = new WeakHashMap<Player, MenuSelector>();
        }
        setupSelectors(model);
    }

    private void setupSelectors(@NotNull final MenuModel model) {
        if (!multiSelection) {
            menuSelectorMap.put(null, new MenuSelector(model, wrapSelections));
        } else {
            menuSelectorMap.clear();
        }
    }

    /**
     * Gets the selected item for the given user.
     *
     * @param user the user to get the selected item for.
     * @return the selected item of the menu for the given user or null if no item is selected.
     */
    @Nullable
    public final MenuItem getSelectedItem(@NotNull final Player user) {
        return getSelector(user).getSelectedItem();
    }

    /**
     * Gets the selected index for the given user.
     * <p/>
     * A less-than-zero results indicates that there no valid selection for the menu for the given user.
     *
     * @param user the user to get the selected index for.
     * @return the selected index of the menu for the given user.
     */
    public final int getSelectedIndex(@NotNull final Player user) {
        return getSelector(user).getSelectedIndex();
    }

    /**
     * Selects the next <em>selectable</em> item in the model for the given user, wrapping to the beginning if
     * {@link MenuSelector#isWrapping()} returns true.
     *
     * @param user The user who is changing the selection.
     */
    public final void selectNext(@NotNull final Player user) {
        getSelector(user).selectNext();
    }

    /**
     * Selects the previous <em>selectable</em> item in the model for the given user, wrapping to the end if
     * {@link MenuSelector#isWrapping()} returns true.
     *
     * @param user The user who is changing the selection.
     */
    public final void selectPrevious(@NotNull final Player user) {
        getSelector(user).selectPrevious();
    }

    /**
     * Selects the specified index for the given user.
     * <p/>
     * If the specified index is outside the bounds of the model or points to a non-selectable item, this will do
     * nothing and the selection index will remain as it was.
     *
     * @param user The user who is changing the selection.
     * @param index the index to select.
     */
    public final void selectIndex(@NotNull final Player user, final int index) {
        getSelector(user).selectIndex(index);
    }

    /**
     * Gets the selector for the given user.
     * <p/>
     * If this menu is not using multi-selector mode, this will always return the same object.  However, changing the
     * menu's model with {@link #setModel(MenuModel)} will cause new selectors to be created.
     *
     * @param user the user to get the selector for.
     * @return the menu selector for the given user.
     */
    @NotNull
    private MenuSelector getSelector(@NotNull final Player user) {
        if (!multiSelection) {
            return menuSelectorMap.get(null);
        } else {
            if (!menuSelectorMap.containsKey(user)) {
                menuSelectorMap.put(user, new MenuSelector(model, wrapSelections));
            }
            return menuSelectorMap.get(user);
        }
    }

    /**
     * Gets the model for this menu.
     * <p/>
     * This model allows you to perform more advanced operations on the list of menu items contained in this menu.
     *
     * @return the model for this menu.
     */
    @NotNull
    public final MenuModel getModel() {
        return model;
    }

    /**
     * Sets the model for this menu.
     *
     * @param model the new model to use for this menu.
     */
    public final void setModel(@NotNull final MenuModel model) {
        this.model = model;
        this.setupSelectors(model);
    }

    /** {@inheritDoc} */
    @Override
    public ListIterator<MenuItem> iterator() {
        return getModel().iterator();
    }

    /**
     * Adds a new menu item to this menu.
     * <p/>
     * The new item will be added to the end of the list.
     *
     * @param item the item to add.
     * @return this menu for method chaining.
     */
    @NotNull
    public Menu addItem(@NotNull final MenuItem item) {
        getModel().add(item);
        return this;
    }

    /**
     * Gets the menu item at the specified index.
     *
     * @param index the index of the item to retrieve.
     * @return the menu item at the specified index or null if the index is not within the bounds of the model.
     */
    @Nullable
    public MenuItem getItem(final int index) {
        if (index >= 0 && index < getModel().size()) {
            return getModel().get(index);
        }
        return null;
    }

    /**
     * Performs the action defined by {@link MenuItem#getAction()} for the currently selected MenuItem for the given
     * user.
     * <p/>
     * Does nothing if selected menu item has no action (null) or there is no valid selected menu item.
     *
     * @param user the player that activated this MenuItem.
     */
    public void performSelectedAction(@NotNull final Player user) {
        final MenuItem item = getSelectedItem(user);
        if (item != null) {
            item.performAction(user);
        }
    }

    /** {@inheritDoc} */
    @NotNull
    @Override
    public Menu setAction(@NotNull final Action action) {
        return (Menu) super.setAction(action);
    }

    /** {@inheritDoc} */
    @NotNull
    @Override
    public Menu setSelectable(final boolean selectable) {
        return (Menu) super.setSelectable(selectable);
    }

    /** {@inheritDoc} */
    @NotNull
    @Override
    public Menu setText(@NotNull final String text) {
        return (Menu) super.setText(text);
    }

    /** {@inheritDoc} */
    @Override
    public Menu setImage(@Nullable final Image image) {
        return (Menu) super.setImage(image);
    }

    /** {@inheritDoc} */
    @Override
    public Menu setItemStack(@Nullable final ItemStack itemStack) {
        return (Menu) super.setItemStack(itemStack);
    }

    /** {@inheritDoc} */
    @Override
    public Menu setBlock(@Nullable final Block block) {
        return (Menu) super.setBlock(block);
    }

    /** {@inheritDoc} */
    @Override
    public Menu setEffect(@Nullable final Effect effect) {
        return (Menu) super.setEffect(effect);
    }
}
