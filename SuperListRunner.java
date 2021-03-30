package com.company;

import java.util.Collections;

public class SuperListRunner {

    public SuperListRunner() {

        SuperList<Integer> list = new SuperList<Integer>();
        for (int i = 0; i < 30; i++) {
            int num = (int)(Math.random()*1000)+1;
            list.add(num);
        }
        System.out.println();

        System.out.println("List: " + list.toString());
        System.out.println("Size: " + list.size());

        System.out.println();

        SuperList<Integer> stack = new SuperList<Integer>();
        while (list.size() > 0)
            stack.push(list.poll());
        System.out.println("Stack: " + stack.toString());

        System.out.println();

        SuperList<Integer> queue = new SuperList<Integer>();
        while (stack.size() > 0)
            queue.add(stack.pop());
        System.out.println("Queue: " + queue.toString());

        System.out.println();

        for (int i = 0; i < 30; i++)
            list.add(i, 0);

        while (list.contains(0)) {
            try {
                int index = (int)(Math.random()*30);
                if (list.get(index) == 0) {
                    int temp = queue.poll();
                    list.add(index, temp);
                    list.remove(index+1);
                }
            } catch (NullPointerException e) {}
        }
        System.out.println("New List: " + list.toString());

        System.out.println();

        int total = 0;
        int odd = 0;
        int even = 0;
        SuperList<Integer> listEven = new SuperList<Integer>();

        for (int i = 0; i < list.size(); i++) {
            total += list.get(i);
            if (i%2 == 0)
                even += list.get(i);
            else
                odd += list.get(i);

            if (list.get(i)%2 == 0)
                listEven.add(list.get(i));
        }
        System.out.println("Sum of List: " + total);
        System.out.println("Sum of Even Indexed Values: " + even);
        System.out.println("Sum of Odd Indexed Values: " + odd);

        while (listEven.size() > 0)
            list.add(listEven.poll());
        System.out.println("With Even Duplicates Added: " + list.toString());

        System.out.println();

        int index = 0;
        while (index < list.size()) {
            if (list.get(index)%3 == 0)
                list.remove(index);
            else
                index++;
        }
        System.out.println("List Without Values Divisible by 3: " + list.toString());

        System.out.println();

        list.add(4, 55555);
        System.out.println("List with New Number: "+list.toString());

        for (int i = 1; i < list.size(); i++) {
            int num = list.get(i);
            list.remove(i);
            int j = i - 1;

            while (j >=0 && num < list.get(j)) {
                list.add(j+1, list.get(j));
                list.remove(j);
                j--;
            }
            list.add(j+1, num);
        }



        System.out.println("List in Ascending Order: " + list.toString());

        System.out.println();

        SuperList<Integer> bMed = new SuperList<Integer>();
        SuperList<Integer> aMed = new SuperList<Integer>();
        int oddM = 0;
        double avg = 0;

        if (list.size()%2 == 0) {
            double one = (double)list.get(list.size()/2);
            double two = (double)list.get(list.size()/2 - 1);
            avg = (one+two) / 2;
            System.out.println("Even Median: " + avg);
            for (int i = 0; i < (list.size()/2 - 1); i++)
                bMed.add(list.get(i));
            for (int i = list.size()/2 + 1; i < list.size(); i++)
                aMed.add(list.get(i));
        }
        else {
            oddM = list.size()/2 + 1;
            System.out.println("Odd Median: " + list.get(oddM));
            for (int i = 0; i < oddM; i++)
                bMed.add(list.get(i));
            for (int i = oddM + 1; i < list.size(); i++)
                aMed.add(list.get(i));
        }
        System.out.println("Before Median: "+bMed);
        System.out.println("After Median: "+aMed);


        System.out.println("");

        SuperList<String> stringList = new SuperList<String>();
        String testString = "The quick brown foxes jumped over the lazy dog.";

        while (testString.contains(" ")) {
            stringList.add(testString.substring(0, testString.indexOf(" ")));
            testString = testString.substring(testString.indexOf(" ") + 1);
        }
        stringList.add(testString.substring(0, testString.indexOf(".")));
        System.out.println("List: "+stringList);

        for (int i = 0; i < stringList.size(); i++) {
            if (stringList.get(i).length() <= 3)
                stringList.remove(i);
        }
        System.out.println("List Without Words With 3 or Less Characters: " + stringList);

        for (int i = 1; i < stringList.size(); i++){
            String word = stringList.get(i);
            stringList.remove(i);
            int j = i - 1;
            while (j >= 0){
                if (word.compareToIgnoreCase(stringList.get(j)) > 0)
                    break;
                stringList.add(j+1, stringList.get(j));
                stringList.remove(j);
                j--;
            }
            stringList.add(j+1, word);
        }

        System.out.println("List in Ascending Order: " + stringList);
        double avgLength = 0;
        for (int i = 0; i < stringList.size(); i++){
            avgLength += stringList.get(i).length();
        }
        avgLength /= stringList.size();
        System.out.println("Average Word Length: " + avgLength);

    }

    public static void main(String[]args) {
        SuperListRunner superList = new SuperListRunner();
    }

}