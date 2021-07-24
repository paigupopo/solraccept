package com.solraccept.solraccept.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    /**
     * 获得文章id
     * @param url
     * @return
     */
    public static String getJournalarticleId(String url) {
        String strs[] = url.split("/");
        return strs[strs.length - 1].split("\\.")[0];
    }

    public static List<String> getImg(String s) {
        String regex;
        List<String> list = new ArrayList<String>();
        regex = "src=\"(.*?)\"";
        Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
        Matcher ma = pa.matcher(s);
        while (ma.find()) {
            list.add(ma.group());
        }
        return list;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
}
