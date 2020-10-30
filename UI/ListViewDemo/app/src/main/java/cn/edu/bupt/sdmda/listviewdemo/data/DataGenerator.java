package cn.edu.bupt.sdmda.listviewdemo.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataGenerator {
    public static List<String> genStrList(int n) {
        List<String> ret = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            ret.add("item in list: " + i);
        }
        return ret;
    }

    public static List<Map<String, Object>> genMapList(int n) {
        List<Map<String, Object>> ret = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            Map<String, Object> item = new HashMap<>();
            item.put("icon", android.R.drawable.btn_star);
            item.put("title", "Title of " + i);
            item.put("content", "Content of " + i);
            ret.add(item);
        }
        return ret;
    }

    public static List<News> genNews(int n) {
        List<News> ret = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            News news = new News();
            news.icon_id = android.R.drawable.btn_star;
            news.title = "Title of " + i;
            news.content = "Content of " + i;
            ret.add(news);
        }
        return ret;
    }
}
