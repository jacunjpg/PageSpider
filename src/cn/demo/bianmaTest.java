package cn.demo;

import java.io.UnsupportedEncodingException;

public class bianmaTest {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
//		System.out.println("����");
//        System.out.println("����".getBytes());
//        System.out.println("����".getBytes("GB2312"));
//        System.out.println("����".getBytes("ISO8859_1"));
//        System.out.println(new String(" ����".getBytes()));
//        System.out.println(new String(" ����".getBytes(), "GB2312"));
//        System.out.println(new String(" ����".getBytes(), "ISO8859_1"));
//        System.out.println(new String(" ����".getBytes("GB2312")));
//        System.out.println(new String(" ����".getBytes("GB2312"), "GB2312"));
//        System.out.println(new String(" ����".getBytes("GB2312"), "ISO8859_1"));
//        System.out.println(new String(" ����".getBytes("ISO8859_1")));
//        System.out.println(new String("����".getBytes("ISO8859_1"), "GB2312"));
//        System.out.println(new String("����".getBytes("ISO8859_1"), "ISO8859_1"));
		int[] nums = { 10, 15, 16, 18, 20 };
		int i = (int) (Math.random() * nums.length);
		System.out.println(nums[i]);
		
	}

}
