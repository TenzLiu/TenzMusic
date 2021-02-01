package com.tenz.tenzmusic.util;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Author: fwp
 * Time: 2019/4/19
 * Desc: Log日志打印工具类
 */

public class LogUtil {

    public static boolean sDebug = false;

    /**
     * 初始化
     * @param debug
     */
    public static void init(boolean debug){
        sDebug = debug;
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    /**
     * 打印日志(Verbose)
     *
     * @param msg 内容
     */
    public static void v(String msg) {
        if (sDebug) {
            Logger.v(msg);
        }
    }

    /**
     * 打印日志(Debug)
     *
     * @param msg 内容
     */
    public static void d(String msg) {
        if (sDebug) {
            Logger.d(msg);
        }
    }


    /**
     * 打印日志(Info)
     *
     * @param msg 内容
     */
    public static void i(String msg) {
        if (sDebug) {
            Logger.i(msg);
        }
    }

    /**
     * 打印日志(Warm)
     *
     * @param msg 内容
     */
    public static void w(String msg) {
        if (sDebug) {
            Logger.w(msg);
        }
    }

    /**
     * 打印日志(wtf)
     *
     * @param msg 内容
     */
    public static void wtf(String msg) {
        if (sDebug) {
            Logger.wtf(msg);
        }
    }


    /**
     * 打印日志(Error)
     *
     * @param msg 内容
     */
    public static void e(String msg) {
        if (sDebug) {
            Logger.e(msg);
        }
    }

    /**
     * 打印日志(Error)
     *
     * @param throwable
     */
    public static void e(Throwable throwable) {
        if (sDebug) {
            Logger.e(throwable, "");
        }
    }


    /**
     * 打印日志(Erro)
     *
     * @param msg       内容
     * @param throwable
     */
    public static void e(String msg, Throwable throwable) {
        if (sDebug) {
            Logger.e(throwable, msg);
        }
    }

    /**
     * 打印日志(json)
     *
     * @param msg 内容
     */
    public static void json(String msg) {
        if (sDebug) {
            Logger.json(msg);
        }
    }

    /**
     * 打印日志(Error)
     *
     * @param object 对象
     */
    public static void d(Object object) {
        if (sDebug) {
            //Logger.d(JsonUtil.object2Json(object));
        }
    }

    /**
     * 写入文件
     *
     * @param msg
     */
//    public static void logFile(String msg) {
//        File logFile = new File(Constant.FILE_LOG);
//        if (!logFile.exists()) {
//            logFile.mkdirs();
//        }
//        SimpleDateFormat sdfTimerDate = new SimpleDateFormat("yyyy-MM-dd");
//        SimpleDateFormat sdfTimer = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
//
//        FileUtils.deleteOldFile(3, logFile.getPath());
//        String filePath = logFile + String.format("/%sLog.txt", sdfTimerDate.format(new Date()));
//        msg = sdfTimer.format(new Date()) + "------" + msg + "\r\n\r\n";
//        FileUtils.writeFile(msg, filePath, true);
//    }

}
