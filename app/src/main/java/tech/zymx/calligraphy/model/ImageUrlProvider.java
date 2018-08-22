package tech.zymx.calligraphy.model;

/**
 * Created by kevinzhan@tencent.com on 2018/8/21
 */
public interface ImageUrlProvider {
    String getImageUrl(int index);
    int getImageCount();
}
