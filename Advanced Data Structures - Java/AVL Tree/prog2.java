package assignment2;

class StringAVLNode {
	private String item;
	private int balance;
	private StringAVLNode left, right;
	
	// just one constructor, please
	public StringAVLNode(String str) {
		item = str;
		balance = 0;
		left = null;
		right = null;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int bal) {
		balance = bal;
	}
	public String getItem() {
		return item;
	}
	// no setItem
	public StringAVLNode getLeft() {
		return left;
	}
	public void setLeft(StringAVLNode pt) {
		left = pt;
	}
	public StringAVLNode getRight() {
		return right;
	}
	public void setRight(StringAVLNode pt) {
		right = pt;
	}
	
	public String toString() {
		return "This node has String: " + item + " and Balance: " + balance + "\n";
	}
} // StringAVLNode


class StringAVLTree {
	// should really be private but I need access for my
	// test program to work
	StringAVLNode root;
	
	//just one constructor
	public StringAVLTree() {
		root = null;
	}
	
	// Rotate the node to the right 
	private static StringAVLNode rotateRight(StringAVLNode t) {
		StringAVLNode savedLeftRight = t.getLeft().getRight();
		StringAVLNode newRoot = t.getLeft();
		
		newRoot.setRight(t);
		newRoot.getRight().setLeft(savedLeftRight);
		
		return newRoot;
	}
	// Rotate the node to the left 
	private static StringAVLNode rotateLeft(StringAVLNode t) {
		StringAVLNode savedRightLeft = t.getRight().getLeft();
		StringAVLNode newRoot = t.getRight();
		
		newRoot.setLeft(t);
		newRoot.getLeft().setRight(savedRightLeft);
		
		return newRoot;
	}
	
	// For these next four, be sure not to use any global variables
	// Return the height of the tree – not to be used anywhere in insert or delete
	public int height() {
		int h = height(root);
		return h;
	}
	//recursive companion method for height()
	private int height(StringAVLNode focusNode) {
		int lfh = 0;
		int rth = 0;
		int result;
		//node/subtree is empty, thus has no height
		if (focusNode == null)
			result = 0;
		else {
			//node has left and right child, check height of both
			if (focusNode.getLeft() != null && focusNode.getRight() != null) {
				lfh = height(focusNode.getLeft());
				rth = height(focusNode.getRight());
			}
			//node only has left child, only check height of left
			else if (focusNode.getLeft() != null && focusNode.getRight() == null) {
				lfh = height(focusNode.getLeft());
			}
			//node only has right child, only check height of right
			else if (focusNode.getLeft() == null && focusNode.getRight() != null) {
				rth = height(focusNode.getRight());
			}
			//node has no children
			else {
				result = 1;
			}
			//add 1 to the child that is larger
			if (lfh > rth)
				result = lfh + 1;
			else
				result = rth + 1;	
		}
		
		return result;
	}
	
	// Return the number of leaves in the tree
	public int leafCt() {
		int leafs = leafCt(root);
		return leafs;
	}
	//recursive companion method for leafCt()
	private int leafCt(StringAVLNode focusNode) {
		int LFleafs = 0;
		int RTleafs = 0;
		int result;
		
		if (focusNode == null)
			result = 0;
		else if (focusNode.getLeft() != null && focusNode.getRight() != null) {
			LFleafs = leafCt(focusNode.getLeft());
			RTleafs = leafCt(focusNode.getRight());
			result = LFleafs + RTleafs;
		} 
		//node only has left child, 
		else if (focusNode.getLeft() != null && focusNode.getRight() == null) {
			LFleafs = leafCt(focusNode.getLeft());
			result = LFleafs;
		}
		//node only has right child, 
		else if (focusNode.getLeft() == null && focusNode.getRight() != null) {
			RTleafs = leafCt(focusNode.getRight());
			result = RTleafs;
		}
		//node has no children
		else {
			result = 1;
		}
		
		return result;
	}
	
	// Return the number of perfectly balanced AVL nodes
	public int balanced() {
		int balanced = balanced(root);
		return balanced;
	}
	//Recursive companion method for balanced()
	private int balanced(StringAVLNode focusNode) {
		int lfbc = 0;
		int rtbc = 0;
		int result;
		
		if (focusNode == null) {
			result = 0;
		}
		//if node has both left and right child, it is possibley perfectly balanced
		else if (focusNode.getLeft() != null && focusNode.getRight() != null) {
			lfbc = balanced(focusNode.getLeft());
			rtbc = balanced(focusNode.getRight());
			if (focusNode.getBalance() == 0)
				result = (lfbc + rtbc + 1);
			else
				result = (lfbc + rtbc);
		}
		//if node has left child but no right child, it can't be perfectly balanced
		else if (focusNode.getLeft() != null && focusNode.getRight() == null) {
			result = balanced(focusNode.getLeft());
		}
		//if node has right child but no left child, it can't be perfectly balanced
		else if (focusNode.getLeft() == null && focusNode.getRight() != null) {
			result = balanced(focusNode.getRight());
		}
		//if node has no children, it is perfectly balanced
		else
			result = 1;
		
		return result;
	}
	
	// Return the inorder successor or null if there is none or str is not in the tree
	public String successor(String str) {
		StringAVLNode savedNode = null;
		String s = successor(root, str, savedNode);
		return s;
	}
	
