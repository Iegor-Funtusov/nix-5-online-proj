package ua.com.alevel.lib;

public class BaseEntityContainer<T extends BaseEntity> {

    private T[] arr = (T[]) new BaseEntity[10];
    private int size;

    public void add(T item) {
        if (size > arr.length * 0.75) {
            T[] temp = (T[]) new BaseEntity[size * 2];
            if (size >= 0) System.arraycopy(arr, 0, temp, 0, size);
            arr = temp;
        }
        arr[size] = item;
        size++;
    }

    public void remove(int id) {
        if(id > size || id < 0)
            throw new IllegalArgumentException();
        if (size - id >= 0) System.arraycopy(arr, id + 1, arr, id, size - id);
        size--;
    }

    public void set(int id, T item) {
        if (id >= 0 && id < size) {
            if (item != null) {
                arr[id] = item;
            } else
                throw new NullPointerException();
        } else
            throw new ArrayIndexOutOfBoundsException();
    }

    public T get(int id) {
        if (id >= 0 && id < size) {
            return arr[id];
        } else
            throw new ArrayIndexOutOfBoundsException();
    }

    public int size() {return size;}

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size; i++) {
            result.append(arr[i]).append(" ");
        }
        return result.toString();
    }
}
