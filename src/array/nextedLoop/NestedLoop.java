package array.nextedLoop;
// ѭ��Ƕ����ϰ���žų˷���
public class NestedLoop {
	public static void main(String[] args){
		for(int j = 1; j <= 9; j++){
			for(int i = 1; i <= j; i++){
				System.out.print(i + "*" + j + "=" + i*j + "\t");
		}
			System.out.println();
		}
		
		System.out.println("----------------------------------------------------------------------------");
		
/*forѭ����Ƕ����ϰ�������
*
**
***
****
*/
		for(int j = 0; j < 4; j++){
			for(int i = 0; i <= j; i++){
				System.out.print("*");
			}
			System.out.println();
		}
		
		System.out.println("----------------------------------------------------------------------------");
		
/*forѭ����Ƕ����ϰ�������
****
***
**
*
*/
		for(int j = 4; j > 0; j--){
			for(int i = 0; i < j; i++){
				System.out.print("*");
			}
			System.out.println();
		}
		
		System.out.println("----------------------------------------------------------------------------");
		
/*forѭ����Ƕ����ϰ�������
    *
   * *
  * * *
 * * * *
* * * * *
 * * * *
  * * *
   * *
    * 
*/
		for(int i = 0; i < 5;i++){//�ϰ벿��
			for(int j = 0; j < 4-i; j++){
				System.out.print(" ");
			}
			
			for(int k = 0; k <= i; k++){
				System.out.print("* ");
			}
			System.out.println(); 
		}
		for(int i = 0; i < 4;i++){//�°벿��
			for(int j = 0; j <= i; j++){
				System.out.print(" ");
			}
			for(int k = 0; k < 4-i; k++){
				System.out.print("* ");
			}
			System.out.println();
		}
		
		System.out.println("----------------------------------------------------------------------------");
		
		for(int i = 1; i < 10; i++){
			for(int j = 1; j < i + 1; j++){
				System.out.print(j + "*" + i + "=" + j*i + "\t");
			}
			System.out.println();
		}
	}	
}
