package com.project87.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

/** 
 * The {@code ArrayCollection} class is simple implementation
 * of {@code java.lang.Collection} interface.
 * The class {@code ArrayCollection} includes only methods defined
 * in {@code java.lang.Collection} inteface unlike of
 * {@code java.util.ArrayList} which have few specific methods.
 * Please note this collection not have any syncronisations.
 * {@code ArrayCollection} can be parametrized and holds more than one
 * {@code null} value.
 *
 * @author Viktor Podkopay
 * @version 0.0
 * 
 * */

public class ArrayCollection<E> implements Collection<E> {

	private Object[] elements; 
	
	/**
     * Returns number of stored elements
     *
     * @return int value
     */
	@Override
	public int size() {
		return elements.length;
	}

	/**
     * Returns {@code true} if collection
     * does not contain any element
     *
     * @return boolean value
     */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
     * Returns {@code true} if collection
     * contains specific element
     *
     * @return boolean value
     */
	@Override
	public boolean contains(Object o) {
		boolean result = false;
        if (o == null) {
            for (Object element : elements) {
                if (element == null) {
                    result = true;
                    break;
                }
            }
        } else {
            for (Object element : elements) {
                if (o.equals(element)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
	}

	@Override
	public Iterator<E> iterator() {
		
		return null;
	}

	@Override
	public Object[] toArray() {
		return elements;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return null;
	}

	@Override
	public boolean add(E e) {
		int newSize = size() + 1;
        elements = Arrays.copyOf(elements, newSize);
        elements[newSize - 1] = e;
        return true;
	}

	@Override
	public boolean remove(Object o) {
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		Objects.requireNonNull(c);
        int count = 0;
        for (Iterator iterator = c.iterator(); iterator.hasNext(); ) {
            Object o = iterator.next();
            if (contains(o)) {
                count++;
            }
        }
        if (count == c.size()) {
            return true;
        }
        return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		Objects.requireNonNull(c);
        if (this == c) {
            throw new IllegalArgumentException();
        }
        int newSize = size() + c.size();
        Object[] newElements = new Object[newSize];
        System.arraycopy(elements, 0, newElements, 0, size());
        System.arraycopy(c.toArray(), 0, newElements, size(), c.size());
        elements = newElements;
        return true;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		Objects.requireNonNull(c);
        boolean result;
        int count = 0;
        ArrayCollection<E> temp = new ArrayCollection<>();
        for (Iterator iterator = c.iterator(); iterator.hasNext(); ) {
            Object o = iterator.next();
            if (contains(o)) {
                temp.add((E)o);
                count++;
            }
        }
        if (count == 0) {
            result = false;
        } else {
            if (count == size() & count == c.size()) {
                result = false;
            } else {
                setArray(temp.getArray());
                result = true;
            }
        }
        return result;
	}

	private void setArray(Object[] array) {
		this.elements = array;
	}

	private Object[] getArray() {
		return this.elements;
	}

	/**
	 * Remove all elements
	 * */
	@Override
	public void clear() {
		elements = new Object[0];
	}

}
