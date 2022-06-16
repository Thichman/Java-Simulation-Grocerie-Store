
public class Customer {

	
	private int items;
	private int time_remaining;
	
	public Customer(int items, int time_remaining){
		this.items = items;
		this.time_remaining = time_remaining;
	}

	public int getItems() {
		return items;
	}


	public int getTime_remaining() {
		return time_remaining;
	}

	public void lessen() {
		this.time_remaining--;
		
	}
	
}
