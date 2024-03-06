package datastruct;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.*;

public class MyUnsortedListTest {
	
	MyUnsortedList<String> list;
	
	@Before
	public void init() {
		list = MyUnsortedList.of();
	}
	
	@Test
	public void testOfWithElements() {
		MyUnsortedList<String> newList = MyUnsortedList.of("test1", "test2", "test3");
		
		assertEquals(newList.size(), 3);
		for (int i = 0; i < newList.size(); i++) {
			assertEquals(newList.getElementByPosition(i), "test" + (i+1));
		}
	}
	
	@Test
	public void testConstructor() {
		assertEquals(list.getHead(), null);
		assertEquals(list.size(), 0);
	}
	
	@Test
	public void testIsEmptyReturnsTrueIfEmpty() {
		assertEquals(list.isEmpty(), true);
	}
	
	@Test
	public void testIsEmptyReturnsFalseIfNotEmpty() {
		
		list.append("jorane");
		assertNotEquals(list.isEmpty(), true);
		assertEquals(list.getHeadElement(), "jorane");
		
	}
	
	@Test
	public void testSize() {
		
		for (int i = 0; i < 10; i++) {
			list.append("test" + i);
			assertEquals(list.size(), i+1);
		}
	}
	
	@Test
	public void testPrependWorksCorrectly() {
		
		for (int i = 0; i < 10; i++) {
			list.prepend("test" + i);
			assertEquals(list.size(), i+1);
			assertEquals(list.getHeadElement(), "test" + i);
		}		
	}
	
	@Test
	public void testAppendAddstoTheEndOfTheList() {
		
		for (int i = 0; i < 10; i++) {
			list.append("test" + i);
			assertEquals(list.size(), i+1);
			assertEquals(list.getElementByPosition(i), "test" + i);
		}
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testInsertWhenPosIsLessThanZero() {
		list.insert("jorane", -1);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testInsertWhenPosMoreThanSize() {
		list.insert("jorane", 20);
	}
	
	@Test
	public void testInsertWhenPosIsCorrect() {
		int pos = 2;
		list.append("ali");
		list.append("jorane");
		list.append("serge");
		list.insert("noemie", pos);
		
		assertEquals(list.size(), 4);
		assertEquals(list.getElementByPosition(pos), "noemie");
	}
	
	@Test
	public void testInsertWhenPosIsZero() {
		int pos = 0;
		list.append("ali");
		list.append("jorane");
		list.append("serge");
		list.insert("noemie", pos);
		
		assertEquals(list.size(), 4);
		assertEquals(list.getHeadElement(), "noemie");
	}
	
	@Test(expected=EmptyListException.class)
	public void testPopWhenListIsEmpty() {
		list.pop();
	}
	
	@Test
	public void testPopWhenListIsNotEmpty() {
		list.append("ali");
		list.append("jorane");
		
		String popedElement = (String) list.pop();
		
		assertEquals(list.size(), 1);
		assertEquals(popedElement, "ali");
		
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testRemoveWhenPosIsLessZero() {
		list.append("ali");
		
		int pos = (int) -(Math.round(Math.random() * 100) + 1);
		
		list.remove(pos);
		
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testRemoveWhenPosIsGreaterThanSize() {
		list.append("ali");
		
		int pos = (int)(Math.round(Math.random() * 100) + 1) + list.size();
		
		list.remove(pos);
		
	}
	
	@Test
	public void testRemoveWithCorrectPosition() {
		
		list.append("ali");
		list.append("jorane");
		list.append("noemie");
		
		String popedElement = (String)list.remove(1);
		
		assertEquals(list.size(), 2);
		assertEquals(popedElement, "jorane");
	}
	
	@Test
	public void testRemoveWithPosZero() {
		
		list.append("ali");
		list.append("jorane");
		list.append("noemie");
		
		String prevHead = (String)list.getHeadElement();
		
		String popedElement = (String)list.remove(0);
		
		assertEquals(list.size(), 2);
		assertEquals(popedElement, prevHead);
	}
	
	@Test
	public void testGetHeadElementWhenHeadIsNull() {
		assertEquals(list.getHeadElement(), null);
	}
	
	@Test
	public void testPopLast() {
		
		list.append("ali");
		list.append("jorane");
		list.append("noemie");
		
		String popedElement = (String)list.popLast();
		
		assertEquals(list.size(), 2);
		assertEquals(popedElement, "noemie");
	}
	
	@Test(expected=EmptyListException.class)
	public void testPopLastOnEmptyList() {
		list.popLast();
	}
	
	@Test
	public void testEqualsWithSameLists() {
		
		list.append("ali");
		list.append("jorane");
		list.append("noemie");
		list.append("serge");
		
		MyUnsortedList<String> newList = MyUnsortedList.of();
		
		newList.append("ali");
		newList.append("jorane");
		newList.append("noemie");
		newList.append("serge");
		
		assertEquals(newList, list);
	}
	
	@Test
	public void testEqualsWithDifferentLists() {
		
		list.append("ali");
		list.append("jorane");
		list.append("noemie");
		list.append("serge");
		
		MyUnsortedList<String> newList = MyUnsortedList.of();
		
		newList.append("ali");
		newList.append("serge");
		newList.append("noemie");
		newList.append("jorane");
		
		assertNotEquals(newList, list);
		assertNotEquals(list, newList);
	}
	
	@Test
	public void testEqualsWithDifferentSizeLists() {
		
		list.append("ali");
		list.append("jorane");
		list.append("noemie");
		list.append("serge");
		
		MyUnsortedList<String> newList = MyUnsortedList.of();
		
		newList.append("ali");
		newList.append("jorane");
		newList.append("noemie");
		
		assertNotEquals(newList, list);
		assertNotEquals(list, newList);
	}
	
	@Test
	public void testEqualsWithEmptyLists() {
		
		MyUnsortedList<String> newList = MyUnsortedList.of();
		
		assertEquals(newList, list);
	}
	
	@Test
	public void testEqualsWithEmptyListAndFilledList() {
		
		list.append("ali");
		list.append("jorane");
		list.append("noemie");
		list.append("serge");
		
		MyUnsortedList<String> newList = MyUnsortedList.of();
		
		assertNotEquals(newList, list);
		assertNotEquals(list, newList);
	}
	
	@Test
	public void testEqualsWithListAndNull() {
		
		list.append("ali");
		list.append("jorane");
		list.append("noemie");
		list.append("serge");
				
		assertFalse(list.equals(null));
	}
	
	@Test
	public void testEqualsWithListAndOtherObject() {
		
		list.append("ali");
		list.append("jorane");
		list.append("noemie");
		list.append("serge");
		
		String newList = "test";
		
		assertNotEquals(list, newList);
	}
	
	@Test
	public void testToStringGivesExpectedOutput() {
		list.append("ali");
		list.append("jorane");
		list.append("noemie");
		list.append("serge");
		
		String expected = "MyUnsortedList { size = 4, [ali, jorane, noemie, serge] }";
		
		assertEquals(list.toString(), expected);
	}
	
	
}
