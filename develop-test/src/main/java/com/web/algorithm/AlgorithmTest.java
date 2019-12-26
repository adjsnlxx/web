package com.web.algorithm;

public class AlgorithmTest {

	public static void main(String[] args) {
		System.out.println(new Solution().uniquePaths(3, 2));
	}

}

class Solution {

	public int uniquePaths(int m, int n) {
		int[][] grid = new int[110][110];

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (i == 1 || j == 1) {
					grid[i][j] = 1;
				} else {
					grid[i][j] = grid[i][j - 1] + grid[i - 1][j];
				}
			}
		}

		return grid[n][m];
	}
}


