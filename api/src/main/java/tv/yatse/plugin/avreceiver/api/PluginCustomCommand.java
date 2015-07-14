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

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * The type PluginCustomCommand.
 * <p/>
 * Not yet used, out of date documentation
 */
public class PluginCustomCommand implements Parcelable {

    /**
     * The constant PARCELABLE_VERSION.
     */
    public static final int PARCELABLE_VERSION = 1;

    private static final String KEY_VERSION = "version";
    private static final String KEY_ID = "id";
    private static final String KEY_COLOR = "color";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_ICON = "icon";
    private static final String KEY_PARAM1 = "param1";
    private static final String KEY_PARAM2 = "param2";
    private static final String KEY_PARAM3 = "param3";
    private static final String KEY_PARAM4 = "param4";
    private static final String KEY_PARAM5 = "param5";
    private static final String KEY_READ_ONLY = "read_only";
    private static final String KEY_SOURCE = "source";
    private static final String KEY_TITLE = "title";
    private static final String KEY_TYPE = "type";

    private long mId;
    private int mColor;
    private String mDescription;
    private String mIcon;
    private String mParam1;
    private String mParam2;
    private String mParam3;
    private String mParam4;
    private String mParam5;
    private boolean mReadOnly;
    private String mSource;
    private String mTitle;
    private int mType;

    /**
     * Id long.
     *
     * @return the long
     */
    public long id() {
        return mId;
    }

    /**
     * Id plugin custom command.
     *
     * @param id the id
     * @return the plugin custom command
     */
    public PluginCustomCommand id(long id) {
        mId = id;
        return this;
    }

    /**
     * Color int.
     *
     * @return the int
     */
    public int color() {
        return mColor;
    }

    /**
     * Color plugin custom command.
     *
     * @param color the color
     * @return the plugin custom command
     */
    public PluginCustomCommand color(int color) {
        mColor = color;
        return this;
    }

    /**
     * Read only.
     *
     * @return the boolean
     */
    public boolean readOnly() {
        return mReadOnly;
    }

    /**
     * Read only.
     *
     * @param readOnly the read only
     * @return the plugin custom command
     */
    public PluginCustomCommand readOnly(boolean readOnly) {
        mReadOnly = readOnly;
        return this;
    }

    /**
     * Description string.
     *
     * @return the string
     */
    public String description() {
        return mDescription;
    }

    /**
     * Description plugin custom command.
     *
     * @param description the description
     * @return the plugin custom command
     */
    public PluginCustomCommand description(String description) {
        mDescription = description;
        return this;
    }

    /**
     * Icon string.
     *
     * @return the string
     */
    public String icon() {
        return mIcon;
    }

    /**
     * Icon plugin custom command.
     *
     * @param icon the icon
     * @return the plugin custom command
     */
    public PluginCustomCommand icon(String icon) {
        mIcon = icon;
        return this;
    }

    /**
     * Param 1.
     *
     * @return the string
     */
    public String param1() {
        return mParam1;
    }

    /**
     * Param 1.
     *
     * @param param1 the param 1
     * @return the plugin custom command
     */
    public PluginCustomCommand param1(String param1) {
        mParam1 = param1;
        return this;
    }

    /**
     * Param 2.
     *
     * @return the string
     */
    public String param2() {
        return mParam2;
    }

    /**
     * Param 2.
     *
     * @param param2 the param 2
     * @return the plugin custom command
     */
    public PluginCustomCommand param2(String param2) {
        mParam2 = param2;
        return this;
    }

    /**
     * Param 3.
     *
     * @return the string
     */
    public String param3() {
        return mParam3;
    }

    /**
     * Param 3.
     *
     * @param param3 the param 3
     * @return the plugin custom command
     */
    public PluginCustomCommand param3(String param3) {
        mParam3 = param3;
        return this;
    }

    /**
     * Param 4.
     *
     * @return the string
     */
    public String param4() {
        return mParam4;
    }

    /**
     * Param 4.
     *
     * @param param4 the param 4
     * @return the plugin custom command
     */
    public PluginCustomCommand param4(String param4) {
        mParam4 = param4;
        return this;
    }

    /**
     * Param 5.
     *
     * @return the string
     */
    public String param5() {
        return mParam5;
    }

