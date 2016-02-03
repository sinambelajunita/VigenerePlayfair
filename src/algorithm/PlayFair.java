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
public class PlayFair {
    private char[][] keyMatrix;
    public PlayFair(){
        keyMatrix = new char[6][6];
    }
    /**
     * clean input string from non alphabet character and change 'j' and 'J' to 'I'
     * @param input
     * @param key
     * @return
     */
    private String cleanInput(String input){
        char[] output = new char[input.length()];
        int j = 0; // counter for output
        for(int i = 0; i < input.length(); i++){
            if(input.charAt(i) >= 'a' && input.charAt(i) <= 'z'){
                if(input.charAt(i) == 'j'){
                    output[j] = 'I';
                }
                else{
                    output[j] = input.charAt(i);
                }
                j++;
            }
            else if(input.charAt(i) >= 'A' && input.charAt(i) <= 'Z'){
                if(input.charAt(i) == 'J'){
                    output[j] = 'I';
                }
                else{
                    output[j] = input.charAt(i);
                }
                j++;
            }
        }
        String outputString = new String(output);
        return outputString.toUpperCase();
    }
    /**
     * clean input string from non alphabet character and 'j' and 'J'
     * @param input
     * @param key
     * @return
     */
    private String cleanKey(String input){
        char[] output = new char[input.length()];
        int j = 0; // counter for output
        for(int i = 0; i < input.length(); i++){
            if(input.charAt(i) >= 'a' && input.charAt(i) <= 'z'){
                if(input.charAt(i) != 'j'){
                    output[j] = input.charAt(i);
                    j++;
                }
            }
            else if(input.charAt(i) >= 'A' && input.charAt(i) <= 'Z'){
                if(input.charAt(i) != 'J'){
                    output[j] = input.charAt(i);
                    j++;
                }
            }
        }
        String outputString = new String(output);
        return outputString.toUpperCase();
    }
    /**
     * make matrix key from key input
     * @param key
     * @return
     */
    private void makeKeyMatrix(String key){
        boolean[] isUsed = new boolean[26];
        for(int i = 0; i < 26; i++){
            isUsed[i] = false;
        }
        String cleanKey = cleanKey(key);
        int nKey = key.length();
        int n = 0; // counter for key
        int m = 0; // counter for alphabet order
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++){
                if(n < nKey){
                    while(isUsed[cleanKey.charAt(n) - 65]){
                        n++;
                    }
                    keyMatrix[i][j] = cleanKey.charAt(n);
                    isUsed[cleanKey.charAt(n) - 65] = true;
                    n++;
                }
                else{
                    while(isUsed[m]){
                        m++;
                    }
                    keyMatrix[i][j] = (char)(m + 65);
                    isUsed[cleanKey.charAt(m) - 65] = true;
                    m++;
                }
            }
        for (int i = 0; i < 5; i++){
            keyMatrix[i][5] = keyMatrix[i][0];
        }
        for (int i = 0; i < 5; i++){
            keyMatrix[5][i] = keyMatrix[0][i];
        }
        keyMatrix[5][5] = '0';
    }
    /**
     * Get number of column of a character in key matrix. Start from 0
     * @param c
     * @return
     */
    private int column(char c){
        int i = 0, j = 0, column = 0;
        while(i < 6){
            while(j < 6){
                if(keyMatrix[i][j] == c){
                    column = j;
                    break;
                }
                j++;
            }
            i++;
        }
        return column;
    }
    /**
     * Get number of row of a character in key matrix. Start from 0
     * @param c
     * @return
     */
    private int row(char c){
        int i = 0, j = 0, row = 0;
        while(i < 6){
            while(j < 6){
                if(keyMatrix[i][j] == c){
                    row = i;
                    break;
                }
                j++;
            }
            i++;
        }
        return row;
    }
    /**
     * Encrypt input plaintext from user with input key. Return cipher string
     * @param key
     * @param input
     * @return
     */
    public String encrypt(String key, String input){
        String cleanInput = cleanInput(input);
        makeKeyMatrix(key);
        char[] output = new char[cleanInput.length()*2];
        char c1, c2;
        int i = 0, j = 0, nInput = cleanInput.length();
        while (i < nInput){
            if(nInput >= i + 1){
                if(cleanInput.charAt(i) != cleanInput.charAt(i + 1)){
                    c1 = cleanInput.charAt(i);
                    c2 = cleanInput.charAt(i + 1);
                    i+=2;
                }
                else{
                    c1 = cleanInput.charAt(i);
                    c2 = 'Z';
                    i++;
                }
            }
            else{
                c1 = cleanInput.charAt(i);
                c2 = 'Z';
                i++;
            }
            if(row(c1) == row(c2)){
                output[j] = keyMatrix[column(c1) + 1][c1];
                output[j + 1] = keyMatrix[column(c2) + 1][row(c2)];
            }
            else if(column(c1) == column(c2)){
                output[j] = keyMatrix[column(c1)][row(c1) + 1];
                output[j + 1] = keyMatrix[column(c2)][row(c2) + 1];
            }
            else{
                output[j] = keyMatrix[column(c2)][row(c1)];
                output[j + 1] = keyMatrix[column(c1)][row(c2)];
            }
            j+=2;
        }
        return new String(output);
    }
    /**
     * Decrypt input cipher from user with input key. Return plaintext string
     * @param key
     * @param input
     * @return
     */
    public String decrypt(String key, String input){
        makeKeyMatrix(key);
        char[] output = new char[input.length()*2];
        char c1, c2;
        int i = 0, j = 0, nInput = input.length();
        while (i < nInput){
            c1 = input.charAt(i);
            c2 = input.charAt(i + 1);
            i+=2;
            if(row(c1) == row(c2)){
                output[j] = keyMatrix[column(c1) - 1][c1];
                output[j + 1] = keyMatrix[column(c2) - 1][row(c2)];
            }
            else if(column(c1) == column(c2)){
                output[j] = keyMatrix[column(c1)][row(c1) - 1];
                output[j + 1] = keyMatrix[column(c2)][row(c2) - 1];
            }
            else{
                output[j] = keyMatrix[column(c2)][row(c1)];
                output[j + 1] = keyMatrix[column(c1)][row(c2)];
            }
            j+=2;
        }
        return new String(output);
    }
}