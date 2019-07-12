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
		assertEquals(1993, map.capacity());
	}
	
	@Test
	public void verifyLoadFactor() {
		MyHashMap<Integer, Integer> map = new MyHashMap<Integer, Integer>();
		
		for (int i = 0; i < 2000 ; i++) {
			map.put(i,  i);
		}
		
		assertEquals(2000, map.size());
		assertEquals(3967, map.capacity());
		
		assertEquals(0.5f, map.loadFactor(), .01f);
	}
	
	@Test
	public void putAndGetWithCollisions() {
		MyHashMap<String, Integer> map = new MyHashMap<String, Integer>();
		map.put("BBBB", 1);
		map.put("AaAa", 1);
		map.put("AaBB", 2);
		map.put("BBAa", 2);
		map.put("Aa", 1);
		map.put("BB", 2);
		assertEquals((Integer)1, map.get("Aa"));
		assertEquals((Integer)2, map.get("BB"));
		assertEquals((Integer)1, map.get("AaAa"));
		assertEquals((Integer)1, map.get("BBBB"));
		assertEquals((Integer)2, map.get("BBAa"));
		assertEquals((Integer)2, map.get("AaBB"));
		assertEquals(6, map.size());
	}
	
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
	
	@Test
	public void removeWithCollisions() {
		MyHashMap<String, Integer> map = new MyHashMap<String, Integer>();
		map.put("BBBB", 1);
		map.put("AaAa", 1);
		map.put("AaBB", 2);
		map.put("BBAa", 2);
		map.put("Aa", 1);
		map.put("BB", 2);
		
		assertEquals((Integer)1, map.get("Aa"));
		assertEquals((Integer)2, map.get("BB"));
		assertEquals((Integer)1, map.get("AaAa"));
		assertEquals((Integer)1, map.get("BBBB"));
		assertEquals((Integer)2, map.get("BBAa"));
		assertEquals((Integer)2, map.get("AaBB"));
		
		map.remove("Aa");
		map.remove("BBAa");
		
		assertEquals(null, map.get("Aa"));
		assertEquals((Integer)2, map.get("BB"));
		assertEquals((Integer)1, map.get("AaAa"));
		assertEquals((Integer)1, map.get("BBBB"));
		assertEquals(null, map.get("BBAa"));
		assertEquals((Integer)2, map.get("AaBB"));
		
		assertEquals(4, map.size());
	}
	
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
