package com.web.algorithm.tree;

public class BinaryTree {

	private TreeNode root;

	public BinaryTree(TreeNode root) {
		this.root = root;
	}

	public void insert(TreeNode node) {

	}

	public void delete(TreeNode node) {

	}

	public int find(TreeNode node) {
		return 0;
	}

	/**
	 * 前序遍历的递推公式：
	 * preOrder(r) = print r->preOrder(r->left)->preOrder(r->right)
	 */
	public void preOrder(TreeNode root) {

	}

	/**
	 * 中序遍历的递推公式：
	 * inOrder(r) = inOrder(r->left)->print r->inOrder(r->right)
	 *
	 * @param root
	 */
	public void inOrder(TreeNode root) {

	}

	/**
	 * 后序遍历的递推公式：
	 * postOrder(r) = postOrder(r->left)->postOrder(r->right)->print r
	 *
	 * @param root
	 */
	public void postOrder(TreeNode root) {

	}
}
