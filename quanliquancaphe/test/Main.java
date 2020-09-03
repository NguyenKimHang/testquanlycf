
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author admin
 */
public class Main {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(Tester.class);
        
        if (result.wasSuccessful()) 
            System.out.println("Thành Công");
        else
        result.getFailures().forEach(f->System.err.println(f));
        }

}
