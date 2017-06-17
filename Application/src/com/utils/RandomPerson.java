package com.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Random;

/**
 * 读取硬盘文件随机得到人名
 * 
 * @author zdlawp
 */
public class RandomPerson {

	public static ArrayList<String> getName() {
		ArrayList<String> nameList = new ArrayList<String>();
		// 读取文件流到系统,保存在集合中
		File file = new File("G:/HR_IT/班级信息/256班/256.txt");
		InputStream is = null;
		BufferedReader br = null;
		Reader r = null;
		try {
			is = new FileInputStream(file);
			r = new InputStreamReader(is);
			br = new BufferedReader(r);
			String name = br.readLine();
			while (name != null) {
				nameList.add(name);
				name = br.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				r.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return nameList;
	}

	public static void main(String[] args) {
		Random ran = new Random();
		// 调用得到名字的方法
		ArrayList<String> nameList = getName();
		// 随机得到一个数字
		int num = ran.nextInt(nameList.size());
		for (int i = 0; i < nameList.size(); i++) {
			// 用随机得到的数字和当前循环比较
			if (num == i) {
				System.out.println(nameList.get(i));
				break;
			}
		}
	}
}
