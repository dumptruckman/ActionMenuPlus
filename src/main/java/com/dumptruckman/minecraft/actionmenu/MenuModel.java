package com.dumptruckman.minecraft.actionmenu;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;

/**
 * Contains the contents of a Menu.
 * <p/>
 * This class functions functions as an {@link Observable} {@link List} of {@link MenuItem}s that will notify observers
 * any time any changes to the list contents occur.
 */
public class MenuModel extends Observable implements List<MenuItem> {

    private final List<MenuItem> contents;

    public MenuModel() {
        this(new ArrayList<MenuItem>());
    }

    MenuModel(@NotNull final List<MenuItem> backingList) {
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
        try {
            return contents.add(menuItem);
        } finally {
            change();
        }
    }

    /** {@inheritDoc} */
    @Override
    public boolean remove(@NotNull final Object o) {
        try {
            return contents.remove(o);
        } finally {
            change();
        }
    }

    /** {@inheritDoc} */
    @Override
    public boolean containsAll(@NotNull final Collection<?> c) {
        return contents.containsAll(c);
    }

    /** {@inheritDoc} */
    @Override
    public boolean addAll(@NotNull final Collection<? extends MenuItem> c) {
        try {
            return contents.addAll(c);
        } finally {
            change();
        }
    }

    /** {@inheritDoc} */
    @Override
    public boolean addAll(final int index, @NotNull final Collection<? extends MenuItem> c) {
        try {
            return contents.addAll(index, c);
        } finally {
            change();
        }
    }

    /** {@inheritDoc} */
    @Override
    public boolean removeAll(@NotNull final Collection<?> c) {
        try {
            return contents.removeAll(c);
        } finally {
            change();
        }
    }

    /** {@inheritDoc} */
    @Override
    public boolean retainAll(@NotNull final Collection<?> c) {
        try {
            return contents.retainAll(c);
        } finally {
            change();
        }
    }

    /** {@inheritDoc} */
    @Override
    public void clear() {
        try {
            contents.clear();
        } finally {
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
        try {
            return contents.set(index, element);
        } finally {
            change();
        }
    }

    /** {@inheritDoc} */
    @Override
    public void add(final int index, @NotNull final MenuItem element) {
        try {
            contents.add(index, element);
        } finally {
            change();
        }
    }

    /** {@inheritDoc} */
    @Override
    @NotNull
    public MenuItem remove(final int index) {
        try {
            return contents.remove(index);
        } finally {
            change();
        }
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
    public Iterator<MenuItem> iterator() {
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
