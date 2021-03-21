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

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import org.json.JSONException
import org.json.JSONObject

/**
 * The type PluginCustomCommand.
 *
 * This is the definition of custom commands that plugins can use to allow integration in Yatse.
 */
@Suppress("unused")
class PluginCustomCommand(
    /**
     * The custom command id.<br></br>
     * This value is internal to Yatse and should not be filled / edited by plugins
     */
    val id: Long = 0,
    /**
     * Custom command color.<br></br>
     * This value is not yet used by Yatse.
     */
    val color: Int = 0,
    /**
     * Description of the custom command.
     */
    val description: String = "",
    /**
     * DisplayOrder of the custom command.<br></br>
     * This value is internal to Yatse and should not be filled / edited by plugins
     */
    val displayOrder: Int = 0,
    /**
     * Custom command icon.<br></br>
     * This value is not yet used by Yatse.
     */
    val icon: String = "",
    /**
     * Custom command param1.<br></br>
     * This string parameter is available to store any needed parameter for this plugin custom command.
     */
    val param1: String = "",
    /**
     * Custom command param2.<br></br>
     * This string parameter is available to store any needed parameter for this plugin custom command.
     */
    val param2: String = "",
    /**
     * Custom command param3.<br></br>
     * This string parameter is available to store any needed parameter for this plugin custom command.
     */
    val param3: String = "",
    /**
     * Return the custom command param4.<br></br>
     * This string parameter is available to store any needed parameter for this plugin custom command.
     */
    val param4: String = "",
    /**
     * Custom command param5.<br></br>
     * This string parameter is available to store any needed parameter for this plugin custom command.
     */
    val param5: String = "",
    /**
     * ReadOnly status of the custom command.
     * If true, the user will only be allowed to rename the command but not edit it.
     * **Your plugin must define the meta-data *customCommandsActivity* for Yatse to correctly call your plugin for editing.**
     */
    val readOnly: Boolean = false,
    /**
     * Custom command source.<br></br>
     * **This value must always match the plugin uniqueId !**
     */
    val source: String = "",
    /**
     * Custom command title.<br></br>
     * This value must not be null !
     */
    val title: String = "",
    /**
     * Custom command type.<br></br>
     * This int parameter is available to store any needed parameter for this plugin custom command.
     */
    val type: Int = 0,
    /**
     * Custom command unique Id.<br></br>
     * This value is internal to Yatse and should not be filled / edited by plugins
     */
    val uniqueId: String = ""
) : Parcelable {

    override fun describeContents(): Int {
        return 0
    }

    fun copy(
        id: Long = this.id,
        color: Int = this.color,
        description: String = this.description,
        displayOrder: Int = this.displayOrder,
        icon: String = this.icon,
        param1: String = this.param1,
        param2: String = this.param2,
        param3: String = this.param3,
        param4: String = this.param4,
        param5: String = this.param5,
        readOnly: Boolean = this.readOnly,
        source: String = this.source,
        title: String = this.title,
        type: Int = this.type,
        uniqueId: String = this.uniqueId
    ): PluginCustomCommand = PluginCustomCommand(id, color, description, displayOrder, icon, param1, param2, param3, param4, param5, readOnly, source, title, type, uniqueId)

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(PARCELABLE_VERSION)
        dest.writeLong(id)
        dest.writeInt(color)
        dest.writeString(description)
        dest.writeInt(displayOrder)
        dest.writeString(icon)
        dest.writeString(param1)
        dest.writeString(param2)
        dest.writeString(param3)
        dest.writeString(param4)
        dest.writeString(param5)
        dest.writeInt(if (readOnly) 1 else 0)
        dest.writeString(source)
        dest.writeString(title)
        dest.writeInt(type)
        dest.writeString(uniqueId)
    }

    /**
     * Serialize to JSONObject.
     *
     * @return the JSONObject
     * @throws JSONException the jSON exception
     */
    @Throws(JSONException::class)
    fun serialize(): JSONObject {
        val data = JSONObject()
        data.put(KEY_VERSION, PARCELABLE_VERSION)
        data.put(KEY_ID, id)
        data.put(KEY_COLOR, color)
        data.put(KEY_DESCRIPTION, description)
        data.put(KEY_DESCRIPTION, displayOrder)
        data.put(KEY_ICON, icon)
        data.put(KEY_PARAM1, param1)
        data.put(KEY_PARAM2, param2)
        data.put(KEY_PARAM3, param3)
        data.put(KEY_PARAM4, param4)
        data.put(KEY_PARAM5, param5)
        data.put(KEY_READ_ONLY, readOnly)
        data.put(KEY_SOURCE, source)
        data.put(KEY_TITLE, title)
        data.put(KEY_TYPE, type)
        data.put(KEY_UNIQUE_ID, uniqueId)
        return data
    }

    /**
     * Save to a [Bundle].
     *
     * @return the bundle
     */
    fun toBundle(): Bundle {
        val data = Bundle()
        data.putInt(KEY_VERSION, PARCELABLE_VERSION)
        data.putLong(KEY_ID, id)
        data.putInt(KEY_COLOR, color)
        data.putString(KEY_DESCRIPTION, description)
        data.putInt(KEY_DISPLAY_ORDER, displayOrder)
        data.putString(KEY_ICON, icon)
        data.putString(KEY_PARAM1, param1)
        data.putString(KEY_PARAM2, param2)
        data.putString(KEY_PARAM3, param3)
        data.putString(KEY_PARAM4, param4)
        data.putString(KEY_PARAM5, param5)
        data.putInt(KEY_READ_ONLY, if (readOnly) 1 else 0)
        data.putString(KEY_SOURCE, source)
        data.putString(KEY_TITLE, title)
        data.putInt(KEY_TYPE, type)
        data.putString(KEY_UNIQUE_ID, uniqueId)
        return data
    }

    override fun equals(other: Any?): Boolean {
        if (other === this) {
            return true
        }
        if (other !is PluginCustomCommand) {
            return false
        }
        return (color == other.color &&
                description == other.description &&
                icon == other.icon &&
                param1 == other.param1 &&
                param2 == other.param2 &&
                param3 == other.param3 &&
                param4 == other.param4 &&
                param5 == other.param5 &&
                readOnly == other.readOnly &&
                source == other.source &&
                title == other.title &&
                type == other.type &&
                uniqueId == other.uniqueId)
    }

    override fun hashCode(): Int {
        var hash = color
        hash = 31 * hash + description.hashCode()
        hash = 31 * hash + icon.hashCode()
        hash = 31 * hash + param1.hashCode()
        hash = 31 * hash + param2.hashCode()
        hash = 31 * hash + param3.hashCode()
        hash = 31 * hash + param4.hashCode()
        hash = 31 * hash + param5.hashCode()
        hash = 31 * hash + if (readOnly) 1 else 0
        hash = 31 * hash + source.hashCode()
        hash = 31 * hash + title.hashCode()
        hash = 31 * hash + type
        hash = 31 * hash + uniqueId.hashCode()
        return hash
    }

    companion object {
        /**
         * The constant PARCELABLE_VERSION.
         */
        const val PARCELABLE_VERSION = 1
        private const val KEY_VERSION = "version"
        private const val KEY_ID = "id"
        private const val KEY_COLOR = "color"
        private const val KEY_DESCRIPTION = "description"
        private const val KEY_DISPLAY_ORDER = "display_order"
        private const val KEY_ICON = "icon"
        private const val KEY_PARAM1 = "param1"
        private const val KEY_PARAM2 = "param2"
        private const val KEY_PARAM3 = "param3"
        private const val KEY_PARAM4 = "param4"
        private const val KEY_PARAM5 = "param5"
        private const val KEY_READ_ONLY = "read_only"
        private const val KEY_SOURCE = "source"
        private const val KEY_TITLE = "title"
        private const val KEY_TYPE = "type"
        private const val KEY_UNIQUE_ID = "unique_id"

        private fun fromParcel(parcel: Parcel): PluginCustomCommand {
            val version = parcel.readInt()
            if (version >= 1) {
                return PluginCustomCommand(
                    id = parcel.readLong(),
                    color = parcel.readInt(),
                    description = parcel.readString() ?: "",
                    displayOrder = parcel.readInt(),
                    icon = parcel.readString() ?: "",
                    param1 = parcel.readString() ?: "",
                    param2 = parcel.readString() ?: "",
                    param3 = parcel.readString() ?: "",
                    param4 = parcel.readString() ?: "",
                    param5 = parcel.readString() ?: "",
                    readOnly = parcel.readInt() == 1,
                    source = parcel.readString() ?: "",
                    title = parcel.readString() ?: "",
                    type = parcel.readInt(),
                    uniqueId = parcel.readString() ?: ""
                )
            }
            return PluginCustomCommand()
        }

        /**
         * Deserialize from a JSONObject.
         *
         * @param data the JSONObject
         * @throws JSONException the jSON exception
         */
        @Throws(JSONException::class)
        fun deserialize(data: JSONObject): PluginCustomCommand {
            val version = data.optInt(KEY_VERSION)
            if (version >= 1) {
                return PluginCustomCommand(
                    id = data.optInt(KEY_ID).toLong(),
                    color = data.optInt(KEY_COLOR),
                    description = data.optString(KEY_DESCRIPTION),
                    displayOrder = data.optInt(KEY_DISPLAY_ORDER),
                    icon = data.optString(KEY_ICON),
                    param1 = data.optString(KEY_PARAM1),
                    param2 = data.optString(KEY_PARAM2),
                    param3 = data.optString(KEY_PARAM3),
                    param4 = data.optString(KEY_PARAM4),
                    param5 = data.optString(KEY_PARAM5),
                    readOnly = data.optBoolean(KEY_READ_ONLY, false),
                    source = data.optString(KEY_SOURCE),
                    title = data.optString(KEY_TITLE) ?: "",
                    type = data.optInt(KEY_TYPE),
                    uniqueId = data.optString(KEY_UNIQUE_ID)
                )
            }
            return PluginCustomCommand()
        }

        /**
         * Load from a [Bundle].
         *
         * @param src the Bundle
         */
        fun fromBundle(src: Bundle): PluginCustomCommand {
            val version = src.getInt(KEY_VERSION)
            if (version >= 1) {
                return PluginCustomCommand(
                    id = src.getInt(KEY_ID).toLong(),
                    color = src.getInt(KEY_COLOR),
                    description = src.getString(KEY_DESCRIPTION) ?: "",
                    displayOrder = src.getInt(KEY_DISPLAY_ORDER),
                    icon = src.getString(KEY_ICON) ?: "",
                    param1 = src.getString(KEY_PARAM1) ?: "",
                    param2 = src.getString(KEY_PARAM2) ?: "",
                    param3 = src.getString(KEY_PARAM3) ?: "",
                    param4 = src.getString(KEY_PARAM4) ?: "",
                    param5 = src.getString(KEY_PARAM5) ?: "",
                    readOnly = src.getInt(KEY_READ_ONLY) == 1,
                    source = src.getString(KEY_SOURCE) ?: "",
                    title = src.getString(KEY_TITLE) ?: "",
                    type = src.getInt(KEY_TYPE),
                    uniqueId = src.getString(KEY_UNIQUE_ID) ?: ""
                )
            }
            return PluginCustomCommand()
        }

        @JvmField
        val CREATOR: Creator<PluginCustomCommand> = object : Creator<PluginCustomCommand> {
            override fun createFromParcel(parcel: Parcel): PluginCustomCommand {
                return fromParcel(parcel)
            }

            override fun newArray(size: Int): Array<PluginCustomCommand?> {
                return arrayOfNulls(size)
            }
        }
    }
}