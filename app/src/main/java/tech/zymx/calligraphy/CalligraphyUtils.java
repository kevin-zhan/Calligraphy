package tech.zymx.calligraphy;

import android.content.Context;
import android.content.res.Resources;

/**
 * Created by kevinzhan on 2018/1/23.
 */

public class CalligraphyUtils {
    public static int getDrawableID(Context context, String drawName) {
        Resources resources = context.getResources();
        return resources.getIdentifier(drawName, "drawable", context.getPackageName());
    }
}
