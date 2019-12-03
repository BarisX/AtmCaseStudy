/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package atmcasestudy;

/**
 *
 * @author Barış Şenyerli
 */
public class Withdrawal extends Transaction 
{
    private int accountNumber;
    private double amount;
    
    //private Screen screen;
    private Keypad keypad;
    private CashDispenser cashDispenser;
    //private BankDatabase bankDatabase;
    
    private final static int CANCELED = 6;
    
    public Withdrawal( int userAccountNumber, Screen atmScreen,
            BankDatabase atmBankDatabase, Keypad atmKeypad,
            CashDispenser atmCashDispenser )
    {
        super( userAccountNumber, atmScreen, atmBankDatabase );
        
        keypad = atmKeypad;
        cashDispenser = atmCashDispenser;
    }
    
    
    @Override
    public void execute()
    {
        boolean cashDispensed = false;
        double availableBalance;
        
        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen();
        
        do
        {
            amount = displayMenuOfAmounts();
            
            if ( amount != CANCELED )
            {
                availableBalance = 
                        bankDatabase.getAvailableBalance( getAccountNumber() );
                
                if ( amount <= availableBalance )
                {
                    if ( cashDispenser.isSufficientCashAvailable( ( int ) amount ) )
                    {
                        bankDatabase.debit( getAccountNumber(), amount );
                        
                        cashDispenser.dispenseCash( ( int ) amount );
                        cashDispensed = true;
                        
                        screen.displayMessageLine( "\nYour cash has been" + 
                                " dispensed. Please take your cash now.");
                    } // end if
                    else
                    {
                        screen.displayMessageLine( 
                        "\nInsufficent cash available in the ATM." + 
                                "\n\nPlease choose smaller amount" );
                    } // end else
                } // end if
                else 
                {
                    screen.displayMessageLine( 
                        "\nInsufficent cash available in the ATM." + 
                                "\n\nPlease choose smaller amount" );
                }
            } 
            else
                {
                    screen.displayMessageLine( "\nCancelling transaction..." );
                    return;
                } // end else
        } while ( !cashDispensed );
    } // end method execute
    
    private int displayMenuOfAmounts()
    {
        int userChoice = 0;
        
        Screen screen = getScreen();
        
        int[] amounts = { 0, 20, 40, 60, 100, 200 };
        
        while ( userChoice == 0 )
        {
            screen.displayMessageLine( "\nWithdrawal Manu:" );
            screen.displayMessageLine( "1 - $20" );
            screen.displayMessageLine( "2 - $40" );
            screen.displayMessageLine( "3 - $60" );
            screen.displayMessageLine( "4 - $100" );
            screen.displayMessageLine( "5 - $200" );
            screen.displayMessageLine( "6 - Cancel transaction" );
            screen.displayMessageLine( "\nChoose a withdrawal amount: " );
            
            int input = keypad.getInput();
            
            switch ( input )
            {
                case 1: // if the user choose a withdrawal amount
                case 2:
                case 3:
                case 4:
                case 5:
                    userChoice = amounts[ input ];
                    break;
                case CANCELED:
                    userChoice = CANCELED;
                    break;
                default:
                    screen.displayMessageLine( "\nInvalid selection. Try again." );
            } // end switch  
        } // end while
        return userChoice;
    } // end method displayMenuOfAmounts
} // end class Withdrawal
