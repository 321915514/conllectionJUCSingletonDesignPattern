package ocp;

public class ocp {
    public static void main(String[] args) {
        new GraphicEditor().drawShape(new Circle());
    }

}
class GraphicEditor{
    public void drawShape(Shape s){
        s.draw();
    }
}
abstract class Shape{
    int m_type;
    public abstract void draw();
}
class Rectangle extends Shape{
public Rectangle(){
    super.m_type=1;
}
    @Override
    public void draw() {
        System.out.println("矩形");
    }
}
class Circle extends Shape{

    @Override
    public void draw() {
        System.out.println("园");
    }
}