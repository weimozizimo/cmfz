package com.baizhi.cmfz.util;

import java.util.Random;

/**
 * ������ã��������ʱ���
 * ���ߣ�gzy
 * ����ʱ�䣺2015��8��3������4:14:27
 */
public class RandomSaltUtil {
	/**
	 * 
	 * �������ã������ĸ��ַ�ʱ���
	 */
	public static String generetRandomSaltCode(){
		//�ַ���תchar����
		char[] str="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456".toCharArray();
	
		StringBuilder sb=new StringBuilder();
		Random random=new Random();
		for(int i=0;i<4;i++){
			//�������0��str����֮���������Ϊ�±�
			int randomIndex=random.nextInt(str.length);
			//׷�ӵ�sb����
			sb.append(str[randomIndex]);
		}
		return sb.toString();
 	}
}
