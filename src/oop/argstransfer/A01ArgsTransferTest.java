package oop.argstransfer;
/**
 * ��������Ϊ�������ݸ�����:��ϰ��
 */
public class A01ArgsTransferTest {
	public static void main(String[] args) {
		Circle c = new Circle();
		PassObject2 p = new PassObject2();
		p.PtintArea(c, 5);
		p.PtintArea(new Circle(), 3);
	}
}
class Circle{
	double radius; //���ԣ��뾶
	
	public void setRadius(double r){ //���������ð뾶
		this.radius = r;
	}
	public double getRadius(){  //��������ð뾶
		return radius;
	}
	public double findArea(){  //��������Բ�������û����Σ����ǲ���֮ǰ ���õİ뾶 �����м���
		return Math.PI * radius * radius;//�еļ��㣬���Բ��á�Math.PI���ķ�ʽ�����ã���ȷ�ȸ���
	}
}
class PassObject2{
	public void PtintArea(Circle c, int time){//����һ��Բ��һ����ֵ��
		System.out.println("Radius" + "\t\t" + "Area");
		int t = 1;
		while(t <= time){
			c.setRadius(t);
			//c.findArea();������˼ά���������֮��û���κ����ã�����һ�������ʱ�������½����˵��ã�����������Բ�д��
			System.out.println(t + "\t\t" + c.findArea());
			t++;
		}
	}
}