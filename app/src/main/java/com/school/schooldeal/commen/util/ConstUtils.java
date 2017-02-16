package com.school.schooldeal.commen.util;

/**
 * Created by GavynZhang on 2016/8/15.
 */
public class ConstUtils {
    /******************** 存储相关常量 ********************/
    /**
     * Byte与Byte的倍数
     */
    public static final long BYTE = 1;
    /**
     * KB与Byte的倍数
     */
    public static final long KB = 1024;
    /**
     * MB与Byte的倍数
     */
    public static final long MB = 1048576;
    /**
     * GB与Byte的倍数
     */
    public static final long GB = 1073741824;

    /******************** 时间相关常量 ********************/
    /**
     * 毫秒与毫秒的倍数
     */
    public static final int MSEC = 1;
    /**
     * 秒与毫秒的倍数
     */
    public static final int SEC = 1000;
    /**
     * 分与毫秒的倍数
     */
    public static final int MIN = 60000;
    /**
     * 时与毫秒的倍数
     */
    public static final int HOUR = 3600000;
    /**
     * 天与毫秒的倍数
     */
    public static final int DAY = 86400000;

    /******************** DES加密相关常量 ********************/
    /**
     * DES加密为ECB、无填充
     */
    public static final String DES_ECB_NO_PADDING = "DES/ECB/NoPadding";
    /**
     * DES加密为CBC、无填充
     */
    public static final String DES_CBC_NO_PADDING = "DES/CBC/NoPadding";
    /**
     * DES加密为CFB、无填充
     */
    public static final String DES_CFB_NO_PADDING = "DES/CFB/NoPadding";
    /**
     * DES加密为OFB、无填充
     */
    public static final String DES_OFB_NO_PADDING = "DES/OFB/NoPadding";
    /**
     * DES加密为ECB、零填充
     */
    public static final String DES_ECB_ZEROS_PADDING = "DES/ECB/ZerosPadding";
    /**
     * DES加密为CBC、零填充
     */
    public static final String DES_CBC_ZEROS_PADDING = "DES/CBC/ZerosPadding";
    /**
     * DES加密为CFB、零填充
     */
    public static final String DES_CFB_ZEROS_PADDING = "DES/CFB/ZerosPadding";
    /**
     * DES加密为OFB、零填充
     */
    public static final String DES_OFB_ZEROS_PADDING = "DES/OFB/ZerosPadding";
    /**
     * DES加密为ECB、PKCS5Padding填充
     */
    public static final String DES_ECB_PKCS5_PADDING = "DES/ECB/PKCS5Padding";
    /**
     * DES加密为CBC、PKCS5Padding填充
     */
    public static final String DES_CBC_PKCS5_PADDING = "DES/CBC/PKCS5Padding";
    /**
     * DES加密为CFB、PKCS5Padding填充
     */
    public static final String DES_CFB_PKCS5_PADDING = "DES/CFB/PKCS5Padding";
    /**
     * DES加密为OFB、PKCS5Padding填充
     */
    public static final String DES_OFB_PKCS5_PADDING = "DES/OFB/PKCS5Padding";

    /******************** 正则相关常量 ********************/

    /**
     * 正则：手机号（简单）
     */
    public static final String REGEX_MOBILE_SIMPLE = "^[1]\\d{10}$";
    /**
     * 正则：手机号（精确）
     * <p>移动：134(0-8)、135、136、137、138、139、147、150、151、152、157、158、159、178、182、183、184、187、188</p>
     * <p>联通：130、131、132、145、155、156、175、176、185、186</p>
     * <p>电信：133、153、173、177、180、181、189</p>
     * <p>全球星：1349</p>
     * <p>虚拟运营商：170</p>
     */
    public static final String REGEX_MOBILE_EXACT = "^((13[0-9])|(14[5,7])|(15[0-3,5-8])|(17[0,3,5-8])|(18[0-9])|(147))\\d{8}$";
    /**
     * 正则：电话号码
     */
    public static final String REGEX_TEL = "^0\\d{2,3}[- ]?\\d{7,8}";
    /**
     * 正则：身份证号码15位
     */
    public static final String REGEX_IDCARD15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
    /**
     * 正则：身份证号码18位
     */
    public static final String REGEX_IDCARD18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9Xx])$";
    /**
     * 正则：邮箱
     */
    public static final String REGEX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    /**
     * 正则：URL
     */
    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?";
    /**
     * 正则：汉字
     */
    public static final String REGEX_CHZ = "^[\\u4e00-\\u9fa5]+$";
    /**
     * 正则：用户名，取值范围为a-z,A-Z,0-9,"_",汉字，不能以"_"结尾,用户名必须是6-20位
     */
    public static final String REGEX_USERNAME = "^[\\w\\u4e00-\\u9fa5]{3,20}(?<!_)$";
    /**
     * 正则：yyyy-MM-dd格式的日期校验，已考虑平闰年
     */
    public static final String REGEX_DATE = "^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$";
    /**
     * 正则：IP地址
     */
    public static final String REGEX_IP = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";

}
