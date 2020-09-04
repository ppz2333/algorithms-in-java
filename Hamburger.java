package designPattern.decorator;

/**
 * @author ppz
 * @version 1.0
 * @date 2020/8/22 16:37
 * @description:
 */


public class Hamburger {
    public static void main(String[] args) {
        Compont burger = new Bread();
        System.out.println("-----未装饰-----");
        System.out.println(burger.detail());

        ConcreteDecorator decorator = new ConcreteDecorator(burger);
        System.out.println("-----装饰后-----");
        System.out.println(decorator.detail());
    }


}

interface Compont {
    String detail();
}

class Bread implements Compont {
    @Override
    public String detail() {
        return "Bread";
    }
}

class Egg implements Compont {
    @Override
    public String detail() {
        return "Egg + ";
    }
}

abstract class ComponentDecorator implements Compont {
    protected Compont compont;

    public ComponentDecorator (Compont compont){
        super();
        this.compont = compont;
    }

    @Override
    public String detail() {
        return compont.detail();
    }
}

class ConcreteDecorator extends ComponentDecorator {
    public ConcreteDecorator(Compont compont) {
        super(compont);
    }

    public String decorate() {
        return new Egg().detail();
    }
    @Override
    public String detail() {
        return decorate() + super.detail();
    }
}

