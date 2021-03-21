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
package tv.yatse.plugin.avreceiver.sample

import android.os.Bundle
import android.view.View
import android.widget.TextView
import tv.yatse.plugin.avreceiver.api.AVReceiverCustomCommandsAppCompatActivity

class CustomCommandsActivity : AVReceiverCustomCommandsAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_commands)
        val title: TextView = findViewById(R.id.custom_command_title)
        val param1: TextView = findViewById(R.id.custom_command_param1)
        if (isEditing) {
            title.text = pluginCustomCommand.title
            param1.text = pluginCustomCommand.param1
        }
        findViewById<View>(R.id.btn_save).setOnClickListener {
            // Custom command source field must always equals to plugin uniqueId !!
            pluginCustomCommand = pluginCustomCommand.copy(
                source = getString(R.string.plugin_unique_id),
                title = title.text.toString(),
                param1 = param1.text.toString()
            )
            saveAndFinish()
        }
        findViewById<View>(R.id.btn_cancel).setOnClickListener {
            cancelAndFinish()
        }
    }
}