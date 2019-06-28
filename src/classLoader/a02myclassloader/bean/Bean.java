package classLoader.a02myclassloader.bean;

public class Bean {
//	public static void main(String[] args) {
//		System.out.println(Bean.class.getName());
//		System.out.println(Bean.class.getTypeName());
//		System.out.println(Bean.class.getCanonicalName());
//		System.out.println(Bean.class.getSimpleName());
//		System.out.println(Bean.class.getPackage());
//	}
	public void test() {
		System.out.println(this.getClass().getName() + " : test()" );
	}
}
