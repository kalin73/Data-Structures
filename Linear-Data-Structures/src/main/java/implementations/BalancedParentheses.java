package implementations;

import interfaces.Solvable;

public class BalancedParentheses implements Solvable {
	private String parentheses;

	public BalancedParentheses(String parentheses) {
		this.parentheses = parentheses;
	}

	@Override
	public Boolean solve() {
		ArrayDeque<Character> stack = new ArrayDeque<>();
		
		if(parentheses == null || parentheses.trim().isEmpty()) {
			return false;
		}

		for (int i = 0; i < parentheses.length(); i++) {
			char par = this.parentheses.charAt(i);

			if (stack.size() == 0 && isRightParentheses(par)) {
				return false;
			}

			if (isLeftParentheses(par)) {
				stack.push(par);

			} else {
				if (isReverseParentheses(stack.peek(), par)) {
					stack.pop();
				}
			}
		}

		return stack.isEmpty();
	}

	private boolean isLeftParentheses(char parentheses) {
		if (parentheses == '(' || parentheses == '{' || parentheses == '[') {
			return true;
		}
		return false;
	}

	private boolean isRightParentheses(char parentheses) {
		if (parentheses == ')' || parentheses == '}' || parentheses == ']') {
			return true;
		}
		return false;
	}

	private boolean isReverseParentheses(char leftParentheses, char rightParentheses) {
		if (leftParentheses == '(' && rightParentheses == ')') {
			return true;

		} else if (leftParentheses == '{' && rightParentheses == '}') {
			return true;

		} else if (leftParentheses == '[' && rightParentheses == ']') {
			return true;
		}

		return false;
	}
}
