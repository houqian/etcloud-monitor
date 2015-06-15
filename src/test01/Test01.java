package test01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 题目：
 * 如果不缺内存，如何使用一个具有库的语言来实现一种排序算法以表示和排序集合。
 * 
 * 实现语言:
 * java 1.7
 * @author Huizhong
 *
 */
public class Test01 {
	private static int ranCount = 1000;
	private static int ranRange = 1000;
	public static void main(String[] args) {
		// 1、随机数发生器
		List<Integer> list = new ArrayList<Integer>();
		Random random = new Random();
		for (int i = 0; i < ranCount; i++) {
			list.add(random.nextInt(ranRange));
		}
		
		// 2、输出原始数组
		Object[] array = list.toArray();
		System.out.println("Meta Array : ");
		System.out.println(Arrays.toString(array));
		System.out.println("----------------------------------------");
		
		// 3.调用jdk库来实现排序
		Arrays.sort(array);
		
		// 4.输出排序后的数组
		System.out.println("Sorted Array : ");
		System.out.println(Arrays.toString(array));
		System.out.println("----------------------------------------");
	}
}
