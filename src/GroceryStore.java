import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class GroceryStore{
	int time_steps;
	int line_number;
	double customer_probability;
	int bag_time;
	int max_items;
	int customers_left;
	int customers_served;

	private Queue<Customer>[] queue;
	
	int[] line_max;
	GroceryStore(int lines){
		queue = new Queue[lines];
		for(int i = 0; i < lines; i++) {
			queue[i] = new LinkedList<Customer>();
		}
		this.line_number = lines;
		line_max = new int[lines];
		
		
	}
	
	public void run(int time_steps, double customer_probability, int bag_time, int max_items) {
		this.time_steps = time_steps;
		this.customer_probability = customer_probability;
		this.bag_time = bag_time;
		this.max_items = max_items;
		this.customers_served = 0;
		Random ran = new Random();
		while(time_steps != 0) {
			int lowest_line = 0;
			double gen = ran.nextDouble();
			if(gen <= customer_probability) {
				int items = ran.nextInt(max_items) + 1;
				int time_remaining = bag_time*items;
				Customer c = new Customer(items,time_remaining);
				for(int i = 0; i < queue.length; i++) {
					if(queue[i].size() < queue[lowest_line].size()) {
						lowest_line = i;
					}
				}
				queue[lowest_line].add(c);
			}
			for(int i = 0; i < queue.length; i++) {
				if(queue[i].size() > line_max[i]) {
					line_max[i] = queue[i].size();
				}
				if(queue[i].isEmpty() == true) {
					continue;
				} 
				if(queue[i].peek().getTime_remaining() == 0) {
					queue[i].remove();
					this.customers_served++;
				}
				if(queue[i].isEmpty() != true) {
					queue[i].peek().lessen();
				}
			}
			
			
			time_steps--;
			
		}
		
	}
	public void printData() {
		System.out.println("Time steps = " + this.time_steps);
		System.out.println("Number of lines = " + this.line_number);
		System.out.println("Customer arrival probability = " + this.customer_probability);
		System.out.println("Time per item = " + this.bag_time);
		System.out.println("Maximum number of items = " + this.max_items);
		for(int i = 0; i < queue.length; i++) {
			int temp = i+1;
			System.out.println("Max size of line " + temp + " = " + line_max[i]);
		}
		for(int i = 0; i < queue.length; i++) {
			int temp = i+1;
			System.out.println("Customers left in line " + temp + " = " + queue[i].size());
		}
		System.out.println("Total customers served = " + this.customers_served);
		
		
		
		
	}
	
	
}
