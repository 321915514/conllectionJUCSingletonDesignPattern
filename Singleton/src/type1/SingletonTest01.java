package type1;
//饿汉式(静态)
public class SingletonTest01 {
    public static void main(String[] args) {
        System.out.println(Singleton.getInstance());
        System.out.println(Singleton1.getInstance());
        Singleton06 singleton06 = Singleton06.INSTANCE;
        Singleton06 singleton07 = Singleton06.INSTANCE;
        System.out.println(singleton06==singleton07);
    }



}
class Singleton{
    private Singleton(){}//外部不能new

//本类内部创建对象实例
    private static final Singleton singleton=new Singleton();
//对外提供一个共有的静态方法,返回实例对象
    public static Singleton getInstance(){
        return singleton;
    }
}
//饿汉式(静态代码块)
class Singleton1{
    private static Singleton1 singleton1;
        static {//在静态代码块中创建单例
            singleton1=new Singleton1();
        }
        private Singleton1(){}
        public static Singleton1 getInstance(){
            return singleton1;
        }
}
//懒汉式(线程不安全)
class Singleton02{
    private static Singleton02 singleton02;
    private Singleton02(){}
    //懒汉式
    public static Singleton02 getInstance(){
        if (singleton02==null){
            singleton02=new Singleton02();
        }
        return singleton02;
    }
}
//懒汉式(线程安全,效率低)
class Singleton03{
    private static Singleton03 singleton03;
    private Singleton03() {
    }
    //懒汉式
        public synchronized static Singleton03 getInstance () {
        if (singleton03 == null) {
            singleton03 = new Singleton03();
        }
        return singleton03;
    }
}
//懒汉式 (双重检查)
class Singleton04 {
    private static volatile Singleton04 singleton04;

    private Singleton04() {}
    //懒汉式(双重检查)
    public static Singleton04 getInstance() {
        if (singleton04 == null) {
            synchronized (Singleton04.class) {
                if (singleton04 == null) {
                    singleton04 = new Singleton04();
                }
            }
        }
        return singleton04;
    }
}
//静态内部类实现单例
class Singleton05{
    private Singleton05(){}
    //
    private static  class SingletonInstance{
        private static final Singleton05 INSTANCE=new Singleton05();
    }
    public static Singleton05 getInstance(){
        return SingletonInstance.INSTANCE;
    }
}
//枚举类单例
enum Singleton06{
    INSTANCE;
}

