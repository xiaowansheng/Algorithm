package knightTour;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;

public class KnightTourProblem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] chessboard=KnightTour.traversalChessboard(8, 8, 1, 1);
	}

}

//骑士周游问题、马踏棋盘问题
//回溯算法
class KnightTour{
	private static int X;//列数
	private static int Y;//行数
	private static boolean[][] visited;//记录是否被访问
	private static boolean finished;//标记是否完成

	/**
	 * 遍历马踏棋盘
	 * @param high 棋盘高度
	 * @param width 棋盘宽度
	 * @param x 右上角为原点，向左的X轴坐标
	 * @param y 右上角为原点，向下的Y轴坐标
	 * @return
	 */
	public static int[][] traversalChessboard(int high,int width,int x,int y){
		X=high;
		Y=width;
		//根据长和宽，创建棋盘
		int[][] chessboard=new int[X][Y];
		//创建访问数组
		visited=new boolean[X][Y];
		//开始的完成标记为false
		finished=false;
		//进行遍历
		traversalChessboard(chessboard, y-1, x-1, 0);
		//成功输出遍历路线
		if(finished) {
			System.out.println("遍历成功。");
			for(int i=0;i<X;i++) {
				for(int j=0;j<Y;j++) {
					System.out.print(chessboard[i][j]+"\t");
				}
				System.out.println();
			}
		}else {
			//失败则提示
			System.out.println("遍历失败。");
		}
		//重置变量
		X=0;
		Y=0;
		visited=null;
		finished=false;
		//返回路线图，失败则数据都是0
		return chessboard;
	}
	
	private static void traversalChessboard(int[][] chessboard,int x,int y,int stamp){
		//遍历数+1
		stamp+=1;
		//遍历位置记录数
		chessboard[x][y]=stamp;
		//System.out.println(stamp);
		//标记访问的位置
		visited[x][y]=true;//标记已访问
		//返回该坐标可能访问到的其它坐标集合
		ArrayList<Point> arr=next(new Point(x,y));
		//贪心算法
		//该坐标位置可能访问到的所有坐标，让能访问到的所有坐标的可能访问坐标的数量，从小到大排序
		sort(arr);
		//循环遍历，并产生回溯
		while(!arr.isEmpty()) {
			Point p=arr.remove(0);
			if (!visited[p.x][p.y]) {
				traversalChessboard(chessboard, p.x, p.y, stamp);
			}
		}
		//根据访问数量，标记是否遍历完成
		//且也是算法回溯时执行
		if(!finished&&stamp<X*Y) {
			visited[x][y]=false;
			chessboard[x][y]=0;
		}else {
			finished=true;
		}
	}
	
	private static void sort(ArrayList<Point> arr) {
		//数组中的坐标的可访问坐标的数量进行从小到大排序
		arr.sort(new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {
				// TODO Auto-generated method stub
				if(next(o1).size()>next(o2).size()) {
					return 1;
				}else if(next(o1).size()==next(o2).size()) {
					return 0;
				}else {
					return -1;
				}
			}
		});
	}
	
	private static ArrayList<Point> next(Point p){
		ArrayList<Point> arr=new ArrayList<Point>();
		Point newP=new Point();
		if((newP.x = p.x - 1) >= 0 && (newP.y = p.y -2) >= 0) {
			arr.add(new Point(newP));
		}
		if( (newP.x=p.x-2)>=0&& (newP.y = p.y - 1) >=0) {
			arr.add(new Point(newP));
		}
		if ((newP.x = p.x - 2) >=0 && (newP.y = p.y + 1) < Y) {
			arr.add(new Point(newP));
		}
		if ((newP.y = p.y + 2) < Y && (newP.x = p.x - 1) >= 0) {
			arr.add(new Point(newP));
		}
		if ((newP.y = p.y + 2) < Y && (newP.x = p.x + 1) < X) {
			arr.add(new Point(newP));
		}
		if ((newP.y = p.y + 1) < Y && (newP.x = p.x + 2) < X) {
			arr.add(new Point(newP));
		}
		if ((newP.y = p.y - 1) >= 0 && (newP.x = p.x + 2) < X) {
			arr.add(new Point(newP));
		}
		if ((newP.y = p.y - 2) >= 0 && (newP.x = p.x + 1) < X) {
			arr.add(new Point(newP));
		}
		return arr;
	}
}