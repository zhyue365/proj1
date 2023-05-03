package deque;

public interface Deque<T> {
    /** 在队列前端添加一个类型为T的元素，你可以默认参数 item 永远不会是 null */
    void addFirst(T item);
    /** 在队列后端添加一个类型为T的元素，你可以默认参数 item 永远不会是 null */
    void addLast(T item);
    /** 如果此队列是空的，返回 true ；如果此队列有元素，返回 false */
    default boolean isEmpty() {
        return size() == 0;
    }
    /** 返回队列内的元素数 */
    int size();
    /** 按照从队列的前端到后端的顺序，打印队列内的所有元素，每个元素间用空格隔开。*/
    void printDeque();
    /**从队列中移除排在最前端的元素，并返回该元素，如果该元素不存在，返回 null */
    T removeFirst();
    /** 从队列中移除排在最后端的元素，并返回该元素，如果该元素不存在，返回 null */
    T removeLast();
    /**照给定的索引返回相应的元素，其中，0号位是队列的最前端，1号位是0号位的后一个元素，
     * 以此类推。如果该元素不存在，返回 null。获取元素时严禁影响到队列 */
    T get(int index);
    /** 返回参数 o 是否与此队列相等。如果 o 包含与此队列相同的元素
     *  (使用泛型 (genetic) T的 equals 方法），且元素顺序相同，则 o 与此队列相等。
     */
    boolean equals(Object o);
}