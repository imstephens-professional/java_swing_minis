package lookethhere;


public class Stack {
	public Node head = null;

	// pushes element on top of the stack
	public void push(Node i) {

		if (head == null) {
			head = i;
		} else {
			i.setNext(head);
			head = i;
		}
	}

	// pops element off of the stack
	public Node pop() {
		if (head == null) {
			System.out.print("Error: can't pop off an empty stack");
			return head;
			
		} else if (head.getNext() == null) {
			Node temp = head;
			head = null;
			return temp;
			
		} else {
			Node placeHolder = head.getNext(); // the future head of the list
			Node theReturn = head;
			head = placeHolder;
			return theReturn;
		}
	}

	// looks at the first element of the stack
	public Node peek() {
		Node returned = head;
		return returned;
	}
	
	public void PrintList() {
		Node index = head;
		while (index != null) {
			System.out.println(index.getName());
			index = index.getNext();
		}
	}



}
