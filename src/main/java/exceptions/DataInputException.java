/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author carlac
 */
public class DataInputException extends Exception{
    
    
    public DataInputException (String msg) {
		
		super ("Ha habido un problema: " + msg);
		
		
	}
    
}
