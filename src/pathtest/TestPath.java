package pathtest;

import java.io.File;
import org.junit.Test;

/**
 * \\ 和 \ 和 / 的这三者的区别：
 * 1.背景：
 * 	Unix使用/作为路径分隔符，而web应用最新使用在Unix系统上面，所以目前所有的网络地址都采用 /作为分隔符。
 * 	Windows由于使用斜杠 /作为DOS命令提示符的参数标志了，为了不混淆，所以采用 反斜杠\作为路径分隔符。
 * 	所以目前windows系统上的文件浏览器都是用 反斜杠\作为路径分隔符。
 * 	随着发展，DOS系统已经被淘汰了，命令提示符也用的很少，斜杆和反斜杠在大多数情况下可以互换，没有影响。
 * 2.结论：
 *	（1）浏览器地址栏网址使用 斜杆/;
 *	（2）windows文件浏览器上使用 反斜杠\;
 *	（3）出现在html url() 属性中的路径，指定的路径是网络路径，所以必须用 斜杆/;
 *	（4）出现在普通字符串中的路径，如果代表的是windows文件路径，则使用 /和 \是一样的；如果代表的是网络文件路径，则必须使用 /;
 *	（5）转义字符。在java中写windows路径一般用"/"或将"\"转义一下，就成了"\\"。
 *		下面两种路径都对，第二行通过转义字符"\",使得各编译器解析得到的都是"\"
 *			File file2 = new File("Q:\\mystudy\\studynotes");
 *			File file3 = new File("Q:/mystudy/studynotes");
 * 	（6）目录
 *		./SRC/  这样写表示，当前目录中的SRC文件夹；
 *		../SRC/ 这样写表示，当前目录的上一层目录中SRC文件夹；
 *		/SRC/   这样写表示，项目根目录（可以指磁盘根目录，也可以指项目根目录，具体根据实际情况而定）
 */
public class TestPath{
	@Test
	public void testPath(){
		//写法一：用 \ ==>错误的写法，这里编译器会认为"\"是转义字符。
//		File file1 = new File("Q:\mystudy\studynotes");
		//写法二：用 \\
		File file2 = new File("Q:\\mystudy\\studynotes");
		//写法三：用 /
		File file3 = new File("Q:/mystudy/studynotes");
		
		System.out.println(file2.length());//4096
		System.out.println(file3.length());//4096
	}
}