package dynamicProgram;

import java.util.Arrays;

//动态规划算法
public class DynamicProgramming {

	public static void main(String[] args) {
		String[] goods= {"吉他","音响","电脑"};
		int[] weight= {1,4,3};
		int[] values= {1500,3000,2000};	
		Dynamic.bagProblem(goods, weight, values, 4);
	}

}

//背包问题
class Dynamic{
//	private static String[] goods;//记录物品
//	private static int[] weight;//记录物品重量
//	private static int[] values;//记录物品价值
//	private static int[][] programme;//动态规划的数组
//	private static int[][] 
	
//	public int bagProblem(int[] weight,int[] values) {
//		
//	}	
	
	public static void bagProblem(String[] goods,int[] weight,int[] values,int bag) {
		//初始化数据
		int[][] programmeValues=new int[goods.length+1][bag+1];
		int[][] programmeGoods=new int[goods.length+1][bag+1];
		int w=0;//当前商品重量
		int v=0;//当前商品价值
		int maxV=0;
		//i:背包容纳的重量
		//j:商品集合，从上往下到j点的所包含商品
		//programme数组中，第一行，第一列都是0
		for(int i=1;i<programmeValues[0].length;i++) {
			for(int j=1;j<programmeValues.length;j++) {
				w=weight[j-1];
				v=values[j-1];
				//当前商品质量大于背包容纳
				if(w>i) {
					programmeValues[j][i]=programmeValues[j-1][i];
				}else {//当前商品质量小于等于背包容纳
					maxV=v+programmeValues[j-1][i-w];
					//该位置最大价值是否大于上一个格子
					if(programmeValues[j-1][i]<=maxV) {
						programmeValues[j][i]=maxV;
						programmeGoods[j][i]=1;
					}else {
						programmeValues[j][i]=programmeValues[j][i-1];
					}
				}
			}
		}
		//输出规划价格
		for(int i=0;i<programmeValues.length;i++) {
			for(int j=0;j<programmeValues[0].length;j++) {
				System.out.print(programmeValues[i][j]+"\t");
			}
			System.out.println();
		}
		System.out.println("背包中放入物品价值最大为："+programmeValues[programmeValues.length-1][programmeValues[0].length-1]);
		//输出规划物品
		System.out.println("背包中放入的商品为：");
		int i = programmeGoods.length - 1; //行的最大下标
		int j = programmeGoods[0].length - 1;  //列的最大下标
		while(i > 0 && j > 0 ) { //从programmeGoods的最后开始找
			if(programmeGoods[i][j] == 1) {
				System.out.printf("第%d个商品放入到背包\n", i); 
				j -= weight[i-1];
			}
			i--;
		}
	}

}