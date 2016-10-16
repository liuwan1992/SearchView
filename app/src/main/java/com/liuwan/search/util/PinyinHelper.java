package com.liuwan.search.util;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PinyinHelper {
    private static PinyinHelper instance;
    private Properties properties = null;

    public static String[] getUnformattedHanyuPinyinStringArray(char ch) {
        return getInstance().getHanyuPinyinStringArray(ch);
    }

    private PinyinHelper() {
        initResource();
    }

    public static PinyinHelper getInstance() {
        if (instance == null) {
            instance = new PinyinHelper();
        }
        return instance;
    }

    private void initResource() {
        try {
//        	final String resourceName = "/assets/unicode_to_hanyu_pinyin.txt";
            final String resourceName = "/assets/unicode_to_simple_pinyin.txt";

            BufferedInputStream bis = new BufferedInputStream(PinyinHelper.class.getResourceAsStream(resourceName));
            properties = new Properties();
            properties.load(bis);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String[] getHanyuPinyinStringArray(char ch) {
        String pinyinRecord = getHanyuPinyinRecordFromChar(ch);

        if (null != pinyinRecord) {
            int indexOfLeftBracket = pinyinRecord.indexOf(Field.LEFT_BRACKET);
            int indexOfRightBracket = pinyinRecord.lastIndexOf(Field.RIGHT_BRACKET);

            String stripedString = pinyinRecord.substring(indexOfLeftBracket
                    + Field.LEFT_BRACKET.length(), indexOfRightBracket);

            return stripedString.split(Field.COMMA);

        } else
            return null;

    }

    private String getHanyuPinyinRecordFromChar(char ch) {
        int codePointOfChar = ch;
        String codepointHexStr = Integer.toHexString(codePointOfChar).toUpperCase();
        String foundRecord = properties.getProperty(codepointHexStr);
        return foundRecord;
    }

    class Field {
        static final String LEFT_BRACKET = "(";
        static final String RIGHT_BRACKET = ")";
        static final String COMMA = ",";
    }

}
