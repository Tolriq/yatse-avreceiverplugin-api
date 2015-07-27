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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * The CustomCommandsActivity Activity that plugin must extend if they support adding / editing Custom Commands
 * <p/>
 * Activity should call : <br />
 * - {@link AVReceiverCustomCommandsActivity#cancelAndFinish()} to cancel any change and return to Yatse.<br/>
 * - {@link AVReceiverCustomCommandsActivity#saveAndFinish()} to return the updated pluginCustomCommand for adding / saving in Yatse.
 */
public class AVReceiverCustomCommandsActivity extends Activity {

    public final static String EXTRA_CUSTOM_COMMAND = "tv.yatse.plugin.avreceiver.EXTRA_CUSTOM_COMMAND";

    protected PluginCustomCommand pluginCustomCommand;
    private boolean mIsEditing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if (intent != null) {
            mIsEditing = intent.hasExtra(EXTRA_CUSTOM_COMMAND);
            if (mIsEditing) {
                pluginCustomCommand = intent.getParcelableExtra(EXTRA_CUSTOM_COMMAND);
            } else {
                pluginCustomCommand = new PluginCustomCommand();
            }
        }
        setResult(RESULT_CANCELED, new Intent());
    }

    /**
     * @return true if editing an existing custom command
     */
    protected boolean isEditing() {
        return mIsEditing;
    }

    /**
     * Cancel adding / editing and finish the activity.
     */
    protected void cancelAndFinish() {
        setResult(RESULT_CANCELED, new Intent());
        finish();
    }

    /**
     * Finish the activity and return the added / updated custom command to Yatse.
     */
    protected void saveAndFinish() {
        Intent result = new Intent();
        result.putExtra(EXTRA_CUSTOM_COMMAND, pluginCustomCommand);
        setResult(RESULT_OK, result);
        finish();
    }
}
