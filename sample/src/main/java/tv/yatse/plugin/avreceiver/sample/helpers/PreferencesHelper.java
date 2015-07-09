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

package tv.yatse.plugin.avreceiver.sample.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;

import tv.yatse.plugin.avreceiver.api.YatseLogger;

/**
 * Sample PreferencesHelper that shows an easy way to correctly support the API
 * <p/>
 * - Support configuration per media center via the media center uniqueId<br />
 * - Support backup / restore of settings via Yatse<br />
 * - Integrate an automated settings versioning for easier integration<br />
 */
public class PreferencesHelper {
    private volatile static PreferencesHelper INSTANCE;

    private static final String TAG = "PreferencesHelper";

    private final SharedPreferences mPreferences;
    private Context mContext;

    protected PreferencesHelper(Context context) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        mContext = context;
    }

    public static PreferencesHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (PreferencesHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PreferencesHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public String getSettingsAsJSON() {
        JSONObject settings = new JSONObject();
        try {
            for (Map.Entry<String, ?> entry : mPreferences.getAll().entrySet()) {
                Object val = entry.getValue();
                if (val == null) {
                    settings.put(entry.getKey(), null);
                } else {
                    settings.put(entry.getKey(), String.valueOf(entry.getValue()));
                }
            }
        } catch (JSONException e) {
            YatseLogger.getInstance(mContext).logError(TAG, "Error encoding settings", e);
        }
        return settings.toString();
    }

    public boolean importSettingsFromJSON(String settings, long version) {
        try {
            JSONObject data = new JSONObject(settings);
            Iterator<String> keys = data.keys();

            SharedPreferences.Editor mEditor = mPreferences.edit();
            while (keys.hasNext()) {
                String key = keys.next();
                if (!TextUtils.equals(key, "settings_version")) {
                    mEditor.putString(key, data.getString(key));
                }
            }
            mEditor.apply();
            settingsVersion(version);
        } catch (JSONException e) {
            YatseLogger.getInstance(mContext).logError(TAG, "Error decoding settings", e);
        }
        return true;
    }


    public String hostIp(String hostUniqueId) {
        return mPreferences.getString("host_ip_" + hostUniqueId, "");
    }

    public void hostIp(String hostUniqueId, String ip) {
        if (!TextUtils.equals(hostIp(hostUniqueId), ip)) {
            settingsVersion(settingsVersion() + 1);
        }
        SharedPreferences.Editor mEditor = mPreferences.edit();
        mEditor.putString("host_ip_" + hostUniqueId, ip);
        mEditor.apply();
    }

    public long settingsVersion() {
        return mPreferences.getLong("settings_version", 0);
    }

    public void settingsVersion(long settingsVersion) {
        SharedPreferences.Editor mEditor = mPreferences.edit();
        mEditor.putLong("settings_version", settingsVersion);
        mEditor.apply();
    }

}