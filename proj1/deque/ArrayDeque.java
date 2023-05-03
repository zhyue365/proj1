/** 2. 基于数组的Deque
 注意：除了迭代器，其他所需知识在lecture 7 (2/03)中均已涉及。迭代器相关知识会在lecture 11 (2/12) 里学到
 在你的 proj1/deque 目录中新建名为 ArrayDeque.java 的文件。同样，使用关键字 package 声明其属于包 deque 。
 作为你的第二个deque实现，你需要实现类 ArrayDeque ，一个使用数组作为核心数据结构的deque实现。在此实现中，
 你实现的操作必须满足如下规则：add 和 remove 语句的用时必须是常数时间，除了数组长度改变时,get 和 size 的用时必须是常数时间
 数组的初始大小应为8,你的程序的内存用量应与元素数尽量成正比。举个例子，假设你添加了10000个元素，然后移除了9999个元素。
 你就不应该继续用数组的第10000位了。对于长度在16及以上的数组，你至少要用到25%的数组空间。这意味着执行一个移除操作后若占用率小于25%，
 我们应减小数组的长度。对于长度在16以下的数组，占用率不做要求。实现所有列在 ” The Deque API ” 一节中的方法。
 除此之外，你还得实现：
 public ArrayDeque(): 新建一个空的基于数组的deque
 如果你觉得有必要，可以往 ArrayDeque.java 添加必要的类与方法来帮助你的编程。
 你需要用某种方式追踪Deque头尾元素在数组中的位置。在这个项目中，我们强烈建议你把数组当成圆的。换句话说，如果你的头元素在0号位，
 然后你执行了一次 addFirst ，新的头端应该位于数组的末端（也就是数组的最后一个元素）。*/
package deque;

public class ArrayDeque<ZHY> {
    private ZHY[] items;
    /** 队列头 */
    private int front;
    /** 队列尾 */
    private int rear;
    /** 队列容量 */
    private int size;

    public ArrayDeque() {
        items = (ZHY[]) new Object[8];
        size = 0;
        front = 0;
        rear = 0;
    }

    /** 队列容量 */
    public int size(){
        return (rear + items.length - front) % items.length ;
    }
    /** 判断队列为空 */
    public boolean isEmpty() {
        return rear == front;
    }
    /** 前一位*/
    private int One_step_ahead(int index) {
        return Math.floorMod(index - 1, items.length);
    }

    /** 后一位 */
    private int Backward_one(int index) {
        return Math.floorMod(index + 1, items.length);
    }

    private int Backward_one(int index, int length) {
        return Math.floorMod(index + 1, length);
    }

    public void addFirst(ZHY item) {
        resize();
        items[front] = item;
        size++;
        front = One_step_ahead(front);
    }

    private ZHY getFirst() {
        return items[Backward_one(front)];
    }

    public ZHY removeFirst() {
        if (isEmpty()) {
            return null;
        }
        resize();
        ZHY removedItem = getFirst();
        front = Backward_one(front);
        items[front] = null;
        size--;
        return removedItem;
    }

    public void addLast(ZHY item) {
        resize();
        items[rear] = item;
        size++;
        rear = Backward_one(rear);
    }
    private ZHY getLast() {
        return items[One_step_ahead(rear)];
    }

    public ZHY removeLast() {
        if (isEmpty()) {
            return null;
        }
        resize();
        ZHY removedItem = getLast();
        rear =One_step_ahead(rear);
        items[rear] = null;
        size--;
        return removedItem;
    }

    public void printDeque() {
        for (int i =Backward_one(front);i != rear;i = Backward_one(i)){
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    public ZHY get(int index) {
        if (index < 0 || index >= size || isEmpty()) {
            return null;
        }
        index = Math.floorMod((Backward_one(front)+index),items.length);
        //index = Math.floorMod((front)+index,items.length);
        return items[index];
    }
    /** 当数组中没有位置可以容纳更多的item时 ---> 扩容 ---->容量*2
     当数组的格子仅有不到1/4被占用且格子的数量大于初始的格子数量（8）时--->缩容 --->容量减半
     扩容/缩容步骤---->
     创建一个临时的数组(temp)，将其指针指向原数组(original)。（将原数组的数据储存到这个临时的数组）
     创建一个全新的数组(newArray)，且指定容量（扩大/缩小）
     遍历临时数组(temp)，将临时数组中的数据读取到全新的数组(newArray)中去。*/
    private void resize() {
        if (size == items.length) {
            expand();
        }
        if (size < (items.length) * 0.25 && items.length > 8) {
            reduce();
        }
    }

    private void expand() {
        resizeHelper(items.length * 2);
    }

    private void reduce() {
        resizeHelper(items.length / 2);
    }

    private void resizeHelper(int capacity) {
        ZHY[] tempArr = items;
        int begin = Backward_one(front);
        int end = One_step_ahead(rear);
        items = (ZHY[]) new Object[capacity];
        front = 0;
        rear = 1;
        for (int i = begin; i != end; i = Backward_one(i, tempArr.length)) {
            items[rear] = tempArr[i];
            rear = Backward_one(rear);
        }
        items[rear] = tempArr[end];
        rear = Backward_one(rear);
    }


    public static void main(String[] args) {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<Integer>();
        for (int i = 0; i < 20; i++) {
            arrayDeque.addLast(i);
        }
        arrayDeque.printDeque();
        for (int i = 0; i < 15; i++) {
            System.out.print(arrayDeque.removeFirst()+" ");
        }
        arrayDeque.printDeque();

        System.out.println(arrayDeque.get(5));
    }
}
