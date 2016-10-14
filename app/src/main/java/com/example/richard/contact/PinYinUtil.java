package com.example.richard.contact;


import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;



public class PinYinUtil {
    /**
     * change chinese to pinyin
     */
    public static String getPingYin(String inputString) {

        String inputLetter = inputString.substring(0, 1).toUpperCase();
        if (inputLetter.matches("[A-Z]")) {
            return inputLetter;
        }

        HanyuPinyinOutputFormat pinyinFormat = new HanyuPinyinOutputFormat();
        pinyinFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        pinyinFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        pinyinFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
        char[] inputChars = inputString.trim().toCharArray();

        String outputString = "";
        try {
            for (int i = 0; i < inputChars.length; i++) {
                if (Character.toString(inputChars[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] tempString = PinyinHelper.toHanyuPinyinStringArray(inputChars[i], pinyinFormat);
                    outputString += tempString[0];
                } else
                    outputString += java.lang.Character.toString(inputChars[i]);
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return outputString.toUpperCase();
    }
}
