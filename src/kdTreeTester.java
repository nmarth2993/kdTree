import java.util.ArrayList;

/*
 * Nicholas Marthinuss
 * k-d tree tester
 * 12/29/2021
 */

public class kdTreeTester {

	public static String TEST_PASS_STRING = "TEST PASSED";
	public static String TEST_FAIL_STRING = "==TEST FAILED==";

	public static void main(String[] args) {

		System.out.println("Insert Test: " + (testInsert() ? TEST_PASS_STRING : TEST_FAIL_STRING));
		System.out.println("Delete Test: " + (testDelete() ? TEST_PASS_STRING : TEST_FAIL_STRING));

	}

	public static boolean testInsert() {
		NodeFactory<Integer> nodeFactory = new NodeFactory<>();

		kdTree<Integer> tree = new kdTree<>();
		ArrayList<Node<Integer>> nodeList = new ArrayList<>();

		for (int i = 0; i < 7; i++) {
			Node<Integer> node = nodeFactory.createRandIntegerNode();
			nodeList.add(node);
			tree.insert(node);

			// System.out.println("add Node " + node);

			// tree.dump(tree.tree);
			// System.out.println();
		}

		// tree.dump();
		return validTree(tree.head, true);

	}

	private static boolean validTree(Node<Integer> head, boolean checkX) {
		if (head == null) {
			return true;
		}

		if (head.getLeftChild() != null && head.getRightChild() != null) {
			// make sure that the dimensions of the things are good
			// check that left of dimensions < right of dimension
			boolean ordered;
			// System.out.println("checking head with 2 children: " + head);
			if (checkX) {
				ordered = head.getRightChild().getX().compareTo(head.getLeftChild().getX()) > 0;
				// System.out.println("bool1: " + ordered);
			}
			else {
				ordered = head.getRightChild().getY().compareTo(head.getLeftChild().getY()) > 0;
				// System.out.println("bool2: " + ordered);
			}
			return ordered && validTree(head.getLeftChild(), !checkX) && validTree(head.getRightChild(), !checkX);
		}

		else {
			if (head.getLeftChild() != null) {
				return validTree(head.getLeftChild(), !checkX);
			}
			if (head.getRightChild() != null) {
				return validTree(head.getRightChild(), !checkX);
			}

			// the case where current node has no children
			return true;
		}
	}

	public static boolean testDelete() {
		NodeFactory<Integer> nodeFactory = new NodeFactory<>();

		kdTree<Integer> simpleTree = new kdTree<>();
		simpleTree.insert(nodeFactory.createNode(5, 7));
		simpleTree.delete(nodeFactory.createNode(5, 7));

		if (simpleTree.head != null) {
			System.err.println("expected null tree, got " + simpleTree.head + " instead");
			return false;
		}

		kdTree<Integer> tree = new kdTree<>();
		ArrayList<Node<Integer>> nodeList = new ArrayList<>();

		for (int i = 0; i < 50; i++) {
			Node<Integer> node = nodeFactory.createRandIntegerNode();
			nodeList.add(node);
			tree.insert(node);

			System.out.println("add Node " + node);

			// tree.dump(tree.tree);
			// System.out.println();
		}

		/*
		 * // test deletion of a node not in the tree
		 * tree.delete(nodeFactory.createNode(-1, -1));
		 * 
		 * if (tree.head != null) { System.err.println(""); return false; }
		 */

		// first, remove the first 10 elements and check that the tree is valid
		for (int i = 0; i < 10; i++) {
			tree.delete(nodeList.get(i));
		}

		// tree must still be valid
		if (!validTree(tree.head, true)) {
			return false;
		}

		/*
		 * // then, remove the remaining elements. after, the tree should be empty for
		 * (int i = 10; i < 40; i++) { tree.delete(nodeList.get(i)); }
		 */

		// tree.dump();
		return validTree(tree.head, true);
	}
}
