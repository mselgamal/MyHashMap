package implementation;

/**
 * 
 * @author Mamdouh Elgamal
 * @implNote Custom HashMap with linked list for collision support
 */
public class MyHashMap<K, V> {
	
	/**
	 * max load factor, when current load == max load, 
	 * capacity = next largest prime < capacity^2
	 */
	private static float MAX_LOAD = new Float(0.7f);
	/**
	 * integer representing initial capacity
	 */
	private static int INIT_CAPACITY = 997;
	/**
	 * integer representing hash map capacity
	 */
	private int capacity;
	/**
	 * integer for number of entries in array
	 */
	private int size;
	/**
	 * array of bucket lists objects for storing <key,value> objects
	 */
	private Bucket<K, V>[] buckets;
	
	public MyHashMap() {
		this.capacity = INIT_CAPACITY;
		this.init();
	}
	
	/**
	 * initialize the buckets array and update load
	 */
	@SuppressWarnings("unchecked")
	private void init() {
		this.buckets = new Bucket[this.capacity];
		for (int i = 0; i < this.buckets.length ;i++) {
			this.buckets[i] = new Bucket<K, V>();
			
		}
	}
	
	/**
	 * starting from current capacity * 2, 
	 * compute next largest prime <= capacity * 2
	 * 
	 * @param capacity
	 * @return new capacity
	 */
	
	private int recomputeCapacity(int capacity) {
		return 0;
	}
	
	/**
	 * resize hash map with new capacity
	 */
	private void resizeHashMap() {
		this.capacity = this.recomputeCapacity(this.capacity * 2);
		Bucket<K, V>[] buckets = this.buckets;
		this.init();
		for (int i = 0; i < buckets.length ;i++) {
			this.buckets[i] = buckets[i];
		}
	}
	
	/**
	 * Use object's computed hashCode, then compress 
	 * and return hashmap index
	 * 
	 * @param key
	 * @return hashmap index
	 */
	private int keyHash(K key) {
		return key.hashCode() % this.capacity;
	}
	
	/**
	 * hash map size
	 * 
	 * @return capacity
	 */
	public int capacity() {
		return this.capacity;
	}
	
	/**
	 * computes load factor # of entries / capacity
	 * 
	 * @return load factor
	 */
	public Float loadFactor() {
		return (float) this.size / (float) this.capacity;
	}
	
	/**
	 * number of items in hashmap
	 * 
	 * @return number of entries in HashMap
	 */
	public int size() {
		return this.size;
	}
	
	/**
	 * puts a <key, value> pair in hashmap
	 * 
	 * @param key 
	 * @param value
	 * @throws Exception
	 */
	public void put(K key, V value) {
		int index = this.keyHash(key);
		int oldLength = this.buckets[index].length;
		Float load = this.loadFactor();
		
		if (this.buckets[index].head != null && 
				key.equals(this.buckets[index].head.key)) {
			this.buckets[index].head.value = value;
		} else if (load.compareTo(MAX_LOAD) >= 0) {
			this.resizeHashMap();
			index = this.keyHash(key);
		}
		this.buckets[index].insert(key, value);
		if (oldLength != this.buckets[index].length) this.size++;
	}
	
	/**
	 * returns value in hashmap
	 * 
	 * @param key
	 * @return value or null if key not found
	 */
	public V get(K key) {
		return this.buckets[this.keyHash(key)].get(key);
	}
	
	/**
	 * removes a value from hashmap
	 * 
	 * @param key
	 * @throws IllegalArgumentException if key not found
	 */
	public void remove(K key) {
		V value = this.buckets[this.keyHash(key)].remove(key);
		if (value != null) {
			this.size--;
		}
	}
	
	/**
	 * A bucket is a Singly-linked list for storing entries in hashmap
	 * it's expected that majority of buckets will only have a single node
	 * 
	 * @author Mamdouh Elgamal
	 *
	 * @param <K> generic key
	 * @param <V> generic value
	 */
	@SuppressWarnings("hiding")
	private class Bucket<K, V> {
		
		/**
		 * first node in the linked list
		 */
		private Item<K, V> head;
		/**
		 * linked list size
		 */
		private int length;
		
		/**
		 * inserts <key, value> to linked list
		 * 
		 * @param key
		 * @param value
		 */
		public void insert(K key, V value) {
			Item<K, V> curr = new Item<K, V>(key, value);
			curr.next = this.head;
			curr.key = key;
			curr.value = value;
			this.head = curr;
			this.length += 1;
		}
		
		private Item<K, V> find(K key) {
			Item<K, V> curr = this.head;
			while (curr != null) {
				if (curr.key.equals(key)) return curr;
				curr = curr.next;
			}
			return null;
		}
		
		public V get(K key) {
			Item<K, V> node = this.find(key);
			if (node != null) return node.value;
			return null;
		}
		
		public V remove(K key) {
			Item<K, V> curr = this.head, prev = null;
			while (curr != null) {
				if (curr.key.equals(key)) break;
				prev = curr;
				curr = curr.next;
			}

			if (curr == null) {
				throw new IllegalArgumentException("Key " + key +" not found");
			} else if (prev == null) {
				this.head = curr.next;
			} else {
				prev.next = curr.next;
			}
			return curr.value;
		}
		
		private class Item<K, V>{
			private K key;
			private V value;
			private Item<K, V> next;
			
			private Item(K key, V value) {
				this.key = key;
				this.value = value;
			}	
		}
	}
}
