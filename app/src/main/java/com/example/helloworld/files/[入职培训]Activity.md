# [入职培训]Activity

## Activity生命周期

### 返回栈

![image-20220802135344290](C:\Users\Tianyu.Fu\AppData\Roaming\Typora\typora-user-images\image-20220802135344290.png)

### Activity状态

每个Activity运行过程中最多有四种状态:

1. 运行状态

当activity位于**栈顶时，此时处于运行状态**，系统最好不回收运行状态的activity，因为会给用户带来不好体验

2. 暂停状态

activity**不再位于栈顶但依然可见时，处于暂停状态**，比如对话框等不占满整个屏幕，一般系统也不会回收

3. 停止状态

**activity不位于栈顶，并且完全不可见时处于停止状态**。系统仍会为这种activity 提供成员变量和保存响应状态，但如果有其他地方需要内存，系统有可能回收暂停状态的activity

4. 销毁状态

当activity从返回栈中移除，系统很可能会回收这些activity的内存。

---



### Activity生命周期

**Activity七个回调方法**

+ onCreate() : 完成Activity各种初始化操作，在Activity第一次被创建时调用。（加载布局、绑定事件）
+ onStart()：Activity由不可见转为可见状态，对资源进行加载
+ onResume() ：准备与用户开始交互，activity位于返回栈栈顶
+ onPause():系统启动或者恢复另一个Activity时，将资源释放，保存关键数据释放CPU资源。结束用户交互
+ onStop():对话框类的activity执行onPause()，其他执行onStop()。结束可见状态
+ onDestory():销毁activity
+ onRestart()：将activity由停止状态变为运行状态时调用

三种生存期：

+ 完成生存期：从onCreate()到onDestory() :从Activity被创建初始化到被销毁
+ 可见生存期：从onStart()到onStop():从资源加载对用户可见，到资源回收对用户不可见
+ 前台生存期：从onResume()到onPause()，activity总是处于活动状态，这个时期均可与用户交互

![image-20220802152018762](C:\Users\Tianyu.Fu\AppData\Roaming\Typora\typora-user-images\image-20220802152018762.png)



---

## Activity四种启动模式

四种启动模式，在**Manifest中<activity>指定android:launchMode=" "** 设置：

+ *standard*
+ *singleTop*
+ *singleTask*
+ *singleInstance*

---

## 生命周期学习理解



