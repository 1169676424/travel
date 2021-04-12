package cn.itcast.travel.util;

import java.util.UUID;

/**
 * 产生UUID随机字符串工具类
 */
public final class UuidUtil {

	public static String getUuid(){
		return UUID.randomUUID().toString().replace("-","");
	}
	/**
	 * 测试
	 */
	public static void main(String[] args) {
		System.out.println(UUID.randomUUID());
		System.out.println(UuidUtil.getUuid());
//		System.out.println(UuidUtil.getUuid());
//		System.out.println(UuidUtil.getUuid());
//		System.out.println(UuidUtil.getUuid());
	}
}
