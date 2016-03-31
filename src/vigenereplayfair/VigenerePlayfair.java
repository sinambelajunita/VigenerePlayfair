/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vigenereplayfair;

import algorithm.ExtendedVigenere;
import algorithm.PlayFair;
import algorithm.StandardVigenere;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class VigenerePlayfair {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String input, output = "", key;
        int n, ed;
        Scanner in = new Scanner(System.in);
        System.out.println("Masukkan kalimat yang ingin diolah :");
        input = in.nextLine();
        System.out.println("Masukkan key: ");
        key = in.nextLine();
        System.out.println("Masukkan nomor algoritma :");
        n = in.nextInt();
        System.out.println("Masukkan encrypt/decrypt :");
        ed = in.nextInt();
        if(n == 1){
            StandardVigenere s = new StandardVigenere();
            if(ed == 1)
                output = s.encryptText(input, key);
            else
                output = s.decryptText(input, key);
        }
        else if(n == 2){
            ExtendedVigenere s = new ExtendedVigenere();
            if(ed == 1)
                output = s.encryptText(input, key);
            else
                output = s.decryptText(input, key);
        }
        else if(n == 3){
            PlayFair s = new PlayFair();
            if(ed == 1)
                output = s.encryptText(input, key);
            else
                output = s.decryptText(input, key);
        }
        System.out.println("Hasilnya : \n" + output);
    }
    
}