    /**
     * Param 5.
     *
     * @param param5 the param 5
     * @return the plugin custom command
     */
    public PluginCustomCommand param5(String param5) {
        mParam5 = param5;
        return this;
    }

    /**
     * Source string.
     *
     * @return the string
     */
    public String source() {
        return mSource;
    }

    /**
     * Source plugin custom command.
     *
     * @param source the source
     * @return the plugin custom command
     */
    public PluginCustomCommand source(String source) {
        mSource = source;
        return this;
    }

    /**
     * Title string.
     *
     * @return the string
     */
    public String title() {
        return mTitle;
    }

    /**
     * Title plugin custom command.
     *
     * @param title the title
     * @return the plugin custom command
     */
    public PluginCustomCommand title(String title) {
        mTitle = title;
        return this;
    }

    /**
     * Type int.
     *
     * @return the int
     */
    public int type() {
        return mType;
    }

    /**
     * Type plugin custom command.
     *
     * @param type the type
     * @return the plugin custom command
     */
    public PluginCustomCommand type(int type) {
        mType = type;
        return this;
    }

    /**
     * Instantiates a new Plugin custom command.
     *
     * @param in the in
     */
    protected PluginCustomCommand(Parcel in) {
        readFromParcel(in);
    }

    /**
     * Instantiates a new Plugin custom command.
     */
    public PluginCustomCommand() {
    }

