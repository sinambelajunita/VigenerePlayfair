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
    /*
     * clean all the non-alphabetic char
     * @param input
     * @return
     */
    public String cleanInput(String input){
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < input.length(); i++){
            if(input.charAt(i) >= 'a' && input.charAt(i) <= 'z'){
                output.append(input.charAt(i));
            }
            else if(input.charAt(i) >= 'A' && input.charAt(i) <= 'Z'){
                output.append(input.charAt(i));
            }
        }
        return new String(output);
    }
    /**
     * Encrypt uppercase plaintext with 256 ASCII character key.
     * @param cleanInput
     * @param key
     * @return
     */
    public String encrypt(byte[] cleanInput, String key){
        char[] output = new char[cleanInput.length];
        int nKey = key.length();
        int j = 0;
        for(int i = 0; i < cleanInput.length; i++){
            output[i] = (char) ((cleanInput[i] + key.charAt(j)) % 256);
            System.out.println((int)output[i]);
            j++;
            if(j == nKey){
                j = 0;
            }
        }
        return new String(output);
    }
    /**
     * Decrypt cipher with 256 ASCII character.
     * @param cleanInput
     * @param key
     * @return
     */
    public String decrypt(byte[] cleanInput, String key){
        char[] output = new char[cleanInput.length];
        int nKey = key.length();
        int j = 0;
        for(int i = 0; i < cleanInput.length; i++){
            output[i] = (char) ((cleanInput[i] - key.charAt(j) + 256) % 256);
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
