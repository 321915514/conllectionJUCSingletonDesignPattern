package Liskov;

public class Liskov{
    public static void main(String[] args) {

    }



}
class fun{

}
class A extends fun{
    public int func1(int num1,int num2){
        return num1+num2;
    }
}
class B extends fun{
    private A a =new A();
    public int func2(int num1,int num2){
        return a.func1(num1,num2)+9;
    }

}
