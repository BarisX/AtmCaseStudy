/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package atmcasestudy;

import java.util.Scanner;

/**
 *
 * @author Barış Şenyerli
 */
public class Keypad {
    private Scanner input;
        
    public Keypad()
    {
        input = new Scanner( System.in );
    }
    
    public int getInput()
    {
        return input.nextInt();
    }
}
