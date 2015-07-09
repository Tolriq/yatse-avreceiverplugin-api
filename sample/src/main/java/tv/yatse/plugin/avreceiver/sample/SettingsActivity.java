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

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tv.yatse.plugin.avreceiver.api.AVReceiverPluginService;
import tv.yatse.plugin.avreceiver.api.YatseLogger;
import tv.yatse.plugin.avreceiver.sample.helpers.PreferencesHelper;

/**
 * Sample SettingsActivity that handle correctly the parameters passed by Yatse.
 * <p/>
 * It is very important to save the passed extra {@link AVReceiverPluginService#EXTRA_STRING_MEDIA_CENTER_UNIQUE_ID}
 * and return it in the result intent.
 * <p/>
 * <b>Production plugin should make input validation and tests before accepting the user input and returning RESULT_OK.</b>
 */
public class SettingsActivity extends AppCompatActivity {

    private static final String TAG = "SettingsActivity";

    private String mMediaCenterUniqueId;
    private String mMediaCenterName;
    private boolean mMuted;

    @Bind(R.id.receiver_settings_title)
    TextView mViewSettingsTitle;
    @Bind(R.id.receiver_ip)
    EditText mViewReceiverIP;
    @Bind(R.id.btn_toggle_mute)
    ImageButton mViewMute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            mMediaCenterUniqueId = getIntent().getStringExtra(AVReceiverPluginService.EXTRA_STRING_MEDIA_CENTER_UNIQUE_ID);
            mMediaCenterName = getIntent().getStringExtra(AVReceiverPluginService.EXTRA_STRING_MEDIA_CENTER_NAME);
        }
        if (TextUtils.isEmpty(mMediaCenterUniqueId)) {
            YatseLogger.getInstance(getApplicationContext()).logError(TAG, "Error : No media center unique id sent");
            Snackbar.make(findViewById(R.id.receiver_settings_content), "Wrong data sent by Yatse !", Snackbar.LENGTH_LONG).show();
        }
        mViewSettingsTitle.setText(getString(R.string.sample_plugin_settings) + " " + mMediaCenterName);
        mViewReceiverIP.setText(PreferencesHelper.getInstance(getApplicationContext()).hostIp(mMediaCenterUniqueId));
    }

    @OnClick({R.id.btn_ok, R.id.btn_cancel, R.id.btn_vol_down, R.id.btn_toggle_mute, R.id.btn_vol_up})
    public void onClick(View v) {
        Intent resultIntent;
        switch (v.getId()) {
            case R.id.btn_toggle_mute:
                mViewMute.setImageResource(!mMuted ? R.drawable.ic_volume_low : R.drawable.ic_volume_off);
                mMuted = !mMuted;
                Snackbar.make(findViewById(R.id.receiver_settings_content), "Toggling mute", Snackbar.LENGTH_LONG).show();
                break;
            case R.id.btn_vol_down:
                Snackbar.make(findViewById(R.id.receiver_settings_content), "Volume down", Snackbar.LENGTH_LONG).show();
                break;
            case R.id.btn_vol_up:
                Snackbar.make(findViewById(R.id.receiver_settings_content), "Volume up", Snackbar.LENGTH_LONG).show();
                break;
            case R.id.btn_ok:
                PreferencesHelper.getInstance(getApplicationContext()).hostIp(mMediaCenterUniqueId, mViewReceiverIP.getText().toString());
                resultIntent = new Intent();
                resultIntent.putExtra(AVReceiverPluginService.EXTRA_STRING_MEDIA_CENTER_UNIQUE_ID, mMediaCenterUniqueId);
                setResult(RESULT_OK, resultIntent);
                finish();
                break;
            case R.id.btn_cancel:
                resultIntent = new Intent();
                resultIntent.putExtra(AVReceiverPluginService.EXTRA_STRING_MEDIA_CENTER_UNIQUE_ID, mMediaCenterUniqueId);
                setResult(RESULT_CANCELED, resultIntent);
                finish();
                break;
            default:
                break;
        }
    }

}
