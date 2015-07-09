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

package tv.yatse.plugin.avreceiver.sample;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import tv.yatse.plugin.avreceiver.api.AVReceiverPluginService;
import tv.yatse.plugin.avreceiver.api.PluginCustomCommand;
import tv.yatse.plugin.avreceiver.api.YatseLogger;
import tv.yatse.plugin.avreceiver.sample.helpers.PreferencesHelper;

/**
 * Sample AVReceiverPluginService that implement all functions with dummy code that only logs to Yatse logging system.
 *
 * See {@link AVReceiverPluginService} for documentation on all functions
 */
public class AVPluginService extends AVReceiverPluginService {

    private static final String TAG = "AVPluginService";

    private String mHostUniqueId;
    private String mHostName;
    private String mHostIp;

    private boolean mIsMuted = false;
    private double mVolumePercent = 50;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected int getVolumeUnitType() {
        return UNIT_TYPE_PERCENT;
    }

    @Override
    protected double getVolumeMinimalValue() {
        return 0.0;
    }

    @Override
    protected double getVolumeMaximalValue() {
        return 100.0;
    }

    @Override
    protected boolean setMuteStatus(boolean status) {
        YatseLogger.getInstance(getApplicationContext()).logVerbose(TAG, "Setting mute status : %s", status);
        mIsMuted = status;
        return true;
    }

    @Override
    protected boolean getMuteStatus() {
        return mIsMuted;
    }

    @Override
    protected boolean toggleMuteStatus() {
        YatseLogger.getInstance(getApplicationContext()).logVerbose(TAG, "Toggling mute status");
        setMuteStatus(!mIsMuted);
        return true;
    }

    @Override
    protected boolean setVolumeLevel(double volume) {
        YatseLogger.getInstance(getApplicationContext()).logVerbose(TAG, "Setting volume level : %s", volume);
        mVolumePercent = volume;
        return true;
    }

    @Override
    protected double getVolumeLevel() {
        return mVolumePercent;
    }

    @Override
    protected boolean volumePlus() {
        mVolumePercent = Math.min(100.0, mVolumePercent + 5);
        YatseLogger.getInstance(getApplicationContext()).logVerbose(TAG, "Calling volume plus");
        return true;
    }

    @Override
    protected boolean volumeMinus() {
        mVolumePercent = Math.max(0.0, mVolumePercent - 5);
        YatseLogger.getInstance(getApplicationContext()).logVerbose(TAG, "Calling volume minus");
        return true;
    }

    @Override
    protected boolean refresh() {
        YatseLogger.getInstance(getApplicationContext()).logVerbose(TAG, "Refreshing values from receiver");
        return true;
    }

    @Override
    protected List<PluginCustomCommand> getDefaultCustomCommands() {
        return new ArrayList<>();
    }

    @Override
    protected boolean executeCustomCommand(PluginCustomCommand customCommand) {
        return false;
    }

    @Override
    protected void connectToHost(String uniqueId, String name, String ip) {
        mHostUniqueId = uniqueId;
        mHostName = name;
        mHostIp = ip;
        String receiverIp = PreferencesHelper.getInstance(getApplicationContext()).hostIp(mHostUniqueId);
        if (TextUtils.isEmpty(receiverIp)) {
            YatseLogger.getInstance(getApplicationContext()).logError(TAG, "No configuration for %s", name);
        }
        YatseLogger.getInstance(getApplicationContext()).logVerbose(TAG, "Connected to : %s / %s ", name, mHostUniqueId);
    }

    @Override
    protected long getSettingsVersion() {
        return PreferencesHelper.getInstance(getApplicationContext()).settingsVersion();
    }

    @Override
    protected String getSettings() {
        return PreferencesHelper.getInstance(getApplicationContext()).getSettingsAsJSON();
    }

    @Override
    protected boolean restoreSettings(String settings, long version) {
        boolean result = PreferencesHelper.getInstance(getApplicationContext()).importSettingsFromJSON(settings, version);
        if (result) {
            connectToHost(mHostUniqueId, mHostName, mHostIp);
        }
        return result;
    }
}
