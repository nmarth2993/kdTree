/*
 * Nicholas Marthinuss
 * Node class for k-d tree implementation
 * 12/29/2021
 */

public class Node<T extends Number & Comparable<T>> {

	private T x;
	private T y;

	private Node<T> leftChild;
	private Node<T> rightChild;

	// not generalizing this to be n-dimensional
	public Node(T x, T y) {
		this.x = x;
		this.y = y;
	}

	public T getX() {
		return x;
	}

	public void setX(T x) {
		this.x = x;
	}

	public T getY() {
		return y;
	}

	public void setY(T y) {
		this.y = y;
	}

	public Node<T> getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(Node<T> leftChild) {
		this.leftChild = leftChild;
	}

	public Node<T> getRightChild() {
		return rightChild;
	}

	public void setRightChild(Node<T> rightChild) {
		this.rightChild = rightChild;
	}

	public Node<T> min(Node<T> node, boolean checkX) {
		if (checkX) {
			return node.getX().compareTo(getX()) < 0 ? node : this;
		}
		else {
			return node.getY().compareTo(getY()) < 0 ? node : this;
		}
	}

	@Override
	public String toString() {
		// return "(" + x + ", " + y + ")";
		return x + ", " + y;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean equals(Object o) {
		/*
		 * Pattern matching is JDK 16+ if (o instanceof Node<?> node) { return
		 * node.x.compareTo(x) == 0 && node.y.compareTo(y) == 0; }
		 */
		if (o instanceof Node<?>) {
			Node<T> node = (Node<T>) o;
			return node.x.compareTo(x) == 0 && node.y.compareTo(y) == 0;
		}
		return false;
	}

}
