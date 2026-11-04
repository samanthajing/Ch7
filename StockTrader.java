import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class StockTrader {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("tiny.txt"));
        String symbol = in.next();
        
        int n = in.nextInt();
		int[] years = new int[n];
		int[] months = new int[n];
		double[] prices = new double[n];
		
		loadData(in, years, months, prices);
		printData(years, months, prices);
		
		int indexL = indexofLowest(prices);
		System.out.print(symbol + " lowest: $");
		System.out.printf("%.2f", prices[indexL]);
		System.out.println(" in " + monthName(months[indexL]) + " " + years[indexL]);
		
		int indexH = indexofHighest(prices);
		System.out.print(symbol + " highest: $");
		System.out.printf("%.2f", prices[indexH]);
		System.out.println(" in " + monthName(months[indexH]) + " " + years[indexH]);
	}
	
	public static void loadData(Scanner in, int[] years, int[] months, double[] prices) {
		for(int i = 0; i<years.length; i++) {
			years[i] = in.nextInt();
			months[i] = in.nextInt();
			prices[i] = in.nextDouble();
	}
}
	public static String monthName(int months) {
		String[] mon = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
		return mon[months - 1];
	}
	public static void printData(int[] years, int[] months, double[] prices) {
		for (int i=0; i<years.length; i++) {
		System.out.print(monthName(months[i]) + " " + years[i] + ":" + " " + "$");
		System.out.printf("%.2f", prices[i]);
		System.out.println();
		}
	}
	public static int indexofLowest(double[] prices) {
		int index=0;
		double lowest = prices[0];
		for (int i=1; i<prices.length; i++) {
			if (prices[i] < lowest) {
				index = i;
				lowest = prices[i];
			} 
		} 
		return index;
	} 
	public static int indexofHighest(double[] prices) {
		int index=0;
		double lowest = prices[0];
		for (int i=1; i>prices.length; i++) {
			if (prices[i] < lowest) {
				index = i;
				lowest = prices[i];
			} 
		} 
		return index;
	}
}
