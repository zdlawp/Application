package com.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

/**
 * ��ȡHTML�ļ�ת����JSP�ļ�
 * 
 * @author zdlawp
 *
 */
public class HTMLConverToJSP {
	
	/**
	 * ����ָ�����ļ���,������.html�ļ�ת����.jsp�ļ�
	 * @param file
	 * @return
	 */
	public static int ConverHTML(File file) {
		// ��ʼ��״̬
		int count = 0;
		// �ж��Ƿ����
		if (file.exists()) {
			// �õ������ļ�
			File[] files = file.listFiles();
			// ������ļ�
			for (File f : files) {
				
				if (f.isFile()) {
					// �õ���ǰ�ļ����������ļ����ļ���
					String name = f.getName();
					
					// �õ�����.html��β���ļ�
					if (name.endsWith(".html")) {
						try {
							// �õ��ϵĺ�׺��.html,�޸�Ϊ�µĺ�׺��.jsp
							String apName = f.getAbsolutePath();
							apName = apName.substring(0, apName.lastIndexOf(".html"));
							apName = apName + ".jsp";
							//�ڶ�ȡ�ĵ�ǰ�ļ�����д���µ��ļ�
							File newFile = new File(apName);
							
							//�ֽڶ�ȡ��
							InputStream is = new FileInputStream(f);
							//�ֽ�д����
							OutputStream os = new FileOutputStream(newFile);

							//�ַ���ȡ��,��ȡʱָ�������ʽΪUTF-8
							Reader r = new InputStreamReader(is,"UTF-8");
							//�ַ�д����
							Writer w = new OutputStreamWriter(os);

							//�ַ�������
							BufferedReader br = new BufferedReader(r);
							BufferedWriter bw = new BufferedWriter(w);

							//���ļ��ĵ�һ����������д��JSP�����ʽ��
							bw.write("<%@ page language=\"java\" import=\"java.util.*\" pageEncoding=\"UTF-8\"%>");
							bw.newLine();
							bw.write("<%");
							bw.newLine();
							bw.write("String path = request.getContextPath();");
							bw.newLine();
							bw.write("String basePath = request.getScheme()+\"://\"+request.getServerName()+\":\"+request.getServerPort()+path+\"/\";");
							bw.newLine();
							bw.write("%>");
							bw.newLine();

							//ѭ����ȡ����
							String msg = br.readLine();
							while (msg != null) {
								//ѭ��д������
								bw.write(msg);
								bw.newLine();
								msg = br.readLine();
							}
							
							//�ر�����������
							bw.close();
							w.close();
							os.close();
							br.close();
							r.close();
							is.close();
							
							//�����ۼ�
							count++;
							
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return count;
	}

	public static void main(String[] args) {
		File file = new File("G:/HR_IT/�γ���ϵ/S2JAVA�γ���ϵ/S2������/html");
		int count=HTMLConverToJSP.ConverHTML(file);
		if(count!=0){
			System.out.println("�ɹ�ת���ļ�"+count+"��!");
		}else{
			System.out.println("��ת���ļ�");
		}
	}

}
