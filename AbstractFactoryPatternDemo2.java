package designPattern.abstractFactory;

/**
 * @author ppz
 * @version 1.0
 * @date 2020/8/27 19:15
 * @description:
 */


public class AbstractFactoryPatternDemo2 {
    public static void main(String[] args) {
        Factory factory;
        Gun gun;
        Bullet bullet;

        factory = new AKFactory();
        bullet = factory.produceBullet();
        bullet.load();
        gun = factory.produceGun();
        gun.shooting();
    }
}


interface Gun {
    public void shooting();
}

interface Bullet {
    public void load();
}

class AK implements Gun {
    @Override
    public void shooting() {
        System.out.println("shooting with AK");
    }
}

class M4A1 implements Gun {
    @Override
    public void shooting() {
        System.out.println("shooting with M4A1");
    }
}

class AKBullet implements Bullet {
    @Override
    public void load() {
        System.out.println("Load bullets with AK");
    }
}

class M4A1Bullet implements Bullet {
    @Override
    public void load() {
        System.out.println("Load bullets with M4A1");
    }
}


interface Factory {
    public Gun produceGun();
    public Bullet produceBullet();
}

class AKFactory implements Factory {
    @Override
    public Gun produceGun() {
        return new AK();
    }

    @Override
    public Bullet produceBullet() {
        return new AKBullet();
    }
}

class M4A1Factory implements Factory {
    @Override
    public Gun produceGun() {
        return new M4A1();
    }

    @Override
    public Bullet produceBullet() {
        return new M4A1Bullet();
    }
}



