package com.connected;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Class that implements Queue and one that consists of all unique elements only.
 * @author Srinivas Reddy Karra
 *
 * @param <T>
 */
public class DistinctQueue<T> implements Queue<T> {

	private final Queue<T> queue = new LinkedList<T>();
	private final Set<T> set = new HashSet<T>();
	public int size() {
		return queue.size();
	}
	public boolean isEmpty() {
		return set.isEmpty();
	}
	public boolean contains(Object o) {
		 return set.contains(o);	
	}
	public Iterator<T> iterator() {
		return queue.iterator();
	}
	public Object[] toArray() {
		return queue.toArray();	
	}
	public <T> T[] toArray(T[] a) {
		return queue.toArray(a);
	}
	public boolean remove(Object o) {
	   boolean ret = queue.remove(o);
	   set.remove(o);
	   return ret;
	}
	public boolean containsAll(Collection<?> c) {
		return set.containsAll(c);
	}
	public boolean addAll(Collection<? extends T> c) {
	    boolean ret = false;
	    for (T t: c)
	        if (set.add(t)) {
	            queue.add(t);
	            ret = true;
	        }
	    return ret;
	}
	public boolean removeAll(Collection<?> c) {
	    boolean ret = queue.removeAll(c);
	    set.removeAll(c);
	    return ret;
	}
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();	
	}
	public void clear() {
		set.clear();
		queue.clear();		
	}
	public boolean add(T e) {
	    if (set.add(e))
	        queue.add(e);
	    return true;
	}
	public boolean offer(T e) {
		return queue.offer(e);
	}
	public T remove() {
	    T ret = queue.remove();
	    set.remove(ret);
	    return ret;
	}
	public T poll() {
	    T ret = queue.poll();
	    set.remove(ret);
	    return ret;
	}
	public T element() {
		return queue.element();
	}
	public T peek() {
		return queue.peek();
	}
}
