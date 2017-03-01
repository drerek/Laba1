package com.vadymandartem;

 import java.io.*;
 import java.util.HashMap;
 import java.util.Map;


public class Main {

    public static void frequencyOfMonogramsAndEntropyWithNamespace(char[] ourText, int length){
        Map mono = new HashMap<String,Integer>();
        for (char monogramm = 'а'; monogramm<='я'; monogramm++){
            int j=0;
            for (int i=0;i<length;i++){
                if (ourText[i]==monogramm) j++;
            }
            if (j>0) mono.put(monogramm,j);
        }

        int namespace=0;
        for (int i=0;i<length;i++){
            if (ourText[i]==' ') namespace++;
        }
        if (namespace>0) mono.put(' ',namespace);

        System.out.println(mono);

        float entropy = 0;
        for (char monogramm = 'а'; monogramm<='я'; monogramm++){
            if (mono.get(monogramm)!=null) {
                int countity = (int) mono.get(monogramm);
                float prob = (float)countity/(float)length;
                entropy -= prob*((float)Math.log(prob)/Math.log(2.0));
            }
        }
        int countity = (int) mono.get(' ');
        float prob = (float)countity/(float)length;
        entropy -= prob*((float)Math.log(prob)/Math.log(2.0));
        System.out.println("H1=" + entropy);
    }

    public static void frequencyOfMonogramsAndEntropyWithoutNamespace(char[] ourText, int length){
        char[] tempText = new char[400000];
        int countNamespace = 0;
        for (int temp = 0; temp < length; temp++) {
            if (ourText[temp] == ' ') countNamespace++;
            else tempText[temp - countNamespace] = ourText[temp];
        }

        int tempLength = length - countNamespace;

        Map mono = new HashMap<String,Integer>();
        for (char monogramm = 'а'; monogramm<='я'; monogramm++){
            int j=0;
            for (int i=0;i<tempLength;i++){
                if (tempText[i]==monogramm) j++;
            }
            if (j>0) mono.put(monogramm,j);
        }

        System.out.println(mono);

        float entropy = 0;
        for (char monogramm = 'а'; monogramm<='я'; monogramm++){
            if (mono.get(monogramm)!=null) {
                int countity = (int) mono.get(monogramm);
                float prob = (float)countity/(float)tempLength;
                entropy -= prob*((float)Math.log(prob)/Math.log(2.0));
            }
        }
        System.out.println("H1=" + entropy);
    }

    public static void frequencyOfbigramsAndEntropyWithNamespaceStep1(char[] ourText, int length){
        Map bi = new HashMap<String, Integer>();
        for (char monogramm1 = 'а'; monogramm1<='я'; monogramm1++){
            for (char monogramm2= 'а'; monogramm2<='я'; monogramm2++) {
                int i, j = 0;
                for (i = 0; i < length - 1; i++) {
                    if (ourText[i] == monogramm1 && ourText[i + 1] == monogramm2) j++;
                }
                if (j>0) bi.put(String.valueOf(monogramm1)+String.valueOf(monogramm2),j);
            }
        }
        System.out.println(bi);
    }

    public static void frequencyOfbigramsAndEntropyWithNamespaceStep2(char[] ourText, int length){
        Map bi = new HashMap<String, Integer>();
        for (char monogramm1 = 'а'; monogramm1<='я'; monogramm1++){
            for (char monogramm2= 'а'; monogramm2<='я'; monogramm2++) {
                int i, j = 0;
                for (i = 0; i < length - 2; i++) {
                    if (ourText[i] == monogramm1 && ourText[i + 2] == monogramm2) j++;
                }
                if (j>0) bi.put(String.valueOf(monogramm1)+String.valueOf(monogramm2),j);
            }
        }
        System.out.println(bi);
    }

    public static void frequencyOfbigramsAndEntropyWithoutNamespaceStep1(char[] ourText, int length){
        char[] tempText = new char[400000];
        int countNamespace = 0;
        for (int temp = 0; temp < length; temp++) {
            if (ourText[temp] == ' ') countNamespace++;
            else tempText[temp - countNamespace] = ourText[temp];
        }

        int tempLength = length - countNamespace;

        Map bi = new HashMap<String, Integer>();
        for (char monogramm1 = 'а'; monogramm1<='я'; monogramm1++){
            for (char monogramm2= 'а'; monogramm2<='я'; monogramm2++) {
                int i, j = 0;
                for (i = 0; i < tempLength - 1; i++) {
                    if (tempText[i] == monogramm1 && tempText[i + 1] == monogramm2) j++;
                }
                if (j>0) bi.put(String.valueOf(monogramm1)+String.valueOf(monogramm2),j);
            }
        }
        System.out.println(bi);
    }

    public static void frequencyOfbigramsAndEntropyWithoutNamespaceStep2(char[] ourText, int length){
        char[] tempText = new char[400000];
        int countNamespace = 0;
        for (int temp = 0; temp < length; temp++) {
            if (ourText[temp] == ' ') countNamespace++;
            else tempText[temp - countNamespace] = ourText[temp];
        }

        int tempLength = length - countNamespace;

        Map bi = new HashMap<String, Integer>();
        for (char monogramm1 = 'а'; monogramm1<='я'; monogramm1++){
            for (char monogramm2= 'а'; monogramm2<='я'; monogramm2++) {
                int i, j = 0;
                for (i = 0; i < tempLength - 2; i++) {
                    if (tempText[i] == monogramm1 && tempText[i + 2] == monogramm2) j++;
                }
                if (j>0) bi.put(String.valueOf(monogramm1)+String.valueOf(monogramm2),j);
            }
        }
        System.out.println(bi);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        char[] ourText = new char[4000000];
        int lengthOfAlphabet = 0;
        for (char tempSymbol = 'а'; tempSymbol<='я'; tempSymbol++){lengthOfAlphabet++;}
        System.out.println("lengthOfAlphabet = " + lengthOfAlphabet);

        // Read in mass
        try(BufferedReader buf = new BufferedReader (new InputStreamReader(new FileInputStream("C:\\Users\\vadim\\IdeaProjects\\Laba1(git)\\11.txt"), "UTF-8"))){
            int c;
            int i=0;
            while ( (c = buf.read()) != -1){
                char symbol = ((char)c);
                if (Character.isUpperCase(symbol)) symbol=Character.toLowerCase(symbol);
                if (symbol >= 'а' && symbol<= 'я'){
                    ourText[i] = symbol;
                    i++;
                }
                if ((symbol==' ' || symbol=='\n' || symbol=='\t' || symbol=='.' || symbol==',' || symbol=='-') && i>0 && ourText[i-1]!=' ' ){
                    ourText[i] = ' ';
                    i++;
                }
                if ((symbol==' ' || symbol=='\n' || symbol=='\t' || symbol=='.' || symbol==',' || symbol=='-') && i==0){
                    ourText[i] = ' ';
                    i++;
                }
            }
            int length = i;
            buf.close();
            //for(int t = 0; t < length; t++) System.out.println(ourText[t]);

            // Read monogramm and H1
            frequencyOfMonogramsAndEntropyWithNamespace(ourText, length);
            frequencyOfMonogramsAndEntropyWithoutNamespace(ourText, length);


            //Read bigramm
            frequencyOfbigramsAndEntropyWithNamespaceStep1(ourText, length);
            frequencyOfbigramsAndEntropyWithNamespaceStep2(ourText, length);
            frequencyOfbigramsAndEntropyWithoutNamespaceStep1(ourText, length);
            frequencyOfbigramsAndEntropyWithoutNamespaceStep2(ourText, length);

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
