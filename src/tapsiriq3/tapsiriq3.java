package tapsiriq3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class tapsiriq3 {
    public static void main(String[] args) {


    }
    public static void task1(int[] array){
        int lowest = array[0];
        int highest = array[0];
        for(int i:array){
            if(i > highest) highest = i;
            if(i < lowest) lowest = i;
        }

        System.out.println("highest is: " + highest);
        System.out.println("lowest is: " + lowest);
    }

    public static void task2(int[][] array){
        int outerLength = array.length;
        int sumMain = 0;
        int sumSide = 0;
        for(int i = 0;i < outerLength;i++){
            if (array[i].length != outerLength){
                System.out.println("given two dimensional array is not a square");
                return;
            }
            sumMain += array[i][i];
            sumSide += array[outerLength - 1 - i][i];
        }
        System.out.printf("The main diagonal's sum is %d, the other diagonal's sum is %d", sumMain, sumSide);

    }
    public static void task3(int[][][] array){
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                for (int k = 0; k < array[i][j].length; k++) {
                    if (array[i][j][k] > 6) {
                        System.out.println(array[i][j][k]);
                    }
                }
            }
        }

    }

    public static void task4(int[] array){
        int sortedPart = 0;
        while (sortedPart < array.length - 1) {
            int nextNumber = array[sortedPart + 1];
            int i = sortedPart;
            while (i >= 0 && array[i] > nextNumber) {
                array[i + 1] = array[i];
                i--;
            }
            array[i + 1] = nextNumber;
            sortedPart++;
        }

        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }

        System.out.printf(Arrays.toString(array));


    }
    public static void task5(String string){
        String reversed = new StringBuilder(string).reverse().toString();
        System.out.println(reversed);
//        Array-ı tərsinə çevirmək taskında .reverse() istifade etməyin yazılıb, burda yazılmayıb
    }
    public static void task6(String string){
        if(string.equalsIgnoreCase(new StringBuilder(string).reverse().toString()) ){
            System.out.println(string + " is a palindrome");
        }else System.out.println(string + " is not a palindrome");
//        Yenə də, reverse istifadə etməyin yazılmayıb. O biri taskda xüsusilə qeyd olunub, ona görə düşündüm ki,
//        istifadə etmək nəzərdə tutlubş
    }
    public static void task7(String string){
        Map<Character, Integer> countMap = new HashMap<>();

        for (char c : string.toCharArray()) {
            if (Character.isLetter(c)) {
                c = Character.toLowerCase(c);
                countMap.put(c, countMap.getOrDefault(c, 0) + 1);
            }
        }
        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    }


