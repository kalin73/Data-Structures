package implementations;

import java.util.Iterator;

public class ReversedList<E> implements Iterable<E> {
	private static final int INITIAL_CAPACITY = 2;

	private Object[] elements;
	private int size;

	public ReversedList() {
		this(INITIAL_CAPACITY);
	}

	public ReversedList(int capacity) {
		this.elements = new Object[capacity];
		this.size = 0;
	}

	public boolean add(E element) {
		if (size == elements.length) {
			this.grow();
		}
		this.elements[size++] = element;

		return true;
	}

	public int size() {
		return this.size;
	}

	public int capacity() {
		return this.elements.length;
	}

	public E get(int index) {
		isValidIndex(index);

		int reverseIndex = this.size - 1 - index;

		return getAtIndex(reverseIndex);
	}

	public E removeAt(int index) {
		isValidIndex(index);

		int reverseIndex = this.size - 1 - index;

		E element = this.getAtIndex(reverseIndex);

		this.elements[reverseIndex] = null;

		this.shiftLeft(reverseIndex);

		this.size--;

		return element;
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			private int startingIndex = size - 1;

			@Override
			public boolean hasNext() {
				return startingIndex >= 0;
			}

			@Override
			public E next() {
				return getAtIndex(startingIndex--);
			}
		};
	}

	private void shiftLeft(int index) {
		for (int i = index; i < this.size - 1; i++) {
			this.elements[i] = this.elements[i + 1];
		}
		this.elements[size - 1] = null;
	}

	private void isValidIndex(int index) {
		if (index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException();
		}
	}

	private void grow() {
		int newCapacity = this.elements.length * 2;

		Object[] newArray = new Object[newCapacity];

		for (int i = 0; i < size; i++) {
			newArray[i] = this.elements[i];
		}

		this.elements = newArray;
	}

	@SuppressWarnings("unchecked")
	private E getAtIndex(int index) {
		return (E) this.elements[index];
	}
}
