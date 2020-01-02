package com.web.algorithm.tree;

public class TreeTest {

	/**
	 * 翻转一棵二叉树。
	 * 示例：
	 * 输入：
	 * 4
	 * /   \
	 * 2     7
	 * / \   / \
	 * 1   3 6   9
	 * 输出：
	 * 4
	 * /   \
	 * 7     2
	 * / \   / \
	 * 9   6 3   1
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/invert-binary-tree
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 *
	 * @param root
	 * @return
	 */
	public TreeNode invertTree(TreeNode root) {
		if (root == null) {
			return root;
		}

		if (root.left != null) {
			invertTree(root.left);
		}

		if (root.right != null) {
			invertTree(root.right);
		}

		TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;
		return root;
	}

	/**
	 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
	 * 假设一个二叉搜索树具有如下特征：
	 * 节点的左子树只包含小于当前节点的数。
	 * 节点的右子树只包含大于当前节点的数。
	 * 所有左子树和右子树自身必须也是二叉搜索树。
	 * 示例 1:
	 * 输入:
	 * 2
	 * / \
	 * 1   3
	 * 输出: true
	 * 示例 2:
	 * 输入:
	 * 5
	 * / \
	 * 1   4
	 *      / \
	 *     3   6
	 * 输出: false
	 * 解释: 输入为: [5,1,4,null,null,3,6]。
	 *      根节点的值为 5 ，但是其右子节点值为 4 。
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 *
	 * @param root
	 * @return
	 */
	private static TreeNode pre;

	public static boolean isValidBST(TreeNode root) {
		if (null == root) {
			return true;
		}
		if (!isValidBST(root.left)) {
			return false;
		}
		if (null == pre) {
			pre = root;
		} else {
			if (root.val > pre.val) {
				pre = root;
			} else {
				return false;
			}
		}
		return isValidBST(root.right);
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(10);
		TreeNode node1 = new TreeNode(5);
		TreeNode node2 = new TreeNode(15);
		TreeNode node3 = new TreeNode(6);
		TreeNode node4 = new TreeNode(20);

		root.left = node1;
		root.right = node2;

		node2.left = node3;
		node2.right = node4;

		System.out.println(isValidBST(root));
	}
}
