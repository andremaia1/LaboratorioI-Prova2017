/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prova;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author angeloluz
 */
public class Util {
    
    /**
     * 
     * @param msg Mensagem que será exibida para o usuário (antes da leitura)
     * @return Retorna a String digitada pelo usuário
     */
    public static String lerString (String msg) {
        
        Scanner teclado = new Scanner(System.in);
        
        System.out.print(msg);
        
        return teclado.nextLine();
    }
    
    /**
     * 
     * @param msg Mensagem que será exibida para o usuário (antes da leitura)
     * @param msgErro Mensagem que será exibida para o usuário caso ocorra um erro na leitura
     * @return Retorna o valor (int) digitado pelo usuário ou -1 em caso de erro
     */
    public static int lerInt (String msg, String msgErro) {
        
        Scanner teclado = new Scanner(System.in);
        int num;
        
        System.out.print(msg);
        
        try {
            num = teclado.nextInt();
        } catch (Exception e) {
            num = -1;
            System.out.print(msgErro);
        }
        
        return num;
    }
    
    /**
     * 
     * @param msg Mensagem que será exibida para o usuário (antes da leitura)
     * @param msgErro Mensagem que será exibida para o usuário caso ocorra um erro na leitura
     * @return Retorna o valor (double) digitado pelo usuário ou -1 em caso de erro
     */
    public static double lerDouble (String msg, String msgErro) {
        
        Scanner teclado = new Scanner(System.in);
        double num;
        
        System.out.print(msg);
        
        try {
            num = teclado.nextDouble();
        } catch (Exception e) {
            num = -1;
            System.out.print(msgErro);
        }
        
        return num;
    }
    
    public static void load(String mensagem, long tempo) {
        try {
            System.out.print(mensagem);
            long load = tempo / 5;
            int ponto = 0;
            while (ponto++ < 5) {
                System.out.print(".");
                Thread.sleep(load);
            }
            System.out.println("");
        } catch (InterruptedException ex) {
            Logger.getLogger(Boteco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
