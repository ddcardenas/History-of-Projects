//Daniel Cardenas
//Comp333 Concurrency Assignment
package concurrencyproject;

public class TaskingThread implements Runnable {

	private Object leftResource;
	private Object rightResource;
	
	public TaskingThread(Object leftResource, Object rightResource) {
		this.leftResource = leftResource;
		this.rightResource = rightResource;
	}
	
	private void doAction(String action) throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + " " + action);
		Thread.sleep(((int) (Math.random() * 100)));		
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				doAction(": Performing Activity B");
				synchronized (leftResource) {
					doAction(": Acquired Left Resource");
					synchronized (rightResource) {
						doAction(": Acquired Right Resource and now performing Activity A");
						doAction(": Let go of Right Resource");
					}
					doAction(": Let go of Left Resource and back to Activity B");
				}
			}
		} catch (InterruptedException e) { 
			Thread.currentThread().interrupt();
			return;
		}
	}
}
