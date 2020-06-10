package SlayTheSpider.Model.ListCycler;

import java.util.List;

public interface ListCycler<T> {
    T getSelected();

    T getRandom();

    List<T> getList();

    void selectFirst();

    void selectRight();

    void selectLeft();

    void add(T item);

    boolean remove(T item);

    void clear();

    boolean contains(T item);

    int size();

    T get(int index);
}
