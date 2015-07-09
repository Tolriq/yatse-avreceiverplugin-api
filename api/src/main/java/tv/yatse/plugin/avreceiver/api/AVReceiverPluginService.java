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

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.List;

/**
 * The AVReceiverPluginService service that any plugin must extend
 * <p/>
 * Unless noted in command description most commands can be called from main thread so should handled this correctly : Fast and no network / disk access.
 */
public abstract class AVReceiverPluginService extends Service {

    /**
     * String extra that will contains the associated media center unique id when Yatse connect to the service or start the settings activity.
     * This extra should always be filled, empty values should be checked and indicate a problem.
     */
    public static String EXTRA_STRING_MEDIA_CENTER_UNIQUE_ID = "tv.yatse.plugin.avreceiver.EXTRA_STRING_MEDIA_CENTER_UNIQUE_ID";
    /**
     * String extra that will contains the associated media center name when Yatse connect to the service or start the settings activity.
     */
    public static String EXTRA_STRING_MEDIA_CENTER_NAME = "tv.yatse.plugin.avreceiver.EXTRA_STRING_MEDIA_CENTER_NAME";
    /**
     * String extra that will contains the associated media center IP when Yatse connect to the service or start the settings activity.
     */
    public static String EXTRA_STRING_MEDIA_CENTER_IP = "tv.yatse.plugin.avreceiver.EXTRA_STRING_MEDIA_CENTER_IP";

    /**
     * This volume unit disable discrete volume values and only allows volumePlus and volumeMinus calls.
     */
    public static int UNIT_TYPE_NONE = 0;

    /**
     * Default volume unit. Volume is handled as a percent value between 0 and 100
     */
    public static int UNIT_TYPE_PERCENT = 1;

    /**
     * Volume is handled as a db value between {@link AVReceiverPluginService#getVolumeMinimalValue()} and {@link AVReceiverPluginService#getVolumeMaximalValue()}
     * <p/>
     * <b>This value is not yet supported by Yatse and will be treated as {@link AVReceiverPluginService#UNIT_TYPE_PERCENT} !</b>
     */
    public static int UNIT_TYPE_DB = 2;

    /**
     * Returns supported volume unit.
     *
     * @return the volume unit. Must be one of : {@link AVReceiverPluginService#UNIT_TYPE_NONE} / {link AVReceiverPluginService#UNIT_TYPE_PERCENT}
     */
    protected abstract int getVolumeUnitType();

    /**
     * Gets volume minimal value.
     *
     * @return the volume minimal value. Should return 0 for {link AVReceiverPluginService#UNIT_VOLUME_PERCENT}
     */
    protected abstract double getVolumeMinimalValue();

    /**
     * Gets volume maximal value.
     *
     * @return the volume maximal value. Should return 100 for {link AVReceiverPluginService#UNIT_VOLUME_PERCENT}
     */
    protected abstract double getVolumeMaximalValue();

    /**
     * Sets mute status.
     *
     * @param status the new new mute status
     * @return true on success
     */
    protected abstract boolean setMuteStatus(boolean status);

    /**
     * Gets current mute status.
     *
     * @return the mute status
     */
    protected abstract boolean getMuteStatus();

    /**
     * Toggle mute status.
     *
     * @return true on success
     */
    protected abstract boolean toggleMuteStatus();

    /**
     * Sets new volume level.
     *
     * @param volume the volume
     * @return true on success
     */
    protected abstract boolean setVolumeLevel(double volume);

    /**
     * Gets current volume level.
     *
     * @return the volume level
     */
    protected abstract double getVolumeLevel();

    /**
     * Update current volume up by a configured step.
     *
     * @return true on success
     */
    protected abstract boolean volumePlus();

    /**
     * Update current volume down by a configured step.
     *
     * @return true on success
     */
    protected abstract boolean volumeMinus();

    /**
     * Refresh the current status of the audio/video receiver.
     * This command should be synchronous and only returns when the internal data is up to date.
     * This command will be called from a background thread so can last a little and use network.
     *
     * @return true on success
     */
    protected abstract boolean refresh();

