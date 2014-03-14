package com.example.cube2048;

import java.util.Random;

public class Arithmetic {

	public static final int DIR_UP = 0;
	public static final int DIR_DOWN = 1;
	public static final int DIR_LEFT = 2;
	public static final int DIR_RIGHT = 3;

	public static void init(int[][] cubeNum) {
		for (int m = 0; m < 4; m++) {
			for (int n = 0; n < 4; n++) {
				cubeNum[m][n] = -1;
			}
		}
		randomGen(cubeNum);
		randomGen(cubeNum);
	}

	public static boolean isWin(int[][] cubeNum) {
		for (int m = 0; m < 4; m++) {
			for (int n = 0; n < 4; n++) {
				if (cubeNum[m][n] == 2048) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean next(int[][] cubeNum, int dir) {
		step(cubeNum, dir);
		// isOver(cubeNum)
		return isOver(cubeNum);
	}

	private static boolean step(int[][] cubeNum, int dir) {
		boolean isMeger = false;
		boolean isMove = false;
		if (dir == DIR_UP) {
			isMove = isMove || moveUp(cubeNum);
			isMeger = mergeUp(cubeNum);
			isMove = isMove || moveUp(cubeNum);
		}
		if (dir == DIR_LEFT) {
			isMove = isMove || moveLeft(cubeNum);
			isMeger = mergeLeft(cubeNum);
			moveLeft(cubeNum);
		}
		if (dir == DIR_DOWN) {
			isMove = isMove || moveDown(cubeNum);
			isMeger = mergeDown(cubeNum);
			moveDown(cubeNum);
		}
		if (dir == DIR_RIGHT) {
			isMove = isMove || moveRight(cubeNum);
			isMeger = mergeRight(cubeNum);
			moveRight(cubeNum);
		}
		boolean isChange = false;
		if (isMeger || isMove) {
			randomGen(cubeNum);
			isChange = true;
		}
		return isChange;
	}

	public static boolean isOver(int[][] cubeNum) {
		int[][] my = new int[4][4];
		for (int i = 0; i < 4; i++) {
			System.arraycopy(cubeNum[i], 0, my[i], 0, 4);
		}
		boolean isChanged = false;
		isChanged = isChanged || step(my, DIR_DOWN);
		isChanged = isChanged || step(my, DIR_LEFT);
		isChanged = isChanged || step(my, DIR_RIGHT);
		isChanged = isChanged || step(my, DIR_UP);
		return !isChanged;
	}

	private static int[][] randomGen(int[][] cubeNum) {
		Random random = new Random();
		boolean hasGen = false;
		while (!hasGen) {
			int first = random.nextInt(4);
			int next = random.nextInt(4);
			if (cubeNum[first][next] < 0) {
				hasGen = true;
				int genFour = random.nextInt(5000);
				if (genFour % 6 == 0) {
					cubeNum[first][next] = 4;
				} else {
					cubeNum[first][next] = 2;
				}
			}
		}
		return cubeNum;
	}

	private static boolean mergeDown(int[][] cubeNum) {
		boolean isMerge = false;
		for (int m = 0; m < 4; m++) {
			for (int n = 3; n > 0; n--) {
				int first = cubeNum[n][m];
				int next = cubeNum[n - 1][m];
				if (first == next && !(first < 0)) {
					isMerge = true;
					cubeNum[n][m] *= 2;
					cubeNum[n - 1][m] = -1;
					n--;
				}
			}
		}
		return isMerge;
	}

	private static boolean mergeRight(int[][] cubeNum) {
		boolean isMerge = false;
		for (int m = 0; m < 4; m++) {
			for (int n = 3; n > 0; n--) {
				int first = cubeNum[m][n];
				int next = cubeNum[m][n - 1];
				if (first == next && !(first < 0)) {
					isMerge = true;
					cubeNum[m][n] *= 2;
					cubeNum[m][n - 1] = -1;
					n--;
				}
			}
		}
		return isMerge;
	}

	private static boolean mergeLeft(int[][] cubeNum) {
		boolean isMerge = false;
		for (int m = 0; m < 4; m++) {
			for (int n = 0; n < 3; n++) {
				int first = cubeNum[m][n];
				int next = cubeNum[m][n + 1];
				if (first == next && !(first < 0)) {
					isMerge = true;
					cubeNum[m][n] *= 2;
					cubeNum[m][n + 1] = -1;
					n++;
				}
			}
		}
		return isMerge;
	}

	private static boolean mergeUp(int[][] cubeNum) {
		boolean isMerge = false;
		for (int m = 0; m < 4; m++) {
			for (int n = 0; n < 3; n++) {
				int first = cubeNum[n][m];
				int next = cubeNum[n + 1][m];
				if (first == next && !(first < 0)) {
					isMerge = true;
					cubeNum[n][m] *= 2;
					cubeNum[n + 1][m] = -1;
					n++;
				}
			}
		}
		return isMerge;
	}

	private static boolean moveLeft(int[][] cubeNum) {
		boolean isMove = false;
		for (int m = 0; m < 4; m++) {
			for (int n = 1; n < 4; n++) {
				int value = cubeNum[m][n];
				if (value < 0) {
					continue;
				}
				for (int j = n; j >= 1; j--) {
					int pre = cubeNum[m][j - 1];
					if (pre < 0) {
						isMove = true;
						cubeNum[m][j - 1] = value;
						cubeNum[m][j] = -1;
					} else {
						break;
					}
				}
			}
		}
		return isMove;
	}

	private static boolean moveRight(int[][] cubeNum) {
		boolean isMove = false;
		for (int m = 0; m < 4; m++) {
			for (int n = 2; n >= 0; n--) {
				int value = cubeNum[m][n];
				if (value < 0) {
					continue;
				}
				for (int j = n; j <= 2; j++) {
					int pre = cubeNum[m][j + 1];
					if (pre < 0) {
						isMove = true;
						cubeNum[m][j + 1] = value;
						cubeNum[m][j] = -1;
					} else {
						break;
					}
				}
			}
		}
		return isMove;
	}

	private static boolean moveDown(int[][] cubeNum) {
		boolean isMove = false;
		for (int m = 0; m < 4; m++) {
			for (int n = 2; n >= 0; n--) {
				int value = cubeNum[n][m];
				if (value < 0) {
					continue;
				}
				for (int j = n; j <= 2; j++) {
					int pre = cubeNum[j + 1][m];
					if (pre < 0) {
						isMove = true;
						cubeNum[j + 1][m] = value;
						cubeNum[j][m] = -1;
					} else {
						break;
					}
				}
			}
		}
		return isMove;
	}

	private static boolean moveUp(int[][] cubeNum) {
		boolean isMove = false;
		for (int m = 0; m < 4; m++) {
			for (int n = 1; n < 4; n++) {
				int value = cubeNum[n][m];
				if (value < 0) {
					continue;
				}
				for (int j = n; j >= 1; j--) {
					int pre = cubeNum[j - 1][m];
					if (pre < 0) {
						isMove = true;
						cubeNum[j - 1][m] = value;
						cubeNum[j][m] = -1;
					} else {
						break;
					}
				}
			}
		}
		return isMove;
	}

}
