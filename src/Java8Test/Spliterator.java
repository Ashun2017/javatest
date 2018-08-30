package Java8Test;

import LinkedListTest.MyLinkedList;

import java.util.*;

/**
 * Describe：
 * Author：sunqiushun
 * Date：2018-07-30 13:49:00
 */
public class Spliterator {

    public static int SHUFFLE_THRESHOLD = 1;
    public static Random r = new Random(4);

    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        shuffle(list, r);
        System.out.println(list);
    }

    public static void shuffle(List<?> list) {
        Random rnd = r;
        if (rnd == null)
            r = rnd = new Random(); // harmless race.
        shuffle(list, rnd);
    }

    public static void shuffle(List<?> list, Random rnd) {
        int size = list.size();
        if (list instanceof RandomAccess) {
            System.out.println("RandomAccess");
            for (int i = size; i > 1; i--) {
                swap(list, i - 1, rnd.nextInt(i));
            }
        } else {
            System.out.println("no RandomAccess");
            Object arr[] = list.toArray();

            for (int i = size; i > 1; i--) {
                int randInt = r.nextInt(i);
                System.out.println("rand.next= " + randInt);
                swap(arr, i - 1, randInt);
            }

            ListIterator it = list.listIterator();
            for (int i = 0; i < arr.length; i++) {
                it.next();
                it.set(arr[i]);
            }
        }
    }

    public static void swap(List<?> list, int i, int j) {
        System.out.println("List i=" + i + " j=" + j);
        final List l = list;
        l.set(i, l.set(j, l.get(i)));
    }

    public static void swap(Object[] arr, int i, int j) {
        System.out.println("Array i=" + i + " j=" + j);
        Object tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
