package array.helloworld;
/**
 * ���飺���
 */
public class GetSun {
	public static void main(String[] args){
		int[][] m = new int[][]{{3,8,2},{2,7},{9,0,1,6}};
		int sum = 0;
		for(int i = 0; i < m.length; i++){
			for(int j = 0; j < m[i].length; j++){
				sum += m[i][j];
			}
		}
		System.out.println("�ö�ά������ܺ�Ϊ" + sum);
	}
}
