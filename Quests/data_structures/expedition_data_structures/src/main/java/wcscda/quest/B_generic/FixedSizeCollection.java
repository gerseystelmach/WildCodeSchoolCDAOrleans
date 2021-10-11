package wcscda.quest.B_generic;

import wcscda.quest.Sentinel;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;

public class FixedSizeCollection<T> {
    private int maxSize;
    private T[] array;
    private int currentSize;

    public FixedSizeCollection(Class<T> clazz, int maxSize) {
        this.maxSize = maxSize;
        array = (T[])Array.newInstance(clazz, maxSize);
        currentSize = 0;
    }
}