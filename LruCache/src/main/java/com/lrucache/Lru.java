/**
 * 
 */
package com.lrucache;

/**
 * @author azharm
 *
 */
public class Lru {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LruCache lru = new LruCache(3);
		lru.cacheNode(1);
		lru.printLruCache();
		lru.cacheNode(2);
		lru.printLruCache();
		lru.cacheNode(3);
		lru.printLruCache();
		lru.cacheNode(4);
		lru.printLruCache();
		lru.cacheNode(1);
		lru.printLruCache();
		lru.cacheNode(2);
		lru.printLruCache();
		lru.cacheNode(5);
		lru.printLruCache();
		lru.cacheNode(1);
		lru.printLruCache();
		lru.cacheNode(2);
		lru.printLruCache();
		lru.cacheNode(3);
		lru.printLruCache();
		lru.cacheNode(4);
		lru.printLruCache();
		lru.cacheNode(5);
		lru.printLruCache();

	}

}
