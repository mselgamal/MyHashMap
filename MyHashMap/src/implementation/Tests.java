package implementation;

import static org.junit.Assert.*;

import org.junit.Test;

public class Tests {

	@Test
	public void putAndGetNoCollisions() {
		MyHashMap<Integer, Integer> map = new MyHashMap<Integer, Integer>();
		
		for (int i = 0; i < 1000 ; i++) {
			map.put(i,  i);
		}
		
		for (int i = 0; i < 1000 ; i++) {
			assertEquals((Integer) i, map.get(i));
		}
		
		assertEquals(1000, map.size());
	}
	
	@Test
	public void verifyLoadFactor() {
		MyHashMap<Integer, Integer> map = new MyHashMap<Integer, Integer>();
		
		for (int i = 0; i < 2000 ; i++) {
			map.put(i,  i);
		}
		
		assertEquals(2000, map.size());
		
		assertEquals(0.5f, map.loadFactor(), .01f);
	}
	
	/**@Test
	public void putAndGetWithCollisions() {
		MyHashMap<Integer, Integer> map = new MyHashMap<Integer, Integer>();
		
		for (int i = 0; i < 1200 ; i++) {
			try {
				map.put(i,  i);
			} catch (Exception e) {
				fail(e.getMessage());
			}
		}
		
		for (int i = 0; i < 1200 ; i++) {
			assertEquals((Integer) i, map.get(i));
		}
		
		assertEquals(1200, map.size());
	}*/
	
	@Test
	public void removeNoCollisions() {
		MyHashMap<Integer, Integer> map = new MyHashMap<Integer, Integer>();
		
		for (int i = 0; i < 1000 ; i++) {
			try {
				map.put(i,  i);
			} catch (Exception e) {
				fail(e.getMessage());
			}
		}
		
		map.remove(500);
		map.remove(900);
		map.remove(455);
		map.remove(260);
		
		assertEquals(996, map.size());
	}
	
	/**@Test
	public void removeWithCollisions() {
		MyHashMap<Integer, Integer> map = new MyHashMap<Integer, Integer>();
		
		for (int i = 0; i < 2000 ; i++) {
			try {
				map.put(i,  i);
			} catch (Exception e) {
				fail(e.getMessage());
			}
		}
		
		map.remove(1200);
		map.remove(200);
		
		assertEquals(1998, map.size());
	}*/
	
	@Test
	public void exceptionOnInvalidRemove() {
		MyHashMap<Integer, Integer> map = new MyHashMap<Integer, Integer>();
		try {
			map.remove(1200);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}
	}

}
