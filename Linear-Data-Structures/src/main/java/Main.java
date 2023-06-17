import implementations.Queue;
import implementations.SinglyLinkedList;
import implementations.Stack;

public class Main {
	public static void main(String[] args) {
		SinglyLinkedList<Integer> ints = new SinglyLinkedList<>();
			
		ints.addFirst(5);
		ints.addFirst(6);	
		ints.addFirst(7);
		
		System.out.println(ints.getFirst());

	}
}
