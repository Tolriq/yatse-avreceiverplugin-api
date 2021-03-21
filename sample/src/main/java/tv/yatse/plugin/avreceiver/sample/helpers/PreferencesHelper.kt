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
package tv.yatse.plugin.avreceiver.sample.helpers

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import androidx.preference.PreferenceManager
import org.json.JSONException
import org.json.JSONObject
import tv.yatse.plugin.avreceiver.api.YatseLogger.logError

/**
 * Sample PreferencesHelper that shows an easy way to correctly support the API
 *
 *
 * - Support configuration per media center via the media center uniqueId<br></br>
 * - Support backup / restore of settings via Yatse<br></br>
 * - Integrate an automated settings versioning for easier integration<br></br>
 */
class PreferencesHelper private constructor(context: Context) {

    private val mPreferences: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)
    private val mContext: Context = context

    val settingsAsJSON: String
        get() {
            val settings = JSONObject()
            try {
                for (entry: Map.Entry<String, *> in mPreferences.all.entries) {
                    if (entry.value == null) {
                        settings.put(entry.key, null)
                    } else {
                        settings.put(entry.key, entry.value.toString())
                    }
                }
            } catch (e: JSONException) {
                logError(mContext, TAG, "Error encoding settings", e)
            }
            return settings.toString()
        }

    fun importSettingsFromJSON(settings: String, version: Long): Boolean {
        try {
            val data = JSONObject(settings)
            val keys = data.keys()
            val mEditor = mPreferences.edit()
            while (keys.hasNext()) {
                val key = keys.next()
                if (!TextUtils.equals(key, "settings_version")) {
                    mEditor.putString(key, data.getString(key))
                }
            }
            mEditor.apply()
            settingsVersion(version)
        } catch (e: JSONException) {
            logError(mContext, TAG, "Error decoding settings", e)
        }
        return true
    }

    fun hostIp(hostUniqueId: String): String {
        return mPreferences.getString("host_ip_$hostUniqueId", "") ?: ""
    }

    fun hostIp(hostUniqueId: String, ip: String?) {
        if (!TextUtils.equals(hostIp(hostUniqueId), ip)) {
            settingsVersion(settingsVersion() + 1)
        }
        val mEditor = mPreferences.edit()
        mEditor.putString("host_ip_$hostUniqueId", ip)
        mEditor.apply()
    }

    fun settingsVersion(): Long {
        return mPreferences.getLong("settings_version", 0)
    }

    fun settingsVersion(settingsVersion: Long) {
        mPreferences.edit()
            .putLong("settings_version", settingsVersion)
            .apply()
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: PreferencesHelper? = null
        private const val TAG = "PreferencesHelper"

        fun getInstance(context: Context): PreferencesHelper {
            if (INSTANCE == null) {
                synchronized(PreferencesHelper::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = PreferencesHelper(context)
                    }
                }
            }
            return INSTANCE!!
        }
    }

}