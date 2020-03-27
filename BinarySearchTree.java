

public class BinarySearchTree {
	Node root;

	int height(Node node){
		if (node == null){
			return 0;
		}
		else{
			int lHeight = height(node.left);
			int rHeight = height(node.right);

			if (lHeight < rHeight){
				return (rHeight + 1);
			}
			else{
				return (lHeight + 1);
			}
		}
	}

	private class Node{
		private int key;
		private Node left, right;
		
		public Node(int key) {
			this.key = key;
			this.left = null;
			this.right = null;
		}
	}
		
		public void  BST() {
			root = null;
		}
		
	public void add(Integer x) {
		//only add if the element isnt already in the tree
		if(!isElement(root, x)){
			root = insertNode(root, x);
	
		}
		return;
	}
	Node insertNode(Node root, Integer x){
		//if the tree is empty, just create a new node
		if(root == null){
			root = new Node(x);
			return root;
		}

		//if the value to add is less than the current value
		if (x < root.key){
			//reccur on the left child
			root.left = insertNode(root.left, x);
		}
		//if the value to add is more than the current value
		else if(x > root.key){
			//reccur on the right child
			root.right = insertNode(root.right, x);
		}
		return root;
	}

	public void remove(int x) {
		//Only go to helper if the element exists
		if(!isElement(root, x)){
			root = deleteNode(root, x);
		}
		return;
	}

	Node deleteNode(Node root, int x){
		//if the value to delete is less than the current value
		if(x < root.key){
			//reccur on the left child
			root.left = deleteNode(root.left, x);
		}
		//if the value to delete is more than the current value
		else if(x > root.key){
			//reccur on the right child
			root.right = deleteNode(root.right, x);
		}
		else{
			//if the node to delete only has a right child, set the right child as the new root
			if(root.left == null){
				return root.right;
			}
			//if the node to delete only has a left child, set the left child as the new root
			else if(root.right == null){
				return root.left;
			}
			//set new root to the next highest value
			root.key = minValue(root.right);
			//reccur to delete the duplicate of the new root
			root.right = deleteNode(root.right, x);
		}

		return root;
	}

	public boolean isElement(Node root, int x) {
		while(root!=null){
			if(root.key == x){
				return true;
			}
			if(root.key > x){
				root = root.left;
			}
			else{
				root = root.right;
			}
		}
		return false;
	}

	public boolean setEmpty() {
		//if the root is null, the tree is empty
		if(root == null){
			return true;
		}
		return false;
	}

	public int SetSize(Node root){
		Node current = root;

		if(current == null){
			return 0;
		}
		//reccur on both children, adding one to account for the root
		return SetSize(root.right) + SetSize(root.left) + 1;
	}

	

	public int minValue(Node root){
		int min = root.key;
		//keep going down the left side of the tree until you get to the bottom
		while(root.left != null){
			min = root.left.key;
			root = root.left;
		}
		return min;
	}

	public BinarySearchTree intersection(BinarySearchTree tree1, BinarySearchTree tree2){
		BinarySearchTree intersection = new BinarySearchTree();
		intersection = intersectionTraversel(tree1.root, tree2.root, intersection);
		return intersection;
	}

	public BinarySearchTree intersectionTraversel(Node current1, Node current2, BinarySearchTree intersection){
		//if both trees are empty, return tree as us
		if(current1 == null || current2 == null){
			return intersection;
		}
		//if the current item doesn't exist in the second tree, add it to the new bst
		if(isElement(current2, current1.key)){
			intersection.add(current1.key);
		}
		//reccur on the right child if there is one
		if(current1.right != null){
			intersectionTraversel(current1.right, current2, intersection);
		}
		//reccur on the left child if there is one
		if(current1.left != null){
			intersectionTraversel(current1.left, current2, intersection);
		}

		return intersection;
	}

	public BinarySearchTree union(BinarySearchTree tree1, BinarySearchTree tree2){
		BinarySearchTree union = new BinarySearchTree();
		union = unionTraversel(tree1.root, union);
		union = unionTraversel(tree2.root, union);
		return union;
	}

	public BinarySearchTree unionTraversel(Node current, BinarySearchTree union){
		//if the tree is empty, return new tree as is
		if (current == null){
			return union;
		}
		//if the item doesn't exist in the new tree yet, add it
		if(!isElement(union.root, current.key)){
			union.add(current.key);
		}
		//reccur on the right child if there is one
		if(current.right != null){
			unionTraversel(current.right, union);
		}
		//reccur on the left child if there is one
		if(current.left != null){
			unionTraversel(current.left, union);
		}

		return union;
	}

	public BinarySearchTree difference(BinarySearchTree tree1, BinarySearchTree tree2){
		BinarySearchTree difference = new BinarySearchTree();
		difference = differenceTraversel(tree1.root, tree2.root, difference);
		return difference;
	}

	public BinarySearchTree differenceTraversel(Node current1, Node current2, BinarySearchTree difference){
		//if both subtrees are empty return resultant tree as is
		if(current1 == null && current2 == null){
			return difference;
		}
		//if the current value in the first tree doesn't exist in the second tree, add it to the new BST
		if(!isElement(current2, current1.key)){
			difference.add(current1.key);
		}
		//reccur on the right child if there is one
		if(current1.right != null){
			differenceTraversel(current1.right, current2, difference);
		}
		//reccur on the left child if there is one
		if(current1.left != null){
			differenceTraversel(current1.left, current2, difference);
		}

		return difference;
	}


	public boolean isSubset(BinarySearchTree tree1, BinarySearchTree tree2){
		boolean result = isSubsetTraversel(tree1.root, tree2.root);
		return result;
	}

	public boolean isSubsetTraversel(Node current1, Node current2){
		//initialse to true
		boolean result = true;
		//if both trees are empty, then tree one isn't a subset of tree2
		if(current1 == null && current2 == null){
			return false;
		}
		//if theres an element in tree1 thats not in tree2, tree1 is not a subset
		if(!isElement(current2, current1.key)){
			return false;	
		}
		//reccur on the right child if there is one
		if(current2.right != null){
			isSubsetTraversel(current1, current2.right);	
		}
		//reccur on the left child if there is one
		if(current2.left != null){
			isSubsetTraversel(current1, current2.left);
		}
		return result;
	}
}


