package deque;

import jh61b.junit.In;

public class LinkedListDeque<ZHY> {
    //创建节点类
    public class Intnode{
        public ZHY item;
        public Intnode next;
        public Intnode prev;

        public Intnode(ZHY i, Intnode n,Intnode p){
            item = i;
            next = n;
            prev = p;
        }
    }

    public int size;
    public Intnode sentinel;
    //创建空列表
    public LinkedListDeque(){
        sentinel = new Intnode(null,null,null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }
    public LinkedListDeque(LinkedListDeque<ZHY> other){
        sentinel = new Intnode(null,null,null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;

        for (int i = 0;i < other.size();i++){
            addLast(other.get(i));
        }
    }
    /** 初始化一个非空链表 */
    public LinkedListDeque(ZHY item){
        sentinel = new Intnode(null,null,null);
        sentinel.next = new Intnode(item,null,null);
        sentinel.prev = sentinel.next;
        size = 1;
    }
    /** 在链表头加入数据 */
    public void addFirst(ZHY item){
        sentinel.next.prev = new Intnode(item,sentinel.next,sentinel);
        sentinel.next = sentinel.next.prev;
        size++;
    };
    /** 在链表尾加如数据 */
    public void addLast(ZHY item){
        sentinel.prev.next = new Intnode(item,sentinel,sentinel.prev);
        sentinel.prev = sentinel.prev.next;
        size++;
    }
    /** 判断链表是否为空 */
    public boolean isEmpty(){
        if (sentinel.next == sentinel){
            return true;
        }
        return false;
    }
    /** 返回链表长度 */
    public int size(){
        return size;
    }
    /** 打印链表 */
    void printDeque(){
        Intnode p = sentinel;
        while(p.next != sentinel){
            System.out.print(p.next.item + " ");
            p = p.next;
        }
        System.out.println();
    }
    /** 删除表头数据 */
    public ZHY removeFirst(){
        if (isEmpty()){
            size = 0;
            return null;
        }
        ZHY i = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        return i;
    }
    /** 删除表尾数据 */
    public ZHY removeLast(){
        if (isEmpty()){
            size = 0;
            return null;
        }
        ZHY i = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size--;
        return i;
    }
    /** 取index处的值 */
    public ZHY get(int index){
        if (index > (size - 1)){
            return null;
        }
        Intnode p = sentinel;
        for (int i = 0;i <=index;i++){
            p=p.next;
        }
        return p.item;
    }
    public ZHY getRecursiveHelp(Intnode a, int i){
        if(i == 0){
            return a.next.item;
        }
        else {
            a = a.next;
            i--;
        }
        return getRecursiveHelp(a, i);
    }
    public ZHY getRecursive(int index){
        if (index > (size - 1)){
            return null;
        }
        Intnode p = sentinel;
        return getRecursiveHelp(p ,index);
    }
    public static void main(String[] args){
        LinkedListDeque<Integer> t = new LinkedListDeque<>();
        t.addFirst(1);
        t.removeFirst();
        t.removeFirst();
        t.removeFirst();
        t.removeFirst();
        for (int i = 0; i < 10; i++){
            t.addLast(i);
        }
        t.printDeque();
        System.out.println(t.get(4));
//        t.addFirst(2);
//        t.addFirst(3);
//        t.addLast(100);
//        t.addLast(101);
        System.out.println(t.size);
//        System.out.println(t.isEmpty());
//        t.printDeque();
//        t.removeFirst();
//        t.printDeque();
//        System.out.println(t.get(0));
//        System.out.println(t.get(2));
//        System.out.println(t.getRecursive(2));

    }
}
