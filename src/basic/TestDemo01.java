package basic;

public class TestDemo01 extends Person{
    public TestDemo01(){
        System.err.println("子类构造方法");
    }
    {
        System.out.println("子类构造代码块");
    }
    static {
        System.out.println("子类静态代码块");
    }

    public void student(){
        System.out.println("我是子类的方法");

    }

    public static void main(String[] args) {
        TestDemo01 testDemo01=new TestDemo01();
/*        testDemo01.student();//子类对象
        testDemo01.show();*/
        System.out.println("---------------------以上子类对象");
        Person person=new TestDemo01();//new 子类的对象
        ((TestDemo01) person).student();
        person.show();
        System.out.println("---------------------用父类new子类对象");
        Person parent =new Person();
        parent.show();//不能调用子类方法
        System.out.println("---------------------父类对象");
    }

}
class Person{
    private int value= 2;
    public Person(){
        System.out.println("父类构造方法");
    }
    {
        System.out.println("父类构造代码块");
    }
    static {
        System.out.println("父类静态代码块");
    }
    public void show(){
        System.out.println("父类show方法");
    }
}
