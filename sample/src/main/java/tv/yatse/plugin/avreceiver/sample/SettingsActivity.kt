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

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import tv.yatse.plugin.avreceiver.api.AVReceiverPluginService
import tv.yatse.plugin.avreceiver.api.YatseLogger
import tv.yatse.plugin.avreceiver.sample.helpers.PreferencesHelper

/**
 * Sample SettingsActivity that handle correctly the parameters passed by Yatse.
 *
 *
 * You need to save the passed extra [AVReceiverPluginService.EXTRA_STRING_MEDIA_CENTER_UNIQUE_ID]
 * and return it in the result intent.
 *
 *
 * **Production plugin should make input validation and tests before accepting the user input and returning RESULT_OK.**
 */
class SettingsActivity : AppCompatActivity() {
    private var mMediaCenterUniqueId: String = ""
    private var mMediaCenterName: String = ""
    private var mMuted = false

    private lateinit var mViewSettingsTitle: TextView
    private lateinit var mViewReceiverIP: EditText
    private lateinit var mViewMute: ImageButton

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        if (intent != null) {
            mMediaCenterUniqueId = intent.getStringExtra(AVReceiverPluginService.EXTRA_STRING_MEDIA_CENTER_UNIQUE_ID) ?: ""
            mMediaCenterName = intent.getStringExtra(AVReceiverPluginService.EXTRA_STRING_MEDIA_CENTER_NAME) ?: ""
        }
        if (TextUtils.isEmpty(mMediaCenterUniqueId)) {
            YatseLogger.logError(applicationContext, TAG, "Error : No media center unique id sent")
            Snackbar.make(findViewById(R.id.receiver_settings_content), "Wrong data sent by Yatse !", Snackbar.LENGTH_LONG).show()
        }
        mViewSettingsTitle = findViewById(R.id.receiver_settings_title)
        mViewReceiverIP = findViewById(R.id.receiver_ip)
        mViewMute = findViewById(R.id.btn_toggle_mute)

        mViewSettingsTitle.text = "${getString(R.string.sample_plugin_settings)} $mMediaCenterName"
        mViewReceiverIP.setText(
            PreferencesHelper.getInstance(applicationContext).hostIp(mMediaCenterUniqueId)
        )

        findViewById<View>(R.id.btn_toggle_mute).setOnClickListener {
            mViewMute.setImageResource(if (!mMuted) R.drawable.ic_volume_low else R.drawable.ic_volume_off)
            mMuted = !mMuted
            Snackbar.make(findViewById(R.id.receiver_settings_content), "Toggling mute", Snackbar.LENGTH_LONG).show()
        }
        findViewById<View>(R.id.btn_vol_down).setOnClickListener {
            Snackbar.make(findViewById(R.id.receiver_settings_content), "Volume down", Snackbar.LENGTH_LONG).show()
        }
        findViewById<View>(R.id.btn_vol_up).setOnClickListener {
            Snackbar.make(findViewById(R.id.receiver_settings_content), "Volume up", Snackbar.LENGTH_LONG).show()
        }
        findViewById<View>(R.id.btn_ok).setOnClickListener {
            PreferencesHelper.getInstance(applicationContext)
                .hostIp(mMediaCenterUniqueId, mViewReceiverIP.text.toString())
            setResult(
                Activity.RESULT_OK, Intent()
                    .putExtra(AVReceiverPluginService.EXTRA_STRING_MEDIA_CENTER_UNIQUE_ID, mMediaCenterUniqueId)
            )
            finish()
        }
        findViewById<View>(R.id.btn_cancel).setOnClickListener {
            setResult(
                Activity.RESULT_CANCELED, Intent()
                    .putExtra(AVReceiverPluginService.EXTRA_STRING_MEDIA_CENTER_UNIQUE_ID, mMediaCenterUniqueId)
            )
            finish()
        }
    }

    companion object {
        private const val TAG = "SettingsActivity"
    }
}