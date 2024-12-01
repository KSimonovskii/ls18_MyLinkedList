package telran.list.model;

import telran.list.interfaces.IList;

import java.util.Iterator;

public class MyLinkedList<E> implements IList<E> {

    private Node<E> first;
    private Node<E> last;
    private int size;

    //O(1)
    @Override
    public int size() {
        return size;
    }

    //O(n)
    @Override
    public boolean add(int index, E element) {
        if (index == size) { //O(n)
            Node<E> newNode = new Node<>(last, element, null);
            if (last != null) {
                last.next = newNode;
            } else {
                first = newNode;
            }
            last = newNode;
        } else {
            Node<E> node = getNodeByIndex(index); //O(n)
            Node<E> newNode = new Node<>(node.prev, element, node);
            if (index != 0){
                node.prev.next = newNode;
                node.prev = newNode;
            } else {
                first = newNode;
            }
        }
        size++;
        return false;
    }

    //O(n)
    @Override
    public E get(int index) {
        return getNodeByIndex(index).payload;
    }

    //O(n)
    @Override
    public int indexOf(Object o) {
        int index = 0;
        for (Node<E> node = first; node != null; node = node.next, index++) {
            if ((o != null && o.equals(node.payload))
                    || (o == null && null == node.payload)) {
                return index;
            }
        }
        return -1;
    }

    //O(n)
    @Override
    public int lastIndexOf(Object o) {

        int index = size - 1;
        for (Node<E> node = last; node != null; node = node.prev, index--) {
            if ((o != null && o.equals(node.payload))
                    || (o == null && null == node.payload)) {
                return index;
            }
        }
        return -1;
    }

    //O(n)
    @Override
    public E remove(int index) {
        checkIndex(index);
        Node <E> removeNode = null;

        if (index == 0) {
            removeNode = first;
            first = removeNode.next;
            first.prev = null;
        } else if (index == size - 1) {
            removeNode = last;
            last = removeNode.prev;
            last.next = null;
        } else {
            removeNode = getNodeByIndex(index); //O(n)

            removeNode.next.prev = removeNode.prev;
            removeNode.prev.next = removeNode.next;
        }
        E removeData = removeNode.payload;
        size--;
        return removeData;
    }

    @Override
    public Iterator<E> iterator() {

        Iterator <E> listIterator = new Iterator<E>() {
            private int counter;

            @Override
            public boolean hasNext() {
                return counter < size;
            }

            @Override
            public E next() {
                return getNodeByIndex(counter++).payload;
            }
        };

        return listIterator;
    }

    //O(n)
    private Node<E> getNodeByIndex(int index){
        checkIndex(index);

        Node<E> node;
        if (index < size / 2) {
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
        }
        return node;
    }

    //O(1)
    private void checkIndex(int index) {
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException(index);
        }
    }

    //O(n)
    @Override
    public void clear(){
        while (size > 1){
            Node<E> node = first;
            first = first.next;
            if (first.next != null) {
                first.next.prev = null;
            }
            node = null;
            size--;
        }

        first = null;
        last = null;
        size = 0;
    }

    @Override
    //0(n)
    public E set(int index, E element){
        checkIndex(index);
        Node<E> node = getNodeByIndex(index); //O(n)
        E res = node.payload;
        node.payload = element;
        return res;
    }

    private static class Node <E>{

        E payload;
        Node<E> prev;
        Node<E> next;

        Node(Node<E> prev, E payload, Node<E> next) {
            this.payload = payload;
            this.prev = prev;
            this.next = next;
        }
    }
}


