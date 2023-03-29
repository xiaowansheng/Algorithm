package dijkstra;

import java.util.Arrays;

public class DijkstraAlgorithm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] vertex = { "A", "B", "C", "D", "E", "F", "G" };
		// 邻接矩阵
		int[][] matrix = new int[vertex.length][vertex.length];
		final int N = Dijkstra.MAXDISTANCE;// 表示不可以连接
		matrix[0] = new int[] { N, 5, 7, N, N, N, 2 };
		matrix[1] = new int[] { 5, N, N, 9, N, N, 3 };
		matrix[2] = new int[] { 7, N, N, N, 8, N, N };
		matrix[3] = new int[] { N, 9, N, N, N, 4, N };
		matrix[4] = new int[] { N, N, 8, N, N, 5, 4 };
		matrix[5] = new int[] { N, N, N, 4, 5, N, 6 };
		matrix[6] = new int[] { 2, 3, N, N, 4, 6, N };
		Dijkstra d = new Dijkstra(vertex, matrix);
		d.show();
		d.dijkstraAlgorithm("G");
	}

}

class Dijkstra {
	String[] vertexs;// 记录顶点数据
	int[][] matrix;// 邻接矩阵
	
	final static int MAXDISTANCE=65535;

	public Dijkstra(String[] vertex, int[][] matrix) {
		super();
		this.vertexs = vertex;
		this.matrix = matrix;
	}
	
	public void dijkstraAlgorithm(String start) {
		int sta=getIndex(start);
		//记录顶点被访问情况
		boolean[] visited=new boolean[vertexs.length];
		//visited[sta]=true;//开始节点设置为已访问
		//记录该顶点到起点的最短路径
		int[] dis=new int[vertexs.length];
		Arrays.fill(dis, MAXDISTANCE);
		dis[sta]=0;//开始路径设为0
		//记录前驱节点
		int[] pre=new int[vertexs.length];
		pre[sta]=sta;//开始节点的前驱节点设置为自己
		setDistanceAndPre(dis, pre,sta);//开始节点的邻接节点的前驱节点都设置为开始节点
		int index=0;
		for(int i=0;i<vertexs.length;i++) {
			int minIndex=getMinIndex(dis,visited);
			setDistanceAndPre(dis, pre, minIndex);
		}
		//输出结果
		int p=-1;
		StringBuilder str=new StringBuilder();
		for(int i=0;i<vertexs.length;i++) {
			str.append(vertexs[i]+"<-");
			p = i;
			while (pre[sta] != pre[p]) {
				p = pre[p];
				str.append(vertexs[p]+"<-");
			}
			str.append(vertexs[pre[p]]);
			System.out.println(start+"到"+vertexs[i]+"的最短路径为["+str+"],最短距离为："+dis[i]);
			str.delete(0, str.length());
		}
//		System.out.println(Arrays.toString(vertexs));
//		System.out.println(Arrays.toString(dis));
//		System.out.println(Arrays.toString(pre));
	}
	
	private int getMinIndex(int[] dis,boolean[] visited) {
		int minIndex=-1;
		int minDis=MAXDISTANCE;
		for(int i=0;i<vertexs.length;i++) {
			if(visited[i]==false&&minDis>dis[i]) {
				minIndex=i;
				minDis=dis[i];
			}
		}
		visited[minIndex]=true;
		return minIndex;
	}
	
	/**
	 *   设置起始点到该节点的最短路径
	 * @param dis 记录最短路径的数组
	 * @param pre 设置前驱节点
	 * @param index 遍历到的当前循环中最小路径的顶点
	 */
	private void setDistanceAndPre(int[] dis,int[] pre,int index) {
		//遍历顶点
		for(int i=0;i<vertexs.length;i++) {
			//找到与index顶点相连接的顶点i，并且i到起始点(start)的距离 < i到index+index到start的距离
			if (matrix[index][i] != MAXDISTANCE&&dis[i]>dis[index]+matrix[index][i]) {
				//更改i到起始点start的最小距离
				dis[i]=dis[index]+matrix[index][i];
				//把前驱更改为index
				pre[i]=index;
			}
		}
	}
	
	/**
	 * 返回顶点数据对应下标，没有则返回-1
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

	// 展示邻接矩阵
	public void show() { 
		for (int i = 0; i < vertexs.length; i++) {
			for (int j = 0; j < vertexs.length; j++) {
				System.out.printf("%12d",matrix[i][j]);
			}
			System.out.println();
		}
	}
}