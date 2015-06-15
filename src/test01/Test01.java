package test01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * ��Ŀ��
 * �����ȱ�ڴ棬���ʹ��һ�����п��������ʵ��һ�������㷨�Ա�ʾ�����򼯺ϡ�
 * 
 * ʵ������:
 * java 1.7
 * @author Huizhong
 *
 */
public class Test01 {
	private static int ranCount = 1000;
	private static int ranRange = 1000;
	public static void main(String[] args) {
		// 1�������������
		List<Integer> list = new ArrayList<Integer>();
		Random random = new Random();
		for (int i = 0; i < ranCount; i++) {
			list.add(random.nextInt(ranRange));
		}
		
		// 2�����ԭʼ����
		Object[] array = list.toArray();
		System.out.println("Meta Array : ");
		System.out.println(Arrays.toString(array));
		System.out.println("----------------------------------------");
		
		// 3.����jdk����ʵ������
		Arrays.sort(array);
		
		// 4.�������������
		System.out.println("Sorted Array : ");
		System.out.println(Arrays.toString(array));
		System.out.println("----------------------------------------");
	}
}
