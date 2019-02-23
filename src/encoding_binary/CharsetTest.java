package encoding_binary;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.junit.Test;

/**	
 * 这个网页讲的比较好：http://blog.csdn.net/x805433354/article/details/41448057
 * 
 * 	一、字符集和编码规则的演变过程：
 * 		1.刚刚开始是ASCII码, 只能搞英文字符,基本数字,基本符号.
 * 		2.后来256位不够用（一个位有0或1这两种情况，8个位就有“2的8次方”种情况）,各个地区又搞出了自己一套字符集和编码规则——统称ANSI，
 * 			 如GB2312, GBK, JIS 等(GBK是GB2312的超集)。
 * 			用两个两个字节来表示ASCII不能表示的字符(一个ASCII字符是一个字节, 也就是ASCII不能表示的字符是用两个字节).
 * 			当然在ASCII范围内能够表示的字符，则依然是一个字节.
 * 		==> 缺点:	各个地区的编码规则和字符集都不一样. 要想正常显示该地区的字符,就得安装该地区的完整的编码规则和字符集.
 * 			不同 ANSI 编码之间互不兼容，当信息在国际间交流时，无法将属于两种语言的文字，存储在同一段 ANSI 编码的文本中.
 *		3.后来UNICODE一统天下. UNICODE将世界上所有的字符都囊括了,一个字符唯一被一个编码代表. 
 *			UNICODE都是用两个字节来表示一个字符(连半角英文或者数字都是用两个字节来表示), UNICODE可以表示出65536种字符（一个位有
 *			0或1两种情况，2个字节就有16个位，即有“2的16次方”种情况来表示字符）. 
 *			如果还不够,UNICODE还准备另一种方案UCS-4(说简单了就是四个字节来表示一个字符), 那怎么样都够用了.
 *		==> UNICODE只是一套字符集(也就是码和字符一一对应的码表).http://www.Unicode.org/charts/ 可根据unicode码查询对应的字符
 * 			以下类中的方法printUnicode就可以输出UNICODE字符对应的码.
 * 		==> 缺点:每个字符都要两个字节, 太浪费空间.
 * 		4. UTF就是针对UNICODE的一系列编码规则, 有UTF-8,UTF-16,UTF-32(主要就UTF-8)
 * 				UTF-8最大的一个特点，就是它是一种变长的编码方式。它可以使用1~4个字节表示一个符号，根据不同的符号而变化字节长度。
 * 				UTF-8这种编码方式和UNICODE字符集的关系大概如下:
 * 					UTF-8码 <--UTF-8编码规则--> Unicode码 <--Unicode字符集映射关系--> 字符.方向是双向的。
 * 					解释:UTF-8码和Unicode码通过UTF-8编码方式相互转换.Unicode码和字符在Unicode字符集中是一一对应的.
 * 				UTF-8的编码规则:
 * 					Unicode码到UTF8-码的转换规则:
 * 						Unicode(十六进制)    	UTF-8(位，有固有格式)
 * 						0000   至   007F		    0xxxxxxx
 * 											(1个字节，其实也就是：除了“0”这1个格式位后，剩余的7个位的范围(即000 0000至111 1111，也就是0000至007f))
 * 						0080   至   07FF			110xxxxx 10xxxxxx(2个字节)
 * 											(2个字节，其实也就是：除了“110”和“10”这5个格式位后，剩余的11个位的范围(也就是0000至007f))
 * 						0800   至   FFFF			1110xxxx 10xxxxxx 10xxxxxx(3个字节)
 * 						解释:	不同范围内的Unicode码，对应着不同的UTF-8码的格式(即：不同范围内的Unicode码对应着不同的UTF-8的字节数).
 * 							且规定了不同字节开始那几位的格式。如：字符"A"对应的Unicode码为十六进制的65，65在0000~007F范围内，是一个
 * 							字节的，十六进制的1位等于二进制的4个位,所以十六进制的65用二进制表示：6为“0110”；5为“0101”，套上格式为：
 * 							“0110 0101”，所以，"A"的UTF-8码为0110 0101，其中第一个"0"代表的是UTF-8的格式。
 * 					UTF-8的编码规则具体:
 * 						1:对于单字节的字符，UTF-8码表示的字节的第一位设为0，后面7位为字符的Unicode码。因此对于英语字母，UTF-8编码和
 * 							ASCII码是相同的。
 * 						2:对于n字节的符号（n>1），第一个字节的前n位都设为1，第n+1位设为0；从第二位字节开始的字节，前两位一律设为10。
 * 							剩下的没有提及的二进制位，均为这个符号的Unicode码。
 * 							如：0080~07FF范围内的都为两个字节的，转为UTF-8码格式时，第一个字节为110xxxxx，第二个字节为10xxxxxx，字节与字节直接用空格隔开。
 * 					实例:
 * 						'严'的Unicode是4E25（0100 1110 0010 0101）
 * 						根据UNICODE到UTF8的转换规则得到'严'的UTF8模板是:1110xxxx 10xxxxxx 10xxxxxx
 * 						然后，从“严”的最后一个二进制位开始，依次从后向前填入格式中的x，多出的位补0。这样就得到了1110 0100 1011 1000 1010 0101，
 * 						“严”的UTF-8编码是“11100100 10111000 10100101”.
 *				UTF-16的编码规则:
 *					UNICODE保持一致的, 也就是说如果用UTF16编码的话, 每一个字符是两个字节.
 *				
 *				UTF-8和UTF-16的区别：
 *					① UTF-8编码、解码过程：Unicode字符 <——通过Unicode字符集——> Unicode码 <——通过UTF-8编码规则，比对Unicode码所对应的UTF-8格式——> UTF-8码
 *						UTF-8更节约内存，因为它可以使用1~4个字节表示一个符号，根据不同的符号而变化字节长度；
 *						但是也因为UTF-8需要根据字符的Unicode码找到其对应的UTF-8码，所以编码、解码速度慢。
 *						还有一点：UTF-8健壮性好，因为每个字符都有其对应的二进制格式，如果在传输过程中，二进制的某个位有丢失，也不会受影响。
 *					② UTF-16编码、解码过程：Unicode字符  <——通过Unicode字符集——> Unicode码 <——通过UTF-16编码规则——> UTF-16码
 *						UTF-16 统一采用两个字节表示一个字符，所以编码、解码速度快；但是相较于UTF-8而言，更费内存。
 *						UTF-16的健壮性差，系统读UTF-16的二进制时，是按照2个字节(即16位)一组来读的，如果中间某个位丢失了，
 *						当前两个字节所表示的字符就会出错，后面所有的位表示的字符也会出错。
 * 	二、字符集  和  编码规则
 * 			UNICODE是字符集, UTF8是其编码规则.
 * 			GB2312 包括了字符集和编码规则
 *
 *	三、UNICODE字符如何转换成其他编码的字节串:
 *			1:对于UTF-8, UTF-7, UTF-16, UTF-32, UnicodeLittle, UnicodeBig这些编码规则是通过一定的
 *				计算规则转换的, 转换规则已经在以上第四点中阐述了。且这些编码规则可以编码、解码所有的UNICODE字符。
 *				(UTF-7:是一种可变长度字元编码方式，用以将 Unicode字元以 ASCII编码的字元串来呈现，可以应用在电子邮件传输之类的应用。)
 *				
 *			2:单字节字符编码(ISO-8859-1)
 *				如下面第三点也是需要通过codepage来跟UNICODE映射的.
 *
 *				编码:UNICODE字符串通过 iso-8859-1 转化为字节串时，只能正常转化 0~255 范围的字符(单字节)
 *					超过 iso-8859-1范围的字符,统统被编码成3f, 也就是'?'这个符号, 出现乱码, 这就是传说中的黑洞
 *				解码:每一个字节直接作为一个 Unicode 字符
 *					
 *			3:ANSI 编码
 *				例如:byte[] bs = "中".getBytes(Charset.forName("GBK")); 
 *				刚刚开始真的很疑惑, UNICODE跟ANSI编码毫无关系, 他两是怎么可以进行这样互相翻译的.
 *				解答:是利用codepage
 *					codepage就是各国的文字编码和Unicode之间的映射表。
 *						以下是几个常用的codepage，相应的修改上面的地址的数字即可。
 * 						codepage=936   简体中文GBK
 * 						codepage=950   繁体中文BIG5
 * 						codepage=437   美国/加拿大英语
 * 						codepage=932   日文
 * 						codepage=949   韩文
 * 						codepage=866   俄文
 * 					codepage=936的内容示例:
 * 						其中一行:0x9993   0x6ABD   #CJK   UNIFIED   IDEOGRAPH
 * 						第一列是GBK的编码, 第二列是Unicode码
 *				"中".getBytes(Charset.forName("GBK"));这行代码的执行顺序就明朗了
 *					'中'这个Unicode先找到Unicode码, 然后找到GBK的codepage, 然后映射到GBK的码, 然后用GBK的编码规则计算就可以得到bytes了.
 *					这个过程跟utf-8一点关系都没有.
 *				注意点：
 *					1. 这些“ANSI 编码标准”都只能处理各自语言范围之内的 Unicode字符，如GBK只能处理中文。
 *					2. “Unicode 字符”与“转换出来的字节”之间的关系是人为规定的。
 *
 * 	四、java中String.getBytes方法和String.charAt方法的注意点
 * 			String.getBytes
 * 				得到的字节数字是由unicode字符根据某个编码规则编码得到的.
 *			String.charAt
 *				单纯的得到unicode字符. java默认是用unicode编码的, char占用两个字节(因为是这个字符是unicode符).
 *				将char转换成16进制,可以参考以下printUnicode方法
 *  	
 *  五、乱码原因:
 *  		原因:
 *  			字符转字节的时候:字符是unicode的,要转成的编码A的字节的时候, 如果该字符超出编码A的字节范围(也就是codepage没有对应项).
 *  				这时候依然可以得到字节数组, 只不过这个字节数组是什么就得根据编码A的编码规则确定的(编码规则:当codepage没有对应项返回什么)
 *  				例如iso-8859-1就规定没有对应项的时候返回3f(在解码得到的就是符号'?').
 *  				
 *  				"中"字是2个字节的，通过Unicode字符集，得到对应的Unicode码，再通过codepage去找到对应的iso-8859-1码，但因为iso-8859-1只能
 *  				表示1个字节的字符，所以在codepage中就没有“中”字符的Unicode码所对应的iso-8859-1码，所以iso-8859-1就返回5f.
 *  				一串中文字符，通过编码成了iso-8859-1码，再通过iso-8859-1编码规则进行解码，其中的所有3f都会被解码成"?",这个乱码问题会改变字符的意思，
 *  				==>对于每一个2个字节表示的字符，用iso-8859-1来编码，都会出现上面所说的问题。
 *  				但如果给一个通过UTF-8进行编码的byte[]，再让你通过iso-8859-1进行解码，因为iso-8859-1是用一个字节表示一个字符的，所以每个UTF-8编码
 *  				而成的字节(UTF-8是可变字节长度)，都会被iso-8859-1进行一个字节一个字节的读取，就会形成乱码。
 *  
 *  			乱码问题出现在编码过程中，也有可能出现在解码过程中。
 *  				用什么编码规则编的码，就需要用什么编码规则进行解码。
 *  				
 *  			
 *  			字节转字符:new String(bytes, Charset.forName("UTF-8")) 
 *  				过程:
 *  					如果是UTF编码:
 *  						bytes-->UTF-8码-->unicode码-->unicode字符
 *  					如果是ANSI编码:
 *  						bytes-->ANSI码-->unicode码-->unicode字符
 * 					所以:
 *  					当字节数组bytes的内容跟所给的编码A是一致的时候,那么得到的结果就是正确的.
 *  					当字节数组bytes的内容跟所给的编码A不一致的时候,那么得到的结果就是错误的,
 *  					注意:
 *  						这里也不是说bytes的编码而是说bytes的内容, 因为bytes如果刚好是ISO-8859-1的内容  或者  GBK的单字节字符的内容的时候, 
 *  							那么跟UTF-8的单字节编码结果是一致的,也就不会乱码, 如下测试用例的testEncodeDecode.
 *  						
 *  		场景:
 *  			tomcat实现:request.getParameter("name");
 *  				现象:如果不做特殊的设置, request.getParameter方法会将客户端传过来的字节数组按照iso-8859-1编码进行解码.
 *  					那得到字符串就是乱码的.
 *  				问:为什么tomcat会选择默认是用iso-8859-1, 而不是选择其他的编码.
 *  				答:首先是客户端选用的编码是不可预知的,可能是iso-8859-1, gbk, utf-8等等. 
 *  					这时候服务端默认采用iso-8859-1来进行解码的话, 至少能保证其字节内容不会有变化.
 *  					注意是字节内容不变,字符可能是乱码.用iso-8859-1解码是按照一个一个字节来翻译成单字节字符.
 *  				问:那么如何将得到的字符串转换成非乱码字符串
 *  				答:new String(name.getBytes("iso-8859-1"), "utf-8");
 *  					name.getBytes("iso-8859-1")就能得到原字节数组, 然后用跟前端约定好的编码来进行解码即可.
 *  				
 *  				注意1:以上过程并没有细讲编码以及解码的过程(也就是跟codepoint相关的东西, 详见第六大点).
 *  				注意2:tomcat默认iso-8859-1, 这毕竟是默认, 这样编码的转来转去去是没必要的浪费
 *  					跟前端约定好之后,就应该直接改掉其默认的编码规则.
 *  六、以下测试用例涉及的代码部分注意点
 *  		b & 0xff, 会看到将字节都做了这样的&操作.
 *  		问:为什么要这样做, Integer.toHexString的方法的入参是int类型的, b是小于int的, 直接传不用&操作应该是没问题的.
 *  		答:byte和char在某些情况直接转换成int类型的时候,可能会出我们不希望看到的结果.
 *  			首先介绍一下这样直接转换在数值的转换是没问题的.  -1,2,-6,24都照样会转成原值int.
 *  			但是我们并不是希望转成原数值. 而只是希望输出这个这个字节的十六进制. 所以跟补码,跟负数一点关系都没有.
 *  			所以当b的第一个位是1时,也就是为负数的时候,就会出意想不到的结果. 所以要进行一下&操作.
 *  			
 * 	七、关于GB2312,GBK,UTF-16编解码的补充
 *  		GB2312:
 *  			它有个码表, 叫做区位表. 一个字节表示区, 一个字节表示位，区和位都是有一定范围的.
 *  				一个区位号映射一个字符(中文字符, 全角的英文字符和数字).
 *  			编码过程:拿到区位号. 如1618, 得到区是16 ，位是18. 然后分别将它两加上0xA0 也就是
 *  				0xA0+16, 0xA0+18, 得到的结果就是GB2312的码.
 *  			解码过程:当遇到一个字节是小于127的时候, 就用ASCII码直接解码, 当两个字节都是大于127的,就将它两用GB2312进行解码.
 *  		GBK：
 *  			GBK是GB2312的扩展.
 *  			本来BG2312要求两个字节都大于127, 扩展后的要求是只要第一个字节大于127就行,第二个字节不做要求.所以第二个字节能表示的范围就大起来了.
 *  		UTF-16
 *  			编码规则: utf-16将0xfe 0xff作为utf16码的头部, 也就是说utf16的前两个字节是0xfe 0xff
 *  				然后拿到每个字符的unicode码, 拼接在头的后面.
 *  				如:"abc"的unicode码是U+0061;  U+0062;  U+0063;
 *  					其utf16的码是：0xfe; 0xff; 0x00; 0x61; 0x00; 0x62; 0x00; 0x63;
 *  				如"b"的unicode码是  U+0062
 *  					其utf-16的码是：0xfe; 0xff; 0x00; 0x62;
 */
