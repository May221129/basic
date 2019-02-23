package io.a05otherstream;
import java.io.DataInputStream;
//Data数据流：用来处理基本数据类型、String、字节数组的数据:DataInputStream DataOutputStream
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.junit.Test;

public class TestData {
	@Test 
	public void testData(){
		DataOutputStream dos = null;
		try {
			FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\data.txt"));
			dos = new DataOutputStream(fos);
				
			dos.writeUTF("我爱你，而你却不知道！");
			dos.writeBoolean(true);
			dos.writeLong(1432522344);
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(dos != null){
				try {
					dos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		}	
	}
	@Test
	public void testData1(){
		DataInputStream dis = null;
		try{
			dis = new DataInputStream(new FileInputStream(new File("data.txt")));
//			byte[] b = new byte[20];
//			int len;
//			while((len = dis.read(b)) != -1){
//				System.out.println(new String(b,0,len));
//			}
//			上面这种方式是读取文件中的String，Boolean，long类型的内容，读取结果是论码，必须要用下面这种方式才能读取出我们能够看得懂的文字。
			String str = dis.readUTF();
			System.out.println(str);
			boolean b = dis.readBoolean();
			System.out.println(b);
			long l = dis.readLong();
			System.out.println(l);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(dis != null){
				try {
					dis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
