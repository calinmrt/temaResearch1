package arbori;

class N {
	int value;
	N parent;
	N left;
	N right;
	Boolean red;

	N(int val) {
		this.value = val;
		parent = null;
		left = null;
		right = null;
		red = true;
	}
}

public class Rbt {
	static N root = null;

	public static void main(String[] args) {

		insert(8);
		insert(18);
		insert(5);
		insert(15);
		insert(17);
		insert(25);
		insert(40);
		insert(80);
		display(root);
		// System.out.println(find(40).value);

		erase(8);
		//erase(17);
		// erase(5);
		System.out.println("");
		display(root);

	}

	static N succesor(N a) {
		while (a.left != null) {
			a = a.left;
		}
		return a;
	}

	static void erase(int val) {
		N x = find(val);
		N doubleBlack = null;

		// if x has 2 childs
		if (x.left != null & x.right != null) {
			x.value = succesor(x.right).value;
			x = succesor(x.right);
		}

		// if x is red and has no or at most 1 child
		if (x.red == true && (x.left == null & x.right == null)) {
			if (x.parent.left == x) {
				x.parent.left = null;
				x.parent = null;
			} else if (x.parent.right == x) {
				x.parent.right = null;
				x.parent = null;
			}
		} else if (x.red == true && (x.left == null ^ x.right == null)) {
			if (x.left != null) {
				x.value = x.left.value;
				x.red = false;
				x.left.parent = null;
				x.left = null;
			} else if (x.right != null) {
				x.value = x.right.value;
				x.red = false;
				x.right.parent = null;
				x.right = null;
			}
			return;
		}
		// if x is black and has a red child
		if (x.red == false && ((x.left != null && (x.left.red == true & x.right == null))
				^ (x.right != null && (x.left == null & x.right.red == true)))) {
			if (x.right == null) {
				x.value = x.left.value;
				// x.left.parent = null;
				x.left = null;
			} else if (x.left == null) {
				x.value = x.right.value;
				// x.right.parent = null;
				x.right = null;
			}
			return;
		}
		// if x is black and has a black child
		if (x.red == false && (((x.left != null && (x.left.red == false & x.right == null))
				^ (x.right != null && (x.left == null & x.right.red == false))) ^ (x.left == null & x.right == null))) {
			if (x.left == null & x.right == null) {
				doubleBlack = x.parent;
				if (x.value < x.parent.value) {
					x.parent.left = null;
				} else {
					x.parent.right = null;
				}

			} else {

				if (x.left != null) {
					x.value = x.left.value;
					if (x.left.left != null) {
						x.left = x.left.left;
						x.left.parent = x;
					}
					if (x.left.right != null) {
						x.right = x.left.right;
						x.right.parent = x;
					}
				}
				if (x.right != null) {
					x.value = x.right.value;
					if (x.right.left != null) {
						x.left = x.right.left;
						x.left.parent = x;
					}
					if (x.right.right != null) {
						x.right = x.right.right;
						x.right.parent = x;
					}
				}
				doubleBlack = x;
				
			}
			fix(doubleBlack);
		}
	}

	static void fix(N a) {
		// case 1 doubleBlack node =root
		if (a == root) {
			return;
		}
		// case 2 doubleBlack node parent is black and sibling is red
		if ((a.parent.red == false & sibling(a) != null) && sibling(a).red == true) {
			a.parent.red = true;
			sibling(a).red = false;
			if (a.value < sibling(a).value) {
				rotateLeft(sibling(a));
			} else {
				rotateRight(sibling(a));
			}
		}
		// case 3 doubleBlack node parent is black, sibling and his kids are all black
		if ((a.parent.red == false & sibling(a) != null & sibling(a).left != null & sibling(a).right != null)
				&& (sibling(a).red == false & sibling(a).left.red == false & sibling(a).right.red == false)) {
			sibling(a).red = true;
			a = a.parent;
		}
		// case 4 doubleBlack node parent is red
		if (a.parent.red == true) {
			a.parent.red = false;
			sibling(a).red = true;

			return;
		}
		// case 5 doubleBlack node parent is black, sibling is black and inner kid of
		// sibling is red
		if ((a.parent.red == false & sibling(a).red == false)
				& ((a.value < sibling(a).value & sibling(a).left.red == true)
						^ (a.value > sibling(a).value & sibling(a).right.red == true))) {
			sibling(a).red = true;
			if (a.value < sibling(a).value) {
				sibling(a).left.red = false;
				rotateRight(sibling(a).left);
			} else {
				sibling(a).right.red = false;
				rotateLeft(sibling(a).right);
			}
		}
		// case 6 doubleBlack node parent is black, sibling is black and outer kid of
		// sibling is red
		if ((sibling(a).red == false) & ((a.value < sibling(a).value & sibling(a).right.red == true)
				^ (a.value > sibling(a).value & sibling(a).left.red == true))) {
			if (a.value < sibling(a).value) {
				sibling(a).right.red = false;
				rotateLeft(sibling(a));
			} else {
				sibling(a).left.red = false;
				rotateRight(sibling(a));
			}
		}
	}

