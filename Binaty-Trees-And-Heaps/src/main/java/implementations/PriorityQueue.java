package implementations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import interfaces.AbstractQueue;

public class PriorityQueue<E extends Comparable<E>> implements AbstractQueue<E> {
	private List<E> elements;

	public PriorityQueue() {
		this.elements = new ArrayList<>();
	}

	@Override
	public int size() {
		return this.elements.size();
	}

	@Override
	public void add(E element) {
		this.elements.add(0, element);

		heapifyDown(0);

	}

	@Override
	public E peek() {
		ensureNonEmpty();

		return this.elements.get(0);
	}

	@Override
	public E poll() {
		ensureNonEmpty();

		E element = this.elements.get(0);
		Collections.swap(this.elements, 0, this.size() - 1);
		this.elements.remove(this.size() - 1);
		this.heapifyDown(0);

		return element;
	}

	private void heapifyDown(int index) {
		while (getLeftChildIndex(index) < this.size() && isLess(index, getLeftChildIndex(index))) {
			int childIndex = getLeftChildIndex(index);
			int rightChildIndex = getRightChildIndex(index);

			if (rightChildIndex < this.size() && isLess(childIndex, rightChildIndex)) {
				childIndex = rightChildIndex;
			}

			Collections.swap(this.elements, childIndex, index);
			index = childIndex;
		}
	}

	private boolean isLess(int first, int second) {
		return this.elements.get(first).compareTo(this.elements.get(second)) < 0;
	}

	private void ensureNonEmpty() {
		if (this.size() == 0) {
			throw new IllegalStateException("Can't peek or poll from empty heap!");
		}
	}

	private int getLeftChildIndex(int index) {
		return 2 * index + 1;
	}

	private int getRightChildIndex(int index) {
		return 2 * index + 2;
	}
}
