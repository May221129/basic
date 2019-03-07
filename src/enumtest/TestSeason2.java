package enumtest;
//enumö���ࣺ�Լ���Ϊ��
public class TestSeason2{
	public static void main(String[] args) {
		Season2 Fall = Season2.FALL;
		System.out.println(Fall);
		System.out.println(Fall.getSeasonName());
		System.out.println(Fall.getSeasonDesc());
		System.out.println(Fall.toString());
		Fall.show();
		
		System.out.println();
//		1��values()������
		Season2[] seasons = Season2.values();//values()�����ܹ����������ʽ��������ö����Ķ���
		for(int i = 0; i < seasons.length; i++){
			System.out.println(seasons[i]);
		}
		System.out.println();
//		2��valueof(String name)������Ҫ������β�name��ö�����������֣�
//		����java.lang.IllegalArgumentException�쳣 
		Season2 s2 = Season2.valueOf("SPRING");
		System.out.println(s2);
		s2.show();
	}
}
interface Info{//Info�ӿ�
	public void show();
}
enum Season2 implements Info{//ʵ��Info�ӿ�
//	1����ö������������棬����д������������ʽ��ö������ö����ֱ���ö��Ÿ��������ö�������÷�ţ�
//	public static final Season SPRING = new Season("Spring", "��ů����");//==>�Զ���ʱ����ôд�ģ�ע������
	SPRING("Spring", "��ů����"){
		public void show(){
			System.out.println("���ش�أ����︴�ա�");
		}
	},
	SUMMER("Summer", "��������"){
		public void show(){
			System.out.println("�����̫���������ģ�ɹ���ˡ�");
		}
	},
	FALL("Fall", "�����ˬ"){
		public void show(){
			System.out.println("������ʺϷŷ��ݡ�");
		}
	},
	WINTER("Winter", "��������"){
		public void show(){
			System.out.println("��ѩ�׷��ꡣ");
		}
	};
//	2���ṩ������ԣ�����Ϊprivate final��
	private final String seasonName;
	private final String seasonDesc;
//	3��������˽�л�����������Ϊfinal�����ԣ��ڹ������г�ʼ����
	private Season2(String seasonName, String seasonDesc) {
		this.seasonName = seasonName;
		this.seasonDesc = seasonDesc;
	}
//	4���ṩ���õķ������������ԣ�
	public String getSeasonName() {
		return seasonName;
	}
	public String getSeasonDesc() {
		return seasonDesc;
	}
	@Override
	public String toString() {
		return "Season [seasonName=" + seasonName + ", seasonDesc=" + seasonDesc + "]";
	}
//	public void show(){
//		System.out.println("һ�����ڡ�");
//	}
}