package com.z2.t5;

public class CyclicSortedList {
	public static void main(String[] args) {
		CyclicSortedList mainTest = new CyclicSortedList();
		Node tNode = constructTestList(mainTest);

		insertCyclicSortedList(mainTest.new Node(3), tNode);

		System.out.println(tNode.toString());

		insertCyclicSortedList(mainTest.new Node(2), tNode);

		System.out.println(tNode.toString());

		insertCyclicSortedList(mainTest.new Node(4), tNode);

		System.out.println(tNode.toString());

		insertCyclicSortedList(mainTest.new Node(4), tNode);

		System.out.println(tNode.toString());

		Node tNode1 = constructTestList1(mainTest);
		insertCyclicSortedList(mainTest.new Node(7), tNode1);

		System.out.println(tNode1.toString());

		insertCyclicSortedList(mainTest.new Node(10), tNode1);

		System.out.println(tNode1.toString());
	}

	public static Node constructTestList(CyclicSortedList list) {
		CyclicSortedList.Node node = list.new Node(3);
		node.next = node;
		return node;
	}

	public static Node constructTestList1(CyclicSortedList list) {
		CyclicSortedList.Node node = list.new Node(3);

		CyclicSortedList.Node node1 = list.new Node(6);

		node.next = node1;

		CyclicSortedList.Node node2 = list.new Node(8);
		node1.next = node2;

		node2.next = node;

		return node;
	}

	/**
	 * cover all the cases 1.one node only 2.insert in middle 3.insert the max
	 * or min node.
	 * 
	 * @param node
	 * @param list
	 */
	public static void insertCyclicSortedList(Node node, Node head) {
		Node curNode, nextNode;

		if (head == null) {
			node.next = node;
			return;
		}

		curNode = head;
		nextNode = head.next;

		if (curNode.equals(nextNode)) {
			curNode.next = node;
			node.next = curNode;
			return;
		}

		do {
			if (node.value >= curNode.value && node.value <= nextNode.value) {
				break;
			} else if (curNode.value > nextNode.value) {
				if (node.value >= curNode.value || node.value <= nextNode.value) {
					break;
				}
			}
			curNode = curNode.next;
			nextNode = curNode.next;
		} while (!curNode.equals(head));

		curNode.next = node;
		node.next = nextNode;

	}

	class Node {
		private Node next;
		private int value;

		Node(int value) {
			this.value = value;
			this.next = null;
		}

		Node(int value, Node next) {
			this.value = value;
			this.next = next;
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			Node curNode;
			curNode = this;
			do {
				sb.append(curNode.value + " ");
			} while ((curNode = curNode.next) != this);

			return sb.toString();
		}
	}

}
