package com.liuwan.search.util;

import java.util.HashSet;
import java.util.Set;

public class PinYin4j {

    public PinYin4j() {
    }

    /**
     * 字符串集合转换字符串(逗号分隔)
     */
    public String makeStringByStringSet(Set<String> stringSet) {
        StringBuilder str = new StringBuilder();
        int i = 0;
        for (String s : stringSet) {
            if (i == stringSet.size() - 1) {
                str.append(s);
            } else {
                str.append(s + ",");
            }
            i++;
        }
        return str.toString().toLowerCase();
    }

    /**
     * 获取拼音集合
     */
    public Set<String> getPinyin(String src) {
        char[] srcChar;
        srcChar = src.toCharArray();

        // 1:多少个汉字
        // 2:每个汉字多少种读音
        String[][] temp = new String[src.length()][];
        for (int i = 0; i < srcChar.length; i++) {
            char c = srcChar[i];
            // 是中文或者a-z或者A-Z转换拼音(我的需求，是保留中文或者a-z或者A-Z)
            if (String.valueOf(c).matches("[\\u4E00-\\u9FA5]+")) {// 中文
                String[] t = PinyinHelper
                        .getUnformattedHanyuPinyinStringArray(c);
                temp[i] = new String[t.length];
                for (int j = 0; j < t.length; j++) {
                    // temp[i][j]=t[j].substring(0,1);//获取首字母
                    temp[i][j] = t[j];// //获取首字母，不需要再截取了
                }
            } else if (((int) c >= 65 && (int) c <= 90)
                    || ((int) c >= 97 && (int) c <= 122) || c >= 48 && c <= 57
                    || c == 42) {// a-zA-Z0-9*
                temp[i] = new String[]{String.valueOf(srcChar[i])};
            } else {
                temp[i] = new String[]{"null!"};
            }

        }
        return paiLie(temp);// 为了去掉重复项,直接返回Set
    }

    /**
     * 求2维数组所有排列组合情况 比如:{{1,2},{3},{4},{5,6}}共有2中排列,为:1345,1346,2345,2346
     */
    private Set<String> paiLie(String[][] str) {
        int max = 1;
        for (int i = 0; i < str.length; i++) {
            max *= str[i].length;
        }
        Set<String> result = new HashSet<String>();
        for (int i = 0; i < max; i++) {
            String s = "";
            int temp = 1; // 注意这个temp的用法。
            for (int j = 0; j < str.length; j++) {
                temp *= str[j].length;
                s += str[j][i / (max / temp) % str[j].length];
            }
            result.add(s);
        }

        return result;
    }

}
