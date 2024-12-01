package telran.list.interfaces;

public interface IList<E> extends Iterable<E>{

    default boolean add(E element) {
        return add(size(), element);
    }

    //O(n*n)
    default void clear() {
        while (!isEmpty()){
            remove(0);
        }
    }

    default boolean contains(Object o){
        return indexOf(o) >= 0;
    }

    //O(1)
    default boolean isEmpty() {
        return size() == 0;
    }

    //O(n)
    default boolean remove(Object o){
        int index = indexOf(o); //O(n)
        if (index < 0){
            return false;
        }
        remove(index); //O(n)
        return true;
    }
    int size();

    boolean add(int index, E element);
    E get(int index);
    int indexOf(Object o);
    int lastIndexOf(Object o);
    E remove(int index);

    //O(n)
    default E set(int index, E element){
        E res = remove(index); //O(n)
        add(index, element); //(n)
        return res;
    }
}
