package intlist;

import java.util.Arrays;
import java.util.stream.IntStream;

// Stappenplan definiëren API data-abstractie:
//
// 1. Rauwe abstractetoestandsruimte =
//    getters declareren (= naam en return type)
//
// 2. Geldige abstracte toestanden definiëren =
//    abstractetoestandsinvarianten noteren
//
// 3. Declareren en documenteren van constructoren en mutatoren

public class IntList {
	
	private class Node {
		int value;
		Node next;
	}
	
	/**
	 * @invar | 0 <= size
	 * @invar | IntStream.range(0, size).allMatch(i -> getNodes()[i] != null)
	 * @invar | size == 0 ? head == null : getNodes()[size - 1].next == null
	 */
	private Node head;
	private int size;
	
	/**
	 * @representationObjects
	 * @creates | result
	 */
	private Node[] getNodes() {
		Node[] nodes = new Node[size];
		int i = 0;
		for (Node n = head; n != null && i < size; n = n.next)
			nodes[i++] = n;
		return nodes;
	}
	
	/**
	 * @post | result != null
	 * @creates | result
	 */
	public int[] getElements() {
		int[] result = new int[size];
		int i = 0;
		for (Node n = head; n != null && i < size; n = n.next)
			result[i++] = n.value;
		return result;
	}
	
	/**
	 * @post | result == getElements().length
	 */
	public int getLength() { return size; }
	
	/**
	 * @pre | 0 <= index && index < getLength()
	 * @post | result == getElements()[index]
	 */
	public int getElementAt(int index) {
		int i = 0;
		Node n = head;
		for (; i < index; n = n.next)
			i++;
		return n.value;
	}
	
	/**
	 * @post | getLength() == 0
	 */
	public IntList() {}
	
	/**
	 * @mutates | this
	 * @post | getLength() == old(getLength()) + 1
	 * @post | Arrays.equals(getElements(), 0, old(getLength()), old(getElements()), 0, old(getLength()))
	 * @post | getElements()[old(getLength())] == element
	 */
	public void add(int element) {
		if (size == 0) {
			head = new Node();
			head.value = element;
		} else {
			Node n = head;
			while (n.next != null)
				n = n.next;
			n.next = new Node();
			n.next.value = element;
		}
		size++;
	}
	
	/**
	 * @pre | getLength() > 0
	 * @mutates | this
	 * @post | getLength() == old(getLength()) - 1
	 * @post | Arrays.equals(getElements(), 0, getLength(), old(getElements()), 0, getLength())
	 */
	public void removeLast() {
		if (head.next == null) {
			head = null;
		} else {
			Node n = head;
			while (n.next.next != null)
				n = n.next;
			n.next = null;
		}
		size--;
	}

}
