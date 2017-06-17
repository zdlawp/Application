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
 * 读取HTML文件转换成JSP文件
 * 
 * @author zdlawp
 *
 */
public class HTMLConverToJSP {
	
	/**
	 * 传入指定的文件夹,将所有.html文件转换成.jsp文件
	 * @param file
	 * @return
	 */
	public static int ConverHTML(File file) {
		// 初始化状态
		int count = 0;
		// 判断是否存在
		if (file.exists()) {
			// 得到所有文件
			File[] files = file.listFiles();
			// 如果是文件
			for (File f : files) {
				
				if (f.isFile()) {
					// 得到当前文件夹中所有文件的文件名
					String name = f.getName();
					
					// 得到所有.html结尾的文件
					if (name.endsWith(".html")) {
						try {
							// 得到老的后缀名.html,修改为新的后缀名.jsp
							String apName = f.getAbsolutePath();
							apName = apName.substring(0, apName.lastIndexOf(".html"));
							apName = apName + ".jsp";
							//在读取的当前文件夹中写入新的文件
							File newFile = new File(apName);
							
							//字节读取流
							InputStream is = new FileInputStream(f);
							//字节写入流
							OutputStream os = new FileOutputStream(newFile);

							//字符读取流,读取时指定编码格式为UTF-8
							Reader r = new InputStreamReader(is,"UTF-8");
							//字符写入流
							Writer w = new OutputStreamWriter(os);

							//字符缓存流
							BufferedReader br = new BufferedReader(r);
							BufferedWriter bw = new BufferedWriter(w);

							//在文件的第一行至第五行写入JSP编码格式等
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

							//循环读取数据
							String msg = br.readLine();
							while (msg != null) {
								//循环写入数据
								bw.write(msg);
								bw.newLine();
								msg = br.readLine();
							}
							
							//关闭所有流对象
							bw.close();
							w.close();
							os.close();
							br.close();
							r.close();
							is.close();
							
							//数量累加
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
		File file = new File("G:/HR_IT/课程体系/S2JAVA课程体系/S2易买网/html");
		int count=HTMLConverToJSP.ConverHTML(file);
		if(count!=0){
			System.out.println("成功转换文件"+count+"个!");
		}else{
			System.out.println("无转换文件");
		}
	}

}
