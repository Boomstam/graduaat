        /**
         * TicketMachine models a naive ticket machine that issues
         * flat-fare tickets.
         * The price of a ticket is specified via the constructor.
         * It is a naive machine in the sense that it trusts its users
         * to insert enough money before trying to print a ticket.
         * It also assumes that users enter sensible amounts.
         *
         * @author David J. Barnes and Michael Kölling
         * @version 2011.07.31
         */
        public class TicketMachine
        {
            // The price of a ticket from this machine.
            private int price;
            // The amount of money entered by a customer so far.
            private int balance;
            // The total amount of money collected by this machine.
            private int total;
            // The default starting value for a ticket.
            private final static int defaultPrice = 1000;
            
            /**
             * Create a machine that issues tickets of the given price.
             * Note that the price must be greater than zero, and there
             * are no checks to ensure this.
             */
            public TicketMachine(int cost)
            {
                price = cost;
                balance = 0;
                total = 0;
            }
        
            /**
         * Create a machine that issues tickets of the default price.
         * 
         */
        public TicketMachine()
    {
        price = defaultPrice;
    }
    
    /**
     * Return the price of a ticket.
     */
    public int getPrice()
    {
        return price;
    }

    /**
     * Return the amount of money already inserted for the
     * next ticket.
     */
    public int getBalance()
    {
        return balance;
    }
    
    /**
     * Return the total amount of money collected by this machine.
     */
    public int getTotal()
    {
        return total;
    }
    
    /**
     * Increase the price by the amount of points.
     */
    public void increase(int points){
        this.price += points;
    }
    
    /**
     * Increase the price by the specified amount.
     */
    public void discount(int amount){
        this.price -= amount;
    }
    
    /**
     * Return the total amount of money collected by this machine.
     */
    public void setCost(int cost)
    {
        this.price = cost;
    }
    
    /**
     * Print the price for a ticket.
     */
    public void showPrice()
    {
        System.out.println("De prijs van een kaartje is " + price + " cents.");
    }

    /**
     * Print the price for a ticket.
     */
    public void empty()
    {
        total = 0;
    }
    
    /**
     * Receive an amount of money from a customer.
     */
    public void insertMoney(int amount)
    {
        balance = balance + amount;
    }

    /**
     * Print a ticket.
     * Update the total collected and
     * reduce the balance to zero.
     */
    public void printTicket()
    {
        // Simulate the printing of a ticket.
        System.out.println("##################");
        System.out.println("# The BlueJ Line");
        System.out.println("# Ticket");
        System.out.println("# " + price + " cents.");
        System.out.println("##################");
        System.out.println();

        // Update the total collected with the balance.
        total = total + balance;
        // Clear the balance.
        balance = 0;
    }
}
