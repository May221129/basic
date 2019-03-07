package thread.a07juc;

/**
 * һ��ԭ��������
 * 	1."a = i++"�ڼ�����ײ��ʵ�ʲ�����
 * 		int temp = i;
 * 		i = i + 1;
 * 		a = temp;
 * ����ԭ�ӱ�����jdk1.5��java.util.concurrent.atomic�����ṩ�˳��õ�ԭ�ӱ�����
 * 	1.ԭ�ӱ������ص㣺
 * 		����volatile���Σ���֤�ڴ�ɼ��ԣ��磺java.util.concurrent.atomic.AtomicInteger��
 * 		����CAS��Compare And Swap���㷨����֤���ݵ�ԭ���ԡ�
 * 			CAS�㷨��Ӳ�����ڲ��������������ݵ�֧�֡�
 * 			CAS�㷨�����������������ڴ�ֵ V��Ԥ��ֵ A������ֵ B������V == Aʱ��V = B�����򽫲����κβ�����
 * 			CAS�㷨��Ч��Ҫ����ͬ��������Ϊ��V ��= Aʱ����ǰ�̲߳��ᱻ����Ҳ�������CPU��ִ��Ȩ�������������ٽ����жϣ�ͨ��forѭ��ʵ�֣���
 * 		��CAS�㷨�Ĳ��Լ���thread.a07juc.A01Atomic1
 * 		���Լ�ʵ��CAS����thread.a07juc.A01MyCAS2
 */
public class A01Atomic {
	public static void main(String[] args) {
		int i = 10;
		int a = i++;
		System.out.println(i);//11
		System.out.println(a);//10
	}
}