    /**
     * Gets the list of default custom commands that Yatse can import.
     * <p/>
     * <b>This command is not yet supported by Yatse</b>
     *
     * @return the list of custom commands.
     */
    protected abstract List<PluginCustomCommand> getDefaultCustomCommands();

    /**
     * Execute the given custom command.
     * <p/>
     * <b>This command is not yet supported by Yatse</b>
     *
     * @param customCommand the custom command
     * @return true on success
     */
    protected abstract boolean executeCustomCommand(PluginCustomCommand customCommand);

    /**
     * This function is called each time Yatse binds to the service for a specific host.
     * This allows per host settings with the host uniqueId
     *
     * @param uniqueId the host unique id
     * @param name     the host name
     * @param ip       the host ip
     */
    protected abstract void connectToHost(String uniqueId, String name, String ip);

    /**
     * Gets settings version.
     * This function should return an increased value on each plugin settings change to allow Yatse
     * to backup plugin data for later restore
     *
     * @return the current settings version
     */
    protected abstract long getSettingsVersion();

    /**
     * This function is called by Yatse to get the plugin settings data for backup purpose.
     *
     * @return the settings as a string.
     */
    protected abstract String getSettings();

    /**
     * This function is called by Yatse when it have a more recent version of the settings than the plugin itself,
     * like on a reinstall and restore from Cloud Save
     *
     * @param settings the settings as String
     * @param version  the settings version
     * @return true on success
     */
    protected abstract boolean restoreSettings(String settings, long version);


    private final IReceiverPluginInterface.Stub mBinder = new IReceiverPluginInterface.Stub() {
        @Override
        public int getVolumeUnitType() throws RemoteException {
            return AVReceiverPluginService.this.getVolumeUnitType();
        }

        @Override
        public double getVolumeMinimalValue() throws RemoteException {
            return AVReceiverPluginService.this.getVolumeMinimalValue();
        }

        @Override
        public double getVolumeMaximalValue() throws RemoteException {
            return AVReceiverPluginService.this.getVolumeMaximalValue();
        }

        @Override
        public boolean setMuteStatus(boolean status) throws RemoteException {
            return AVReceiverPluginService.this.setMuteStatus(status);
        }

        @Override
        public boolean getMuteStatus() throws RemoteException {
            return AVReceiverPluginService.this.getMuteStatus();
        }

        @Override
        public boolean toggleMuteStatus() throws RemoteException {
            return AVReceiverPluginService.this.toggleMuteStatus();
        }

        @Override
        public boolean setVolumeLevel(double volume) throws RemoteException {
            return AVReceiverPluginService.this.setVolumeLevel(volume);
        }

        @Override
        public double getVolumeLevel() throws RemoteException {
            return AVReceiverPluginService.this.getVolumeLevel();
        }

        @Override
        public boolean volumePlus() throws RemoteException {
            return AVReceiverPluginService.this.volumePlus();
        }

        @Override
        public boolean volumeMinus() throws RemoteException {
            return AVReceiverPluginService.this.volumeMinus();
        }

        @Override
        public boolean refresh() throws RemoteException {
            return AVReceiverPluginService.this.refresh();
        }

        @Override
        public List<PluginCustomCommand> getDefaultCustomCommands() throws RemoteException {
            return AVReceiverPluginService.this.getDefaultCustomCommands();
        }

        @Override
        public boolean executeCustomCommand(PluginCustomCommand customCommand) throws RemoteException {
            return AVReceiverPluginService.this.executeCustomCommand(customCommand);
        }

        @Override
        public long getSettingsVersion() throws RemoteException {
            return AVReceiverPluginService.this.getSettingsVersion();
        }

        @Override
        public String getSettings() throws RemoteException {
            return AVReceiverPluginService.this.getSettings();
        }

        @Override
        public boolean restoreSettings(String settings, long version) throws RemoteException {
            return AVReceiverPluginService.this.restoreSettings(settings, version);
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        if (intent != null) {
            connectToHost(intent.getStringExtra(EXTRA_STRING_MEDIA_CENTER_UNIQUE_ID), intent.getStringExtra(EXTRA_STRING_MEDIA_CENTER_NAME), intent.getStringExtra(EXTRA_STRING_MEDIA_CENTER_IP));
            return mBinder;
        }
        return null;
    }

}
