package generic;

public class GenericTest<T> {
    private T key;

    private GenericTest(T key) {
        this.key = key;
    }

    private T getFurit(T t){
        return t;

    }

    public static void main(String[] args) {
        System.out.println(new GenericTest<Number>(12154454).getFurit(123));
    }
}
