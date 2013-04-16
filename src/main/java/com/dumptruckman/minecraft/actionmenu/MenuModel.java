/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.dumptruckman.minecraft.actionmenu;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;

/**
 * Contains the contents (menu items) of a Menu, selectable or otherwise.
 * <p/>
 * This class functions functions as an {@link Observable} {@link List} of {@link MenuItem}s that will notify observers
 * any time any changes to the list contents occur.
 * <p/>
 * The specific behavior of the list is that of an {@link ArrayList}.
 */
public final class MenuModel extends Observable implements List<MenuItem>, Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private List<MenuItem> contents;

    /**
     * Constructs an empty model with an initial capacity of ten.
     */
    public MenuModel() {
        this(new ArrayList<MenuItem>());
    }

    /**
     * Constructs a model containing the items of the specified collection, in the order they are returned by the
     * collection's iterator.
     *
     * @param initialContents the collection whose items are to be placed in this model.
     */
    public MenuModel(@NotNull final java.util.Collection<MenuItem> initialContents) {
        this(new ArrayList<MenuItem>(initialContents));
    }

    /**
     * Constructs an empty model with the specified initial capacity.
     *
     * @param initialCapacity the initial capacity of the model.
     * @throws IllegalArgumentException if the initial capacity is negative.
     */
    public MenuModel(final int initialCapacity) {
        this(new ArrayList<MenuItem>(initialCapacity));
    }

    private MenuModel(@NotNull final List<MenuItem> backingList) {
        this.contents = backingList;
    }

    /** {@inheritDoc} */
    @Override
    public int size() {
        return contents.size();
    }

    /** {@inheritDoc} */
    @Override
    public boolean isEmpty() {
        return contents.isEmpty();
    }

    /** {@inheritDoc} */
    @Override
    public boolean contains(@NotNull final Object o) {
        return contents.contains(o);
    }

    /** {@inheritDoc} */
    @Override
    public boolean add(@NotNull final MenuItem menuItem) {
        if (contents.add(menuItem)) {
            change();
            return true;
        }
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public boolean remove(@NotNull final Object o) {
        if (contents.remove(o)) {
            change();
            return true;
        }
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public boolean containsAll(@NotNull final Collection<?> c) {
        return contents.containsAll(c);
    }

    /** {@inheritDoc} */
    @Override
    public boolean addAll(@NotNull final Collection<? extends MenuItem> c) {
        if (contents.addAll(c)) {
            change();
            return true;
        }
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public boolean addAll(final int index, @NotNull final Collection<? extends MenuItem> c) {
        if (contents.addAll(index, c)) {
            change();
            return true;
        }
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public boolean removeAll(@NotNull final Collection<?> c) {
        if (contents.removeAll(c)) {
            change();
            return true;
        }
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public boolean retainAll(@NotNull final Collection<?> c) {
        if (contents.retainAll(c)) {
            change();
            return true;
        }
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public void clear() {
        if (!contents.isEmpty()) {
            contents.clear();
            change();
        }
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(final Object o) {
        return o instanceof MenuModel && contents.equals(o);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return contents.hashCode();
    }

    /** {@inheritDoc} */
    @Override
    @NotNull
    public MenuItem get(final int index) {
        return contents.get(index);
    }

    /** {@inheritDoc} */
    @Override
    @NotNull
    public MenuItem set(final int index, @NotNull final MenuItem element) {
        final MenuItem result = contents.set(index, element);
        change();
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public void add(final int index, @NotNull final MenuItem element) {
        contents.add(index, element);
        change();
    }

    /** {@inheritDoc} */
    @Override
    @NotNull
    public MenuItem remove(final int index) {
        final MenuItem result = contents.remove(index);
        change();
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public int indexOf(@NotNull final Object o) {
        return contents.indexOf(o);
    }

    /** {@inheritDoc} */
    @Override
    public int lastIndexOf(@NotNull final Object o) {
        return contents.lastIndexOf(o);
    }

    /** {@inheritDoc} */
    @NotNull
    @Override
    public ListIterator<MenuItem> listIterator() {
        return new ModelIterator(contents.listIterator());
    }

    /** {@inheritDoc} */
    @NotNull
    @Override
    public ListIterator<MenuItem> listIterator(final int index) {
        return new ModelIterator(contents.listIterator(index));
    }

    /** {@inheritDoc} */
    @NotNull
    @Override
    public List<MenuItem> subList(final int fromIndex, final int toIndex) {
        return contents.subList(fromIndex, toIndex);
    }

    private void change() {
        this.setChanged();
        this.notifyObservers();
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "MenuModel{" +
                "contents=" + contents +
                '}';
    }

    /** {@inheritDoc} */
    @NotNull
    @Override
    public ListIterator<MenuItem> iterator() {
        return new ModelIterator(contents.listIterator());
    }

    /** {@inheritDoc} */
    @NotNull
    @Override
    public MenuItem[] toArray() {
        return contents.toArray(new MenuItem[size()]);
    }

    /** {@inheritDoc} */
    @NotNull
    @Override
    public <T> T[] toArray(@NotNull final T[] a) {
        return contents.toArray(a);
    }

    /**
     * A private implementation of ListIterator that notifies observers of the MenuModel of any content changes.
     */
    private class ModelIterator implements ListIterator<MenuItem> {

        @NotNull
        private final ListIterator<MenuItem> backingIterator;

        private ModelIterator(@NotNull final ListIterator<MenuItem> backingIterator) {
            this.backingIterator = backingIterator;
        }

        /** {@inheritDoc} */
        @Override
        public boolean hasNext() {
            return backingIterator.hasNext();
        }

        /** {@inheritDoc} */
        @NotNull
        @Override
        public MenuItem next() {
            return backingIterator.next();
        }

        /** {@inheritDoc} */
        @Override
        public boolean hasPrevious() {
            return backingIterator.hasPrevious();
        }

        /** {@inheritDoc} */
        @NotNull
        @Override
        public MenuItem previous() {
            return backingIterator.previous();
        }

        /** {@inheritDoc} */
        @Override
        public int nextIndex() {
            return backingIterator.nextIndex();
        }

        /** {@inheritDoc} */
        @Override
        public int previousIndex() {
            return backingIterator.previousIndex();
        }

        /** {@inheritDoc} */
        @Override
        public void remove() {
            backingIterator.remove();
            change();
        }

        /** {@inheritDoc} */
        @Override
        public void set(@NotNull final MenuItem menuItem) {
            backingIterator.set(menuItem);
            change();
        }

        /** {@inheritDoc} */
        @Override
        public void add(@NotNull final MenuItem menuItem) {
            backingIterator.add(menuItem);
            change();
        }

    }
}
