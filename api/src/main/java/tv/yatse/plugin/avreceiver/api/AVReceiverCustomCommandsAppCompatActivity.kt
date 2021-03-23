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

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * The CustomCommandsActivity Activity that plugin must extend if they support adding / editing Custom Commands
 *
 *
 * Activity should call : <br></br>
 * - [AVReceiverCustomCommandsAppCompatActivity.cancelAndFinish] to cancel any change and return to Yatse.<br></br>
 * - [AVReceiverCustomCommandsAppCompatActivity.saveAndFinish] to return the updated pluginCustomCommand for adding / saving in Yatse.
 *
 *
 * See [AVReceiverCustomCommandsActivity] for an non AppCompat version
 */
open class AVReceiverCustomCommandsAppCompatActivity : AppCompatActivity() {
    lateinit var pluginCustomCommand: PluginCustomCommand

    /**
     * @return true if editing an existing custom command
     */
    var isEditing = false
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isEditing = intent?.hasExtra(AVReceiverCustomCommandsActivity.EXTRA_CUSTOM_COMMAND) ?: false
        pluginCustomCommand = intent?.getParcelableExtra(AVReceiverCustomCommandsActivity.EXTRA_CUSTOM_COMMAND) ?: PluginCustomCommand()
        setResult(RESULT_CANCELED, Intent())
    }

    /**
     * Cancel adding / editing and finish the activity.
     */
    fun cancelAndFinish() {
        setResult(RESULT_CANCELED, Intent())
        finish()
    }

    /**
     * Finish the activity and return the added / updated custom command to Yatse.
     */
    fun saveAndFinish() {
        setResult(RESULT_OK, Intent().apply { putExtra(EXTRA_CUSTOM_COMMAND, pluginCustomCommand) })
        finish()
    }

    companion object {
        const val EXTRA_CUSTOM_COMMAND = "tv.yatse.plugin.avreceiver.EXTRA_CUSTOM_COMMAND"
    }
}