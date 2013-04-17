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

import javax.persistence.Transient;
import java.awt.*;
import java.io.Serializable;
import java.util.Observable;

/**
 * An item in a Menu, selectable or otherwise, that is typically associated with an {@link Action}.
 * <p/>
 * A menu item has several methods that can be used to associate it visibly or audibly with a menu.
 * These methods and what they offer are as follows:
 * <ul>
 *     <li>{@link #setText(String)} - A simple string</li>
 *     <li>{@link #setImage(java.awt.Image)} - An image</li>
 *     <li>{@link #setItemStack(org.bukkit.inventory.ItemStack)} - An item in an inventory</li>
 *     <li>{@link #setBlock(org.bukkit.block.Block)} - A block in the world</li>
 *     <li>{@link #setEffect(org.bukkit.Effect)} - An audio/visual effect</li>
 * </ul>
 * <em>(Each of the above setters have respective accessor methods)</em>
 * <p/>
 * How exactly a menu item is displayed is entirely up to the {@link MenuView} that is displaying the menu to a player.
 * Not all of the methods above will actually be displayable on every medium capable of showing a menu.  As such, it is
 * important to make sure that at minimum a MenuItem has a non-empty string set with {@link #MenuItem(String)} or
 * {@link #setText(String)} as almost all menu mediums are capable of displaying text in some way.
 * <p/>
 * Menu items are capable of performing pre-defined tasks by calling {@link #performAction(org.bukkit.entity.Player)}.
 * The tasks performed is defined by the {@link Action} returned by {@link #getAction()}.
 * <p/>
 * This MenuItem may or may not be selectable as indicated by {@link #isSelectable()}.  A MenuItem that is not
 * selectable should typically be visually represented as such to avoid confusion by the end-user.  A non-selectable
 * MenuItem cannot be set as the selected MenuItem of a Menu by any means.
 * <p/>
 * {@link java.util.Observer}s of this MenuItem will be notified any time a change occurs that may require a
 * {@link MenuView} to update via {@link MenuView#updateView(Menu, org.bukkit.entity.Player)}.
 */
