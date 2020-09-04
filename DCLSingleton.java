package designPattern.singleton;

/**
 * Double Checked Locking
 * 双重检验锁实现对象单例
 *
 *这种方式采用双锁机制，安全且在多线程情况下能保持高性能。
 * 大大减少 getInstance() 的时间消费对应用程序性能很关键。
 *
 *
 * 在synchronized的懒汉式单例中，程序每次使用getInstance() 都要经过synchronized加锁这一层，
 * 这难免会增加getInstance()的方法的时间消费，而且还可能会发生阻塞。
 * 我们下面介绍到的 双重检查加锁版本 就是为了解决这个问题而存在的。
 *
 * 利用双重检查加锁（double-checked locking），首先检查是否实例已经创建，
 * 如果尚未创建，“才”进行同步。这样以来，只有一次同步，这正是我们想要的效果。
 */
public class DCLSingleton {

    //volatile保证，当instance变量被初始化成Singleton实例时，多个线程可以正确处理instance变量
    private volatile static DCLSingleton instance;

    private DCLSingleton() {}

    public static DCLSingleton getInstance() {
        //先判断对象是否已经实例过，没有实例化过才进入加锁代码
        if(instance == null) {
            //只有第一次才彻底执行这里的代码,类对象加锁
            synchronized (DCLSingleton.class) {
                //进入同步代码块后，再检查一次，如果仍是null，才创建实例
                if(instance == null) {
                    instance = new DCLSingleton();
                }
            }
        }
        return instance;
    }


}
