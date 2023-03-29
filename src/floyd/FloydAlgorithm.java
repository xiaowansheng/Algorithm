package floyd;

import java.util.Arrays;

public class FloydAlgorithm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] vertexs= {"A","B","C","D","E","F","G"};
		//创建邻接矩阵
		int[][] matrix = new int[vertexs.length][vertexs.length];
		final int N = Floyd.MAXDISTANCE;
		matrix[0] = new int[] { 0, 5, 7, N, N, N, 2 };
		matrix[1] = new int[] { 5, 0, N, 9, N, N, 3 };
		matrix[2] = new int[] { 7, N, 0, N, 8, N, N };
		matrix[3] = new int[] { N, 9, N, 0, N, 4, N };
		matrix[4] = new int[] { N, N, 8, N, 0, 5, 4 };
		matrix[5] = new int[] { N, N, N, 4, 5, 0, 6 };
		matrix[6] = new int[] { 2, 3, N, N, 4, 6, 0 };
		Floyd f=new Floyd(vertexs, matrix);
		//f.show();
		f.floydAlgorithm();
	}

}

class Floyd{
	String[] vertexs;//顶点数据集合
	int[][] matrix;//邻接矩阵
	final static int MAXDISTANCE=65535;
	public Floyd(String[] vertexs, int[][] matrix) {
		super();
		this.vertexs = vertexs;
		this.matrix = matrix;
	}
	
	public void floydAlgorithm(){
		//顶点数量
		int len=vertexs.length;
		//记录最短距离
		int[][] dis=new int[len][len];
		//记录最短路径
		String[][] pre=new String[len][len];
		//初始化路径
		for(int i=0;i<len;i++) {
			Arrays.fill(pre[i],vertexs[i]);
		}
		//初始化最短距离数组
		for(int i=0;i<len;i++) {
			for(int j=0;j<len;j++) {
				dis[i][j]=matrix[i][j];
			}
		}
		//三层循环结束即可得出每两个顶点之间的最短距离即路径
		//记录当前距离
		int distance=0;
		//i：每次作为中间节点的顶点
		for(int i=0;i<len;i++) {
			//j:开始节点
			for(int j=0;j<len;j++) {
				//k：终点
				for(int k=0;k<len;k++) {
					//即：j->k的最短距离=j->i的最短距离+i->k的最短距离
					//三层for循环，经过所有可能的尝试，找出两点之间的最短距离和路径
					distance=dis[j][i]+dis[i][k];
					//判断是否比当前最短的距离短
					if(distance<dis[j][k]) {
						//短，则更换最小值
						dis[j][k]=distance;
						//更换该最小值对应的路径
						pre[j][k]=pre[j][i]+pre[i][k];
					}
				}
			}
		}
		//输出结果
		showMinPath(pre, dis);
	}
	
	/**
	 * 输出结果
	 * @param pre 记录路径数组
	 * @param dis 记录最短距离数组
	 */
	private void showMinPath(String[][] pre,int[][] dis) {
		int len=vertexs.length;
		StringBuilder path=new StringBuilder();
		String str=null;
		for(int i=0;i<len;i++) {
			for(int j=0;j<len;j++) {
				str=vertexs[i]+pre[i][j]+vertexs[j];
				for(int k=0;k<str.length();k++) {
					String c=str.substring(k, k+1);
					if(path.indexOf(c)==-1) {
						path.append(c+"->");
					}
				}
				path.delete(path.length()-2,path.length());
				System.out.println("从"+vertexs[i]+"到"+vertexs[j]+",最短路径：["+path+"],最短距离："+dis[i][j]);
				path.delete(0, path.length());
			}
		}
	}
	
	public void show() {
		for(int i=0;i<vertexs.length;i++) {
			for(int j=0;j<vertexs.length;j++) {
				System.out.printf("%12d",matrix[i][j]);
			}
			System.out.println();
		}
	}
}