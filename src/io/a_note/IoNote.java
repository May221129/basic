package io.a_note;

/**
一、有关文件(磁盘)IO：
	1. 流一定需要自己手动关闭吗？
		FileInputStream类重写了顶级父类Object的finalize()方法，该方法中含close()方法，所以如果你没有手动关闭流，流也会被自动关闭的。
	2. 流是怎么被自动关闭的？（FileInputStream对象被回收，句柄是否会被释放？）
		当FileInputStream被GC回收前，会先去执行FileInputStream的finalize()方法，执行这个方法时就会执行到方法里的close()方法，流就被自动关闭了。
		单个进程可以拥有的句柄的个数是有限的，对象被回收和IO流的关闭，这两者之间没有必然关系，IO流的关闭必须调用close()方法。
		（IO是要手动close，GC确实回收不了. 只不过GC回收的时候触发了finalize方法, finalize方法内调用了close）
	3. 既然流最终都会被自动关闭，我们会什么还需要手动关闭流？且手动关闭流是很重要的。
		如果没有把流进行手动关闭，GC会自动帮你关闭，但GC回收的时机不确定，有可能在GC还未进行回收，
		情况一：有很多对象调用了该方法，结果因方法中的流没有进行手动关闭，此时就可能出现栈内存溢出。
		情况二：一个for循环中不停地去调用含流的那个方法，因为单个进程可以拥有的句柄个数是有限的，但该方法中的流没有手动关闭，此时也会报错。
		注：情况一和情况二说的不清楚，有待深究。
	
二、有关缓冲流：
	1. 根据数据操作单位可以把缓冲流分为：
		BufferedInputStream 和 BufferedOutputStream
		BufferedReader 和 BufferedWriter
	2. 缓冲流的读写的效率为什么高于比一般的节点流（FileInputSteam、FileOutputStream、FileReader、FileWriter）？
		2.1 BufferedOutputStream：
			查看BufferedOutputStream类，声明了一个成员变量protected byte buf[]，这个buf[]在构造函数中进行赋值，默认值为8192byte（即1024byte*8），
			BufferedOutputStream在做输出时，写出的数据会先放将buf[]放满，buf[]在内存中缓存，使用flush()将会使内存中的数据一次性、立刻写出到磁盘上。
			==>优势：如果是自己写的FileOutputStream输出流，输出的字节数可能会有过少等不合理现象。而缓冲流会将buf[]一次写满8K缓冲到内存中，再一次性输出到磁盘。
			==>劣势：BufferedOutputStream也是在OutputStream节点流的基础上做输出的，但是它还多了一步——将buf[]先缓存到内存中，再输出。
			==>总结：只要你在写FileOutputStream时，每次向磁盘输出的byte数组设置的合理，不是一个字节一个字节输出，或者1个G这么大的不合理的输出，
				直接使用FileOutputStream节点流会比使用BufferedOutputStream缓冲流来的效率高（少了先缓存到内存中这一步）。
		2.2 BufferedInputStream：和 BufferedOutputStream类似
			（1）场景：我们直接使用节点流来读取磁盘上的文件时，可能每次读取的字节数不太合理，有些人甚至会一个字节一个字节去读取，IO流需要充磁盘上找到文件，
				第一次读取时，找到文件的第一个字节，通过IO流reader()到，第二次读取时，找到第二个字节……一直重复，直到把一整个文件读完。
			（2）场景分析：
				① 很多人直接使用节点流来读取/输出文件内容时，对于每次读取/输出的字节数组的大小，并不能设计的很合理，可能很小，也可能太大了。
				② IO从磁盘中找到第N个字节这一动作，是非常消耗资源的，如果每次读取/输出的字节数组设计的很小，就需要多次重复“从磁盘找到某个字节”这一动作。
			（3） BufferedInputStream怎么解决上述问题的：
				查看BufferedOutputStream类，声明了一个成员变量protected volatile byte buf[]，这个buf[]在构造函数中进行赋值，默认值为8192byte（即1024byte*8）。
				BufferedInputStream比FileInputStream多了一个缓冲区，输入的数据会先将buf[]一次性存放满、缓存到缓冲区中。
				执行read时先从缓冲区读取，当缓冲区数据读完时再把缓冲区的buf[]填满。因此，当每次读取的数据量很小时，
				FileInputStream每次都是从硬盘读入，而BufferedInputStream是先从磁盘读入的数据存满buf[]并将buf[]放入缓冲区，
				再从缓冲区读入到内存中。读取内存速度比读取硬盘速度快得多，因此BufferedInputStream效率高。
				==>劣势和 BufferedOutputStream类似：BufferedInputStream的默认缓冲区大小是8192字节。当每次读取数据量接近或远超这个值时，两者效率就没有明显差别了。
	3. 注意：
		（1）缓冲流要“套接”在相应的节点流之上。
		（2）缓冲流的作用：为了提高数据读写的速度，Java API提供了带缓冲功能的流类，在使用这些流类时，会创建一个内部缓冲区数组，
	
 */
public class IoNote {

}
