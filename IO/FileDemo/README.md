# FileDemo

## 1. 整体逻辑

只有一个 Activity。

整体界面为 LinearLayout。依次为状态栏，文本编辑栏，按钮组。

按钮组中为 LinearLayout 的嵌套，共6个按钮，分别对应内部存储的读/写，外部存储公共部分的读/写，外部存储私有部分的读/写

## 2. 申请权限

文件的读写一般需要申请权限。权限申请分两种：

- 第一种为在 Manifests 文件中申请，通过增加元素`uses-permission`申请权限。这里申请的权限在安装应用时能看到
- 第二种为在 Java 代码中申请，具体见函数`checkPermission`、`applyPermission`和`onRequestPermissionsResult`。一些较敏感的权限除了在 Manifests 中申请之外，还需要在 Java 代码中再申请一次，申请时用户会看到提示。

## 3. 文件读写

安卓中的文件读写关键在于正确获取权限和正确获取文件路径。在新版本的安卓中，外部存储的公共部分属于敏感权限，需要同时在 Manifests 和 Java 中申请。

获取权限和路径后，使用标准的 Java 库函数读写文件即可。