package list.LinkedList;

public class Node {
    public Node pre;
    public Node next;
    public Object obj;
    public void setObj(Object obj){
        this.obj=obj;
    }
    public Node(Node pre, Node next, Object obj) {
        this.pre = pre;
        this.next = next;
        this.obj = obj;
    }

    public Node() {
    }
}
