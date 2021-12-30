import java.util.Random;

/*
 * Nicholas Marthinuss
 * Node Factory
 * 12/29/2021
 */

public class NodeFactory<T extends Number & Comparable<T>> {

	Random randomGen;

	public NodeFactory() {
		randomGen = new Random(42);
	}

	public Node<T> createNode(T x, T y) {
		return new Node<T>(x, y);
	}

	public Node<Integer> createRandIntegerNode() {
		int min = 0;
		int max = 256;
		return createRandIntegerNode(min, max);
	}

	public Node<Integer> createRandIntegerNode(int min, int max) {
		return new Node<Integer>(min + randomGen.nextInt(max + 1), min + randomGen.nextInt(max + 1));
	}

	public Node<Double> createRandDoubleNode() {
		double min = 0;
		double max = 256;
		return createRandDoubleNode(min, max);
	}

	public Node<Double> createRandDoubleNode(double min, double max) {
		return new Node<Double>(min + randomGen.nextDouble() * max, min + randomGen.nextDouble() * max);
	}

}
