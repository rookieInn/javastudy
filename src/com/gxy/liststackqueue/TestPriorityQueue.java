package com.gxy.liststackqueue;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class TestPriorityQueue {

	public static void main(String[] args) {
		Patient patient1 = new Patient("tom", 7);
		Patient patient2 = new Patient("jerry", 1);
		Patient patient3 = new Patient("jack", 5);
		Patient patient4 = new Patient("tim", 6);
		
		MyPriorityQueue priorityQueue = new MyPriorityQueue();
		priorityQueue.enqueue(patient1);
		priorityQueue.enqueue(patient2);
		priorityQueue.enqueue(patient3);
		priorityQueue.enqueue(patient4);
		
		while (priorityQueue.getSize() > 0) {
			System.out.println(priorityQueue.dequeue());
		}
	}

	static class Patient implements Comparable{

		private String name;
		private int priority;
		
		public Patient(String name, int priority) {
			this.name = name;
			this.priority = priority;
		}
		
		@Override
		public String toString() {
			return name + "(priority:" + priority + ")";
		}
		
		@Override
		public int compareTo(Object o) {
			return this.priority - ((Patient)o).priority;
		}
		
	}
	
}