	static N sibling(N a) {
		if (a.value < a.parent.value) {
			return a.parent.right;
		} else
			return a.parent.left;

	}

	static N find(int val) {
		N current = root;
		while (true) {
			if (current.value == val) {
				break;
			}
			if (val < current.value) {
				if (current.left != null) {
					current = current.left;
				} else
					break;
			} else {
				if (current.right != null) {
					current = current.right;
				} else
					break;
			}
		}

		return current;

	}

	static void insert(int val) {
		N newN = new N(val);

		if (root == null) {
			root = newN;
			root.red = false;
		} else {
			N current = root;
			goToLeaf(newN, current);
			checkAndArrange(newN);
		}

		//////////

	}

	static void checkAndArrange(N n) {
		// if(n==root) {
		// return;
		// }
		if (n.parent.red == false) {
			return;
		} else {
			if (u(n) == null || u(n).red == false) { // |
				if (g(n).left == n.parent && n.parent.left == n) {
					rotateRight(n.parent);
					n.parent.red = !n.parent.red;
					n.parent.right.red = !n.parent.right.red;
					if (n.parent.right.right != null & n.parent != root)
						checkAndArrange(n.parent.right.right);
					else
						return;
				}
				if (g(n).left == n.parent && n.parent.right == n) {
					rotateLeft(n);
					rotateRight(n);
					n.red = !n.red;
					n.right.red = !n.right.red;
					if (n.right.right != null)
						checkAndArrange(n.right.right);
					else
						return;
				}
				if (g(n).right == n.parent && n.parent.right == n) {
					rotateLeft(n.parent);
					n.parent.red = !n.parent.red;
					n.parent.left.red = !n.parent.left.red;
					if (n.parent.left.left != null & n.parent != root)
						checkAndArrange(n.parent.left.left);
					else
						return;
				}
				if (g(n).right == n.parent && n.parent.left == n) {
					rotateRight(n);
					rotateLeft(n);
					n.red = !n.red;
					n.left.red = !n.left.red;
					if (n.left.left != null)
						checkAndArrange(n.left.left);
					else
						return;
				}

			} else if (u(n).red == true) {
				n.parent.red = false;
				u(n).red = false;
				n.parent.parent.red = true;
				if (n.parent.parent != root)
					checkAndArrange(n.parent.parent);
				else {
					root.red = false;
					return;
				}
			}
		}
	}

	static void rotateLeft(N p) {

		p.parent.right = p.left;
		p.left = p.parent;

		if (p.left != root) {
			if (p.parent.parent.value > p.parent.value) {
				p.parent.parent.left = p;
				p.parent = p.parent.parent;
			} else {
				p.parent.parent.right = p;
				p.parent = p.parent.parent;//
			}
		} else {
			root = p;
			root.parent = null;

		}
		p.left.parent = p;
		if (p.left.right != null) {
			p.left.right.parent = p.left;
		}

	}

	static void rotateRight(N p) {

		p.parent.left = p.right;
		p.right = p.parent;

		if (p.right != root) {
			if (p.parent.parent.value > p.parent.value) {
				p.parent.parent.left = p;
				p.parent = p.parent.parent;
			} else {
				p.parent.parent.right = p;
				p.parent = p.parent.parent;//
			}
		} else {
			root = p;
			root.parent = null;

		}
		p.right.parent = p;
		if (p.right.left != null) {
			p.right.left.parent = p.right;
		}

	}

	static N u(N n) {
		N un;
		if (n.parent.value > n.parent.parent.value) {
			un = n.parent.parent.left;

		} else
			un = n.parent.parent.right;
		return un;
	}

	static N g(N n) {
		N gp = n.parent.parent;
		return gp;

	}

	static void goToLeaf(N newN, N current) {
		if (newN.value > current.value) {
			if (current.right == null) {
				current.right = newN;
				newN.parent = current;

			} else {
				current = current.right;
				goToLeaf(newN, current);
			}
		} else if (newN.value < current.value) {
			if (current.left == null) {
				current.left = newN;
				newN.parent = current;

			} else {
				current = current.left;
				goToLeaf(newN, current);
			}
		}

	}

	static void display(N root) {

		if (root != null) {
			display(root.left);
			System.out.println(root.value + " " + root.red + " -- " + show(root.parent));
			display(root.right);
		}

	}

	static int show(N n) {
		if (n == null)
			return 0;

		else
			return n.value;
	}

	static void print(N n) {
		System.out.println(n.value + "  " + n.left.value + " " + n.right.value);
	}
}
