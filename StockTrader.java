import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

//for people who kept their stocks, it means that they lost money in their investment as the stock value decreased after the purchse
//the bankruptcy of Lehman Brothers in September 2008 caused a stock market crash, and The Great Depression from 1929 to 1939 greatly damaged stick values of publicly traded companies

public class StockTrader {
	
	public static final double START_CASH = 10000.0;
	public static final double FEE = 10.0;
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("sp500_1990_2025.txt"));
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
		
		double result = simulate(years, months, prices);
		System.out.printf("Final value: $%,.2f%n", result);

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
		double highest = prices[0];
		for (int i=1; i<prices.length; i++) {
			if (prices[i] > highest) {
				index = i;
				highest = prices[i];
			} 
		} 
		return index;
	}
	public static double simulate(int[] years, int[] months, double[] prices) {
    double cash = START_CASH;
    int shares = 0;
    double boughtAt = 0.0;

    for (int i = 0; i < prices.length; i++) {
        double[] history = Arrays.copyOf(prices, i + 1);
        String action = decide(history, shares > 0, boughtAt);

        if (action.equals("BUY") && shares == 0) {
            int canBuy = (int) ((cash - FEE) / prices[i]);
            if (canBuy > 0) {
                shares = canBuy;
                cash = cash - shares * prices[i] - FEE;
                boughtAt = prices[i];
                System.out.printf("%s %d: BUY %d shares at $%.2f%n",
                        monthName(months[i]), years[i], shares, prices[i]);
            }
        } else if (action.equals("SELL") && shares > 0) {
            cash = cash + shares * prices[i] - FEE;
            System.out.printf("%s %d: SELL %d shares at $%.2f%n",
                    monthName(months[i]), years[i], shares, prices[i]);
            shares = 0;
            boughtAt = 0.0;
			}
		}
    return cash + shares * prices[prices.length - 1];
	}
	public static String decide(double[] history, boolean holding, double boughtAt) {
		return buyAndHold(history, holding, boughtAt);   
}
	public static String buyAndHold(double[] history, boolean holding, double boughtAt) {
		return "BUY";
	}
	public static String dipBuyer(double[] history, boolean holding, double boughtAt) {
		if (holding == false) {
			if ()
	}
}
