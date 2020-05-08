package com.basetools;

import android.app.Application;

/**
 * 反射初始化callPlugin的App类，只有使用Module方式引用时才需要处理</br>
 * </br>
 * 使用：
 * </br>
 * 1、
 * </br>
 * public class App extends Application implements IComponentApplication {</br>
 * </br>
 * }
 * </br>
 * 2、
 * </br>
 * try {</br>
 *      Class<?> clazz = Class.forName("com.xxx.xxx" + ".App");</br>
 *      Object obj = clazz.newInstance();</br>
 *      if (obj instanceof IComponentApplication) {</br>
 *          ((IComponentApplication) obj).init(MyApplication.getInstance());</br>
 *      }</br>
 * } catch (ClassNotFoundException e) {</br>
 *     e.printStackTrace();</br>
 * } catch (IllegalAccessException e) {</br>
 *     e.printStackTrace();</br>
 * } catch (InstantiationException e) {</br>
 *     e.printStackTrace();</br>
 * }</br>
 *
 * @author lvst
 */
public interface IComponentApplication {

    void init(Application application);

}
