package generic.a_note;

/**
 要明确泛型就是不知道其类型的类型, 所以不管是类泛型,还是方法泛型, 都不要在其方法的具体实现中强转成某一个具体类型.

1. 什么时候指定泛型？
	1.1 new出这个对象的时候：
		Order<String> o1 = new Order<String>();
	1.2 某个子类去继承这个父类的时候：
		public class BookDaoImplement extends BaseDao<Book>{}
		
2. 类的泛型 VS 方法的泛型
	2.1 类的泛型：public interface Dao<T> {}
	2.2 方法的泛型：
		<V> 表明该泛型是一个方法的泛型，而非类泛型。
		情况一:	public <A> List<A> getContext(A a){//表明了该方法有一个不确定类型的类型A, 其入参类型和返回值List的泛型类型是一样的.
					return new ArrayList<A>();
   				}
   				调用public <A> List<A> getContext(A a)时,是:List<傻帽> list = g.getContext(傻帽的实例); 保证入参和返回值List的类型都是傻帽类型.
   		方法的泛型可以不在任何地方去指定这个泛型，调用该方法时，不限制其返回值类型即为它的作用。
   		情况二： public <B> B getBb();//该方法的声明表示该方法有一个不确定类型的返回值.
				调用该方法的时候就可以  int a = getBb() 或者  double d = getBb();
 	      		运行时可能会出错, 但是编译的时候就可以不用强转.
      	 		其方法的实现类似:return (B)new Object(); 注意要强转成(B)

 */
public class GenericNote {

}
