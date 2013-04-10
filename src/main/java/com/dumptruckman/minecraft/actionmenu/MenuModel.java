package com.dumptruckman.minecraft.actionmenu;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;

public class MenuModel extends Observable implements List<MenuItem> {

    private final List<MenuItem> contents;

    MenuModel() {
        this(new ArrayList<MenuItem>());
    }

    MenuModel(@NotNull final List<MenuItem> backingList) {
        this.contents = backingList;
    }

    @Override
    public int size() {
        return contents.size();
    }

    @Override
    public boolean isEmpty() {
        return contents.isEmpty();
    }

    @Override
    public boolean contains(@NotNull final Object o) {
        return contents.contains(o);
    }

    @NotNull
    @Override
    public Iterator<MenuItem> iterator() {
        return contents.iterator();
    }

    @NotNull
    @Override
    public Object[] toArray() {
        return contents.toArray();
    }

    @NotNull
    @Override
    public <T> T[] toArray(@NotNull final T[] a) {
        return contents.toArray(a);
    }

    @Override
    public boolean add(@NotNull final MenuItem menuItem) {
        return contents.add(menuItem);
    }

    @Override
    public boolean remove(@NotNull final Object o) {
        return contents.remove(o);
    }

    @Override
    public boolean containsAll(@NotNull final Collection<?> c) {
        return contents.containsAll(c);
    }

    @Override
    public boolean addAll(@NotNull final Collection<? extends MenuItem> c) {
        return contents.addAll(c);
    }

    @Override
    public boolean addAll(final int index, @NotNull final Collection<? extends MenuItem> c) {
        return contents.addAll(index, c);
    }

    @Override
    public boolean removeAll(@NotNull final Collection<?> c) {
        return contents.removeAll(c);
    }

    @Override
    public boolean retainAll(@NotNull final Collection<?> c) {
        return contents.retainAll(c);
    }

    @Override
    public void clear() {
        contents.clear();
    }

    @Override
    public boolean equals(final Object o) {
        return o instanceof MenuModel && contents.equals(o);
    }

    @Override
    public int hashCode() {
        return contents.hashCode();
    }

    @NotNull
    @Override
    public MenuItem get(final int index) {
        return contents.get(index);
    }

    @NotNull
    @Override
    public MenuItem set(final int index, @NotNull final MenuItem element) {
        return contents.set(index, element);
    }

    @Override
    public void add(final int index, @NotNull final MenuItem element) {
        contents.add(index, element);
    }

    @NotNull
    @Override
    public MenuItem remove(final int index) {
        return contents.remove(index);
    }

    @Override
    public int indexOf(@NotNull final Object o) {
        return contents.indexOf(o);
    }

    @Override
    public int lastIndexOf(@NotNull final Object o) {
        return contents.lastIndexOf(o);
    }

    @NotNull
    @Override
    public ListIterator<MenuItem> listIterator() {
        return contents.listIterator();
    }

    @NotNull
    @Override
    public ListIterator<MenuItem> listIterator(final int index) {
        return contents.listIterator(index);
    }

    @NotNull
    @Override
    public List<MenuItem> subList(final int fromIndex, final int toIndex) {
        return contents.subList(fromIndex, toIndex);
    }
}
