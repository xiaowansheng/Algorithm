package kmp;

import java.util.Arrays;

public class KMPAlgorithm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a="ABCBCABCAC";
		//System.out.println(Arrays.toString(next(c.toCharArray())));
		String c="AC";
		System.out.println("index="+kmpMatch(a, c));
	}
	
	//KMP算法匹配
	/**
	 * KMP算法匹配字符串
	 * @param string 原字符串
	 * @param pattern 匹配字符串
	 * @return 返回匹配的下标，没有匹配则返回-1
	 */
	public static int kmpMatch(String string,String pattern) {
		char[] str1=string.toCharArray();
		char[] pat=pattern.toCharArray();
		if(str1.length<pat.length) {
			return -1;
		}
		int[] m=next(pat);
		for(int i=0,j=0;i<str1.length;i++) {
			while(j>0&&str1[i]!=pat[j]) {
				//若没有匹配，则回溯，直到相等或者j=0
				j=m[j-1];
			}
			if(str1[i]==pat[j]) {
				//相等则原字符串和匹配字符串后移
				j++;
			}
			if(j==pat.length) {
				//完全匹配则返回目标下标值
				return i-j+1;
			}
		}
		return -1;
	}
	
	/**
	 * 获取匹配值表
	 * @param matchStr 传入用于匹配的字符串
	 * @return 返回匹配值表
	 */
	private static int[] next(char[] matchStr) {
		int[] b=new int[matchStr.length];
		for(int i=1,j=0;i<b.length;i++) {
			while(j>0&&matchStr[i]!=matchStr[j]) {
				j=b[j-1];
			}
			if(matchStr[i]==matchStr[j]) {
				j++;
				b[i]=j;
			}
		}
		return b;
	}

}
