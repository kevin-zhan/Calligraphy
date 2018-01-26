package tech.zymx.calligraphy;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by kevinzhan on 2018/1/23.
 */

public class CalligraphyUtils {
    public static int getDrawableID(Context context, String drawName) {
        Resources resources = context.getResources();
        return resources.getIdentifier(drawName, "drawable", context.getPackageName());
    }

    public static String convertIntegerListToString(List<Integer> integerList) {
        if (integerList == null || integerList.size() == 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Constant.INTEGER_LIST_PREFIX + Constant.TYPE_SEPARATOR);
        for (int f : integerList) {
            stringBuilder.append(f);
            stringBuilder.append(Constant.CONTENT_SEPARATOR);
        }
        return stringBuilder.toString();
    }

    @NonNull
    public static List<Integer> parseStringToIntegerList(String integerStr) {
        if (TextUtils.isEmpty(integerStr)) {
            return new ArrayList<>();
        }
        if (!isLegalIntegerString(integerStr)) {
            return new ArrayList<>();
        }
        String realIntegerListStr = integerStr.split(Constant.TYPE_SEPARATOR)[1];
        String[] integerStrArray = realIntegerListStr.split(Constant.CONTENT_SEPARATOR);
        ArrayList<Integer> resultList = new ArrayList<>();
        try {
            for (String str : integerStrArray) {
                resultList.add(Integer.valueOf(str));
            }
        } catch (Exception e) {
            //todo 可能由于版本原因出现解析失败，先try-catch一下，之后可以去除
            return new ArrayList<>();
        }
        return resultList;
    }

    private static boolean isLegalIntegerString(String integerStr) {
        if (TextUtils.isEmpty(integerStr)) {
            return false;
        }
        String[] element = integerStr.split(Constant.TYPE_SEPARATOR);
        return element[0].equals(Constant.INTEGER_LIST_PREFIX);
    }

    public static long getTimeStamp() {
        Date date = new Date();
        return date.getTime();
    }

}
