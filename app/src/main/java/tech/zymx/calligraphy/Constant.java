package tech.zymx.calligraphy;

import tech.zymx.calligraphy.model.DbtbImageModel;
import tech.zymx.calligraphy.model.ImageUrlProvider;
import tech.zymx.calligraphy.model.JcglqImageModel;

/**
 * Created by kevinzhan on 2018/1/25.
 */

public class Constant {
    public final static String CONTENT_SEPARATOR = "_";
    public final static String TYPE_SEPARATOR = "@";

    public final static String INTEGER_LIST_PREFIX = "integer_list";

    public final static String IMAGE_URL = "image_url";

    //进入练字状态时间阈值
    public final static long EFFECTIVE_TIME = 1000 * 5;

    public final static int INIT_RADIUS = 60;

    public final static ImageUrlProvider[] URL_PROVIDERS = {DbtbImageModel.getInstance(), JcglqImageModel.getInstance()};
}
