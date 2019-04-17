package arbori;

class Node {
	int value;
	Node left;
	Node right;

	Node(int value) {
		this.value = value;
		left = null;
		right = null;
	}
}

public class Bst {

	static Node root = null;

	static boolean search(int val) {
		Node currentNode = root;
		while (currentNode != null) {
			if (currentNode.value == val) {
				return true;
			} else if (currentNode.value > val) {
				currentNode = currentNode.left;
			} else {
				currentNode = currentNode.right;
			}
		}
		return false;
	}

	static void insert(int val) {
		long sTime = System.currentTimeMillis();
		Node newNode = new Node(val);
		if (root == null) {
			root = newNode;
			return;
		}
		Node currentNode = root;
		Node parent = null;

		while (true) {

			parent = currentNode;
			if (val < currentNode.value) {
				currentNode = currentNode.left;
				if (currentNode == null) {
					parent.left = newNode;
					long eTime = System.currentTimeMillis();
					System.out.println("inserting " + val + "took: " + (eTime - sTime) + "ms");
					return;
				}
			} else {
				currentNode = currentNode.right;
				if (currentNode == null) {
					parent.right = newNode;
					long eTime = System.currentTimeMillis();
					System.out.println("inserting " + val + "took: " + (eTime - sTime) + "ms");
					return;
				}
			}

		}

	}

	static void displayInOrder(Node root) {

		if (root != null) {
			displayInOrder(root.left);
			System.out.print(root.value + " ");
			displayInOrder(root.right);
		}

	}

	static void displayPreOrder(Node root) {

		if (root != null) {
			System.out.print(root.value + " ");
			displayPreOrder(root.left);

			displayPreOrder(root.right);
		}

	}

	static void displayPostOrder(Node root) {

		if (root != null) {
			displayPostOrder(root.left);

			displayPostOrder(root.right);
			System.out.print(root.value + " ");
		}

	}

	static boolean delete(int val) {
		if (search(val)) {

			Node currentNode = root;
			Node parent = null;
			while (true) {

				if (currentNode.value == val) {
					if (currentNode.left == null & currentNode.right == null) {
						if (currentNode == root) {
							root = null;
						} else {

							if (currentNode.value > parent.value)
								parent.right = null;
							else
								parent.left = null;
						}

						System.out.println(val + " has been deleted!");
						return true;
					} else if (currentNode.left != null ^ currentNode.right != null) {
						if (currentNode == root) {
							if (root.left != null)
								root = root.left;
							else
								root = root.right;
						} else {
							if (currentNode.left != null) {
								if (parent.value < currentNode.left.value)
									parent.right = currentNode.left;
								else
									parent.left = currentNode.left;
							} else if (currentNode.right != null) {
								if (parent.value < currentNode.right.value)
									parent.right = currentNode.right;
								else
									parent.left = currentNode.right;
							}

						}
						System.out.println(val + " has been deleted!");
						return true;
					} else if (currentNode.left != null & currentNode.right != null) {
						Node succesor = findSuccesor(currentNode.right);
						if (currentNode == root) {
							root = succesor;
							root.left = currentNode.left;
							root.right = currentNode.right;
							return true;
						} else {

							if (parent.value > val) {
								parent.left = succesor;
								succesor.left = currentNode.left;
							} else {
								parent.right = succesor;
								succesor.right = currentNode.right;
							}
						}

						System.out.println(val + " has been deleted!");
						return true;
					}

				} else if (currentNode.value > val) {
					parent = currentNode;
					currentNode = currentNode.left;
				} else {
					parent = currentNode;
					currentNode = currentNode.right;
				}

			}

		} else {
			System.out.println(val + " doesn't exist in the tree!");
			return false;
		}
	}

	static Node findSuccesor(Node a) {
		Node parentA = a;
		while (true) {

			if (a.left == null) {
				if (a.right != null) {
					parentA.left = a.right;
					break;
				} else
					break;

			} else {
				parentA = a;
				a = a.left;
			}

		}

		return a;

	}

	public static void main(String[] args) {

		insert(5);
		insert(9);
		insert(3);
		insert(2);
		insert(1);
		insert(4);
		insert(6);
		insert(7);
		insert(8);
		insert(10);
		long sTime = System.currentTimeMillis();
		displayInOrder(root);
		long eTime = System.currentTimeMillis();
		System.out.println("    displaying entire tree InOrder took: " + (eTime - sTime) + "ms");

		System.out.println(" Searching if 10 exists: " + search(10));
		System.out.println(" Searching if 5 exists: " + search(5));
		delete(10);
		displayInOrder(root);
		sTime = System.currentTimeMillis();
		displayPreOrder(root);
		eTime = System.currentTimeMillis();
		System.out.println("    displaying entire tree PreOrder took: " + (eTime - sTime) + "ms");
		sTime = System.currentTimeMillis();
		displayPostOrder(root);
		eTime = System.currentTimeMillis();
		System.out.println("    displaying entire tree PostOrder took: " + (eTime - sTime) + "ms");

	}

}
