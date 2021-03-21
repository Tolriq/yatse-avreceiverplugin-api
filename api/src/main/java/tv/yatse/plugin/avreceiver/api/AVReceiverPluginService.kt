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

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.RemoteException

/**
 * The AVReceiverPluginService service that any plugin must extend
 *
 *
 * Unless noted in command description most commands can be called from main thread so should handled this correctly : Fast and no network / disk access.
 */
abstract class AVReceiverPluginService : Service() {
    /**
     * Returns supported volume unit.
     *
     * @return the volume unit. Must be one of : [AVReceiverPluginService.UNIT_TYPE_NONE] / {link AVReceiverPluginService#UNIT_TYPE_PERCENT}
     */
    protected abstract fun getVolumeUnitType(): Int

    /**
     * Gets volume minimal value.
     *
     * @return the volume minimal value. Should return 0 for {link AVReceiverPluginService#UNIT_VOLUME_PERCENT}
     */
    protected abstract fun getVolumeMinimalValue(): Double

    /**
     * Gets volume maximal value.
     *
     * @return the volume maximal value. Should return 100 for {link AVReceiverPluginService#UNIT_VOLUME_PERCENT}
     */
    protected abstract fun getVolumeMaximalValue(): Double

    /**
     * Sets mute status.
     *
     * @param status the new new mute status
     * @return true on success
     */
    protected abstract fun setMuteStatus(status: Boolean): Boolean

    /**
     * Gets current mute status.
     *
     * @return the mute status
     */
    protected abstract fun getMuteStatus(): Boolean

    /**
     * Toggle mute status.
     *
     * @return true on success
     */
    protected abstract fun toggleMuteStatus(): Boolean

    /**
     * Sets new volume level.
     *
     * @param volume the volume
     * @return true on success
     */
    protected abstract fun setVolumeLevel(volume: Double): Boolean

    /**
     * Gets current volume level.
     *
     * @return the volume level
     */
    protected abstract fun getVolumeLevel(): Double

    /**
     * Update current volume up by a configured step.
     *
     * @return true on success
     */
    protected abstract fun volumePlus(): Boolean

    /**
     * Update current volume down by a configured step.
     *
     * @return true on success
     */
    protected abstract fun volumeMinus(): Boolean

    /**
     * Refresh the current status of the audio/video receiver.
     * This command should be synchronous and only returns when the internal data is up to date.
     * This command will be called from a background thread so can last a little and use network.
     *
     * @return true on success
     */
    protected abstract fun refresh(): Boolean

    /**
     * Gets the list of default custom commands that Yatse can import.
     *
     *
     * For commands that can have a lot of different parameters like input change,
     * please offer the commands via the add custom command Activity and do not return hundreds
     * of commands.
     *
     * @return the list of custom commands.
     */
    protected abstract fun getDefaultCustomCommands(): List<PluginCustomCommand>

    /**
     * Execute the given custom command.
     *
     *
     *
     * @param customCommand the custom command
     * @return true on success
     */
    protected abstract fun executeCustomCommand(customCommand: PluginCustomCommand?): Boolean

    /**
     * This function is called each time Yatse binds to the service for a specific host.
     * This allows per host settings with the host uniqueId
     *
     * @param uniqueId the host unique id
     * @param name     the host name
     * @param ip       the host ip
     */
    protected abstract fun connectToHost(uniqueId: String?, name: String?, ip: String?)

    /**
     * Gets settings version.
     * This function should return an increased value on each plugin settings change to allow Yatse
     * to backup plugin data for later restore
     *
     * @return the current settings version
     */
    protected abstract fun getSettingsVersion(): Long

    /**
     * This function is called by Yatse to get the plugin settings data for backup purpose.
     *
     * @return the settings as a string.
     */
    protected abstract fun getSettings(): String

