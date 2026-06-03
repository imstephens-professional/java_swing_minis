public class Node {
	String data;
	Node next;
	
	public Node(String d) {
		data = d;
	}
	public void setData(String d) {
		data = d;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node p) {
		next = p;
	}
	public String getName() {
		return data;
	}
	
}
