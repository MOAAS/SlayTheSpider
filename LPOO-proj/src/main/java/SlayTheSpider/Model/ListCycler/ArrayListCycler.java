package SlayTheSpider.Model.ListCycler;

import java.util.ArrayList;
import java.util.Random;

public class ArrayListCycler<T> implements ListCycler<T> {
    private ArrayList<T> list;
    private int selected = 0;
    private T nullItem = null;

    public ArrayListCycler(ArrayList<T> list) {
        this.list = list;
    }

    public ArrayListCycler(T nullItem) {
        this.list = new ArrayList<>();
        this.nullItem = nullItem;
    }

    @Override
    public T getSelected() {
        if (this.list.size() == 0)
            return nullItem;
        if (this.selected < 0 || this.selected >= list.size())
            selectFirst();
        return list.get(selected);
    }

    @Override
    public T getRandom() {
        if (list.isEmpty())
            return nullItem;
        return list.get(new Random().nextInt(list.size()));
    }

    @Override
    public ArrayList<T> getList() {
        return list;
    }

    @Override
    public void selectFirst() {
        selected = 0;
    }

    @Override
    public void selectRight(){
        if (selected >= list.size() - 1)
            selected = 0;
        else selected++;
    }

    @Override
    public void selectLeft(){
        if (selected <= 0)
            selected = list.size() - 1;
        else selected--;
    }

    @Override
    public void add(T item) {
        list.add(item);
    }

    @Override
    public boolean remove(T item) {
        return list.remove(item);
    }

    @Override
    public void clear() {
        list.clear();
        selected = 0;
    }

    @Override
    public boolean contains(T item) {
        return list.contains(item);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public T get(int index) {
        return list.get(index);
    }

}