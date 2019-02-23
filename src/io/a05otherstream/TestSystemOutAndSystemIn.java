package io.a05otherstream;
//��Ŀ���Ӽ��������ַ�����Ҫ�󽫶�ȡ���������ַ���ת�ɴ�д�����Ȼ������������������ֱ�������롰e�����ߡ�exit��ʱ���˳�����
//��׼�������������
//��׼���������System.out
//��׼����������System.in

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;

public class TestSystemOutAndSystemIn {
	@Test
	public void test(){
		BufferedReader br = null;
		try {
			InputStream is = System.in;
			InputStreamReader isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			String str;
			while(true){
				System.out.println("�������ַ�����");
				str = br.readLine();
				if(str.equalsIgnoreCase("e") || str.equalsIgnoreCase("exit")){
					break;
				}
				String str1 = str.toUpperCase();
				System.out.println(str1);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
}
