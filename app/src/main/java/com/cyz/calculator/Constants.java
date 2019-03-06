package com.cyz.calculator;

import android.text.TextUtils;

import java.text.DecimalFormat;

/**
 * Calculator
 *
 * @author cyz
 * @date 2019/3/5
 */
public class Constants {
    /**
     * 3.0 -> 3，
     * 3.1 -> 3.1
     * @param d double
     * @return string
     */
    public static String doubleTrans(double d) {
        if (Math.round(d) - d == 0) {
            return String.valueOf((long) d);
        }
        return String.valueOf(d);
    }

    /**
     * 3.0 -> 3，
     * 3.1 -> 3.1
     * @param str string
     * @return string
     */
    public static String doubleTrans(String str) {
        if (TextUtils.isEmpty(str)) {
            return "0";
        }
        double d = string2Double(str);
        if (Math.round(d) - d == 0) {
            return String.valueOf((long) d);
        }
        return String.valueOf(d);
    }

    /**
     * string转double
     * @param str string
     * @return double
     */
    public static double string2Double(String str){
        if(TextUtils.isEmpty(str)){
            return 0d;
        }
        try {
            return Double.parseDouble(str.replaceAll(" ", ""));
        } catch (Exception e) {
            return  0d;
        }
    }
}
