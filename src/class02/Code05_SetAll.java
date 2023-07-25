package class02;

import java.util.HashMap;

/**
 * 支持setAll操作的HashMap
 */
public class Code05_SetAll {

	public static class MyValue<V> {
		public V value;
		public long time;

		public MyValue(V v, long t) {
			value = v;
			time = t;
		}
	}

	public static class MyHashMap<K, V> {
		private HashMap<K, MyValue<V>> map;
		// 时间戳，记录每个put操作的时间
		private long time;
		// 记录setAll的时间戳，以及setAll的值
		private MyValue<V> setAll;

		public MyHashMap() {
			map = new HashMap<>();
			time = 0;
			setAll = new MyValue<V>(null, -1);
		}

		public void put(K key, V value) {
			map.put(key, new MyValue<V>(value, time++));
		}
		/**
		 * 修改set里所有记录的值，时间复杂度O(1)
		 * @param value setAll值
		 */
		public void setAll(V value) {
			setAll = new MyValue<V>(value, time++);
		}

		public V get(K key) {
			if (!map.containsKey(key)) {
				return null;
			}
			if (map.get(key).time > setAll.time) {
				return map.get(key).value;
			} else {
				return setAll.value;
			}
		}
	}

}
