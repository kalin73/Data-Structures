import implementations.DoublyLinkedList;

public class Main {
	public static void main(String[] args) {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		list.addLast(5);
		list.addLast(7);

		list.removeLast();
		list.removeLast();
		
		System.out.println();

	}
}
