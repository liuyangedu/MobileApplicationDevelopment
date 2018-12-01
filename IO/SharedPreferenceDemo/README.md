# SharedPreferenceDemo

## 1. 整体逻辑

App 中有2个 Activity。

MainActivity 为入口，这里需要输入用户名和密码，如果输入正确，则跳转到 EmptyActivity。

EmptyActivity 为登陆成功后的界面，本工程中是空的。

## 2. SharedPreference

在MainActivity 中，密码输入框下面，有一个复选框，用户可以选择是否记住账号密码。

- 如果选择记住这些信息，则在成功匹配用户名密码后，将用户名，密码和当前时间存入 SharedPreference
- 每次打开 App 的时候，都应该尝试读取 SharedPreference 中的信息，如果记录的信息没有超时，则自动填入用户名和密码
- 为了安全起见，一般在成功登陆后为清空 MainActivity 中两个输入框的内容。