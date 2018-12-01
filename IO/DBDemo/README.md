# DBDemo

## 1. 整体逻辑

两个 Activity。MainActivity 为入口，展示了所有存储的备忘录的主要信息（包括标题和修改时间）。ContentActivity 为备忘录编辑界面。

## 2. 数据库读写

安卓中要读写数据库，一般至少要写两个类，一个类为 Contract，一个类为 SQLiteOpenHelper。

- 一个Contract 类对应数据库中的一个库。Contract 类中再写内部类，一个内部类对应库中的一张表。每个内部类一般需要实现接口`BaseColumns`，这样它就自带列：`_ID`和`_COUNT`。每个内部类需要为表中每一列写一个成员变量。每个内部类还需要写创建表和删除表的 SQL 语句
- SQLiteOpenHelper类为辅助操作数据库的类。重写时重点写函数`onCreate`，它负责在数据库中创建所有的表。还有一个`onUpgrade`函数，每个数据库都有版本号，当版本更新时，会自动调用该函数，负责完成数据迁移工作。通过自己写的SQLiteOpenHelper的函数`getWritableDatabase`和`getReadableDatabase`分别获取可写和可读的数据库实例，通过调用数据库实例的`insert`,`update`,`query`,`delete`完成增删改查的功能。

## 3. 其他注意事项

从 MainActivity 跳转到 ContentActivity 时都是`startActivityForResult`。

需要注意新建备忘录和修改备忘录这两种情况下，两个 Activity间 Intent 的内容。如果是新建，则 ID 为-1，否则 ID 是数据库中这一行记录的唯一 ID。

