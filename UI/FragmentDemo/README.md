# FragmentDemo

## 1. 实现Fragment的注意事项

1. 一定要写一个空白的无参数构造函数
2. 写若干个静态`newInstance`函数，用它来实现带参数地实例化Fragment
3. 若要和Activity互动，需要定义一个接口，并在 onAttach 函数中将Context转为该接口类型；同时显示它的Activitiy要实现这个接口
4. 建议通过右键--New--Fragment的方式新建

## 2. Replace的方式替换Fragment

主要思路:

1. Activity 被 `Create` 时，把第一个Fragment实例化，并`add`
2. 当要切换Fragment时，首先获取当前显示的Fragment，并判断是否需要改变；如果需要改变，则通过`replace`完成替换


## 3. HideAndShow的方式替换Fragment

主要思路:

1. 实例化所有Fragment, 全部`add`进来，除了要显示的那个之外，其他都`hide`
2. 当要切换Fragment时，先`hide`所有的Fragment，然后`show`目标Fragment
