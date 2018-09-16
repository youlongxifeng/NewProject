package com.company.project.android.utils;


import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * @author Administrator
 * @name MyMvp
 * @class name：com.company.project.android.utils
 * @class describe
 * @time 2018/2/8 17:07
 * @change
 * @class describe
 */

public class LogUtils {

    private static String sTag = "LogUtils";
    private static boolean sIsDebug = true;
    private static boolean sIsTrace = true;

    /**
     * 配置 LogUtil
     *
     * @param tag     默认 tag
     * @param isDebug 是否显示
     * @param isTrace 是否显示详细
     */
    public static void init(String tag, boolean isDebug, boolean isTrace) {
        if (tag != null) {
            sTag = tag;
        }
        sIsDebug = isDebug;
        sIsTrace = isTrace;

    }

    /**
     * @param msg
     */
    public static void i(String msg) {
        if (sIsDebug) {
            StackTraceElement[] list = Thread.currentThread().getStackTrace();
            StringBuilder sb = new StringBuilder();
            if (sIsTrace) {
                sb.append("[");
                if (list.length > 3) {
                    sb.append(list[3].getFileName() + "_" + list[3].getMethodName() + "_" + list[3].getLineNumber() + "_");
                }
                if (list.length > 4) {
                    sb.append(list[4].getFileName() + "_" + list[4].getMethodName() + "_" + list[4].getLineNumber() + "_");
                }
                if (list.length > 5) {
                    sb.append(list[5].getFileName() + "_" + list[5].getMethodName() + "_" + list[5].getLineNumber());
                }
                sb.append("] ");
            }

            Log.i(sTag, sb.toString() + msg);
        }
    }

    public static void i(String tag, String msg) {
        if (sIsDebug) {
            StackTraceElement[] list = Thread.currentThread().getStackTrace();
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            if (list.length > 3) {
                sb.append(list[3].getFileName() + "#" + list[3].getMethodName() + "(" + list[3].getLineNumber() + ")_");
            }
            if (list.length > 4) {
                sb.append(list[4].getFileName() + "#" + list[4].getMethodName() + "(" + list[4].getLineNumber() + ")_");
            }
            if (list.length > 5) {
                sb.append(list[5].getFileName() + "#" + list[5].getMethodName() + "(" + list[5].getLineNumber() + ")");
            }
            sb.append("]");
            Log.i(sTag + "_" + tag, sb.toString() + msg);
        }
    }

    public static void w(String msg) {
        if (sIsDebug) {
            StackTraceElement[] list = Thread.currentThread().getStackTrace();
            StringBuilder sb = new StringBuilder();
            if (sIsTrace) {
                sb.append("[");
                if (list.length > 3) {
                    sb.append(list[3].getFileName() + "_" + list[3].getMethodName() + "_" + list[3].getLineNumber() + "_");
                }
                if (list.length > 4) {
                    sb.append(list[4].getFileName() + "_" + list[4].getMethodName() + "_" + list[4].getLineNumber() + "_");
                }
                if (list.length > 5) {
                    sb.append(list[5].getFileName() + "_" + list[5].getMethodName() + "_" + list[5].getLineNumber());
                }
                sb.append("] ");
            }

            Log.w(sTag, sb.toString() + msg);
        }
    }

    public static void w(String tag, String msg) {
        if (sIsDebug) {
            StackTraceElement[] list = Thread.currentThread().getStackTrace();
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            if (list.length > 3) {
                sb.append(list[3].getFileName() + "#" + list[3].getMethodName() + "(" + list[3].getLineNumber() + ")_");
            }
            if (list.length > 4) {
                sb.append(list[4].getFileName() + "#" + list[4].getMethodName() + "(" + list[4].getLineNumber() + ")_");
            }
            if (list.length > 5) {
                sb.append(list[5].getFileName() + "#" + list[5].getMethodName() + "(" + list[5].getLineNumber() + ")");
            }
            sb.append("]");
            Log.w(sTag + "_" + tag, sb.toString() + msg);
        }
    }

    public static void e(String msg) {
        if (sIsDebug) {
            StackTraceElement[] list = Thread.currentThread().getStackTrace();
            StringBuilder sb = new StringBuilder();
            if (sIsTrace) {
                sb.append("[");
                if (list.length > 3) {
                    sb.append(list[3].getFileName() + "_" + list[3].getMethodName() + "_" + list[3].getLineNumber() + "_");
                }
                if (list.length > 4) {
                    sb.append(list[4].getFileName() + "_" + list[4].getMethodName() + "_" + list[4].getLineNumber() + "_");
                }
                if (list.length > 5) {
                    sb.append(list[5].getFileName() + "_" + list[5].getMethodName() + "_" + list[5].getLineNumber());
                }
                sb.append("] ");
            }
            Log.e(sTag, sb.toString() + msg);
        }
    }

