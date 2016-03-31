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
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < input.length(); i++){
            if(input.charAt(i) >= 'a' && input.charAt(i) <= 'z'){
                output.append(input.charAt(i));
            }
            else if(input.charAt(i) >= 'A' && input.charAt(i) <= 'Z'){
                output.append(input.charAt(i));
            }
        }
        String outputString = new String(output);
        return outputString.toUpperCase();
    }
    /**
     * Encrypt uppercase plaintext with 26 alphabet character.
     * @param cleanInput
     * @param key
     * @return
     */
    public String encrypt(byte[] cleanInput, String key){
        char[] output = new char[cleanInput.length];
        int nKey = key.length();
        int j = 0;
        for(int i = 0; i < cleanInput.length; i++){
            output[i] = (char) (((cleanInput[i] - 'A' + key.toUpperCase().charAt(j) - 'A') % 26) + (int) 'A');
            j++;
            if(j == nKey){
                j = 0;
            }
        }
        return new String(output);
    }
    /**
     * Decrypt cipher with 26 alphabet character.
     * @param cleanInput
     * @param key
     * @return
     */
    public String decrypt(byte[] cleanInput, String key){
        char[] output = new char[cleanInput.length];
        int nKey = key.length();
        int j = 0;
        for(int i = 0; i < cleanInput.length; i++){
            int ia = ((cleanInput[i] + 26 - key.toUpperCase().charAt(j)) % 26);
            output[i] = (char) (ia + 'A');
            j++;
            if(j == nKey){
                j = 0;
            }
        }
        return new String(output);
    }
    
    public String encryptText(String input, String key){
        String cleanInput = cleanInput(input);
        byte[] cleanInputByte = cleanInput.getBytes();
        return encrypt(cleanInputByte, key);
    }
    public String decryptText(String input, String key){
        String cleanInput = cleanInput(input);
        byte[] cleanInputByte = cleanInput.getBytes();
        return decrypt(cleanInputByte, key);
    }
}