public class CharsetTest {

	
	@Test
	public void testPrintBytesToHex(){
		
		String name = "赖丽梅";
		//默认编码规则：
		System.out.println(Charset.defaultCharset());
		System.out.println("--------------------------------------");
		
		byte[] byte1 = name.getBytes();
		printBytesToHex(byte1);
		System.out.println("--------------------------------------");
		
		byte[] byte2 = name.getBytes(Charset.forName("UTF-8"));
		printBytesToHex(byte2);
		System.out.println("--------------------------------------");
		
		byte[] byte3 = name.getBytes(Charset.forName("UTF-16"));
		printBytesToHex(byte3);
	}
	/**
	 * 将给的字节数组转成16进制输出
	 */
	private void printBytesToHex(byte[] bytes){
		for(byte b : bytes){
			String tmp = Integer.toHexString(b & 0xff);
			if(tmp.length() == 1){
				tmp = "0" + tmp;
			}
			System.out.print("0x" + tmp + ";  ");
		}
		System.out.println();
	}
	
	@Test
	public void testShowUnicode(){
		String name = "中国人aaa";
		showUnicode(name);
	}
	/**
	 * 1.将给的字符串输出去unicode码
	 * 
	 * 2.不同进制的格式：
	 * 	Java语言里没有二进制直接表示法,原因是太长了,需要的化可以使用16进制
	 * 	八进制：前置 0
	 * 	十进制：不需前置
	 * 	十六进制： 前置 0x 或者 0X
	 */
	public void showUnicode(String str){
		if(str != null){
			for(int i = 0; i < str.length(); i++){
				char nowStr = str.charAt(i);
				
				//将int值转为十六进制：
				//1.为什么入参要求是int型，但为啥传入char类型也行？  答：因为Java自动将char转成了int类型
				//2.为什么入参需要做“与运算”？ 
				//答：char是两个字节的，如果不做0xff这样的按位与运算，它会自动类型转换（小类型转大类型），但很有可能会发生运算错误，就看你这个数据是否会在存取上面由于补码的运算问题导致乱码或者其他错误。
				String unicode = Integer.toHexString(nowStr & 0xffff);
				
				//为什么要为unicode加零：因为Unicode码都是用2个字节来表示一个字符，但ASCII范围内的字符都是一个字节的，
				//这些一个字节能表示的字符转为Unicode码时，需要在前面“补0”了。如：字符“a”对应的Unicode码为0061。
				if(unicode.length() == 1){
					unicode = "000" + unicode;
				}
				if(unicode.length() == 2){
					unicode = "00" + unicode;
				}
				if(unicode.length() == 3){
					unicode = "0" + unicode;
				}
				System.out.println("U+ 0x" + unicode);
			}
		}
	}
	
