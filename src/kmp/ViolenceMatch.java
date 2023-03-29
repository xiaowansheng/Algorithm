package kmp;

public class ViolenceMatch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("index="+violenceMatch("1121231234123456", "123"));
	}

	//暴力匹配算法
	//字符串匹配
	public static int violenceMatch(String str,String matchStr) {
		if(str.length()<matchStr.length()) {
			return -1;
		}
		char[] str1=str.toCharArray();
		char[] str2=matchStr.toCharArray();
		for(int i=0,j=0;i<str1.length;i++) {
			if(str1[i]==str2[j]) {
				j++;
				if(j==str2.length) {	
					return i-j+1;
				}
			}else {
				i=i-j;
				j=0;
			}
		}
		return -1;
	}
}
