package lab2;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DoubleLinkedListTest {
	DoubleLinkedList<String> dll;
	
	@Before
	public void setUp() throws Exception {
		dll = new DoubleLinkedList<String>();
		
	}

	@After
	public void tearDown() throws Exception {
		dll = null;
	}

	@Test
	public void testDoubleLinkedList() {
		assertNotNull(dll);
	}

	@Test
	public void testSetDescr() {
		dll.setDescr("DoubleLinkedList");
		assertEquals("DoubleLinkedList", dll.getDescr());
	}

	@Test
	public void testGetDescr() {
		dll.setDescr("DoubleLinkedList");
		assertEquals("DoubleLinkedList", dll.getDescr());
	}


	@Test
	public void testIsEmpty() {
		assertTrue(dll.isEmpty());
	}

	@Test
	public void testSize() {
		assertEquals(0, dll.size());
	}

	@Test
	public void testIterator() {
		assertNotNull(dll.iterator());
	}

	@Test
	public void testToString() {
		assertEquals("DoubleLinkedList ]", dll.toString());
	}

}
