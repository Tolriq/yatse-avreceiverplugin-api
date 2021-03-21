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

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tv.yatse.plugin.avreceiver.api.AVReceiverPluginService;
import tv.yatse.plugin.avreceiver.api.PluginCustomCommand;
import tv.yatse.plugin.avreceiver.api.YatseLogger;
import tv.yatse.plugin.avreceiver.sample.helpers.PreferencesHelper;

/**
 * Sample AVReceiverPluginService that implement all functions with dummy code that displays Toast and logs to main Yatse log system.
 * <p/>
 * See {@link AVReceiverPluginService} for documentation on all functions
 */
public class AVPluginService extends AVReceiverPluginService {
    private Handler handler = new Handler(Looper.getMainLooper());
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
        displayToast("Setting mute status : " + status);
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
        displayToast("Toggling mute status");
        mIsMuted = !mIsMuted;
        return true;
    }

    @Override
    protected boolean setVolumeLevel(double volume) {
        YatseLogger.getInstance(getApplicationContext()).logVerbose(TAG, "Setting volume level : %s", volume);
        displayToast("Setting volume : " + volume);
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
        displayToast("Volume plus");
        return true;
    }

    @Override
    protected boolean volumeMinus() {
        mVolumePercent = Math.max(0.0, mVolumePercent - 5);
        YatseLogger.getInstance(getApplicationContext()).logVerbose(TAG, "Calling volume minus");
        displayToast("Volume minus");
        return true;
    }

    @Override
    protected boolean refresh() {
        YatseLogger.getInstance(getApplicationContext()).logVerbose(TAG, "Refreshing values from receiver");
        return true;
    }

    @Override
    protected List<PluginCustomCommand> getDefaultCustomCommands() {
        String source = getString(R.string.plugin_unique_id);
        List<PluginCustomCommand> commands = new ArrayList<>();
        // Plugin custom commands must set the source parameter to their plugin unique Id !
        commands.add(new PluginCustomCommand().title("Sample command 1").source(source).param1("Sample command 1").type(0));
        commands.add(new PluginCustomCommand().title("Sample command 2").source(source).param1("Sample command 2").type(1).readOnly(true));
        return commands;
    }

    @Override
    protected boolean executeCustomCommand(PluginCustomCommand customCommand) {
        YatseLogger.getInstance(getApplicationContext()).logVerbose(TAG, "Executing CustomCommand : %s", customCommand.title());
        displayToast(customCommand.param1());
        return false;
    }

    private void displayToast(final String message) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
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
