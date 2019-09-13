package principle;

import java.util.Optional;

public class Segregation {
    public static void main(String[] args) {
        A a = new A();
        a.depend1(new B());
        a.depend2(new B());//
        C c = new C();
        c.depend1(new D());
    }


}
interface interface1{
    void operation1();
}
interface interface2{
    void operation1();
    void operation2();
}
interface interface3{
    void operation1();
}
class B implements interface1,interface2{

    @Override
    public void operation1() {
        System.out.println("B operation1");
    }

    @Override
    public void operation2() {
        System.out.println("B operation2");
    }
}
class D implements interface2,interface3{

    @Override
    public void operation1() {
        System.out.println("D operation1");
    }

    @Override
    public void operation2() {
        System.out.println("D operation2");
    }
}
class A{
    public void depend1(interface1 i){
        i.operation1();
    }
    public void depend2(interface2 i){
        i.operation2();
    }
}
class C{
    public void depend1(interface2 i){
        i.operation1();
    }
    public void depend2(interface3 i){
        i.operation1();
    }
}