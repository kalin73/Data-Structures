package implementations;

import java.util.Iterator;

import interfaces.List;

public class ArrayList<E> implements List<E> {
	private static final int INITIAL_SIZE = 5;
	private Object[] elements;
	private int size;
	private int capacity;

	public ArrayList() {
		this.elements = new Object[INITIAL_SIZE];
		this.size = 0;
		this.capacity = INITIAL_SIZE;
	}

	@Override
	public boolean add(E element) {
		if (capacity == size) {
			this.grow();
		}

		this.elements[size++] = element;

		return true;
	}

	@Override
	public boolean add(int index, E element) {
		isIndexValid(index);

		if (capacity == size) {
			this.grow();
		}

		shiftRight(index);
		this.elements[index] = element;
		this.size++;

		return true;
	}

	@Override
	public E get(int index) {
		isIndexValid(index);

		return (E) this.elements[index];
	}

	@Override
	public E set(int index, E element) {
		isIndexValid(index);

		E prevElement = (E) elements[index];
		this.elements[index] = element;

		return prevElement;
	}

	@Override
	public E remove(int index) {
		isIndexValid(index);

		E removedElement = (E) this.elements[index];

		this.elements[index] = null;
		if (index < size - 1) {
			shiftLeft(index);

		}

		size--;

		return removedElement;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public int indexOf(E element) {
		for (int i = 0; i < size; i++) {
			if (elements[i].equals(element)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public boolean contains(E element) {
		for (int i = 0; i < size; i++) {
			if (elements[i].equals(element)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	@Override
	public Iterator<E> iterator() {
		return new ArrayListIterator();
	}

	private class ArrayListIterator implements Iterator<E> {
		private int index;

		public ArrayListIterator() {
			this.index = 0;
		}

		@Override
		public boolean hasNext() {
			if (index < size) {
				return true;
			}
			return false;
		}

		@Override
		public E next() {
			return (E) elements[index++];
		}

	}

	private void grow() {
		this.capacity *= 2;
		Object[] newArr = new Object[capacity];
		System.arraycopy(elements, 0, newArr, 0, size);

		this.elements = newArr;
	}

	private void isIndexValid(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Invalid index");
		}
	}

	private void shiftRight(int index) {
		for (int i = size; i > index; i--) {
			elements[i] = elements[i - 1];
		}

	}

	private void shiftLeft(int index) {
		for (int i = index; i < size - 1; i++) {
			elements[i] = elements[i + 1];
		}

	}
}
