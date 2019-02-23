package enumtest;
//ö���ࣺ�Լ���Ϊ��
public class TestSeason{
	public static void main(String[] args) {
		Season Fall = Season.FALL;
		System.out.println(Fall);
		System.out.println(Fall.getSeasonName());
		System.out.println(Fall.getSeasonDesc());
		System.out.println(Fall.toString());
	}
}
class Season {
//	1���ṩ������ԣ�����Ϊprivate final��
	private final String seasonName;
	private final String seasonDesc;
//	2��������˽�л�����������Ϊfinal�����ԣ��ڹ������г�ʼ����
	private Season(String seasonName, String seasonDesc) {
		super();
		this.seasonName = seasonName;
		this.seasonDesc = seasonDesc;
	}
//	3���ṩ���õķ������������ԣ�
	public String getSeasonName() {
		return seasonName;
	}
	public String getSeasonDesc() {
		return seasonDesc;
	}
//	4������ö����Ķ���,����Ķ�������Ϊpublic static final��
	public static final Season SPRING = new Season("Spring", "��ů����");
	public static final Season SUMMER = new Season("Summer", "��������");
	public static final Season FALL = new Season("Fall", "�����ˬ");
	public static final Season WINTER = new Season("Winter", "��������");
	
	@Override
	public String toString() {
		return "Season [seasonName=" + seasonName + ", seasonDesc=" + seasonDesc + "]";
	}
}