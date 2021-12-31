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
		System.out.println("Search Test: " + (testSearch() ? TEST_PASS_STRING : TEST_FAIL_STRING));

	}

	public static boolean testInsert() {

		kdTree<Integer> tree = new kdTree<>();
		ArrayList<Node<Integer>> nodeList = new ArrayList<>();

		for (int i = 0; i < 7; i++) {
			Node<Integer> node = NodeFactory.createRandIntegerNode();
			nodeList.add(node);
			tree.insert(node);

			// System.out.println("add Node " + node);

			// tree.dump(tree.tree);
			// System.out.println();
		}

		// tree.dump();
		return validTree(tree.head, true);

	}

	public static boolean testDelete() {
		NodeFactory<Integer> nodeFactory = new NodeFactory<>();

		kdTree<Integer> simpleTree = new kdTree<>();

		// test deleting tree with single root node
		simpleTree.insert(nodeFactory.createNode(5, 7));
		simpleTree.delete(nodeFactory.createNode(5, 7));
		if (simpleTree.head != null) {
			System.err.println("Expected root to be null, got " + simpleTree.head + " instead");
			return false;
		}

		// test deleting from an empty tree
		simpleTree.delete(nodeFactory.createNode(6, 6));
		if (simpleTree.head != null) {
			System.err.println("Expected root to be null, got " + simpleTree.head + " instead");
			return false;
		}

		// test deleting node not in tree
		simpleTree.insert(nodeFactory.createNode(1, 1));
		simpleTree.delete(nodeFactory.createNode(-1, -1));
		if (!simpleTree.head.equals(nodeFactory.createNode(1, 1))) {
			System.err.println("Expected root to be (1, 1), got " + simpleTree.head + " instead");
			return false;
		}

		/*
		 * // test deletion of a node not in the tree
		 * tree.delete(nodeFactory.createNode(-1, -1));
		 * 
		 * if (tree.head != null) { System.err.println(""); return false; }
		 * 
		 * if (simpleTree.head != null) { System.err.println("expected null tree, got "
		 * + simpleTree.head + " instead"); return false; }
		 */

		kdTree<Integer> tree = new kdTree<>();
		ArrayList<Node<Integer>> nodeList = new ArrayList<>();

		for (int i = 0; i < 50; i++) {
			Node<Integer> node = NodeFactory.createRandIntegerNode();
			nodeList.add(node);
			tree.insert(node);

			// System.out.println("add Node " + node);

			// tree.dump(tree.tree);
			// System.out.println();
		}

		// first, remove the first 10 elements and check that the tree is valid
		for (int i = 0; i < 10; i++) {
			tree.delete(nodeList.get(i));

			// check for a valid tree every deletion
			if (!validTree(tree.head, true)) {
				System.err.println("Found invalid tree in first deletion loop");
				return false;
			}
		}

		// tree must still be valid
		if (!validTree(tree.head, true)) {
			System.err.println("Found invalid tree after first deletion");
			return false;
		}

		// then, remove the remaining elements. after, the tree should be empty
		for (int i = 10; i < 40; i++) {
			tree.delete(nodeList.get(i));

			// check for a valid tree every deletion
			if (!validTree(tree.head, true)) {
				System.err.println("Found invalid tree in second deletion loop");
				return false;
			}
		}

		// tree.dump();
		// return validTree(tree.head, true);

		return true;
	}

	public static boolean testSearch() {
		NodeFactory<Integer> nodeFactory = new NodeFactory<>();

		kdTree<Integer> tree = new kdTree<>();
		ArrayList<Node<Integer>> nodeList = new ArrayList<>();

		for (int i = 0; i < 7; i++) {
			Node<Integer> node = NodeFactory.createRandIntegerNode();
			nodeList.add(node);
			tree.insert(node);
		}

		// tree.dump();

		// testing search for each element in list
		for (Node<Integer> node : nodeList) {
			// System.out.println("search: " + node);
			if (tree.search(node) == null) {
				System.err.println("expected to find node " + node + "; got null instead");
				return false;
			}

		}

		// test searching for element not in list
		if (tree.search(nodeFactory.createNode(-1, -1)) != null) {
			System.err.println("expected null");
			return false;
		}

		return true;
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
}