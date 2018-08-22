package tech.zymx.calligraphy.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tech.zymx.calligraphy.Constant;
import tech.zymx.calligraphy.GlideApp;
import tech.zymx.calligraphy.R;

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
            // do nothing
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

    public static void setImageUrl(final ImageView imageView, String url, Context context) {
        GlideApp.with(context)
                .asBitmap()
                .load(url)
                .placeholder(R.drawable.loading_pic)
                .centerCrop()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onLoadStarted(Drawable placeholder) {
                        imageView.setImageDrawable(placeholder);
                    }

                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        imageView.setImageBitmap(resource);
                    }
                });
    }

}
