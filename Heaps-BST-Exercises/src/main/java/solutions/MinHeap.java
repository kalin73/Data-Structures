package solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import interfaces.Decrease;
import interfaces.Heap;

public class MinHeap<E extends Comparable<E> & Decrease<E>> implements Heap<E> {
	private List<E> data;

	public MinHeap() {
		this.data = new ArrayList<>();
	}

	@Override
	public int size() {
		return this.data.size();
	}

	@Override
	public void add(E element) {
		this.data.add(element);

		this.heapifyUp();
	}

	@Override
	public E peek() {
		ensureNonEmpty();
		return this.data.get(0);
	}

	@Override
	public E poll() {
		this.ensureNonEmpty();
		Collections.swap(this.data, 0, this.size() - 1);
		E element = this.data.remove(this.size() - 1);

		this.heapifyDown();

		return element;
	}

	private void heapifyDown() {
		int index = 0;

		int swapIndex = getLeftChildIndex(index);

		while (swapIndex < this.size()) {
			int rightChildIndex = getRightChildIndex(index);

			if (rightChildIndex < this.size()) {
				swapIndex = isLess(swapIndex, rightChildIndex) ? swapIndex : rightChildIndex;
			}

			if (isLess(index, swapIndex)) {
				break;
			}

			Collections.swap(this.data, index, swapIndex);
			index = swapIndex;
			swapIndex = getLeftChildIndex(index);
		}

	}

	private int getLeftChildIndex(int index) {
		return 2 * index + 1;
	}

	private int getRightChildIndex(int index) {
		return 2 * index + 2;
	}

	@Override
	public void decrease(E element) {

	}

	private void heapifyUp() {
		int index = this.data.size() - 1;
		int parentIndex = this.getParentIndex(index);

		while (index > 0 && isLess(index, parentIndex)) {
			Collections.swap(data, index, parentIndex);
			index = parentIndex;
			parentIndex = this.getParentIndex(index);
		}

	}

	private int getParentIndex(int index) {
		return (index - 1) / 2;
	}

	private boolean isLess(int index, int parentIndex) {
		return this.data.get(index).compareTo(this.data.get(parentIndex)) < 0;
	}

	private void ensureNonEmpty() {
		if (this.data.size() == 0) {
			throw new IllegalStateException();
		}

	}
}
