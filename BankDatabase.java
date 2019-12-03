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
public class BankDatabase {
    private Account[] accounts;
    
    public BankDatabase()
    {
        accounts = new Account[ 2 ];
        accounts[ 0 ] = new Account( 12345, 54321, 1000.0, 1200.0 );
        accounts[ 1 ] = new Account( 98765, 56789, 200.0, 200.0 );
    } // end no-argument BankDatabase constructor
    
    private Account getAccount( int accountNumber )
    {
        for ( Account currentAccount : accounts )
        {
            if ( currentAccount.getAccountNumber() == accountNumber )
                return currentAccount;
        }
        return null;
    } // end method getAccount
    
    public boolean authenticateUser( int userAccountNumber, int userPIN )
    {
        Account userAccount = getAccount( userAccountNumber );
        
        if ( userAccount != null )
            return userAccount.validatePIN( userPIN );
        else
            return false;
    }
    
    public double getAvailableBalance( int userAccountNumber )
    {
        return getAccount( userAccountNumber ).getAvailableBalance();
    }
    
    public double getTotalBalance( int userAccountNumber )
    {
        return getAccount( userAccountNumber ).getTotalBalance();
    }
    
    public void credit( int userAccountNumber, double amount )
    {
        getAccount( userAccountNumber ).credit( amount );
    }
    
    public void debit( int userAccountNumber, double amount )
    {
        getAccount( userAccountNumber ).debit( amount );
    }
}
