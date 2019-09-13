package basic;

public class TestCode {
    static{
        System.out.println("1");
    }
    {
        System.out.println("2");
    }
    public TestCode(){
        System.out.println("3");
    }

    public static void main(String[] args) {
        new TestCode();
    }
}