    public static void e(String tag, String msg) {
        if (sIsDebug) {
            StackTraceElement[] list = Thread.currentThread().getStackTrace();
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            if (list.length > 3) {
                sb.append(list[3].getFileName() + "#" + list[3].getMethodName() + "(" + list[3].getLineNumber() + ")_");
            }
            if (list.length > 4) {
                sb.append(list[4].getFileName() + "#" + list[4].getMethodName() + "(" + list[4].getLineNumber() + ")_");
            }
            if (list.length > 5) {
                sb.append(list[5].getFileName() + "#" + list[5].getMethodName() + "(" + list[5].getLineNumber() + ")");
            }
            sb.append("]");
            Log.e(sTag + "_" + tag, sb.toString() + msg);
        }
    }


    public static void print(String msg) {
        StackTraceElement[] list = Thread.currentThread().getStackTrace();
        StringBuilder sb = new StringBuilder();
        if (sIsTrace) {
            sb.append("[");
            if (list.length > 3) {
                sb.append(list[3].getFileName() + "#" + list[3].getMethodName() + "(" + list[3].getLineNumber() + ")");
            }
            if (list.length > 4) {
                sb.append(list[4].getFileName() + "#" + list[4].getMethodName() + "(" + list[4].getLineNumber() + ")");
            }
            if (list.length > 5) {
                sb.append(list[5].getFileName() + "#" + list[5].getMethodName() + "(" + list[5].getLineNumber() + ")");
            }
            sb.append("] ");
        }

        Log.w(sTag + "_print", sb.toString() + msg);
    }

    public static void i(String tag, String msg, boolean isDebug) {
        if (isDebug && sIsDebug) {
            Log.i(tag, msg);
        }
    }

    /**
     * 展示json数据
     * @param tag
     * @param msg
     */
    public static void json(String tag, String msg) {
        if(sIsTrace) {
            if(TextUtils.isEmpty(msg)) {
                Log.i(tag, "msg is null.");
                return;
            }

            try {
                String message;
                String[] lines;
                String[] var5;
                int var6;
                int var7;
                String line;
                if(msg.startsWith("{")) {
                    JSONObject jsonObject = new JSONObject(msg);
                    message = jsonObject.toString(4);
                    lines = message.split(System.getProperty("line.separator"));//是换行符,功能和"\n"是一致的,但是此种写法屏蔽了 Windows和Linux的区别 ，更保险一些.
                    Log.i(tag, "┌───────────────────────────────────────────────────────────────────────────────────────");
                    var5 = lines;
                    var6 = lines.length;

                    for(var7 = 0; var7 < var6; ++var7) {
                        line = var5[var7];
                        Log.i(tag, "│ " + line);
                    }

                    Log.i(tag, "└───────────────────────────────────────────────────────────────────────────────────────");
                    return;
                }

                if(msg.startsWith("[")) {
                    JSONArray jsonArray = new JSONArray(msg);
                    message = jsonArray.toString(4);
                    lines = message.split(System.getProperty("line.separator"));
                    Log.i(tag, "┌───────────────────────────────────────────────────────────────────────────────────────");
                    var5 = lines;
                    var6 = lines.length;

                    for(var7 = 0; var7 < var6; ++var7) {
                        line = var5[var7];
                        Log.i(tag, "│ " + line);
                    }

                    Log.i(tag, "└───────────────────────────────────────────────────────────────────────────────────────");
                }
            } catch (JSONException var9) {
                Log.e(tag, var9.getCause().getMessage() + "\n" + msg);
            }
        }

    }

    /**
     * 保存日志文件
     * @param tag
     * @param msg
     */
    public static void saveLog(String tag, String msg) {
        if (sIsDebug) {
            StackTraceElement[] list = Thread.currentThread().getStackTrace();
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            if (list.length > 3) {
                sb.append(list[3].getFileName() + "#" + list[3].getMethodName() + "(" + list[3].getLineNumber() + ")_");
            }
            if (list.length > 4) {
                sb.append(list[4].getFileName() + "#" + list[4].getMethodName() + "(" + list[4].getLineNumber() + ")_");
            }
            if (list.length > 5) {
                sb.append(list[5].getFileName() + "#" + list[5].getMethodName() + "(" + list[5].getLineNumber() + ")");
            }
            sb.append("]");
            String line = sTag + "_" + tag + "/t" + sb.toString() + msg;
            //   createFolderIfNotExist("log");
            //   writeFileToSD(Environment.getExternalStorageDirectory().getPath() + "/doorduad/log/", line);
        }
    }
}
