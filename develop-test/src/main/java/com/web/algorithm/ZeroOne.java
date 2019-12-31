package com.web.algorithm;

public class ZeroOne {
	static int[] price;//物品的价值数组
	static int[] weight;//物品的重量数组
	static int maxWeight;//最大可以拿的重量
	static int count;//物品的个数

	static int currentWeight;//当前的重量
	static int currentPrice;//当前的价值
	static int bestPrice;//目前最优装载的价值
	static int remainPrice;//剩余物品的价值

	static int[] currentX;//存放当前解
	static int[] bestX;//存放最终解

	public static void main(String[] args) {
		int[][] data = new int[4][4];
		data[0] = new int[] { 1, 3, 5, 9 };
		data[1] = new int[] { 2, 1, 3, 4 };
		data[2] = new int[] { 5, 2, 6, 7 };
		data[3] = new int[] { 6, 8, 4, 3 };
		int result = minDistDP(data, 4);
		System.out.println(result);

		//测试
		//		int[] weight1 = { 0, 15, 25, 40, 20, 15, 24 };
		//		int[] price1 = { 0, 10, 5, 20, 2, 14, 23 };
		//		int maxWeight1 = 30;
		//
		//		int[][] result = DP_01bag(maxWeight1, weight1.length, weight1, price1);
		//		for (int i = 0; i < result.length; i++) {
		//			for (int j = 0; j < result[0].length; j++) {
		//				System.out.print(" " + result[i][j]);
		//			}
		//
		//			System.out.println();
		//		}

		//		Loading(weight1, price1, maxWeight1);
		//		System.out.println("最优装载为：" + bestPrice);
		//		for (int i = 1; i <= count; i++) {
		//			System.out.print(bestX[i] + " ");
		//		}
	}

	public static int Loading(int[] weight1, int[] price1, int maxWeight1) {
		//初始化数据成员，数组下标从1开始
		count = weight1.length - 1;
		weight = weight1;
		price = price1;
		maxWeight = maxWeight1;
		currentWeight = 0;
		bestPrice = 0;
		currentX = new int[count + 1];
		bestX = new int[count + 1];

		//初始化r，即剩余最大价格
		for (int i = 1; i <= count; i++) {
			remainPrice += price[i];
		}

		//调用回溯法计算
		BackTrack(1);
		return bestPrice;
	}

	/**
	 * 回溯
	 *
	 * @param t
	 */
	public static void BackTrack(int t) {
		if (t > count) {//到达叶结点
			if (currentPrice > bestPrice) {
				for (int i = 1; i <= count; i++) {
					bestX[i] = currentX[i];
				}

				bestPrice = currentPrice;
			}
			return;
		}

		remainPrice -= price[t];
		if (currentWeight + weight[t] <= maxWeight) {//搜索左子树
			currentX[t] = 1;
			currentPrice += price[t];
			currentWeight += weight[t];
			BackTrack(t + 1);
			currentPrice -= price[t];//恢复现场
			currentWeight -= weight[t];//恢复现场

		}

		if (currentPrice + remainPrice > bestPrice) {//剪枝操作
			currentX[t] = 0;//搜索右子树
			BackTrack(t + 1);
		}
		remainPrice += price[t];//恢复现场
	}

	/**
	 * @param m : 表示背包的最大容量
	 * @param n : 表示商品的个数
	 * @param w : 表示商品重量数组
	 * @param p : 表示商品价值数组
	 */
	public static int[][] DP_01bag(int m, int n, int w[], int p[]) {
		//c[i][m] 表示前i件物品恰好放入重量为m的背包时的最大价值
		int c[][] = new int[n + 1][m + 1];

		for (int i = 0; i < n + 1; i++) {
			c[i][0] = 0;
		}
		for (int j = 0; j < m + 1; j++) {
			c[0][j] = 0;
		}
		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < m + 1; j++) {
				//当物品为i件重量为j时，如果第i件的重量(w[i-1])小于重量j时，c[i][j]为下列两种情况之一：
				//(1)物品i不放入背包中，所以c[i][j]为c[i-1][j]的值
				//(2)物品i放入背包中，则背包剩余重量为j-w[i-1],所以c[i][j]为c[i-1][j-w[i-1]]的值加上当前物品i的价值
				if (w[i - 1] <= j) {
					if (c[i - 1][j] < c[i - 1][j - w[i - 1]] + p[i - 1]) {
						c[i][j] = c[i - 1][j - w[i - 1]] + p[i - 1];
					} else {
						c[i][j] = c[i - 1][j];
					}

				} else {
					c[i][j] = c[i - 1][j];
				}
			}
		}
		return c;

	}

	public static int minDistDP(int[][] matrix, int n) {
		int[][] states = new int[n][n];
		int sum = 0;
		for (int j = 0; j < n; ++j) { // 初始化states的第一行数据
			sum += matrix[0][j];
			states[0][j] = sum;
		}
		sum = 0;
		for (int i = 0; i < n; ++i) { // 初始化states的第一列数据
			sum += matrix[i][0];
			states[i][0] = sum;
		}
		for (int i = 1; i < n; ++i) {
			for (int j = 1; j < n; ++j) {
				states[i][j] = matrix[i][j] + Math.min(states[i][j - 1], states[i - 1][j]);
			}
		}
		return states[n - 1][n - 1];
	}

}

