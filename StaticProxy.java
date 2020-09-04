package designPattern.proxy;

/**
 * @author ppz
 * @version 1.0
 * @date 2020/8/27 20:03
 * @description: 静态代理中，我们对目标对象的每个方法的增强都是手动完成的（后面会具体演示代码_），
 * 非常不灵活（_比如接口一旦新增加方法，目标对象和代理对象都要进行修改_）且麻烦(_需要对每个目标类都单独写一个代理类)。
 * 实际应用场景非常非常少，日常开发几乎看不到使用静态代理的场景。
 *
 * 上面我们是从实现和应用角度来说的静态代理，从 JVM 层面来说， 静态代理在编译时就将接口、实现类、代理类这些都变成了一个个实际的 class 文件。
 *
 * 静态代理实现步骤:
 *
 * 1. 定义一个接口及其实现类；
 * 2. 创建一个代理类同样实现这个接口
 * 3. 将目标对象注注入进代理类，然后在代理类的对应方法调用目标类中的对应方法。这样的话，
 * 我们就可以通过代理类屏蔽对目标对象的访问，并且可以在目标方法执行前后做一些自己想做的事情。
 */


public class StaticProxy {
    public static void main(String[] args) {
        SmsService smsService = new SmsServiceImpl();
        SmsProxy smsProxy = new SmsProxy(smsService);
        smsProxy.send("java");
    }
}

interface SmsService {
    String send(String message);
}

class SmsServiceImpl implements SmsService {
    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }
}


//创建代理类并同样实现发送短信的接口
class SmsProxy implements SmsService {

    private final SmsService smsService;

    public SmsProxy(SmsService smsService) {
        this.smsService = smsService;
    }

    @Override
    public String send(String message) {
        //调用方法之前，我们可以添加自己的操作
        System.out.println("before method send()");
        smsService.send(message);
        //调用方法之后，我们同样可以添加自己的操作
        System.out.println("after method send()");
        return null;
    }
}