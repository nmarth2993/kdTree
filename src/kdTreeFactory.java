
public class kdTreeFactory<T extends Number & Comparable<T>> {

	public static kdTree<Integer> createRandIntegerTree(int size) {
		kdTree<Integer> tree = new kdTree<>();

		for (int i = 0; i < size; i++) {
			tree.insert(NodeFactory.createRandIntegerNode());
		}

		return tree;
	}

	public static kdTree<Double> createRandDoubleTree(int size) {
		kdTree<Double> tree = new kdTree<>();

		for (int i = 0; i < size; i++) {
			tree.insert(NodeFactory.createRandDoubleNode());
		}

		return tree;
	}

}
