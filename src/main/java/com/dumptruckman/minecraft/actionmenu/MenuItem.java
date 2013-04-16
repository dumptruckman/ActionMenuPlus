package com.dumptruckman.minecraft.actionmenu;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.Observable;

/**
 * An item in a Menu, selectable or otherwise, that is typically associated with an {@link Action}.
 * <p/>
 * This basic menu item is represented in a {@link Menu} with the results of {@link #getText()}.  It is possible to
 * extend this class and create items represented by other things such as images.  Doing so would also require a custom
 * {@link MenuView} that would recognize and correctly display such an item.  In such a case, {@link #getText()}
 * should still return a non-empty String so the item can be displayed on a basic {@link MenuView} which are typically
 * only capable of displaying text.
 * <p/>
 * Menu items are capable of performing pre-defined tasks by calling the {@link #performAction(org.bukkit.entity.Player)}.
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

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "MenuItem{" +
                "text='" + text + '\'' +
                '}';
    }
}
