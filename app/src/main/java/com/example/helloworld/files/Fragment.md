# Fragment

## 一.Fragment与Activity区别 

+ Activity为系统四大组件，由ActivityManager管理，生命周期由系统管理。**Fragment为安卓3.0后引入组件，由FragmentManager管理，需要在Activity中使用所以也受Activity管理，引入和删除比较方便。**
+  相比较与Activity来说更加灵活，**可以在XML文件中直接进行写入，也可以在Activity中动态添加**；
+ 可以使用**show()/hide()**或者**replace()**随时对**Fragment**进行切换，并且切换的时候不会出现明显的效果，用户体验会好

+ **生命周期**: Fragment从显示到被销毁会执行自己生命周期，同时也受到Actibity生命周期影响，比如Activity执行onPause()那么Fragment也会执行onPause()

+ Activity有七个生命周期：

1. onCreate()
2. onStart
3. onResume
4. onPause()
5. onStop
6. onDestoy()
7. onRestart()

**Fragment生命周期**

1. onAttach()
2. onCreate()
3. onCreateView()
4. onActivityCreate()
5. onStart()
6. onResume()
7. onPause()
8. onStop()
9. onDestoryView()
10. onDestory()
11. onDetach()

---



## 二.Fragment生命周期

+ **当一个Activity中存在左右两个Fragment时，显示页面的生命周期顺序为：**
  + 1. 左边Fragment执行 onAttach()、onCreate()、onCreateView()
    2. 右边Fragment执行 onAttach()、onCreate()、onCreateView()
    3. Activity执行onCreate()
    4. Activity创建完毕，左右Fragment执行onActivityCreate()
    5. 左右Fragment执行onStart()
    6. 当所有Fragment执行到onStart()以后，当前Activity执行onStart、onResume
    7. 所有Fragment执行onResume()
    8. =======================所有Fragment已显示，用户可与屏幕交互=======================

+ **当前Acvtivity退到后台时，所有生命周期发生顺序**
+ 1. 左右Fragment执行onPause()
  2. Activity执行onPause()
  3. 左右Fragment执行onStop()
  4. Acitvity执行onStop()
  5. =======================已退至后台=======================

+ **当前Activity从后台返回时，所有生命周期执行顺序**
+ 1. 之前显示页面、退至后台均为先执行Fragment生命周期，在执行Activity
  2. Activity执行onRestart()
  3. 所有Fragment执行onStart()
  4. Activity执行onStart() 、onResume()
  5. 所有Fragment执行onResume()
  6. =======================已返回前台=======================

+ **用户点击返回，当前Activity销毁**
+ 1. 所有Fragment执行 onPause()
  2. Activity 执行onPause()
  3. 所有Fragment执行 onStop()
  4. Activity 执行onStop()
  5. 左边Fragment依次执行 onDestroyView、onDestroy、onDetach，依次销毁Fragment相关视图、Fragment、解除与Activity连接
  6. 右边Fragment依次执行 onDestroyView、onDestroy、onDetach，依次销毁Fragment相关视图、Fragment、解除与Activity连接
  7. Activity 执行onDestoy()
  8. =======================Activity和Fragment均已被销毁=======================

+ **执行replace()方法 替换 右侧Fragment**，生命周期执行顺序
+ 1. 左边Fragment执行 onAttach()、onCreate()、onCreateView()
  2. 右边Fragment执行 onAttach()、onCreate()、onCreateView()
  3. Activity执行onCreate()
  4. Activity创建完毕，左右Fragment执行onActivityCreate()
  5. 左右Fragment执行onStart()
  6. 当所有Fragment执行到onStart()以后，当前Activity执行onStart、onResume
  7. 所有Fragment执行onResume()
  8. =======================所有Fragment已显示，用户可与屏幕交互=======================
  9. ======================点击切换右侧的Fragment后=======================
  10. **原本右侧Fragment执行onPause()、onStop()**
  11. **新的右侧Fragment执行初始化方法， onAttach()、onCreate()、onCreateView()、onActivityCreate()、onStart()**
  12. **原本右侧Fragment执行  onDestroyView、onDestroy、onDetach，依次销毁Fragment相关视图、Fragment、解除与Activity连接**
  13. **新的右侧Fragment执行onResume()**

+ **执行add()方法 切换右侧Fragment**， 生命周期执行顺序
+ 1. **新的右侧Fragment执行 onAttach()、onCreate()、onCreateView()、onActivityCreate()、onStart()、onResume()**
  2. 原本的右侧Fragment并不会执行任何方法，也就是说add只是Fragment的显示与隐藏

---

## 三.切换Fragment两种方式以及区别

+ 比如下面Activity实现两个Fragment之间的切换，其中 **replaceRightFragment()方法用于切换右侧两个Fragment**
+ **两种方式都需要通过fragmentManager获取Fragment事务，然后通过transaction执行add或者replace实现切换Fragment**
+ **不同点在**
  + replace()是先让旧的Fragment隐藏，创建新的Fragment之后销毁原来的Fragment **--- 直接覆盖**
  + add() 只执行新的Fragment创建的生命周期，并不会对旧的有影响。**----显示隐藏**
+ 如果每次切换都是new一个新的fragment进行切换，那么上述两个方法均没有影响
+ 但是如果在类中创建好原本和新的右侧Fragment实例，比如下面代码中rightFragment和anotherRightFragment，**此时使用add**方法从A ->B没问题，但是再次从B->A则会有问题，但使用replace()就不会有错误
+ **原因：**replace()方法每次切换等于先执行remove()移除transaction中的fragment后再执行add进去新的Fragment，但是add()没remove所以transaction不能重复添加同一个fragment对象

```java
/**
 * 测试Fragment生命周期
 * 同时监测Activity与Fragment生命周期如何交替
 */
public class FragmentTestLifecycle extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "FragmentTestLifecycle";
    //判断当前显示是否为原来的右侧Fragment
    private boolean isRightFragment;
    //原本右侧的Fragment
    private final TestLifecycleRightFragment rightFragment = new TestLifecycleRightFragment();
    //另一个右测的Fragment
    private final TestLifecycleAnotherRightFragment anotherRightFragment = new TestLifecycleAnotherRightFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_test_lifecycle);
        Log.i(TAG, "onCreate: " + "--Activity --" + "test lifecycle");
        Button replaceButton = findViewById(R.id.fragment_test_lifecycle_left_btn);
        replaceButton.setOnClickListener(this);
        //首先添加原来的右侧Fragment
        replaceRightFragment(rightFragment);
        isRightFragment = true;
    }

    /**
     * 实现View.OnClickListener点击事件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_test_lifecycle_left_btn:
                if (isRightFragment) {
                    replaceRightFragment(anotherRightFragment);
                }else{
                    replaceRightFragment(rightFragment);
                }
                isRightFragment = !isRightFragment;
                break;
            default:
                break;
        }
    }

    /**
     * 更换右侧Fragment
     */
    private void replaceRightFragment(Fragment fragment) {
        //通过getSupportFragmentManager获得FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        //开启一个事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //replace方法 参数为需要传入的容器id和待添加的Fragment实例
        transaction.replace(R.id.fragment_test_lifecycle_right, fragment);
//        transaction.add(R.id.fragment_test_lifecycle_right, fragment);
        transaction.commit();//提交事务
    }
}
```