	private String successor(StringAVLNode focusNode, String str, StringAVLNode savedNode) {
		String s = null;
		//if node is not null
		if (focusNode != null) {
			//if string found
			if (str.compareTo(focusNode.getItem()) == 0) {
				//found the node
				//	if there is a right child, go right then all the way left
				if (focusNode.getRight() != null) {
					//go right once, then all the way left
					focusNode = focusNode.getRight();
					while (focusNode.getLeft() != null) {
						focusNode = focusNode.getLeft();
					}
					//now focusNode is the left most, and thus the successor
					s = focusNode.getItem();
				}
				//else there is no right child, 
				//	and the successor is the first parent going left
				else {
					if (savedNode != null)
						//in this situation, found node had a higher parent
						s = savedNode.getItem(); 
					else
						//in this situation, found node was the highest in tree
						//	and no left turn was required
						s = null;
				}
			}
			//else if string is less than
			else if (str.compareTo(focusNode.getItem()) < 0) {
				//go left
				savedNode = focusNode;
				s = successor(focusNode.getLeft(), str, savedNode);
			}
			//else if string is greater than
			else if (str.compareTo(focusNode.getItem()) > 0) {
				//go right
				s = successor(focusNode.getRight(), str, savedNode);
			}
		}
		//else if node is null
		else {
			s = null;
		}
		
		return s;
	}
	
	//inserts item to tree and assigns modified tree to root
	public void insert(String str) {
		root = insert(str, root);
	}
	//internal method to modify tree and return modified tree
	private StringAVLNode insert(String str, StringAVLNode t) {
		
		//go through 4 cases; 
		//node's null, node already has item, item is less than, item is greater than
		//if node is null then item can go into it
		if (t == null) {
			t = new StringAVLNode(str);
			
		}
		//otherwise if item is already in node, do nothing
		else if (str.compareTo(t.getItem()) == 0) { 
		}
		//otherwise if item is less than the item in node, 
		//	set into left child byway of insert method, recursively
		//	if str is less than t's item 
		//	then the result from compareTo will be negative, thus less than zero
		else if (str.compareTo(t.getItem()) < 0) {		
			//if left is empty, insert and modify t's balance
			if (t.getLeft() == null) {
				t.setLeft(insert(str, t.getLeft()));
				t.setBalance(t.getBalance() - 1);
			}
			//otherwise left is occupied and we must insert into it,
			//	then check if left's height changed
			else {
				int oldleftbalance = t.getLeft().getBalance();
				t.setLeft(insert(str, t.getLeft()));
				//if left's height changed, then left is longer, thus t's balance minus 1
				//	then check if t is imbalanced
				if (oldleftbalance == 0 && t.getLeft().getBalance() != 0) {
					t.setBalance(t.getBalance() - 1);
					//then check for imbalance, if t's balance is less than -1
					if (t.getBalance() < -1) {
						//perform rotations
						//	if Left-Right
						if (str.compareTo(t.getLeft().getItem()) > 0) {
							//perform left-right rotation
							t.setLeft(rotateLeft(t.getLeft()));
							t = rotateRight(t);
							//if string is in the lowest effected node
							if (t.getBalance() == 0) {
								t.getRight().setBalance(0);
								t.getLeft().setBalance(0);
							}
							//if string was inserted into 
							//	left child of lowest effected node
							else if (t.getBalance() < 0) {
								t.getLeft().setBalance(0);
								t.getRight().setBalance(1);
							}
							//if string was inserted into 
							//	right child of lowest effected node
							else {
								t.getLeft().setBalance(-1);
								t.getRight().setBalance(0);
							}
							t.setBalance(0);	
						}
						//else Left-Left
						else {
							//perform single right rotation
							t = rotateRight(t);
							t.getRight().setBalance(0);
							t.setBalance(0);
						}
					}
				}
			}
		}
		//otherwise str can only be greater than item in node, 
		//	set into right child byway of insert method
		else {
			//if right is empty, insert and modify t's balance
			if (t.getRight() == null) {
				t.setRight(insert(str, t.getRight()));
				t.setBalance(t.getBalance() + 1);
			}
			//otherwise right child is occupied and we must insert into it,
			//	then check if right child's height changed
			else {
				int oldrightbalance = t.getRight().getBalance();
				t.setRight(insert(str, t.getRight()));
				//if right child's height changed (increased), 
				// 	then right child is longer, thus t's balance plus 1
				//	then check if t is imbalanced
				if (oldrightbalance == 0 && t.getRight().getBalance() != 0) {
					t.setBalance(t.getBalance() + 1);
					//check for imbalance
					//	if t's balance is greater than 1, then perform rotations
					if (t.getBalance() > 1) {
						//if Right-Left
						if (str.compareTo(t.getRight().getItem()) < 0) {
							//perform right-left rotation
							t.setRight(rotateRight(t.getRight()));
							t = rotateLeft(t);
							//if string is in the lowest effected node
							if (t.getBalance() == 0) {
								t.getRight().setBalance(0);
								t.getLeft().setBalance(0);
							}
							//if string was inserted into 
							//	left child of lowest effected node
							else if (t.getBalance() < 0) {
								t.getLeft().setBalance(0);
								t.getRight().setBalance(1);
							}
							//if string was inserted into 
							//	right child of lowest effected node
							else {
								t.getLeft().setBalance(-1);
								t.getRight().setBalance(0);
							}
							t.setBalance(0);
						}
						//if Right-Right
						else {
							//perform single left rotation
							t = rotateLeft(t);
							t.getLeft().setBalance(0);	
							t.setBalance(0);
						}
					}
				}	
			}
		} 
			
		return t;
	}
	
	// who are you? Put your name here!
	public static String myName() {
		return "Daniel Cardenas";
	}
}  // end of StringAVLTree class