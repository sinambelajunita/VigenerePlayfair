/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm;

/**
 *
 * @author user
 */
public class ExtendedVigenere {
    /**
     * clean all the non-alphabetic char
     * @param input
     * @return
     */
    public String cleanInput(String input){
        char[] output = new char[input.length()];
        int j = 0; // counter for output
        for(int i = 0; i < input.length(); i++){
            if(input.charAt(i) >= 'a' && input.charAt(i) <= 'z'){
                output[j] = input.charAt(i);
                j++;
            }
            else if(input.charAt(i) >= 'A' && input.charAt(i) <= 'Z'){
                output[j] = input.charAt(i);
                j++;
            }
        }
        String outputString = new String(output);
        return outputString.toLowerCase();
    }
    /**
     * encrypt with lower-case key and 26 alphabet
     * @param input
     * @param key
     * @return
     */
    public String encrypt(String input, String key){
        char[] output = new char[input.length()];
        int nKey = key.length();
        int j = 0;
        for(int i = 0; i < input.length(); i++){
            output[i] = (char) (((input.charAt(i) + key.charAt(j) - 96) % 26) + 96);
            j++;
            if(j == nKey){
                j = 0;
            }
        }
        return new String(output);
    }
}
