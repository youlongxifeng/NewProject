package com.company.project.android.utils;

import android.text.TextUtils;

import java.util.List;

/**
 * Created by xiexie on 2018/9/16.
 */

public class CommonUtil {
    public static boolean isEmpty(List sourceList) {
        return (sourceList == null || sourceList.size() == 0);
    }

    public static boolean isEmpty(String content) {
        return TextUtils.isEmpty(content) || "null".equals(content);
    }

}
