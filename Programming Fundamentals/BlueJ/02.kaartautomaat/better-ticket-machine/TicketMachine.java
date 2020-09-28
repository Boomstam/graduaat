/**
 * TicketMachine models a ticket machine that issues
 * flat-fare tickets.
 * The price of a ticket is specified via the constructor.
 * Instances will check to ensure that a user only enters
 * sensible amounts of money, and will only print a ticket
 * if enough money has been input.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class TicketMachine
{
    // The price of a ticket from this machine.
    private int priceA;
    private int priceB;
    // The amount of money entered by a customer so far.
    private int balance;
    // The total amount of money collected by this machine.
    private int total;

    /**
     * Create a machine that issues tickets of the given price.
     */
    public TicketMachine(int costA, int costB)
    {
        priceA = costA;
        priceB = costB;
        balance = 0;
        total = 0;
    }

    /**
     * @Return The price of a ticket.
     */
    public int getPrice(boolean typeA)
    {
        if(typeA){
            return priceA;
        }
        return priceB;
    }

    /**
     * Return The amount of money already inserted for the
     * next ticket.
     */
    public int getBalance()
    {
        return balance;
    }

    /**
     * Receive an amount of money from a customer.
     * Check that the amount is sensible.
     */
    public void insertMoney(int amount)
    {
        if(amount > 0) {
            balance = balance + amount;
            
            System.out.println("An amount of " +
                               amount + " was added.");
        }
        else {
            System.out.println("Use a positive amount rather than: " +
                               amount);
        }
    }

    /**
     * Get the total savings for a given discount.
     */
    public double getSavings(double discount, boolean typeA){
        int price = getPrice(typeA);
        
        if(discount < 0 || discount > 1){
            System.out.println("Use a number between 0 and 1 rather than: " +
                               discount);
            return 0;
        } else{
            double savings = discount * price;
            return savings;
        }
    }
    
    /**
     * Get the average cost per transaction.
     */
    public double getAverage(int count){
        if(count <= 0){
            System.out.println("Use a number larger than 0 rather than: " +
                               count);
            return 0;
        } else{
            double average = total / count;
            return average;
        }
    }
    
    /**
     * Print if the price is in budget.
     */
    public void compareBudget(int budget, boolean typeA){
        int price = getPrice(typeA);
        
        if(budget < price){
            System.out.println("Te duur, budget is slechts " + budget);
        } else{
            System.out.println("Ok");
        }
    }
    
    /**
     * Empty the machine and return how much was emptied.
     */
    public int emptyMachine(){
        int currentTotal = total;
        total = 0;
        return currentTotal;
    }
    
    /**
     * Print a ticket if enough money has been inserted, and
     * reduce the current balance by the ticket price. Print
     * an error message if more money is required.
     */
    public void printTicket(boolean typeA)
    {
        int price = getPrice(typeA);
        
        int amountLeftToPay = price - balance;
        
        if(amountLeftToPay < 0) {
            // Simulate the printing of a ticket.
            System.out.println("##################");
            System.out.println("# The BlueJ Line");
            System.out.println("# Ticket");
            System.out.println("# " + price + " cents.");
            System.out.println("##################");
            System.out.println();

            // Update the total collected with the price.
            total = total + price;
            // Reduce the balance by the prince.
            balance = balance - price;
        }
        else {
            System.out.println("You must insert at least: " +
                               amountLeftToPay + " more cents.");
                    
        }
    }

    /**
     * Return the money in the balance.
     * The balance is cleared.
     */
    public int refundBalance()
    {
        int amountToRefund;
        amountToRefund = balance;
        balance = 0;
        return amountToRefund;
    }
}
