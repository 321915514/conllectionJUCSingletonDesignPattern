package list.LinkedList;

import java.util.LinkedList;
import java.util.List;

public class MyLinkedList {
    private Node first;
    private Node last;
    private int size;
    public void add(Object obj){
        Node node=new Node();
        if (first==null){
            node.pre=null;
            node.next=null;
            node.setObj(obj);
        }else{
            node.pre=last;
            node.next=null;
            last.next=node;
            node.setObj(obj);
            last=node;
        }
    }
}
