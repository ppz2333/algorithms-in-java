package designPattern.singleton;

/**
 * @author ppz
 * @version 1.0
 * @date 2020/8/20 19:55
 * @description: 懒汉式单例,线程安全
 * 该模式的特点是类加载时没有生成单例，只有当第一次调用 getlnstance 方法时才去创建这个单例。
 *
 * 单例（Singleton）模式的定义：指一个类只有一个实例，且该类能自行创建这个实例的一种模式
 *  *
 *  * 单例模式有 3 个特点：
 *  *  单例类只有一个实例对象；
 *  *  该单例对象必须由单例类自行创建；
 *  *  单例类对外提供一个访问该单例的全局访问点；
 *  *
 *  * 单例模式是设计模式中最简单的模式之一。通常，普通类的构造函数是公有的，外部类可以通过“new 构造函数()”来生成多个实例。
 *  * 但是，如果将类的构造函数设为私有的，外部类就无法调用该构造函数，也就无法生成多个实例。
 *  * 这时该类自身必须定义一个静态私有实例，并向外提供一个静态的公有函数用于创建或获取该静态私有实例。
 *
 */


public class LazySingleton {

    //volatile 保证 instance 在所有线程中同步, 线程安全(不加也可以)
    private static volatile LazySingleton instance = null;

    //private 避免类在外部被实例化
    private LazySingleton() {}

    //getInstance 方法前加同步, 线程安全
    public static synchronized LazySingleton getInstance() {
        if(instance == null) {
            instance = new LazySingleton();
        }

        return instance;
    }

}