	/**
	 * 编码、解码
	 * 用什么编码规则编的码，就得用什么编码规则来解码，否则会出现乱码，甚至会改变原字符所对应的字节数组。详见：五、乱码原因。
	 * @throws UnsupportedEncodingException 
	 */
	@Test
	public void testEncodeDecode() throws UnsupportedEncodingException{
		
		String targetStr = "abc";//Unicode字符
		
		/**
		 * 提问：为什么用UTF-16编的码，再用UTF-8解码，最后输出的字符前面会多两个“？？”呢？
		 * 答：因为UTF-16进行编码时，UTF-8会在Unicode码的最前面加上"0xfe 0xff"来表示这是UTF-16编码后的二进制，
		 * 	"abc"的Unicode码为"0x00  0x61  0x00  0x62  0x00  0x63",
		 * 	经过UTF-16编码后,为"0xfe  0xff  0x00  0x61  0x00  0x62  0x00  0x63"
		 * 	这时候用UTF-8进行解码，按照UTF-8的编码规则，一个字节的Unicode字符的格式为"0xxx xxxx",
		 * 	"abc"的经过UTF-16编译后的十六进制值中，"0xfe  0xff"没有对应的UTF-8的格式(详细的参照UTF-8的编码规则)，
		 * 	所以被转为了默认的"??".但后面的"0x00  0x61  0x00  0x62  0x00  0x63",UTF-8可以依据格式转为Unicode码"abc"
		 */
		byte[] byte1 = targetStr.getBytes("UTF-16");//把Unicode字符通过UTF-16编码规则，转为UTF-16码
		printBytesToHex(byte1);//0xfe;  0xff;  0x00;  0x61;  0x00;  0x62;  0x00;  0x63;  
		System.out.println(new String(byte1, "UTF-16"));//a b c
		System.out.println(new String(byte1, "UTF-8"));//输出：?? a b c 提问：为什么用UTF-16编的码，用UTF-8解码输出多了两个 ?? 呢？
		
		/**
		 * UTF-8对ISO-8859-1兼容，所以用ISO-8859-1进行编码的“abc”，再用UTF-8解码后，结果任然没变。
		 */
		byte[] bytes = targetStr.getBytes(Charset.forName("ISO-8859-1"));
		System.out.println(new String(bytes, Charset.forName("UTF-8")));//abc
		
		/**
		 * GBK对ASCII码兼容；UTF-8对"0000~007f(十六进制)"范围内的Unicode字符，格式为"0xxx xxxx"
		 */
		String name = "abc";
		byte[] bytes2 = name.getBytes(Charset.forName("GBK"));
		System.out.println(new String(bytes2, Charset.forName("UTF-8")));//abc
	}
}
