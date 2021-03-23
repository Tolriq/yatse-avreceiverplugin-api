# Yatse Audio/Video receiver plugin API

API and Sample for Yatse Audio/Video receiver plugins

Current version : **2.0** (Stable)

Version 2.0 is aimed for Kotlin usage, see 1.0 branch for previous Java version.

(Sample code is a quick Kotlin migration as helper to start, not best practice coding)

[api](https://github.com/Tolriq/yatse-avreceiverplugin-api/tree/master/api) folder contains the API to include in your project.

[sample](https://github.com/Tolriq/yatse-avreceiverplugin-api/tree/master/sample) folder contains a fully working plugin that shows everything you need to write a complete plugin.

Javadoc of the API and the sample plugin should describe everything you need to get started.

## Quick start

* Clone this repository.
* Generate a new unique id for your plugin (UUID v4) (You can use for example : https://www.uuidgenerator.net/ )
* Edit [your unique id](https://github.com/Tolriq/yatse-avreceiverplugin-api/blob/master/sample/src/main/res/values/strings.xml#L29) in the Strings.xml file
* Add your custom code to [AVPluginService](https://github.com/Tolriq/yatse-avreceiverplugin-api/blob/master/sample/src/main/java/tv/yatse/plugin/avreceiver/sample/AVPluginService.kt)

Of course do not forget to update your plugin name, description, icons and strings.

And edit [SettingsActivity](https://github.com/Tolriq/yatse-avreceiverplugin-api/blob/master/sample/src/main/java/tv/yatse/plugin/avreceiver/sample/SettingsActivity.kt) and [CustomCommandsActivity](https://github.com/Tolriq/yatse-avreceiverplugin-api/blob/master/sample/src/main/java/tv/yatse/plugin/avreceiver/sample/CustomCommandsActivity.kt) so they fit your plugin needs.

## Documentation

The [Wiki](https://github.com/Tolriq/yatse-avreceiverplugin-api/wiki) will contain the up to date documentation for each API versions.

If something is missing just contact me.

## Release

When your plugin works and you are ready to publish it to the world you need to ensure that the description contains the following :

> This plugin is not affiliated to Yatse or its author Tolriq / Genimee.
>
> Please request support directly from the author of this plugin : XXX (For PlayStore can be : See contact information below. or a website URL)

Adding a support button in the configuration activity so users can contact you, is a great addition too.