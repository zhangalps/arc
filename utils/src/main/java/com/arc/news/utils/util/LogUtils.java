package com.arc.news.utils.util;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by zhanghegang
 * date : 18-4-18
 * descript:LogUtils类
 */
public class LogUtils {
    private static final String TAG = "Debug";
    public static boolean isDebug = true;
    private static final String VALOG = "VALOG:";
    public static boolean isWirteLog = false;


    private static String logPath = null;//log日志存放路径

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS", Locale.US);//日期格式;
    private static SimpleDateFormat fileDteFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss-SSS", Locale.US);//日期格式;
    private static Date date = new Date();//因为log日志是使用日期命名的，使用静态成员变量主要是为了在整个程序运行期间只存在一个.log文件中;
    private static String fileName;

    /**
     * 初始化，须在使用之前设置，最好在Application创建时调用
     *
     * @param context
     */
    public static void init(Context context) {
        logPath = getFilePath(context) + "/Logs";//获得文件储存路径,在后面加"/Logs"建立子文件夹
        if (null == logPath) {
            Log.e(TAG, "logPath == null ，未初始化LogToFile");
            return;
        }

        //log日志名，使用时间命名，保证不重复
        fileName = logPath + "/log_" + fileDteFormat.format(date) + ".log";
        //如果父路径不存在
        File file = new File(logPath);
        if (!file.exists()) {
            file.mkdirs();//创建父路径
        }
    }

    /**
     * 获得文件存储路径
     *
     * @return
     */
    private static String getFilePath(Context context) {

        if (Environment.MEDIA_MOUNTED.equals(Environment.MEDIA_MOUNTED) || !Environment.isExternalStorageRemovable()) {//如果外部储存可用
            return context.getExternalFilesDir(null).getPath();//获得外部存储路径,默认路径为 /storage/emulated/0/Android/data/com.aitek.ai/files/Logs/
        } else {
            return context.getFilesDir().getPath();//直接存在/data/data里，非root手机是看不到的
        }
    }

    /**
     * 将log信息写入文件中
     *
     * @param type
     * @param tag
     * @param msg
     */
    private static void writeToFile(char type, String tag, String msg) {


        if (TextUtils.isEmpty(fileName) || !isWirteLog) return;
        String log = dateFormat.format(new Date()) + " " + type + " " + tag + " " + msg + "\n";//log日志内容，可以自行定制


        FileOutputStream fos = null;//FileOutputStream会自动调用底层的close()方法，不用关闭
        BufferedWriter bw = null;
        try {

            fos = new FileOutputStream(fileName, true);//这里的第二个参数代表追加还是覆盖，true为追加，flase为覆盖
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(log);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();//关闭缓冲流
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * 打印verbose日志信息
     *
     * @param tag
     * @param msg
     */
    public static void v(String tag, String msg) {
        if (isDebug)
            android.util.Log.v(VALOG + tag, msg);
        writeToFile('v', tag, msg);

    }

    public static void v(String tag, String msg, Throwable t) {
        if (isDebug)
            android.util.Log.v(VALOG + tag, msg, t);
        writeToFile('v', tag, msg);
    }

//    public static final void v(String content) {
//        v(TAG, content);
//    }

    /**
     * 打印debug日志信息
     *
     * @param tag
     * @param msg
     */
    public static void d(String tag, String msg) {
        if (isDebug)
            android.util.Log.d(VALOG + tag, msg);
        writeToFile('d', tag, msg);
    }

    public static void d(String tag, Exception msg) {
        if (isDebug)
            android.util.Log.d(VALOG + tag, msg.toString());
        writeToFile('d', tag, msg.toString());
    }

//    public static final void d(String content) {
//        d(TAG, content);
//    }

    public static void d(String tag, String msg, Throwable t) {
        if (isDebug)
            android.util.Log.d(VALOG + tag, msg, t);
        writeToFile('d', tag, msg);
    }

    /**
     * 打印info日志信息
     *
     * @param tag
     * @param msg
     */
    public static void i(String tag, String msg) {
        if (isDebug)
            android.util.Log.i(VALOG + tag, msg);
        writeToFile('i', tag, msg);
    }

    public static void i(String tag, String msg, Throwable t) {
        if (isDebug)
            android.util.Log.i(VALOG + tag, msg, t);
        writeToFile('i', tag, msg);
    }

//    public static final void i(String content) {
//        i(TAG, content);
//    }

    /**
     * 打印warn日志信息
     *
     * @param tag
     * @param msg
     */
    public static void w(String tag, String msg) {
        if (isDebug)
            android.util.Log.w(VALOG + tag, msg);
        writeToFile('i', tag, msg);
    }

    public static void w(String tag, Exception e) {
        if (isDebug)
            android.util.Log.w(VALOG + tag, e.toString());
        writeToFile('i', tag, e.toString());
    }

    public static void w(String tag, String msg, Throwable t) {
        if (isDebug)
            android.util.Log.w(VALOG + tag, msg, t);
        writeToFile('i', tag, msg);
    }

    /**
     * 打印error日志信息
     *
     * @param tag
     * @param msg
     */
    public static void e(String tag, String msg) {
        if (isDebug)
            android.util.Log.e(VALOG + tag, msg);
        writeToFile('i', tag, msg);
    }

    public static void e(String tag, Exception msg) {
        if (isDebug)
            android.util.Log.e(VALOG + tag, msg.toString());
        writeToFile('i', tag, msg.toString());
    }

    public static void e(String tag, String msg, Throwable t) {
        if (isDebug)
            android.util.Log.e(VALOG + tag, msg, t);
        writeToFile('i', tag, msg);
    }

}
