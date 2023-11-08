package org.example;

public class hashMap<K, V> {

    //region Публичные методы

    public V put(K key, V value){
        if(buckets.length * LOAD_FACTOR <= size){
            recalculate();
        }
        int index = calculateBuketIndex(key);
        Bucket bucket = buckets[index];
        if (bucket == null) {
            bucket = new Bucket();
            buckets[index] = bucket;
        }

        Entity entity = new Entity(key, value);
        V buf =  (V) bucket.add(entity);
        if(buf == null){
            size++;
        }
        return buf;
    }

    public V get(K key){
        int index = calculateBuketIndex(key);
        Bucket bucket = buckets[index];
        if (bucket == null) {
            return null;
        }
        return (V)bucket.get(key);
    }

    public V remove(K key){
        int index = calculateBuketIndex(key);
        Bucket bucket = buckets[index];
        if (bucket == null) {
            return null;
        }
        V buf = (V)bucket.remove(key);
        if (buf != null){
            size--;
        }
        return buf;
    }

    //endregion

    private void recalculate(){
        size = 0;
        Bucket<V, K> old[] = buckets;
        buckets = new Bucket[old.length * 2];
        for (int i = 0; i < old.length; i++) {
            Bucket<V, K> bucket = old[i];
            if (bucket == null) {
                continue;
            }
            Bucket.Node node = bucket.head;
            while (node != null){
                put((K)node.value.key, (V)node.value.value);
                node = node.next;
            }
        }
    }

    //region Методы

    private int calculateBuketIndex(K key){
        return Math.abs(key.hashCode()) % buckets.length;
    }

    //endregion

    //region Конструкторы

    public hashMap(){
        buckets = new Bucket[INIT_BUKET_COUNT];
    }

    public hashMap(int capacity){
        buckets = new Bucket[capacity];
    }

    //endregion

    //region Поля

    private Bucket[] buckets;
    private int size;

    //endregion

    //region Константы

    private static final int INIT_BUKET_COUNT = 16;
    private static final double LOAD_FACTOR = 0.5;

    //endregion

    //region Вспомогательные структуры
    class Entity{
        K key;
        V value;

        public Entity(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    class Bucket<K, V> {

        private Node head;

        class Node{
            Node next;
            Entity value;
        }

        public V add(Entity entity){
            Node node = new Node();
            node.value = entity;

            if (head == null) {
                head = node;
                return null;
            }

            Node currentNode = head;
            while (true){
                if (currentNode.value.key.equals(entity.key)) {
                    V buf = (V)currentNode.value.value;
                    currentNode.value.value = entity.value;
                    return buf;
                }

                if (currentNode.next != null) {
                    currentNode = currentNode.next;
                } else {
                    currentNode.next = node;
                    return null;
                }
            }
        }

        public V get(K key){
            Node node = head;
            while (node != null){
                if (node.value.key.equals(key)){
                    return (V)node.value.value;
                }
                node = node.next;
            }
            return null;
        }

        public V remove(K key){
            if(head == null){
                return null;
            }
            if (head.value.key.equals(key)){
                V buf = (V)head.value.value;
                head = head.next;
                return buf;
            } else {
                Node node = head;
                while (node.next != null){
                    if (node.next.value.key.equals(key)) {
                        V buf = (V)head.value.value;
                        node.next = node.next.next;
                        return buf;
                    }
                    node = node.next;
                }
                return null;
            }
        }
    }
    //endregion
}
