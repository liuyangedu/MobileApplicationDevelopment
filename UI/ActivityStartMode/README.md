# ActivityStartMode

## 1. Activity 的关系

所有 Activity 其实都是一个类：BaseActivity，不过为了能设置不同的启动模式，所以变成了好几个类。

## 2. SingleTask+Affinity 和 SingleInstance 的关系

设置了`Affinity`属性的 `SingleTask` 模式和 `SingleInstance`模式后，都会在一个单独的任务中启动该Activity。区别在于：前者的情况下，后续再打开standard 或者singleTop 等其他 Activity 时，会把其加入当前任务；后者会将其加入原任务。

简单版：SingleTask+Affinity 允许其任务中存在其他 Activity； SingleInstance 模式下，该任务中只有一个 Activity
