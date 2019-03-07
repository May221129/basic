package enumtest;
//enum枚举类：以季节为例
public class TestSeason2{
	public static void main(String[] args) {
		Season2 Fall = Season2.FALL;
		System.out.println(Fall);
		System.out.println(Fall.getSeasonName());
		System.out.println(Fall.getSeasonDesc());
		System.out.println(Fall.toString());
		Fall.show();
		
		System.out.println();
//		1、values()方法：
		Season2[] seasons = Season2.values();//values()方法能够以数组的形式返回整个枚举类的对象。
		for(int i = 0; i < seasons.length; i++){
			System.out.println(seasons[i]);
		}
		System.out.println();
//		2、valueof(String name)方法：要求传入的形参name是枚举类对象的名字，
//		否则报java.lang.IllegalArgumentException异常 
		Season2 s2 = Season2.valueOf("SPRING");
		System.out.println(s2);
		s2.show();
	}
}
interface Info{//Info接口
	public void show();
}
enum Season2 implements Info{//实现Info接口
//	1、将枚举类放在最上面，并且写成下面这种形式，枚举类与枚举类直接用逗号隔开，最后枚举完了用封号：
//	public static final Season SPRING = new Season("Spring", "春暖花开");//==>自定义时是这么写的，注意区别。
	SPRING("Spring", "春暖花开"){
		public void show(){
			System.out.println("春回大地，万物复苏。");
		}
	},
	SUMMER("Summer", "夏日炎炎"){
		public void show(){
			System.out.println("夏天的太阳毒辣辣的，晒死人。");
		}
	},
	FALL("Fall", "秋高气爽"){
		public void show(){
			System.out.println("秋天很适合放风筝。");
		}
	},
	WINTER("Winter", "寒冬腊月"){
		public void show(){
			System.out.println("瑞雪兆丰年。");
		}
	};
//	2、提供类的属性，声明为private final：
	private final String seasonName;
	private final String seasonDesc;
//	3、构造器私有化，并将声明为final的属性，在构造器中初始化：
	private Season2(String seasonName, String seasonDesc) {
		this.seasonName = seasonName;
		this.seasonDesc = seasonDesc;
	}
//	4、提供公用的方法来调用属性：
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
//		System.out.println("一个季节。");
//	}
}