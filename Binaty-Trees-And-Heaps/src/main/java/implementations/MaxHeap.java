package implementations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import interfaces.Heap;

public class MaxHeap<E extends Comparable<E>> implements Heap<E> {
	private List<E> elements;

	public MaxHeap() {
		this.elements = new ArrayList<>();
	}

	@Override
	public int size() {
		return this.elements.size();
	}

	@Override
	public void add(E element) {
		this.elements.add(element);

		heapifyUp(size() - 1);

	}

	private void heapifyUp(int index) {
		int parentElementIngex = (index - 1) / 2;
		E parentElement = this.elements.get(parentElementIngex);

		int newElementIndex = size() - 1;
		E newElement = this.elements.get(newElementIndex);

		while (newElement.compareTo(parentElement) > 0) {
			Collections.swap(this.elements, parentElementIngex, newElementIndex);

			newElementIndex = parentElementIngex;
			parentElementIngex = (newElementIndex - 1) / 2;

			newElement = this.elements.get(newElementIndex);
			parentElement = this.elements.get(parentElementIngex);
		}

	}

	@Override
	public E peek() {
		if (this.size() == 0) {
			throw new IllegalStateException("Can't peek in empty heap!");
		}

		return this.elements.get(0);
	}
}
