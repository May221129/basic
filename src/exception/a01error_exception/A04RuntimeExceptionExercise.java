package exception.a01error_exception;

/**
 * �쳣�������ϰ�⣺
 */
public class A04RuntimeExceptionExercise {
	public static void main(String[] args) {
		try {
			int i = Integer.parseInt(args[0]);//������
			int j = Integer.parseInt(args[1]);//����
			ecm(i,j);
		} catch (NumberFormatException e) {
			System.out.println("������������Ͳ�һ�£�");;
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("ȱ�������в�����");
		}catch(ArithmeticException e){
			System.out.println("��ĸΪ���ˣ�");
		}catch(EcDef e){
			System.out.println(e.getMessage());
		}
	}
//	������
	public static void ecm(int i, int j) throws EcDef{
		if(i < 0 || j < 0){
			throw new EcDef("���������ֵ���ڸ�����");
		}
		System.out.println(i / j);
	}
}
class EcDef extends Exception{
	static final long serialVersionUID = -33893124229948L;
	public EcDef(){
		
	}
	public EcDef(String message) {
        super(message);
    }
}
