package designPattern.builder;

/**
 * @author ppz
 * @version 1.0
 * @date 2020/8/27 19:28
 * @description: 建造者模式主要包含四个角色 https://blog.csdn.net/qq_34337272/article/details/80540059
 * Product（产品角色）：一个具体的产品对象。
 * Builder（抽象建造者）：创建一个Product对象的各个部件指定的抽象接口。
 * ConcreteBuilder（具体建造者）：实现抽象接口，构建和装配各个部件。
 * Director（指挥者）：构建一个使用Builder接口的对象。它主要是用于创建一个复杂的对象。它主要有两个作用，一是：隔离了客户与对象的生产过程，二是：负责控制产品对象的生产过程。
 */


public class BuilderPatternDemo2 {
    public static void main(String[] args) {
        MealA a = new MealA();
        KFCWaiter waiter = new KFCWaiter(a);

        Meal2 mealA = waiter.construct();
        System.out.println("套餐A的组成部分:");
        System.out.println("食物："+mealA.getFood()+"；   "+"饮品："+mealA.getDrink());
    }
}

class Meal2 {
    private String food;
    private String drink;

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }
}

abstract class MealBuilder2 {
    Meal2 meal = new Meal2();

    public abstract void buildFood();

    public abstract void buildDrink();

    public Meal2 getMeal(){
        return meal;
    }
}

class MealA extends MealBuilder2{

    public void buildDrink() {
        meal.setDrink("可乐");
    }

    public void buildFood() {
        meal.setFood("薯条");
    }

}

class MealB extends MealBuilder2{

    public void buildDrink() {
        meal.setDrink("柠檬果汁");
    }

    public void buildFood() {
        meal.setFood("鸡翅");
    }

}

class KFCWaiter {
    private MealBuilder2 mealBuilder;

    public KFCWaiter(MealBuilder2 mealBuilder) {
        this.mealBuilder = mealBuilder;
    }

    public Meal2 construct(){
        //准备食物
        mealBuilder.buildFood();
        //准备饮料
        mealBuilder.buildDrink();

        //准备完毕，返回一个完整的套餐给客户
        return mealBuilder.getMeal();
    }
}