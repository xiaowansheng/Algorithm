package primAlgorithm;

import java.util.Arrays;

public class PrimAlgorithm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] v= {"A","B","C","D","E","F","G"};
		int [][]weight=new int[][]{
            {10000,5,7,10000,10000,10000,2},
            {5,10000,10000,9,10000,10000,3},
            {7,10000,10000,10000,8,10000,10000},
            {10000,9,10000,10000,10000,4,10000},
            {10000,10000,8,10000,10000,5,4},
            {10000,10000,10000,4,5,10000,6},
            {2,3,10000,10000,4,6,10000}};
            MGraph m=MinTree.createGraph(v, weight);
            MinTree.primAlgirithm(m);
	}
}

class MinTree{
	
	//创建图的邻接矩阵
	/**
	 * 创建图及其对应邻接矩阵
	 * 不相连则用-1表示
	 * @param data 图的各个顶点的值
	 * @param weight 图的邻接矩阵
	 */
	public static MGraph createGraph(String[] data, int[][] weight) {
		MGraph graph=new MGraph(data.length);
		for(int i = 0; i < graph.number; i++) {//顶点
			graph.vertexs[i] = data[i];
			for(int j = 0; j < graph.number; j++) {
				graph.weight[i][j] = weight[i][j];
			}
		}
		return graph;
	}
	
	/**
	 * 普利姆算法
	 * 求最小生成树
	 * 即连接所有顶点的最短路径
	 * @param m 存储图的二维数组
	 */
	public static void primAlgirithm(MGraph m) {
		int[][] side=m.weight;//引用图的结构
		int number=m.number;//城市数量
		String[] vertexs=m.vertexs;
		int[] select=new int[number];//记录已经选择的城市(路线)
		//没有选择的城市置为-1，选择了就顺序继续城市下标
//		for(int i=0;i<number;i++) {
//			select[i]=-1;
//		}
		//String v=m.vertexs[0];//先选择第一个城市
		int count=1;//选择的城市+1,记录选择的城市数量
		select[0]=1;//从下标0开始记录选择的城市，对应得值是已经选择得城市的下标(vertex数组中对应数据)，从第一个城市开始
		int minX=0;//记录最小路径的两个城市对应index
		int minY=0;
		//要选择完所有城市，并使路径；连接最短
		for(int i=1;i<number;i++) {
			//选择下一个路径前，重置上一次最短路径
			minX=0;
			minY=0;
			//从已经选择的城市中遍历没有选择的路径，选出最短
			for(int j=0;j<number;j++) {
				//若当前顶点没有被选择过，则进行下一轮循环
				if(select[j]==0) {
					continue;
				}
				//选择当前城市的最短路径
				for(int k=0;k<number;k++) {
					//要k点对应城市顶点要没有被选择过，要有>0的路线(权重),要比当前最小的权重要小(路线最短)
					if(select[k]==0&&side[j][k]>0&&side[j][k]<side[minX][minY]) {
						minX=j;
						minY=k;
					}
				}
			}
			//输出从某个顶点到另一个顶点的当前最短距离
			System.out.println("从"+vertexs[minX]+"到"+vertexs[minY]+":"+side[minX][minY]);
			//选择的城市标记为1
			//select[count]=minY;
			select[minY]=1;
			//选择的城市数+1
			count++;
		}
	}
}

class MGraph{
	int number;//顶点个数
	String[] vertexs;//顶点数据
	int[][] weight;//权重
	/**
	 * 
	 * @param vertexs 顶点数据
	 */
	public MGraph(int vertexNumber) {
		super();
		this.vertexs = new String[vertexNumber];
		this.number=vertexNumber;
		this.weight=new int[number][number];
	}
}