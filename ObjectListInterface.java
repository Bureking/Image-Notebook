/**
 * Describes the operations of a generic list including an internal
 * iterator. The list stores a sequence of values of any type. Each
 * value has a position within the list, but retrieval by index is 
 * not supported.
 * 
 * @author Hanadi Jusufovic
 * @param <T> 
 */

public interface ObjectListInterface<T> {

    T getCurrent();

    T getFirst();

    T getNext();

    T getPrevious();

    T getLast();

    boolean append(T newObject);

    boolean insert(T newObject);

    T remove();

    boolean replace(T newObject);

    boolean clear();

    int getLength();

    int getCurrentPosition();
}
