package com.lrucache;

import java.util.HashMap;
import java.util.Map;

/**
 * This class provided the implementation for LRU cache
 * 
 * @author azharm
 *
 */
public class LruCache {

	private Node tail;
	private Node head;
	private Map<Integer, Node> map = new HashMap<Integer, Node>();
	private int lruSize;
	private int currentSize = 0;

	public LruCache(int size) {
		this.lruSize = size;
		currentSize = 0;
	}

	/**
	 * When a page is referenced, the required page may be in the memory. If it is
	 * in the memory, we need to detach the node of the list and bring it to the
	 * front of the queue. If the required page is not in the memory, we bring that
	 * in memory
	 * 
	 * @param data
	 */
	public void cacheNode(int data) {
		if (map.containsKey(data)) {
			Node node = map.get(data);
			if (node != head) {
				deleteNode(data);
				node.next = head;
				head.before = node;
				head = node;
				map.put(data, node);
			}
		} else {
			currentSize++;
			if (head == null) {
				head = new Node();
				head.next = null;
				head.before = null;
				head.data = data;
				tail = head;
				return;
			}
			// System.out.println("3");
			if (currentSize > lruSize) {
				tail = tail.before;
				Node next = tail.next;
				tail.next = null;
				next.before = null;
				next.next = null;
				map.remove(next.data);
			}
			Node node = new Node();
			node.data = data;
			node.next = head;
			// TODO:Test the if condition
			if (head != null)
				head.before = node;
			head = node;
			map.put(data, node);

		}

	}
/**
 * Deletes the Least recently used element
 * @param data
 */
	private void deleteNode(int data) {
		Node node = map.get(data);
		map.remove(data);
		if (currentSize == 1) {
			head = null;
			tail = null;
		} else if (node == tail) {
			tail = tail.before;
			tail.next = null;
		} else if (node == head) {
			head = head.next;
			// TODO:Test the if condition
			if (head != null)
				head.before = null;
			node.next = null;
		} else {
			Node before = node.before;
			Node next = node.next;
			before.next = next;
			next.before = before;

		}
	}

	public void printLruCache() {

		Node tmp = head;
		System.out.println();
		while (tmp != null) {
			System.out.print(tmp.data + " ");
			tmp = tmp.next;
		}
	}

}