    /**
     * This function is called by Yatse when it have a more recent version of the settings than the plugin itself,
     * like on a reinstall and restore from Cloud Save
     *
     * @param settings the settings as String
     * @param version  the settings version
     * @return true on success
     */
    protected abstract fun restoreSettings(settings: String?, version: Long): Boolean
    private val serviceBinder: IReceiverPluginInterface.Stub = object : IReceiverPluginInterface.Stub() {
        @Throws(RemoteException::class)
        override fun getVolumeUnitType(): Int {
            return this@AVReceiverPluginService.getVolumeUnitType()
        }

        @Throws(RemoteException::class)
        override fun getVolumeMinimalValue(): Double {
            return this@AVReceiverPluginService.getVolumeMinimalValue()
        }

        @Throws(RemoteException::class)
        override fun getVolumeMaximalValue(): Double {
            return this@AVReceiverPluginService.getVolumeMaximalValue()
        }

        @Throws(RemoteException::class)
        override fun setMuteStatus(status: Boolean): Boolean {
            return this@AVReceiverPluginService.setMuteStatus(status)
        }

        @Throws(RemoteException::class)
        override fun getMuteStatus(): Boolean {
            return this@AVReceiverPluginService.getMuteStatus()
        }

        @Throws(RemoteException::class)
        override fun toggleMuteStatus(): Boolean {
            return this@AVReceiverPluginService.toggleMuteStatus()
        }

        @Throws(RemoteException::class)
        override fun setVolumeLevel(volume: Double): Boolean {
            return this@AVReceiverPluginService.setVolumeLevel(volume)
        }

        @Throws(RemoteException::class)
        override fun getVolumeLevel(): Double {
            return this@AVReceiverPluginService.getVolumeLevel()
        }

        @Throws(RemoteException::class)
        override fun volumePlus(): Boolean {
            return this@AVReceiverPluginService.volumePlus()
        }

        @Throws(RemoteException::class)
        override fun volumeMinus(): Boolean {
            return this@AVReceiverPluginService.volumeMinus()
        }

        @Throws(RemoteException::class)
        override fun refresh(): Boolean {
            return this@AVReceiverPluginService.refresh()
        }

        @Throws(RemoteException::class)
        override fun getDefaultCustomCommands(): List<PluginCustomCommand> {
            return this@AVReceiverPluginService.getDefaultCustomCommands()
        }

        @Throws(RemoteException::class)
        override fun executeCustomCommand(customCommand: PluginCustomCommand): Boolean {
            return this@AVReceiverPluginService.executeCustomCommand(customCommand)
        }

        @Throws(RemoteException::class)
        override fun getSettingsVersion(): Long {
            return this@AVReceiverPluginService.getSettingsVersion()
        }

        @Throws(RemoteException::class)
        override fun getSettings(): String {
            return this@AVReceiverPluginService.getSettings()
        }

        @Throws(RemoteException::class)
        override fun restoreSettings(settings: String, version: Long): Boolean {
            return this@AVReceiverPluginService.restoreSettings(settings, version)
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        connectToHost(
            intent.getStringExtra(EXTRA_STRING_MEDIA_CENTER_UNIQUE_ID),
            intent.getStringExtra(EXTRA_STRING_MEDIA_CENTER_NAME),
            intent.getStringExtra(EXTRA_STRING_MEDIA_CENTER_IP)
        )
        return serviceBinder
    }

    companion object {
        /**
         * String extra that will contains the associated media center unique id when Yatse connect to the service or start the settings activity.
         * This extra should always be filled, empty values should be checked and indicate a problem.
         */
        const val EXTRA_STRING_MEDIA_CENTER_UNIQUE_ID =
            "tv.yatse.plugin.avreceiver.EXTRA_STRING_MEDIA_CENTER_UNIQUE_ID"

        /**
         * String extra that will contains the associated media center name when Yatse connect to the service or start the settings activity.
         */
        const val EXTRA_STRING_MEDIA_CENTER_NAME =
            "tv.yatse.plugin.avreceiver.EXTRA_STRING_MEDIA_CENTER_NAME"

        /**
         * String extra that will contains the associated media center IP when Yatse connect to the service or start the settings activity.
         */
        const val EXTRA_STRING_MEDIA_CENTER_IP = "tv.yatse.plugin.avreceiver.EXTRA_STRING_MEDIA_CENTER_IP"

        /**
         * This volume unit disable discrete volume values and only allows volumePlus and volumeMinus calls.
         */
        const val UNIT_TYPE_NONE = 0

        /**
         * Default volume unit. Volume is handled as a percent value between 0 and 100
         */
        const val UNIT_TYPE_PERCENT = 1

        /**
         * Volume is handled as a db value between [AVReceiverPluginService.getVolumeMinimalValue] and [AVReceiverPluginService.getVolumeMaximalValue]
         *
         *
         * **This value is not yet supported by Yatse and will be treated as [AVReceiverPluginService.UNIT_TYPE_PERCENT] !**
         */
        @Suppress("unused")
        const val UNIT_TYPE_DB = 2
    }
}