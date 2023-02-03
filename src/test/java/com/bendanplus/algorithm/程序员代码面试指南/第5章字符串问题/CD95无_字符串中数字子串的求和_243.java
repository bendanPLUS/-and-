package com.bendanplus.algorithm.程序员代码面试指南.第5章字符串问题;

public class CD95无_字符串中数字子串的求和_243 {


	public int numSum(String str) {
		char[] charArr = str.toCharArray();
		int res = 0;
		int num = 0;
		int cur = 0;
		boolean posi = true;
		for (int i = 0; i < charArr.length; i++) {
			cur = charArr[i] - '0';
			if (cur < 0 || cur > 9) {
				res += num;
				num = 0;
				if (charArr[i] == '-') {
					if (i > 0 && charArr[i - 1] == '-') posi = !posi;
					else posi = false;
				} else posi = true;
			} else num = num * 10 + (posi ? cur : -cur);
		}
		res += num;
		return res;
	}

	//测试
	public static void main(String[] args) {
		CD95无_字符串中数字子串的求和_243 chapter = new CD95无_字符串中数字子串的求和_243();
		String str = "A--1CD---2EE33";
		System.out.println("A--1CD---2EE33中数字之和：" + chapter.numSum(str));
	}
}


