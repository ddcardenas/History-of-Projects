//Daniel Cardenas
//Comp333 Concurrency Assignment
package concurrencyproject;

public class ConcurrencyProblem {

	public static void main(String[] args) {
		
		final TaskingThread[] taskingthreads = new TaskingThread[5];
		Object[] resources = new Object[5];
		
		for (int i = 0; i < 5; i++) {
			resources[i] = new Object();
		}
		
		for (int i = 0; i < 5; i++) {
			Object leftResource = resources[i];
			Object rightResource = resources[(i + 1) % 5];
			
			if (i == 4) {
				taskingthreads[i] = new TaskingThread(rightResource, leftResource);
			} else {
				taskingthreads[i] = new TaskingThread(leftResource, rightResource);
			}
			
			Thread t = new Thread(taskingthreads[i], "Thread " + (i + 1));
			t.start();
		}	
	}
}
