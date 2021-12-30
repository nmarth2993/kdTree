
/*
 * Nicholas Marthinuss
 * k-d tree implementation
 * 12/29/2021
 */

public class kdTree<T extends Number & Comparable<T>> {

	Node<T> head;

	public kdTree() {
		head = null;
	}

	// this method inserts a node into the kd tree
	public void insert(Node<T> insertNode) {

		// if tree empty, insert node as head
		if (head == null) {
			head = insertNode;
			return;
		}

		// otherwise, call recursive insert helper function
		// pass true to checkX since x is the first coordinate of the pair to be checked
		insert(head, insertNode, true);

	}

	// this method inserts into the subtree with the node `node`
	// this helper method keeps track of which coordinate is being compared
	private void insert(Node<T> head, Node<T> insertNode, boolean checkX) {

		if (checkX) {

			// comparing by x value
			// if less, go to left subtree, greater goes to right subtree
			if (insertNode.getX().compareTo(head.getX()) < 0) {

				// insert at left child if possible, else recursive insert
				if (head.getLeftChild() == null) {
					head.setLeftChild(insertNode);
				}
				else {
					// note that `checkX` is negated because after checking X we check Y and so on
					insert(head.getLeftChild(), insertNode, !checkX);
				}
			}
			else {

				// insert at left child if possible, else recursive insert
				if (head.getRightChild() == null) {
					head.setRightChild(insertNode);
				}
				else {
					// note that `checkX` is negated because after checking X we check Y and so on
					insert(head.getRightChild(), insertNode, !checkX);
				}
			}
		}
		else {
			// same code as above but checking Y values this time
			if (insertNode.getY().compareTo(head.getY()) < 0) {
				if (head.getLeftChild() == null) {
					head.setLeftChild(insertNode);
				}
				else {
					insert(head.getLeftChild(), insertNode, !checkX);
				}
			}
			else {
				if (head.getRightChild() == null) {
					head.setRightChild(insertNode);
				}
				else {
					insert(head.getRightChild(), insertNode, !checkX);
				}
			}
		}
	}

	// this method removes the given node from the kd tree if it exists
	// it returns the node that was removed
	public void delete(Node<T> deleteNode) {
		// https://stackoverflow.com/questions/7970966/kdtree-node-removal
		// https://www.geeksforgeeks.org/k-dimensional-tree-set-3-delete/

		head = delete(head, deleteNode, true);
		// delete(head, deleteNode, true);
	}

	private Node<T> delete(Node<T> head, Node<T> deleteNode, boolean checkX) {
		if (head == null) {
			return null;
		}

		// if we found the node to delete, delete it
		if (head.getX().compareTo(deleteNode.getX()) == 0 && head.getY().compareTo(deleteNode.getY()) == 0) {

			// case 1: leaf node
			// simple deletion
			if (deleteNode.getLeftChild() == null && deleteNode.getRightChild() == null) {
				/*
				 * Node<T> deletedNode = deleteNode; deleteNode = null; return deletedNode;
				 */
				return null;
			}

			// case 2: single right child
			// replace node with min (of current node's dimension) of right subtree
			// and recursively delete found node in right subtree
			else if (deleteNode.getRightChild() != null) {
				Node<T> minNode = findMinimum(head.getRightChild(), checkX);
				head.setX(minNode.getX());
				head.setY(minNode.getY());

				// delete the node we just copied
				Node<T> rightTree = delete(head.getRightChild(), minNode, checkX);
				head.setRightChild(rightTree);

				// return the deleted node
				// return deleteNode;

				return null;
			}

			// case 3:
			// current node is replaced by min (of same dimension) of left subtree
			// recursively delete the found minimum
			// make left subtree right child of current node
			else {
				Node<T> minNode = findMinimum(head.getLeftChild(), checkX);
				head.setX(minNode.getX());
				head.setY(minNode.getY());

				// delete the copied node
				Node<T> leftTree = delete(head.getLeftChild(), minNode, checkX);
				// set the left tree to the *right* child of current node
				head.setRightChild(leftTree);

				// return the deleted node
				// return deleteNode;

				return null;
			}
		}

		// otherwise, keep searching
		else {
			if (checkX) {
				if (deleteNode.getX().compareTo(head.getX()) < 0) {
					// return delete(head.getLeftChild(), deleteNode, !checkX);
					head.setLeftChild(delete(head.getLeftChild(), deleteNode, !checkX));
				}
				else {
					// return delete(head.getRightChild(), deleteNode, !checkX);
					head.setRightChild(delete(head.getRightChild(), deleteNode, !checkX));
				}
			}
			else {
				if (deleteNode.getY().compareTo(head.getY()) < 0) {
					// return delete(head.getLeftChild(), deleteNode, !checkX);
					head.setLeftChild(delete(head.getLeftChild(), deleteNode, !checkX));
				}
				else {
					// return delete(head.getRightChild(), deleteNode, !checkX);
					head.setRightChild(delete(head.getRightChild(), deleteNode, !checkX));
				}
			}
			return head;
		}
	}

	private Node<T> findMinimum(Node<T> subTree, boolean dimension) {
		if (subTree.getLeftChild() == null) {
			return subTree;
		}
		else {
			return subTree.min(findMinimum(subTree.getLeftChild(), dimension), dimension);
		}
	}

	public Node<T> search(Node<T> searchNode) {
		return search(head, searchNode, true);
	}

	private Node<T> search(Node<T> head, Node<T> searchNode, boolean checkX) {

		// if head null, we didn't find the node; return null
		if (head == null) {
			return null;
		}

		// if both x and y match, we found the node; return it
		if (head.getX().compareTo(searchNode.getX()) == 0 && head.getY().compareTo(searchNode.getY()) == 0) {
			return head;
		}

		// by this point we need to traverse a subtree
		// check x/y depending on level of subtree
		else if (checkX) {

			// if searchNode < head, traverse left tree
			if (searchNode.getX().compareTo(head.getX()) < 0) {
				return search(head.getLeftChild(), searchNode, !checkX);
			}

			// otherwise, traverse right tree
			else {
				return search(head.getRightChild(), searchNode, !checkX);
			}
		}
		else {

			// if searchNode < head traverse left tree
			if (searchNode.getY().compareTo(head.getX()) < 0) {
				return search(head.getLeftChild(), searchNode, !checkX);
			}

			// otherwise, traverse right tree
			else {
				return search(head.getRightChild(), searchNode, !checkX);
			}
		}
	}

	public void clear() {
		head = null;
	}

	public void dump() {
		dump(head);
		System.out.println();
	}

	private void dump(Node<T> node) {
		if (node == null) {
			return;
		}

		System.out.print("(");
		dump(node.getLeftChild());
		System.out.print(node);
		dump(node.getRightChild());
		System.out.print(")");
	}

}
