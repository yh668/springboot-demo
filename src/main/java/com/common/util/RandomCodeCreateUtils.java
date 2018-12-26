package com.common.util;

import java.util.Random;

/**
 * 类名称：PasswordUtils 
 * 类描述：随机字符串生成工具类 
 */
public class RandomCodeCreateUtils
{
    private static final byte INDEX_NUMBER = 0;
    private static final byte INDEX_LETTER = 1;
    private static final byte INDEX_SPECIAL_CHAR = 2;

    /** 特殊符号 */
    private static final char[] SPECIAL_CHARS = { '`', '~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '=', '+', '[', ']', '{', '}', '\\', '|', ';', ':', '\'', '"', ',', '<', '.', '>', '/', '?' };

    /**
     * 按一定的概率生成一个随机的N位(N>=3)密码，必须由字母数字特殊符号组成，三者缺一不可
     * 
     * @param len
     *            密码长度,必须大于等于3
     * @param genChances
     *            分别是生成数字、字母、特殊符号的概率
     * @return 生成的随机密码
     */
    public static char[] generateRandom(final int len, final byte[] paramGenChances) throws IllegalArgumentException {
        if (len < 3) {
            throw new IllegalArgumentException("len must not smaller than 3, but now is " + len);
        }
        final char[] password = new char[len];
        // 之所以该复制一份是为了使函数不对外产生影响
        final byte[] genChances = paramGenChances.clone();
        final byte[] genNums = new byte[genChances.length];
        for (byte i = 0; i < genChances.length; i++) {
            genNums[i] = 0;
        }
        final Random random = new Random();
        int r;
        for (int i = 0; i < len; i++) {
            adjustGenChance(len, i, genChances, genNums);
            byte index = getCharType(random, genChances);
            genNums[index]++;
            switch (index) {
            case INDEX_NUMBER:
                password[i] = (char) ('0' + random.nextInt(10));
                break;
            case INDEX_LETTER:
                r = random.nextInt(52);
                if (r < 26) {
                    password[i] = (char) ('A' + r);
                } else {
                    password[i] = (char) ('a' + r - 26);
                }
                break;
            case INDEX_SPECIAL_CHAR:
                r = random.nextInt(SPECIAL_CHARS.length);
                password[i] = SPECIAL_CHARS[r];
                break;
            default:
                password[i] = ' ';
                break;
            }
        }
        logChances(genNums);
        return password;
    }
    /**
     * 打印生成密码中各类字符的个数
     */
    public static void logChances(byte[] genNums) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (byte i = 0; i < genNums.length; i++) {
            sb.append(genNums[i]);
            if (i != genNums.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("}");
        System.out.println(sb.toString());
    }
    
    /**
     * 根据当前需要生成密码字符的位置,动态调整生成概率
     * 
     * @param len
     *            待生成的总长度
     * @param index
     *            当前位置
     * @param genChances
     *            分别是生成数字、字母、特殊符号的概率
     * @param genNums
     *            这些类型已经生成过的次数
     */
    public static void adjustGenChance(final int len, final int index, final byte[] genChances, final byte[] genNums) {
        final int leftCount = len - index;
        byte notGenCount = 0;
        for (byte i = 0; i < genChances.length; i++) {
            if (genNums[i] == 0) {
                notGenCount++;
            }
        }
        if (notGenCount > 0 && leftCount < genChances.length && leftCount == notGenCount) {
            for (byte i = 0; i < genChances.length; i++) {
                if (genNums[i] > 0) {
                    genChances[i] = 0;
                }
            }
        }
    }

    /**
     * 获取该密码字符的类型
     * 
     * @param random
     *            随机数生成器
     * @param genChances
     *            分别是生成数字、字母、特殊符号的概率
     * @return 密码字符的类型
     */
    public static byte getCharType(final Random random, final byte[] genChances) {
        int total = 0;
        byte i = 0;
        for (; i < genChances.length; i++) {
            total += genChances[i];
        }
        int r = random.nextInt(total);
        for (i = 0; i < genChances.length; r -= genChances[i], i++) {
            if (r < genChances[i]) {
                break;
            }
        }
        return i;
    }
    
    public static void main(String[] args) {
		byte[] genChances = { 5, 7, 6 };
		char[] c = generateRandom(128, genChances);
		String s = String.valueOf(c);
		System.out.println(s);
	}
}
