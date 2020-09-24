# TwoEntryPoint

## 1. 增加入口点

本 App 包含两个 Activity：MainActivity 和 MainActivity2

在 AndroidManifest.xml 中，它们两个都增加了 `intent-filter`

```
<intent-filter>
    <action android:name="android.intent.action.MAIN" />
    <category android:name="android.intent.category.LAUNCHER" />
 </intent-filter>
```

安装好后，系统中会出现两个图标，一个叫 `Main`，另一个叫 `Second`。它们的图标也不一样，点击不同的图标会进入不同的 Activity

## 2. 修改名字和图标

AndroidManifest.xml 中可以通过给 activity 标签增加属性`label`和`icon`指定其名字和图标，例如：

```
<activity android:name=".Main2Activity"
          android:label="Main2"
          android:icon="@android:drawable/ic_lock_lock">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />
        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>
```

其中`@android:drawable/ic_lock_lock`为系统自带的图标