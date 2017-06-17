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
 * ��ȡӲ���ļ�����õ�����
 * 
 * @author zdlawp
 */
public class RandomPerson {

	public static ArrayList<String> getName() {
		ArrayList<String> nameList = new ArrayList<String>();
		// ��ȡ�ļ�����ϵͳ,�����ڼ�����
		File file = new File("G:/HR_IT/�༶��Ϣ/256��/256.txt");
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
		// ���õõ����ֵķ���
		ArrayList<String> nameList = getName();
		// ����õ�һ������
		int num = ran.nextInt(nameList.size());
		for (int i = 0; i < nameList.size(); i++) {
			// ������õ������ֺ͵�ǰѭ���Ƚ�
			if (num == i) {
				System.out.println(nameList.get(i));
				break;
			}
		}
	}
}
