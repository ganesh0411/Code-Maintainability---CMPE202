//package Gumball;
import java.util.Scanner;

public class GumballMachine{
	private int machine;
	private boolean endMachine = false;
	private int num_gumballs;
    private boolean has_quarter;
    private int coinValue;
    private boolean firstCoin = true;
    
    Scanner input = new Scanner(System.in);

    public GumballMachine( int size ){
        // initialise instance variables
        this.num_gumballs = size;
        this.has_quarter = false;
    }

    public void insertQuarter(int coin){
        if ( coin == 25 )
            this.has_quarter = true ;
        else 
            this.has_quarter = false ;
    }
    
    public void turnCrank(){
    	if ( this.has_quarter ){
    		if ( this.num_gumballs > 0 && firstCoin){
    			this.num_gumballs-- ;
    			this.has_quarter = false ;
 
    			System.out.println( "Thanks for your coin. Gumball Ejected!" ) ;
    			endMachine = true;
    		}
    		else{
    			this.firstCoin = true;
    			System.out.println( "Please insert another quarter" ) ;
    		}
    	}
    	else {
    		endMachine = true;
    		System.out.println( "Coin Ejected. Please insert a quarter" ) ;
    	}        
    }
    
    public void getMachine(){
    	System.out.println("****Which machine you want to use?****");
    	//System.out.println("Gumballs in Stock: " + this.num_gumballs);
    	System.out.println("1. 25\t2. 50\t3. 50(accepts any coin)");
    	machine = input.nextInt();
    }
    
    public void coinTracker() {
    	if (this.has_quarter) {
    		this.firstCoin = false;
    	}
    }
    
    public void getCoinsOfAllTypes() {
    	int cost = 50;
    	int value = 0;
    	while(value < cost) {
    		getCoin();
    		if(coinValue != 5 && coinValue != 10 && coinValue != 25) {
    			System.out.println("Please insert nickel or dime or quarter only");
    			continue;
    		}
    		value = value + coinValue;
    		
    	}

    	this.coinValue = value;
    	this.has_quarter = true;
    }
    
    public void machineOperation() {
    	getMachine();
    	switch(machine) {
    	case 1:{
    		firstMachine();
    		break;
    	}
    	case 2:{
    		secondMachine();
    		break;
    	}
    	case 3:{
    		thirdMachine();
    		break;
    	}
    	default:{
    		System.out.println("Invalid Input. Please try again");
    		break;
    	}
    	}
    }
    
    public void getCoin() {
    	System.out.println("Insert a coin");
    	this.coinValue = input.nextInt();
    }
    
    public void firstMachine() {
    	getCoin();
    	insertQuarter(coinValue);
    	turnCrank();
    }
    
    public void secondMachine() {
    	this.endMachine = false;
    	while(!this.endMachine) {
    		getCoin();
    		insertQuarter(coinValue);
    		if(this.has_quarter) {
    			coinTracker();
    			turnCrank();
    			getCoin();
        		insertQuarter(coinValue);
        		turnCrank();
    		}
    		else {
    			this.endMachine = true;
    			System.out.println("Coin Ejected. Please try again and insert a quarter");
    		}
    	}
    }
    
    public void thirdMachine() {
    	getCoinsOfAllTypes();
    	if(this.coinValue >= 50) {
    		turnCrank();
    	}
    }
    
    public static void main(String args[]) {
    	GumballMachine gm = new GumballMachine(10);
    	while(gm.num_gumballs > 0) {
    		gm.machineOperation();
    	}
    	gm.input.close();
    	System.out.println("Thank you! Gumballs Sold Out");
    }
}