package principle;

public class Inversion {
    public static void main(String[] args) {
        new OpenAndClose().open(new Changhong());

    }
}
interface IOpenAndClose{
    public void open(Itv tv);
}
interface Itv{
    public void play();
}
class Changhong implements Itv{

    @Override
    public void play() {
        System.out.println("长虹打开");
    }

}
//实现接口
class OpenAndClose implements IOpenAndClose{

    @Override
    public void open(Itv tv) {
        tv.play();
    }
}