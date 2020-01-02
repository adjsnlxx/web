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

	/**
	 * 给定一个二叉树，找出其最大深度。
	 *
	 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
	 *
	 * 说明: 叶子节点是指没有子节点的节点。
	 *
	 * 示例：
	 * 给定二叉树 [3,9,20,null,null,15,7]，
	 *
	 *     3
	 *    / \
	 *   9  20
	 *     /  \
	 *    15   7
	 * 返回它的最大深度 3 。
	 *
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 * @param root
	 * @return
	 */
	public static int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int maxLeft = maxDepth(root.left) + 1;
		int maxRight = maxDepth(root.right) + 1;

		return Math.max(maxLeft,maxRight);
	}

    public static double calcSqrt(double x) {
        double i = 0;
        double j = x;
        double mid = 0d;
        while (i < j) {
            mid = (i + j) / 2;
            if (Math.abs(mid * mid - x) < 0.000001) {
                break;
            } else if (mid * mid > x) {
                j = mid;
            } else if (mid * mid < x) {
                i = mid;
            }
        }

        String s = String.format("%.6f",mid);
        return Double.parseDouble(s);
    }

    /**
     * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
     *
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * 示例: 
     * 给定如下二叉树，以及目标和 sum = 22，
     *
     *               5
     *              / \
     *             4   8
     *            /   / \
     *           11  13  4
     *          /  \      \
     *         7    2      1
     * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/path-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
            return (sum - root.val) == 0;
        }

        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }


    public int numIslands(char[][] grid) {
        int nums = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    nums++;
                    scan(grid, i, j);
                }
            }
        }

        return nums;
    }

    public void scan(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1') {
            return ;
        }

        grid[i][j] = '2';
        scan(grid, i, j + 1);
        scan(grid, i, j - 1);
        scan(grid, i + 1, j);
        scan(grid, i - 1, j);
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

		System.out.println(maxDepth(root));
	}
}
