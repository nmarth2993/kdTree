import java.util.Random;

/*
 * Nicholas Marthinuss
 * Node Factory
 * 12/29/2021
 */

public class NodeFactory<T extends Number & Comparable<T>> {

	public static final long SEED = 42;

	Random randomGen;

	public NodeFactory() {
		randomGen = new Random(SEED);
	}

	public Node<T> createNode(T x, T y) {
		return new Node<T>(x, y);
	}

	public static Node<Integer> createRandIntegerNode() {
		int min = 0;
		int max = 256;
		return createRandIntegerNode(min, max);
	}

	public static Node<Integer> createRandIntegerNode(int min, int max) {
		Random randomGen = new Random(SEED);
		return new Node<Integer>(min + randomGen.nextInt(max + 1), min + randomGen.nextInt(max + 1));
	}

	public static Node<Double> createRandDoubleNode() {
		double min = 0;
		double max = 256;
		return createRandDoubleNode(min, max);
	}

	public static Node<Double> createRandDoubleNode(double min, double max) {
		Random randomGen = new Random(SEED);
		return new Node<Double>(min + randomGen.nextDouble() * max, min + randomGen.nextDouble() * max);
	}

}
