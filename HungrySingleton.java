package designPattern.singleton;

/**
 * @author ppz
 * @version 1.0
 * @date 2020/8/20 20:00
 * @description: 饿汉式单例 线程安全
 * 该模式的特点是类一旦加载就创建一个单例，保证在调用 getInstance 方法之前单例已经存在了。
 *
 * 饿汉式单例在类创建的同时就已经创建好一个静态的对象供系统使用，以后不再改变，所以是线程安全的，可以直接用于多线程而不会出现问题。
 *
 * 所谓 “饿汉方式” 就是说JVM在加载这个类时就马上创建此唯一的单例实例，不管你用不用，先创建了再说，
 * 如果一直没有被使用，便浪费了空间，典型的空间换时间，每次调用的时候，就不需要再判断，节省了运行时间。
 */


public class HungrySingleton {

    //在静态初始化器中创建单例实例，这段代码保证了线程安全
    private static final HungrySingleton instance = new HungrySingleton();

    //Singleton类只有一个构造方法并且是被private修饰的，所以用户无法通过new方法创建该对象实例
    private HungrySingleton(){}

    public static HungrySingleton getInstance() {
        return instance;
    }

}
