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
        StringBuilder output = new StringBuilder();
        int j = 0; // counter for output
        for(int i = 0; i < input.length(); i++){
            if(input.charAt(i) >= 'a' && input.charAt(i) <= 'z'){
                if(input.charAt(i) == 'j'){
                    output.append('I');
                }
                else{
                    output.append(input.charAt(i));
                }
                j++;
            }
            else if(input.charAt(i) >= 'A' && input.charAt(i) <= 'Z'){
                if(input.charAt(i) == 'J'){
                    output.append('I');
                }
                else{
                    output.append(input.charAt(i));
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
        isUsed['J' - 65] = true;
        String cleanKey = cleanKey(key);
        int nKey = cleanKey.length();
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
                    while(m < 26 && isUsed[m]){
                        m++;
                    }
                    if(m < 26){
                        keyMatrix[i][j] = (char)(m + 65);
                        isUsed[m] = true;
                        m++;
                    }
                }
            }
        for (int i = 0; i < 5; i++){
            keyMatrix[i][5] = keyMatrix[i][0];
        }
        for (int i = 0; i < 5; i++){
            keyMatrix[5][i] = keyMatrix[0][i];
        }
        keyMatrix[5][5] = '0';
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                System.out.print(keyMatrix[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
    /**
     * Get number of column of a character in key matrix. Start from 0
     * @param c
     * @return
     */
    private int column(char c){
        int i = 0, j = 0, column = 0;
        while(i < 6){
            j = 0;
            while(j < 6){
                if(keyMatrix[i][j] == c){
                    column = j;
                    return column;
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
            j = 0;
            while(j < 6){
                if(keyMatrix[i][j] == c){
                    row = i;
                    return row;
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
    public String encrypt(String input, String key){
        String cleanInput = cleanInput(input);
        makeKeyMatrix(key);
        char[] output = new char[(cleanInput.length() + 1)*2];
        char c1, c2;
        int i = 0, j = 0, nInput = cleanInput.length();
        while (i < nInput){
            if(i == nInput - 1 || (cleanInput.charAt(i) == cleanInput.charAt(i + 1))){
                c1 = cleanInput.charAt(i);
                c2 = 'Z';
                i++;
            }
            else{
                c1 = cleanInput.charAt(i);
                c2 = cleanInput.charAt(i + 1);
                i+=2;
            }
            if(row(c1) == row(c2)){
                output[j] = keyMatrix[row(c1)][column(c1) + 1];
                output[j + 1] = keyMatrix[row(c2)][column(c2) + 1];
            }
            else if(column(c1) == column(c2)){
                output[j] = keyMatrix[row(c1) + 1][column(c1)];
                output[j + 1] = keyMatrix[row(c2) + 1][column(c2)];
            }
            else{
                output[j] = keyMatrix[row(c1)][column(c2)];
                output[j + 1] = keyMatrix[row(c2)][column(c1)];
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
    public String decrypt(String input, String key){
        makeKeyMatrix(key);
        char[] output = new char[input.length()*2];
        char c1, c2;
        int i = 0, j = 0, nInput = input.length();
        while (i < nInput){
            c1 = input.charAt(i);
            c2 = input.charAt(i + 1);
            i+=2;
            if(row(c1) == row(c2)){
                int column1 = column(c1), column2 = column(c2);
                if(column1 == 0) column1 = 5;
                if(column2 == 0) column2 = 5;
                output[j] = keyMatrix[row(c1)][column1 - 1];
                output[j + 1] = keyMatrix[row(c2)][column2 - 1];
            }
            else if(column(c1) == column(c2)){
                int row1 = row(c1), row2 = row(c2);
                if(row1 == 0) row1 = 5;
                if(row2 == 0) row2 = 5;
                output[j] = keyMatrix[row1 - 1][column(c1)];
                output[j + 1] = keyMatrix[row2 - 1][column(c2)];
            }
            else{
                int column1 = column(c1), column2 = column(c2);
                if(column1 == 0) column1 = 5;
                if(column2 == 0) column2 = 5;
                int row1 = row(c1), row2 = row(c2);
                if(row1 == 0) row1 = 5;
                if(row2 == 0) row2 = 5;
                output[j] = keyMatrix[row1][column2];
                output[j + 1] = keyMatrix[row2][column1];
            }
            j+=2;
        }
        return new String(output);
    }
}