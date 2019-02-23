package enumtest;
//枚举类：以季节为例
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
//	1、提供类的属性，声明为private final：
	private final String seasonName;
	private final String seasonDesc;
//	2、构造器私有化，并将声明为final的属性，在构造器中初始化：
	private Season(String seasonName, String seasonDesc) {
		super();
		this.seasonName = seasonName;
		this.seasonDesc = seasonDesc;
	}
//	3、提供公用的方法来调用属性：
	public String getSeasonName() {
		return seasonName;
	}
	public String getSeasonDesc() {
		return seasonDesc;
	}
//	4、创建枚举类的对象,将类的对象声明为public static final：
	public static final Season SPRING = new Season("Spring", "春暖花开");
	public static final Season SUMMER = new Season("Summer", "夏日炎炎");
	public static final Season FALL = new Season("Fall", "秋高气爽");
	public static final Season WINTER = new Season("Winter", "寒冬腊月");
	
	@Override
	public String toString() {
		return "Season [seasonName=" + seasonName + ", seasonDesc=" + seasonDesc + "]";
	}
}