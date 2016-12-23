import java.util.HashMap;

public class LFUcache<T> {
    private int capacity = 15;
    private HashMap<Integer, Element<T>> map = new HashMap<Integer, Element<T>>();

    public LFUcache(int capacity){
        this.capacity = capacity;
    }

    private class Element<T>{
        private int frequency;
        private T data;
        public Element(T data){
            this.frequency = 0;
            this.data = data;
        }

        public void incrementFrequency(){
            frequency++;
        }

        public int getFrequency(){
            return frequency;
        }

        public void setData(T data){
            this.data = data;
        }

        public T getData(){
            return data;
        }
    }

    public void add(int key, T data){
        if (isFull()) {
            removeOne();
        }

        Element<T> element = new Element<T>(data);

        map.put(key, element);
    }

    public T get(int key){
        Element<T> element = map.get(key);
        element.incrementFrequency();
        return element.getData();
    }

    private void removeOne(){
        int key = 0;
        int minFrequency = Integer.MAX_VALUE;

        for (HashMap.Entry<Integer, Element<T>> element : map.entrySet()){
            int frequency = element.getValue().getFrequency();
            if (minFrequency > frequency){
                key = element.getKey();
                minFrequency = frequency;
            }
        }

        map.remove(key);
    }

    public boolean isFull(){
        return map.size() == capacity;
    }

    public String ToString(){
        String out = "";

        for (HashMap.Entry<Integer, Element<T>> hash : map.entrySet()){
            Element<T> element = hash.getValue();
            out += element.getData() + ":" + element.getFrequency() + " ";
        }

        return out;
    }
}