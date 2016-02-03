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
public class StandardVigenere {
    /**
     * Clean all the non-alphabetic char from plaintext.
     * @param input
     * @return
     */
    private String cleanInput(String input){
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
        return outputString.toUpperCase();
    }
    /**
     * Encrypt uppercase plaintext with 26 alphabet character.
     * @param input
     * @param key
     * @return
     */
    public String encrypt(String input, String key){
        String cleanInput = cleanInput(input);
        char[] output = new char[cleanInput.length()];
        int nKey = key.length();
        int j = 0;
        for(int i = 0; i < input.length(); i++){
            output[i] = (char) (((cleanInput.charAt(i) + key.charAt(j) - 130) % 26) + 65);
            j++;
            if(j == nKey){
                j = 0;
            }
        }
        return new String(output);
    }
    /**
     * Decrypt cipher with 26 alphabet character.
     * @param input
     * @param key
     * @return
     */
    public String decrypt(String input, String key){
        char[] output = new char[input.length()];
        int nKey = key.length();
        int j = 0;
        for(int i = 0; i < input.length(); i++){
            output[i] = (char) (((input.charAt(i) - key.charAt(j) - 130) % 26) + 65);
            j++;
            if(j == nKey){
                j = 0;
            }
        }
        return new String(output);
    }
}
