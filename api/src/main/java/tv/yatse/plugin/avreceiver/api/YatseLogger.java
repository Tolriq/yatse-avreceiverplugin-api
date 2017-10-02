/*
 * Copyright 2015 Tolriq / Genimee.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package tv.yatse.plugin.avreceiver.api;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;

/**
 * Utility class that allows plugins to push logs to main Yatse log system.
 * <p/>
 * This allow central logging and easier debugging for users.
 * <p/>
 * Log methods support formatting of the message : <br/><i>YatseLogger.getInstance(context).logVerbose("TAG", "Message with param : %s", string);</i>
 */
public final class YatseLogger {

    private static final String EXTRA_STRING_PARAMS = "org.leetzone.android.yatsewidget.EXTRA_STRING_PARAMS";
    private static final String EXTRA_STRING_PARAMS2 = "org.leetzone.android.yatsewidget.EXTRA_STRING_PARAMS2";

    private volatile static YatseLogger INSTANCE;
    private Context mContext;

    protected YatseLogger(Context context) {
        mContext = context;
    }

    /**
     * Gets the logger instance.
     *
     * @param context the application context
     * @return the instance
     */
    public static YatseLogger getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (YatseLogger.class) {
                if (INSTANCE == null) {
                    INSTANCE = new YatseLogger(context);
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Log a message with an error level.
     *
     * @param tag     the tag
     * @param message the message
     * @param args    the args
     */
    public void logError(String tag, String message, Object... args) {
        String msg = getMessage(message, args);
        Intent srv = new Intent("org.leetzone.android.yatsewidget.ACTION_LOG_ERROR");
        srv.setPackage("org.leetzone.android.yatsewidgetfree");
        srv.putExtra(EXTRA_STRING_PARAMS2, tag);
        srv.putExtra(EXTRA_STRING_PARAMS, msg);
        try {
            ContextCompat.startForegroundService(mContext, srv);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Log an exception at error level
     *
     * @param tag     the tag
     * @param message the message
     * @param tr      the Throwable
     */
    public void logError(String tag, String message, Throwable tr) {
        String msg = getMessage(message);
        msg += "\n" + getStackTraceString(tr);
        Intent srv = new Intent("org.leetzone.android.yatsewidget.ACTION_LOG_ERROR");
        srv.setPackage("org.leetzone.android.yatsewidgetfree");
        srv.putExtra(EXTRA_STRING_PARAMS2, tag);
        srv.putExtra(EXTRA_STRING_PARAMS, msg);
        try {
            ContextCompat.startForegroundService(mContext, srv);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Log a message with an verbose level.
     * <p/>
     * By default Yatse does not log verbose level messages and needs to have debug activated in advanced settings.
     *
     * @param tag     the tag
     * @param message the message
     * @param args    the args
     */
    public void logVerbose(String tag, String message, Object... args) {
        String msg = getMessage(message, args);
        Intent srv = new Intent("org.leetzone.android.yatsewidget.ACTION_LOG_VERBOSE");
        srv.setPackage("org.leetzone.android.yatsewidgetfree");
        srv.putExtra(EXTRA_STRING_PARAMS2, tag);
        srv.putExtra(EXTRA_STRING_PARAMS, msg);
        try {
            ContextCompat.startForegroundService(mContext, srv);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getStackTraceString(Throwable tr) {
        if (tr == null) {
            return "";
        }

        Throwable t = tr;
        while (t != null) {
            if (t instanceof UnknownHostException) {
                return "";
            }
            t = t.getCause();
        }

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        tr.printStackTrace(pw);
        return sw.toString();
    }

    private static String getSimpleClassName(String className) {
        int i = className.lastIndexOf(".");
        if (i == -1) {
            return className;
        }
        return className.substring(i + 1);
    }

    private static class LogContext {
        LogContext(StackTraceElement element) {
            this.mSimpleClassName = getSimpleClassName(element.getClassName());
            this.mMethodName = element.getMethodName();
            this.mLineNumber = element.getLineNumber();
        }

        String mSimpleClassName;
        String mMethodName;
        int mLineNumber;
    }

    private static LogContext getContext() {
        try {
            StackTraceElement[] trace = Thread.currentThread().getStackTrace();
            StackTraceElement element = trace[5];
            return new LogContext(element);
        } catch (Exception e) {
            return null;
        }
    }

    private static String getMessage(String s, Object... args) {
        try {
            s = String.format(s, args);
        } catch (Exception ignore) {
        }
        LogContext c = getContext();
        String msg;
        if (c != null) {
            msg = c.mSimpleClassName + "." + c.mMethodName + "@" + c.mLineNumber + ": " + s;
        } else {
            msg = "?:" + s;
        }
        return msg;
    }
}