public class MenuItem extends Observable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Nullable
    private Action itemAction = null;
    private boolean selectable = true;
    @NotNull
    private String text;
    @Transient
    @Nullable
    private Image image = null;
    @Transient
    @Nullable
    private ItemStack itemStack = null;
    @Transient
    @Nullable
    private Block block = null;
    @Transient
    @Nullable
    private Effect effect = null;

    /**
     * Constructs a MenuItem with empty text.
     * <p/>
     * It is recommended that you set the text with {@link #setText(String)} so {@link MenuView} displays this item
     * properly.
     */
    public MenuItem() {
        this("");
    }

    /**
     * Constructs a MenuItem with the given text.
     *
     * @param text the text this MenuItem will be displayed with.
     */
    public MenuItem(@NotNull final String text) {
        this.text = text;
    }

    /**
     * Performs the action defined by {@link #getAction()} for this MenuItem.
     * <p/>
     * Does nothing if {@link #getAction()} returns null.
     *
     * @param user the player that activated this MenuItem.
     */
    public final void performAction(@Nullable final Player user) {
        if (getAction() != null) {
            getAction().performAction(user);
        }
    }

    /**
     * The action that this MenuItem will perform when {@link #performAction(org.bukkit.entity.Player)} is called.
     *
     * @return the action for this MenuItem or null if no action should be performed.
     */
    public Action getAction() {
        return itemAction;
    }

    /**
     * Sets the action for this MenuItem which will be used when {@link #performAction(org.bukkit.entity.Player)} is
     * called.
     *
     * @param action the action this MenuItem should perform when {@link #performAction(org.bukkit.entity.Player)} is
     *               called.
     * @return this MenuItem for chaining.
     */
    @NotNull
    public MenuItem setAction(@NotNull final Action action) {
        this.itemAction = action;
        return this;
    }

    /**
     * Gets whether or not this MenuItem is selectable.
     * <p/>
     * A MenuItem that is selectable should typically be displayed differently than a non-selectable item to avoid
     * confusion for the end-user.
     * <p/>
     * A non-selectable item cannot be selected by the Menu by any means.
     *
     * @return true if this item is selectable.
     */
    public boolean isSelectable() {
        return selectable;
    }

    /**
     * Sets whether or not this MenuItem is selectable.
     *
     * @param selectable whether or not this MenuItem will be selectable.
     * @return this MenuItem for chaining.
     */
    @NotNull
    public MenuItem setSelectable(final boolean selectable) {
        this.selectable = selectable;
        return this;
    }

    /**
     * Gets the text that a {@link MenuView} should use to display this MenuItem.
     *
     * @return the text that a {@link MenuView} should use to display this MenuItem.
     */
    @NotNull
    public String getText() {
        return text;
    }

    /**
     * Sets the text that a {@link MenuView} should use to display this MenuItem.
     *
     * @param text the text that a {@link MenuView} should use to display this MenuItem.
     * @return this MenuItem for chaining.
     */
    @NotNull
    public MenuItem setText(@NotNull final String text) {
        this.text = text;
        return this;
    }

    /**
     * Gets the image associated with this MenuItem, if any.
     * <p/>
     * Whether or not this image is used in displaying the menu is entirely dependent upon the {@link MenuView} used
     * to show the menu.
     *
     * @return the image associated with this MenuItem or null if this menu item has no image.
     */
    @Nullable
    public Image getImage() {
        return image;
    }

    /**
     * Sets the image to be associated with this MenuItem.
     * <p/>
     * Whether or not this image is used in displaying the menu is entirely dependent upon the {@link MenuView} used
     * to show the menu.
     *
     * @param image the image to be associated with this MenuItem or null to use no image.
     * @return this MenuItem for chaining.
     */
    public MenuItem setImage(@Nullable final Image image) {
        this.image = image;
        return this;
    }

    /**
     * Gets the item stack associated with this MenuItem, if any.
     * <p/>
     * Whether or not this item stack is used in displaying the menu is entirely dependent upon the {@link MenuView}
     * used to show the menu.
     *
     * @return the item stack associated with this MenuItem or null if this menu item has no item stack.
     */
    @Nullable
    public ItemStack getItemStack() {
        return itemStack;
    }

    /**
     * Sets the item stack to be associated with this MenuItem.
     * <p/>
     * Whether or not this item stack is used in displaying the menu is entirely dependent upon the {@link MenuView}
     * used to show the menu.
     *
     * @param itemStack the item stack to be associated with this MenuItem or null to use no item stack.
     * @return this MenuItem for chaining.
     */
    public MenuItem setItemStack(@Nullable final ItemStack itemStack) {
        this.itemStack = itemStack;
        return this;
    }

    /**
     * Gets the block associated with this MenuItem, if any.
     * <p/>
     * Whether or not this block is used in displaying the menu is entirely dependent upon the {@link MenuView}
     * used to show the menu.
     *
     * @return the block associated with this MenuItem or null if this menu item has no block.
     */
    @Nullable
    public Block getBlock() {
        return block;
    }

    /**
     * Sets the block to be associated with this MenuItem.
     * <p/>
     * Whether or not this block is used in displaying the menu is entirely dependent upon the {@link MenuView}
     * used to show the menu.
     *
     * @param block the block to be associated with this MenuItem or null to use no block.
     * @return this MenuItem for chaining.
     */
    public MenuItem setBlock(@Nullable final Block block) {
        this.block = block;
        return this;
    }

    /**
     * Gets the effect associated with this MenuItem, if any.
     * <p/>
     * Whether or not this effect is used in displaying the menu is entirely dependent upon the {@link MenuView}
     * used to show the menu.
     *
     * @return the effect associated with this MenuItem or null if this menu item has no effect.
     */
    @Nullable
    public Effect getEffect() {
        return effect;
    }

    /**
     * Sets the effect to be associated with this MenuItem.
     * <p/>
     * Whether or not this effect is used in displaying the menu is entirely dependent upon the {@link MenuView}
     * used to show the menu.
     *
     * @return the effect to be associated with this MenuItem or null if this menu item has no effect.
     * @return this MenuItem for chaining.
     */
    public MenuItem setEffect(@Nullable final Effect effect) {
        this.effect = effect;
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "MenuItem{" +
                "text='" + text + '\'' +
                '}';
    }
}
