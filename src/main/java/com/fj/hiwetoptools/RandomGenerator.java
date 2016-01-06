package com.fj.hiwetoptools;

import java.util.Random;

/**
 * 随机生成
 * @author linyu
 *
 */
public final class RandomGenerator {
	private Random random = null;

	public RandomGenerator() {
		this.random = new Random();
	}

	public RandomGenerator(Random paramRandom) {
		if (paramRandom == null)
			throw new IllegalArgumentException("random 参数异常");
		this.random = paramRandom;
	}

	public boolean nextBoolean() {
		return this.random.nextBoolean();
	}

	public int nextNumber(int paramInt1, int paramInt2) {
		if (paramInt2 < paramInt1)
			throw new IllegalArgumentException("end 参数异常");
		int i = this.random.nextInt(paramInt2 - paramInt1 + 1);
		return i + paramInt1;
	}

	public String nextNumberString(int paramInt) {
		if (paramInt < 0)
			throw new IllegalArgumentException("length 参数异常");
		StringBuilder localStringBuilder = new StringBuilder();
		for (int i = 0; i < paramInt; i++) {
			localStringBuilder.append(this.random.nextInt(10));
		}
		return localStringBuilder.toString();
	}

	public String nextLetter(int paramInt) {
		if (paramInt < 0)
			throw new IllegalArgumentException("length 参数异常");
		StringBuilder localStringBuilder = new StringBuilder();
		for (int i = 0; i < paramInt; i++) {
			int j = nextNumber(0, 1);
			switch (j) {
			case 0:
				localStringBuilder.append((char) nextNumber(65, 90));
				break;
			case 1:
				localStringBuilder.append((char) nextNumber(97, 122));
			}
		}

		return localStringBuilder.toString();
	}

	public String nextLetterToUpper(int paramInt) {
		if (paramInt < 0)
			throw new IllegalArgumentException("length 参数异常");
		StringBuilder localStringBuilder = new StringBuilder();
		for (int i = 0; i < paramInt; i++) {
			localStringBuilder.append((char) nextNumber(65, 90));
		}
		return localStringBuilder.toString();
	}

	public String nextLetterToLower(int paramInt) {
		if (paramInt < 0)
			throw new IllegalArgumentException("length 参数异常");
		StringBuilder localStringBuilder = new StringBuilder();
		for (int i = 0; i < paramInt; i++) {
			localStringBuilder.append((char) nextNumber(97, 122));
		}
		return localStringBuilder.toString();
	}

	public String nextNumberLetter(int paramInt) {
		if (paramInt < 0)
			throw new IllegalArgumentException("length 参数异常");
		StringBuilder localStringBuilder = new StringBuilder();
		for (int i = 0; i < paramInt; i++) {
			int j = nextNumber(0, 2);
			switch (j) {
			case 0:
				localStringBuilder.append(nextNumber(0, 9));
				break;
			case 1:
				localStringBuilder.append((char) nextNumber(65, 90));
				break;
			case 2:
				localStringBuilder.append((char) nextNumber(97, 122));
			}
		}

		return localStringBuilder.toString();
	}

	public String nextNumberLetterToUpper(int paramInt) {
		if (paramInt < 0)
			throw new IllegalArgumentException("length 参数异常");
		StringBuilder localStringBuilder = new StringBuilder();
		for (int i = 0; i < paramInt; i++) {
			int j = nextNumber(0, 1);
			switch (j) {
			case 0:
				localStringBuilder.append(nextNumber(0, 9));
				break;
			case 1:
				localStringBuilder.append((char) nextNumber(65, 90));
			}
		}

		return localStringBuilder.toString();
	}

	public String nextNumberLetterToLower(int paramInt) {
		if (paramInt < 0)
			throw new IllegalArgumentException("length 参数异常");
		StringBuilder localStringBuilder = new StringBuilder();
		for (int i = 0; i < paramInt; i++) {
			int j = nextNumber(0, 1);
			switch (j) {
			case 0:
				localStringBuilder.append(nextNumber(0, 9));
				break;
			case 1:
				localStringBuilder.append((char) nextNumber(97, 122));
			}
		}

		return localStringBuilder.toString();
	}
}
