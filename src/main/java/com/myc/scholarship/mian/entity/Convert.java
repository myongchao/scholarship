package com.myc.scholarship.mian.entity;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Set;

/**
 * Copyright (C), 2018 - 2019, ZhengZhouChuangZhi. Co., Ltd.
 * FileName: [ 文件名 ]
 * 类的详细说明
 *
 * @author 马勇超
 * @version 1.0
 * @date 2019/1/10$ 10:20$
 */
public class Convert {
    private Convert() {
    }

//    public static Double toDouble(Object value, Double defaultValue) {
//        if (value == null) {
//            return defaultValue;
//        } else if (value instanceof Double) {
//            return (Double)value;
//        } else if (value instanceof Number) {
//            return ((Number)value).doubleValue();
//        } else {
//            String valueStr = toStr(value, (String)null);
//            if (StrKit.isBlank(valueStr)) {
//                return defaultValue;
//            } else {
//                try {
//                    return (new BigDecimal(valueStr.trim())).doubleValue();
//                } catch (Exception var4) {
//                    return defaultValue;
//                }
//            }
//        }
//    }

//    public static Double toDouble(Object value) {
//        return toDouble(value, (Double)null);
//    }
//
//    public static Double[] toDoubleArray(boolean isIgnoreConvertError, Object... values) {
//        if (CollectionKit.isEmpty(values)) {
//            return new Double[0];
//        } else {
//            Double[] doubles = new Double[values.length];
//
//            for(int i = 0; i < values.length; ++i) {
//                Double v = toDouble(values[i], (Double)null);
//                if (null == v && !isIgnoreConvertError) {
//                    throw new ToolBoxException(StrKit.format("Convert [{}] to Double error!", new Object[]{values[i]}));
//                }
//
//                doubles[i] = v;
//            }
//
//            return doubles;
//        }
//    }

//    public static Float toFloat(Object value, Float defaultValue) {
//        if (value == null) {
//            return defaultValue;
//        } else if (value instanceof Float) {
//            return (Float)value;
//        } else if (value instanceof Number) {
//            return ((Number)value).floatValue();
//        } else {
//            String valueStr = toStr(value, (String)null);
//            if (StrKit.isBlank(valueStr)) {
//                return defaultValue;
//            } else {
//                try {
//                    return Float.parseFloat(valueStr.trim());
//                } catch (Exception var4) {
//                    return defaultValue;
//                }
//            }
//        }
//    }

//    public static Float toFloat(Object value) {
//        return toFloat(value, (Float)null);
//    }
//
//    public static <T> Float[] toFloatArray(boolean isIgnoreConvertError, Object... values) {
//        if (CollectionKit.isEmpty(values)) {
//            return new Float[0];
//        } else {
//            Float[] floats = new Float[values.length];
//
//            for(int i = 0; i < values.length; ++i) {
//                Float v = toFloat(values[i], (Float)null);
//                if (null == v && !isIgnoreConvertError) {
//                    throw new ToolBoxException(StrKit.format("Convert [{}] to Float error!", new Object[]{values[i]}));
//                }
//
//                floats[i] = v;
//            }
//
//            return floats;
//        }
//    }

//    public static Boolean toBool(Object value, Boolean defaultValue) {
//        if (value == null) {
//            return defaultValue;
//        } else if (value instanceof Boolean) {
//            return (Boolean)value;
//        } else {
//            String valueStr = toStr(value, (String)null);
//            if (StrKit.isBlank(valueStr)) {
//                return defaultValue;
//            } else {
//                valueStr = valueStr.trim().toLowerCase();
//                byte var4 = -1;
//                switch(valueStr.hashCode()) {
//                    case 48:
//                        if (valueStr.equals("0")) {
//                            var4 = 6;
//                        }
//                        break;
//                    case 49:
//                        if (valueStr.equals("1")) {
//                            var4 = 5;
//                        }
//                        break;
//                    case 3521:
//                        if (valueStr.equals("no")) {
//                            var4 = 4;
//                        }
//                        break;
//                    case 3548:
//                        if (valueStr.equals("ok")) {
//                            var4 = 3;
//                        }
//                        break;
//                    case 119527:
//                        if (valueStr.equals("yes")) {
//                            var4 = 2;
//                        }
//                        break;
//                    case 3569038:
//                        if (valueStr.equals("true")) {
//                            var4 = 0;
//                        }
//                        break;
//                    case 97196323:
//                        if (valueStr.equals("false")) {
//                            var4 = 1;
//                        }
//                }
//
//                switch(var4) {
//                    case 0:
//                        return true;
//                    case 1:
//                        return false;
//                    case 2:
//                        return true;
//                    case 3:
//                        return true;
//                    case 4:
//                        return false;
//                    case 5:
//                        return true;
//                    case 6:
//                        return false;
//                    default:
//                        return defaultValue;
//                }
//            }
//        }
//    }
//
//    public static Boolean toBool(Object value) {
//        return toBool(value, (Boolean)null);
//    }

//    public static Boolean[] toBooleanArray(boolean isIgnoreConvertError, Object... values) {
//        if (CollectionKit.isEmpty(values)) {
//            return new Boolean[0];
//        } else {
//            Boolean[] bools = new Boolean[values.length];
//
//            for(int i = 0; i < values.length; ++i) {
//                Boolean v = toBool(values[i], (Boolean)null);
//                if (null == v && !isIgnoreConvertError) {
//                    throw new ToolBoxException(StrKit.format("Convert [{}] to Boolean error!", new Object[]{values[i]}));
//                }
//
//                bools[i] = v;
//            }
//
//            return bools;
//        }
//    }

//    public static <E extends Enum<E>> E toEnum(Class<E> clazz, Object value, E defaultValue) {
//        if (value == null) {
//            return defaultValue;
//        } else if (clazz.isAssignableFrom(value.getClass())) {
//            E myE = (Enum)value;
//            return myE;
//        } else {
//            String valueStr = toStr(value, (String)null);
//            if (StrKit.isBlank(valueStr)) {
//                return defaultValue;
//            } else {
//                try {
//                    return Enum.valueOf(clazz, valueStr);
//                } catch (Exception var5) {
//                    return defaultValue;
//                }
//            }
//        }
//    }

//    public static <E extends Enum<E>> E toEnum(Class<E> clazz, Object value) {
//        return toEnum(clazz, value, (E) null);
//    }

//    public static BigInteger toBigInteger(Object value, BigInteger defaultValue) {
//        if (value == null) {
//            return defaultValue;
//        } else if (value instanceof BigInteger) {
//            return (BigInteger)value;
//        } else if (value instanceof Long) {
//            return BigInteger.valueOf((Long)value);
//        } else {
//            String valueStr = toStr(value, (String)null);
//            if (StrKit.isBlank(valueStr)) {
//                return defaultValue;
//            } else {
//                try {
//                    return new BigInteger(valueStr);
//                } catch (Exception var4) {
//                    return defaultValue;
//                }
//            }
//        }
//    }

//    public static BigInteger toBigInteger(Object value) {
//        return toBigInteger(value, (BigInteger)null);
//    }

//    public static BigDecimal toBigDecimal(Object value, BigDecimal defaultValue) {
//        if (value == null) {
//            return defaultValue;
//        } else if (value instanceof BigDecimal) {
//            return (BigDecimal)value;
//        } else if (value instanceof Long) {
//            return new BigDecimal((Long)value);
//        } else if (value instanceof Double) {
//            return new BigDecimal((Double)value);
//        } else if (value instanceof Integer) {
//            return new BigDecimal((Integer)value);
//        } else {
//            String valueStr = toStr(value, (String)null);
//            if (StrKit.isBlank(valueStr)) {
//                return defaultValue;
//            } else {
//                try {
//                    return new BigDecimal(valueStr);
//                } catch (Exception var4) {
//                    return defaultValue;
//                }
//            }
//        }
//    }

//    public static BigDecimal toBigDecimal(Object value) {
//        return toBigDecimal(value, (BigDecimal)null);
//    }

//    public static String toSBC(String input) {
//        return toSBC(input, (Set)null);
//    }
//
//    public static String toSBC(String input, Set<Character> notConvertSet) {
//        char[] c = input.toCharArray();
//
//        for(int i = 0; i < c.length; ++i) {
//            if (null == notConvertSet || !notConvertSet.contains(c[i])) {
//                if (c[i] == ' ') {
//                    c[i] = 12288;
//                } else if (c[i] < 127) {
//                    c[i] += 'ﻠ';
//                }
//            }
//        }
//
//        return new String(c);
//    }
//
//    public static String toDBC(String input) {
//        return toDBC(input, (Set)null);
//    }
//
//    public static String toDBC(String text, Set<Character> notConvertSet) {
//        char[] c = text.toCharArray();
//
//        for(int i = 0; i < c.length; ++i) {
//            if (null == notConvertSet || !notConvertSet.contains(c[i])) {
//                if (c[i] == 12288) {
//                    c[i] = ' ';
//                } else if (c[i] > '\uff00' && c[i] < '｟') {
//                    c[i] -= 'ﻠ';
//                }
//            }
//        }
//
//        String returnString = new String(c);
//        return returnString;
//    }

//    public static String toHex(String str) {
//        return HexKit.encodeHexStr(str.getBytes());
//    }
//
//    public static String toHex(byte[] bytes) {
//        return HexKit.encodeHexStr(bytes);
//    }
//
//    public static byte[] hexToBytes(String src) {
//        return HexKit.decodeHex(src.toCharArray());
//    }
//
//    public static String hexStrToStr(String hexStr, Charset charset) {
//        return HexKit.decodeHexStr(hexStr, charset);
//    }

//    public static String strToUnicode(String strText) throws Exception {
//        StringBuilder str = new StringBuilder();
//
//        for(int i = 0; i < strText.length(); ++i) {
//            char c = strText.charAt(i);
//            String strHex = Integer.toHexString(c);
//            if (c > 128) {
//                str.append("\\u" + strHex);
//            } else {
//                str.append("\\u00" + strHex);
//            }
//        }
//
//        return str.toString();
//    }
//
//    public static String unicodeToStr(String hex) {
//        int t = hex.length() / 6;
//        StringBuilder str = new StringBuilder();
//
//        for(int i = 0; i < t; ++i) {
//            String s = hex.substring(i * 6, (i + 1) * 6);
//            String s1 = s.substring(2, 4) + "00";
//            String s2 = s.substring(4);
//            int n = Integer.valueOf(s1, 16) + Integer.valueOf(s2, 16);
//            char[] chars = Character.toChars(n);
//            str.append(new String(chars));
//        }
//
//        return str.toString();
//    }

//    public static String convertCharset(String str, String sourceCharset, String destCharset) {
//        if (StrKit.hasBlank(new String[]{str, sourceCharset, destCharset})) {
//            return str;
//        } else {
//            try {
//                return new String(str.getBytes(sourceCharset), destCharset);
//            } catch (UnsupportedEncodingException var4) {
//                return str;
//            }
//        }
//    }

//    public static String digitUppercase(double n) {
//        String[] fraction = new String[]{"角", "分"};
//        String[] digit = new String[]{"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
//        String[][] unit = new String[][]{{"元", "万", "亿"}, {"", "拾", "佰", "仟"}};
//        String head = n < 0.0D ? "负" : "";
//        n = Math.abs(n);
//        String s = "";
//
//        int integerPart;
//        for(integerPart = 0; integerPart < fraction.length; ++integerPart) {
//            s = s + (digit[(int)(Math.floor(n * 10.0D * Math.pow(10.0D, (double)integerPart)) % 10.0D)] + fraction[integerPart]).replaceAll("(零.)+", "");
//        }
//
//        if (s.length() < 1) {
//            s = "整";
//        }
//
//        integerPart = (int)Math.floor(n);
//
//        for(int i = 0; i < unit[0].length && integerPart > 0; ++i) {
//            String p = "";
//
//            for(int j = 0; j < unit[1].length && n > 0.0D; ++j) {
//                p = digit[integerPart % 10] + unit[1][j] + p;
//                integerPart /= 10;
//            }
//
//            s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i] + s;
//        }
//
//        return head + s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "").replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");
//    }
//
    public static String humpToUnderline(String source) {
        StringBuilder sb = new StringBuilder(source);
        int temp = 0;

        for(int i = 0; i < source.length(); ++i) {
            if (Character.isUpperCase(source.charAt(i))) {
                sb.insert(i + temp, "_");
                ++temp;
            }
        }

        return sb.toString().toLowerCase();
    }

    public static String underlineToHump(String para) {
        StringBuilder result = new StringBuilder();
        String[] a = para.split("_");
        String[] var3 = a;
        int var4 = a.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            String s = var3[var5];
            if (result.length() == 0) {
                result.append(s.toLowerCase());
            } else {
                result.append(s.substring(0, 1).toUpperCase());
                result.append(s.substring(1).toLowerCase());
            }
        }

        return result.toString();
    }
}
