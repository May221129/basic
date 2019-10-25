package collection.d_tree;

/**
 * 二叉查找树（二叉排序树）之先序遍历、中序遍历和后序遍历的实现。
 * 笔记见：G:\mystudy\studynotes\数据结构_树.xmind
 * 
 * @author May
 * 2019年10月10日
 */
public class BinarySortTree {
	public static void main(String[] args) {
		BinarySortTree binarySortTree = new BinarySortTree();
		Node root = binarySortTree.createTree();
//		binarySortTree.firstRoot(root);
//		binarySortTree.secondRoot(root);
		binarySortTree.lastRoot(root);
	}
	
	public void lastRoot(Node root) {//后序遍历
		if(null != root) {
			if(root.getLeft() != null) {
				lastRoot(root.getLeft());
			}
			if(root.getRight() != null) {
				lastRoot(root.getRight());
			}
			System.out.println(root.getValue());
		}
	}
	
	public void secondRoot(Node root) {//中序遍历
		if(null != root) {
			if(root.getLeft() != null) {
				secondRoot(root.getLeft());
			}
			
			System.out.println(root.getValue());
			
			if(root.getRight() != null) {
				secondRoot(root.getRight());
			}
		}
	}
	
	public void firstRoot(Node root) {//先序遍历
		if(null != root) {
			System.out.println(root.getValue());
			
			if(root.getLeft() != null) {
				firstRoot(root.getLeft());
			}
			if(root.getRight() != null) {
				firstRoot(root.getRight());
			}
		}
	}
	
	public Node createTree() {//建树
		Node n5 = new Node(5);
		Node n2 = new Node(2);
		Node n1 = new Node(1);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n12 = new Node(12);
		Node n8 = new Node(8);
		Node n6 = new Node(6);
		Node n7 = new Node(7);
		Node n10 = new Node(10);
		Node n9 = new Node(9);
		Node n11 = new Node(11);
		Node n13 = new Node(13);
		
		n5.setLeft(n2);
		n2.setLeft(n1);
		n2.setRight(n3);
		n3.setRight(n4);
		n5.setRight(n12);
		n12.setLeft(n8);
		n12.setRight(n13);
		n8.setLeft(n6);
		n8.setRight(n10);
		n6.setRight(n7);
		n10.setLeft(n9);
		n10.setRight(n11);
		
		return n5;
	}
}

class Node {//节点
    private Node left;
    private Node right;
    private int value;
    
    public Node(int value) {
    	this.value = value;
    }
    
	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
}