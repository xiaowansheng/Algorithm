package kruskalAlgorithm;

import java.util.Arrays;

public class kruskalAlgorithm {

	public static void main(String[] args) {
		int INF=Kruskal.MAXLength;
		// TODO Auto-generated method stub
		String[] vertexs = {"A", "B", "C", "D", "E", "F", "G"};
		//克鲁斯卡尔算法的邻接矩阵  
	      int matrix[][] = {
	      /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
	/*A*/ {   0,  12, INF, INF, INF,  16,  14},
	/*B*/ {  12,   0,  10, INF, INF,   7, INF},
	/*C*/ { INF,  10,   0,   3,   5,   6, INF},
	/*D*/ { INF, INF,   3,   0,   4, INF, INF},
	/*E*/ { INF, INF,   5,   4,   0,   2,   8},
	/*F*/ {  16,   7,   6, INF,   2,   0,   9},
	/*G*/ {  14, INF, INF, INF,   8,   9,   0}}; 
	      Kruskal k=new Kruskal(vertexs, matrix);
	      k.kruskalMinTree();
	      //k.show();
	}

}

class Kruskal{
	int sideNumber;//边的数量
	Side[] sides;
	String[] vertexs;//记录顶点数据
	int[][] graph;//图的邻接矩阵，保存的值是边长即权重
	final static int MAXLength=Integer.MAX_VALUE;
	
	public Kruskal(String[] vertexs, int[][] graph) {
		super();
		this.vertexs = new String[vertexs.length];
		for(int i=0;i<vertexs.length;i++) {
			this.vertexs[i]=vertexs[i];
		}
		this.graph = new int[graph.length][graph.length];
		for(int i=0;i<vertexs.length;i++) {
			for(int j=0;j<vertexs.length;j++) {
				this.graph[i][j]=graph[i][j];
			}
		}
		sides=getSides(graph);
		//根据边长从小到大排序
		Arrays.sort(sides);
	}
	
	/**
	 * 克鲁斯卡尔算法计算出最小生成树
	 */
	public void kruskalMinTree() {
		//记录已经选择的边
		Side[] select=new Side[sideNumber];
		//记录顶点的的根节点
		//为0则自身是根节点
		int[] ends=new int[sideNumber];//记录顶点的末尾顶点
		//System.out.println(Arrays.toString(sides));
		Side s=null;
		int index=0;
		for(int i=0;i<sides.length;i++) {
			s=sides[i];
			//查找顶点对应下标
			int m=getIndex(s.start);
			int n=getIndex(s.end);
			//根据顶点下标查找根节点
			int j=getEnd(ends, m);
			int k=getEnd(ends, n);
			//根节点不相同则可以连接两个顶点
			//即让一个子树的根节点等于另一个子树根节点，使所有连接的点根节点一致
			//若根节点相等，则连接会形成回路，所有跳过该边，进行下一条边的判断
			if(j!=k) {
				ends[j]=k;
				select[index++]=s;
			}
		}
		for(int i=0;i<index;i++) {
			System.out.println(select[i]+"\n");
		}
	}
	
	/**
	 * 返回i节点的根节点
	 * @param ends 记录顶点的根节点的数组
	 * @param i 需要查找根节点的顶点
	 * @return 返回i的根节点
	 */
	public int getEnd(int[] ends,int i) {
		//循环查找顶点的根节点
		//若不存在则自身为根节点
		while(ends[i]!=0) {
			i=ends[i];
		}
		return i;
	}
	
	/**
	 * 获取边的集合
	 * @param graph 邻接矩阵
	 * @return 返回给属性sides
	 */
	private Side[] getSides(int[][] graph) {
		int index=0;
		//计算边数
		for(int i=0;i<vertexs.length;i++) {
			for(int j=i+1;j<vertexs.length;j++) {
				if(graph[i][j]!=MAXLength) {
					index++;
				}
			}
		}
		Side[] sides=new Side[index];
		index=0;
		//边存入数组
		for(int i=0;i<vertexs.length;i++) {
			for(int j=i+1;j<vertexs.length;j++) {
				if(graph[i][j]!=MAXLength) {
					sides[index++]=new Side(vertexs[i], vertexs[j],graph[i][j] );
				}
			}
		}
		sideNumber=index;
		return sides;
	}
	
	/**
	 * 返回顶点对应下标
	 * @param vertex 顶点数据
	 * @return 返回下标
	 */
	public int getIndex(String vertex) {
		for(int i=0;i<vertexs.length;i++) {
			if(vertexs[i].equals(vertex)) {
				return i;
			}
		}
		return -1;
	}
	
	//展示邻接矩阵
	public void show() {
		for(int i=0;i<vertexs.length;i++) {
			for(int j=0;j<vertexs.length;j++) {
				System.out.printf("%12d",graph[i][j]);
			}
			System.out.println();
		}
	}
}

class Side implements Comparable<Side>{
	String start;//开始顶点
	String end;//结束顶点
	int weight;//边长
	public Side(String start, String end, int weight) {
		super();
		this.start = start;
		this.end = end;
		this.weight = weight;
	}
	@Override
	public String toString() {
		return "[" + start + "->" + end + ", 距离:" + weight + "]";
	}
	@Override
	public int compareTo(Side o) {
		// TODO Auto-generated method stub
		return weight-o.weight;
	}
	
}