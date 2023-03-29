package hanoiTowerProblem;

//分治算法
//汉诺塔问题
public class HanoiTowerProblem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		hanoiTower(3);
	}
	
	/**
	 * 汉诺塔问题求解：第一个底座有number个盘子，盘子从下往上是从大到大堆放，要把盘子全部移动到第三个底座，移动时也要保持盘子大的在下面，小的在上面
	 * 求解出移动步骤
	 * @param number 汉诺塔层数
	 * @param a 第一个底座
	 * @param b 第二个底座
	 * @param c 第三个底座
	 */
	private static void hanoiTower(int number,char a,char b,char c){
		if(number==1) {
			//递归出口，当只有一个盘子时，从A直接移动到C
			System.out.println("第"+number+"个盘，从"+a+"——>"+c);
		}else if(number>1) {
			//将A底座的上面number-1个盘，通过C，移动到B底座
			hanoiTower(number-1, a, c, b);
			//接着将A底座的第number个盘，从A直接移动到C
			System.out.println("第"+number+"个盘，从"+a+"——>"+c);//做完这，number-1个盘放在了B底座
			//最后又将B底座的number-1个盘，通过A，移动到B
			hanoiTower(number-1, b, a, c);
		}
	}
	
	public static void hanoiTower(int number) {
		hanoiTower(number, 'A', 'B', 'C');
	}

}
