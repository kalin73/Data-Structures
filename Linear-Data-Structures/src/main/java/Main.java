import implementations.Stack;

public class Main {
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();

		stack.push(5);
		stack.push(6);
		stack.push(7);

		System.out.println(stack.peek());

		System.out.println(stack.pop());

		System.out.println(stack.peek());
	}
}
