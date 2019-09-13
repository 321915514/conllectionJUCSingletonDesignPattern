package list.mylist;
class test{
    public static void main(String[] args) {
        MyList list=new MyList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
        list.add(11);
        list.add(12);
    }
}
public class MyList {
    //需要一个数组
    private Object[] elementData;
    //还需要一个size
    private int size;
    //初始化,采用构造方法
    public MyList() {
        //定义初始长度为10
        elementData = new Object[10];
    }
    public void add(Object obj){
        //判断,如果长度大于elementData的长度,我们就扩容
        if(size>=elementData.length){
          Object[] temp=new Object[elementData.length*2];
          //将原数组的内容copy到新的数组
            System.arraycopy(elementData,0,temp,0,size);
            elementData=temp;
        }
        //将
        elementData[size++]=obj;
    }
}