    /**
     * The constant CREATOR.
     */
    public static final Creator<PluginCustomCommand> CREATOR = new Creator<PluginCustomCommand>() {
        @Override
        public PluginCustomCommand createFromParcel(Parcel in) {
            return new PluginCustomCommand(in);
        }

        @Override
        public PluginCustomCommand[] newArray(int size) {
            return new PluginCustomCommand[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(PARCELABLE_VERSION);
        dest.writeLong(mId);
        dest.writeInt(mColor);
        dest.writeString(mDescription);
        dest.writeString(mIcon);
        dest.writeString(mParam1);
        dest.writeString(mParam2);
        dest.writeString(mParam3);
        dest.writeString(mParam4);
        dest.writeString(mParam5);
        dest.writeInt(mReadOnly ? 1 : 0);
        dest.writeString(mSource);
        dest.writeString(mTitle);
        dest.writeInt(mType);
    }

    private void readFromParcel(Parcel in) {
        int version = in.readInt();
        if (version >= 1) {
            mId = in.readLong();
            mColor = in.readInt();
            mDescription = in.readString();
            mIcon = in.readString();
            mParam1 = in.readString();
            mParam2 = in.readString();
            mParam3 = in.readString();
            mParam4 = in.readString();
            mParam5 = in.readString();
            mReadOnly = in.readInt() == 1;
            mSource = in.readString();
            mTitle = in.readString();
            mType = in.readInt();
        }
    }

    /**
     * Serialize jSON object.
     *
     * @return the jSON object
     * @throws JSONException the jSON exception
     */
    public JSONObject serialize() throws JSONException {
        JSONObject data = new JSONObject();
        data.put(KEY_VERSION, PARCELABLE_VERSION);
        data.put(KEY_ID, mId);
        data.put(KEY_COLOR, mColor);
        data.put(KEY_DESCRIPTION, mDescription);
        data.put(KEY_ICON, mIcon);
        data.put(KEY_PARAM1, mParam1);
        data.put(KEY_PARAM2, mParam2);
        data.put(KEY_PARAM3, mParam3);
        data.put(KEY_PARAM4, mParam4);
        data.put(KEY_PARAM5, mParam5);
        data.put(KEY_READ_ONLY, mReadOnly);
        data.put(KEY_SOURCE, mSource);
        data.put(KEY_TITLE, mTitle);
        data.put(KEY_TYPE, mType);
        return data;
    }

    /**
     * Deserialize void.
     *
     * @param data the data
     * @throws JSONException the jSON exception
     */
    public void deserialize(JSONObject data) throws JSONException {
        int version = data.optInt(KEY_VERSION);
        if (version >= 1) {
            this.mId = data.optInt(KEY_ID);
            this.mColor = data.optInt(KEY_COLOR);
            this.mDescription = data.optString(KEY_DESCRIPTION);
            this.mIcon = data.optString(KEY_ICON);
            this.mParam1 = data.optString(KEY_PARAM1);
            this.mParam2 = data.optString(KEY_PARAM2);
            this.mParam3 = data.optString(KEY_PARAM3);
            this.mParam4 = data.optString(KEY_PARAM4);
            this.mParam5 = data.optString(KEY_PARAM5);
            this.mReadOnly = data.optBoolean(KEY_READ_ONLY, false);
            this.mSource = data.optString(KEY_SOURCE);
            this.mTitle = data.optString(KEY_TITLE);
            this.mType = data.optInt(KEY_TYPE);
        }
    }

    /**
     * To bundle.
     *
     * @return the bundle
     */
    public Bundle toBundle() {
        Bundle data = new Bundle();
        data.putInt(KEY_VERSION, PARCELABLE_VERSION);
        data.putLong(KEY_ID, mId);
        data.putInt(KEY_COLOR, mColor);
        data.putString(KEY_DESCRIPTION, mDescription);
        data.putString(KEY_ICON, mIcon);
        data.putString(KEY_PARAM1, mParam1);
        data.putString(KEY_PARAM2, mParam2);
        data.putString(KEY_PARAM3, mParam3);
        data.putString(KEY_PARAM4, mParam4);
        data.putString(KEY_PARAM5, mParam5);
        data.putInt(KEY_READ_ONLY, mReadOnly ? 1 : 0);
        data.putString(KEY_SOURCE, mSource);
        data.putString(KEY_TITLE, mTitle);
        data.putInt(KEY_TYPE, mType);
        return data;
    }

    /**
     * From bundle.
     *
     * @param src the src
     */
    public void fromBundle(Bundle src) {
        int version = src.getInt(KEY_VERSION);
        if (version >= 1) {
            this.mId = src.getInt(KEY_ID);
            this.mColor = src.getInt(KEY_COLOR);
            this.mDescription = src.getString(KEY_DESCRIPTION);
            this.mIcon = src.getString(KEY_ICON);
            this.mParam1 = src.getString(KEY_PARAM1);
            this.mParam2 = src.getString(KEY_PARAM2);
            this.mParam3 = src.getString(KEY_PARAM3);
            this.mParam4 = src.getString(KEY_PARAM4);
            this.mParam5 = src.getString(KEY_PARAM5);
            this.mReadOnly = src.getInt(KEY_READ_ONLY) == 1;
            this.mSource = src.getString(KEY_SOURCE);
            this.mTitle = src.getString(KEY_TITLE);
            this.mType = src.getInt(KEY_TYPE);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PluginCustomCommand)) {
            return false;
        }
        PluginCustomCommand other = (PluginCustomCommand) o;
        return mColor == other.color()
                && TextUtils.equals(mDescription, other.description())
                && TextUtils.equals(mIcon, other.icon())
                && TextUtils.equals(mParam1, other.param1())
                && TextUtils.equals(mParam2, other.param2())
                && TextUtils.equals(mParam3, other.param3())
                && TextUtils.equals(mParam4, other.param4())
                && TextUtils.equals(mParam5, other.param5())
                && mReadOnly == other.readOnly()
                && TextUtils.equals(mSource, other.source())
                && TextUtils.equals(mTitle, other.title())
                && mType == other.type();
    }

    @Override
    public int hashCode() {
        int hash = mColor;
        if (mDescription != null) {
            hash = (31 * hash + mDescription.hashCode());
        }
        if (mIcon != null) {
            hash = (31 * hash + mIcon.hashCode());
        }
        if (mParam1 != null) {
            hash = (31 * hash + mParam1.hashCode());
        }
        if (mParam2 != null) {
            hash = (31 * hash + mParam2.hashCode());
        }
        if (mParam3 != null) {
            hash = (31 * hash + mParam3.hashCode());
        }
        if (mParam4 != null) {
            hash = (31 * hash + mParam4.hashCode());
        }
        if (mParam5 != null) {
            hash = (31 * hash + mParam5.hashCode());
        }
        hash = 31 * hash + (mReadOnly ? 1 : 0);
        if (mSource != null) {
            hash = (31 * hash + mSource.hashCode());
        }
        if (mTitle != null) {
            hash = (31 * hash + mTitle.hashCode());
        }
        hash = 31 * hash + mType;
        return hash;
    }
}
