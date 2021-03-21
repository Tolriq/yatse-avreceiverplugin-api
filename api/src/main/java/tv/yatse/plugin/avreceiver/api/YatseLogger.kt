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
package tv.yatse.plugin.avreceiver.api

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * Utility class that allows plugins to push logs to main Yatse log system.
 *
 * This allow central logging and easier debugging for users.
 */
object YatseLogger {

    private const val TAG_PARAM = "TAG"
    private const val MESSAGE_PARAM = "MESSAGE"
    private val targetComponentName = ComponentName("org.leetzone.android.yatsewidgetfree", "tv.yatse.api.ApiReceiver")

    /**
     * Log a message with an error level.
     *
     * @param context   a valid context
     * @param tag       the tag
     * @param message   the message
     * @param throwable optional Throwable to log
     */
    fun logError(context: Context, tag: String, message: String, throwable: Throwable? = null) {
        val intent = Intent("tv.yatse.api.LOG_ERROR")
            .setComponent(targetComponentName)
            .putExtra(TAG_PARAM, tag)
            .putExtra(
                MESSAGE_PARAM, message + if (throwable != null) {
                    "\n" + Log.getStackTraceString(throwable)
                } else {
                    ""
                }
            )
        runCatching {
            context.sendBroadcast(intent)
        }.onFailure {
            it.printStackTrace()
        }
    }

    /**
     * Log a message with an error level.
     *
     * @param context a valid context
     * @param tag     the tag
     * @param message the message
     */
    fun logVerbose(context: Context, tag: String, message: String) {
        val intent = Intent("tv.yatse.api.LOG_VERBOSE")
            .setComponent(targetComponentName)
            .putExtra(TAG_PARAM, tag)
            .putExtra(MESSAGE_PARAM, message)
        runCatching {
            context.sendBroadcast(intent)
        }.onFailure {
            it.printStackTrace()
        }
    }
}
