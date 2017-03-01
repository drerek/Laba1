package com.vadymandartem;

import java.io.*;
import java.util.*;


public class Main {

    public static void frequencyOfMonogramsAndEntropyWithNamespace(char[] ourText, int length) throws IOException {
        Map mono = new HashMap<String, Float>();

        for (char monogramm = 'а'; monogramm<='я'; monogramm++){
            int j=0;
            for (int i=0;i<length;i++){
                if (ourText[i]==monogramm) j++;
            }
            if (j>0) mono.put(monogramm, (float)j/length);
        }

        int namespace=0;
        for (int i=0;i<length;i++){
            if (ourText[i]==' ') namespace++;
        }
        if (namespace>0) mono.put(' ', (float)namespace/length);

        Map<String, Float> treeMap = new TreeMap<String, Float>(mono);
        System.out.println(treeMap);

        FileWriter writer = new FileWriter("C:\\Users\\vadim\\IdeaProjects\\Laba1(git)\\01.txt", false);
        float entropy = 0;
        for (char monogramm = 'а'; monogramm<='я'; monogramm++){
            if (mono.get(monogramm)!=null) {
                float countity = (float) mono.get(monogramm);
                writer.write(monogramm+ " " + countity + "\n");
                writer.flush();
                entropy -= countity*((float)Math.log(countity)/Math.log(2.0));
            }
            else {
                writer.write(monogramm+ " " + "0"+"\n");
                writer.flush();
            }
        }
        float countity = (float)mono.get(' ');
        entropy -= countity*((float)Math.log(countity)/Math.log(2.0));
        writer.write(" " + "="+countity+"\n");
        writer.write("H1 = " + String.valueOf(entropy));
        writer.flush();
        writer.close();
    }

    public static void frequencyOfMonogramsAndEntropyWithoutNamespace(char[] ourText, int length) throws IOException {
        char[] tempText = new char[400000];
        int countNamespace = 0;
        for (int temp = 0; temp < length; temp++) {
            if (ourText[temp] == ' ') countNamespace++;
            else tempText[temp - countNamespace] = ourText[temp];
        }

        int tempLength = length - countNamespace;

        Map mono = new HashMap<String,Float>();
        for (char monogramm = 'а'; monogramm<='я'; monogramm++){
            int j=0;
            for (int i=0;i<tempLength;i++){
                if (tempText[i]==monogramm) j++;
            }
            if (j>0) mono.put(monogramm,(float)j/tempLength);
        }
        System.out.println(mono);

        FileWriter writer = new FileWriter("C:\\Users\\vadim\\IdeaProjects\\Laba1(git)\\02.txt", false);
        float entropy = 0;
        for (char monogramm = 'а'; monogramm<='я'; monogramm++){
            if (mono.get(monogramm)!=null) {
                float countity = (float) mono.get(monogramm);
                writer.write(monogramm+ " " + countity + "\n");
                writer.flush();
                entropy -= countity*((float)Math.log(countity)/Math.log(2.0));
            }
            else {
                writer.write(monogramm+ " " + "0"+"\n");
                writer.flush();
            }
        }
        writer.write(String.valueOf(entropy));
        writer.flush();
        writer.close();
    }

    public static void frequencyOfbigramsAndEntropyWithNamespaceStep1(char[] ourText, int length) throws IOException {
        Map bi = new HashMap<String, Float>();
        for (char monogramm1 = 'а'; monogramm1<='я'; monogramm1++){
            for (char monogramm2= 'а'; monogramm2<='я'; monogramm2++) {
                int i, j = 0;
                for (i = 0; i < length - 1; i++) {
                    if (ourText[i] == monogramm1 && ourText[i + 1] == monogramm2) j++;
                }
                if (j>0) bi.put(String.valueOf(monogramm1)+String.valueOf(monogramm2),(float)j/(length-1));
            }
            int p=0, k=0;
            for (int h = 0; h<length-1; h++){
                if(ourText[h] == ' ' && ourText[h+1] == monogramm1) p++;
                if (ourText[h] == monogramm1 && ourText[h+1] == ' ') k++;
            }
            if (p>0) bi.put(String.valueOf(' ')+String.valueOf(monogramm1),(float)p/(length-1));
            if (k>0) bi.put(String.valueOf(monogramm1)+String.valueOf(' '),(float)k/(length-1));
        }
        System.out.println(bi);
        FileWriter writer = new FileWriter("C:\\Users\\vadim\\IdeaProjects\\Laba1(git)\\1.txt", false);
        float entropy = 0;
        for (char monogramm1 = 'а'; monogramm1<='я'; monogramm1++)
            writer.write("   "+monogramm1 + "       ");
        writer.write("\n");
        for (char monogramm1 = 'а'; monogramm1<='я'; monogramm1++){
            writer.write(monogramm1+" ");
            for (char monogramm2= 'а'; monogramm2<='я'; monogramm2++) {

            if (bi.get(String.valueOf(monogramm1)+String.valueOf(monogramm2))!=null) {
                float countity = (float) bi.get(String.valueOf(monogramm1)+String.valueOf(monogramm2));
                writer.write( String.valueOf(countity)+" ");
                entropy -= countity*((float)Math.log(countity)/Math.log(2.0));
            }
            else writer.write("0.00000000 ");

        }
            if (bi.get(String.valueOf(monogramm1)+String.valueOf(' '))!=null) {
                float countity = (float) bi.get(String.valueOf(monogramm1)+String.valueOf(' '));
                writer.write( String.valueOf(countity)+" ");
                entropy -= countity*((float)Math.log(countity)/Math.log(2.0));
            }
            if (bi.get(String.valueOf(' ')+String.valueOf(monogramm1))!=null) {
                float countity = (float) bi.get(String.valueOf(' ')+String.valueOf(monogramm1));
                writer.write( String.valueOf(countity)+" ");
                entropy -= countity*((float)Math.log(countity)/Math.log(2.0));
            }
            else writer.write("0.00000000 ");
            writer.write("\n");
            writer.flush();
    }
        writer.write("\n"+ entropy);
        writer.flush();
        writer.close();

    }

    public static void frequencyOfbigramsAndEntropyWithNamespaceStep2(char[] ourText, int length) throws IOException {
        int lengthBi;
        if( length % 2 == 0) {
           lengthBi = length/2;
        } else {
            lengthBi = (length-1)/2;
        }

        Map bi = new HashMap<String, Float>();
        for (char monogramm1 = 'а'; monogramm1<='я'; monogramm1++){
            for (char monogramm2= 'а'; monogramm2<='я'; monogramm2++) {
                int i, j = 0;
                for (i = 0; i < length - 2; i++) {
                    if (ourText[i] == monogramm1 && ourText[i + 2] == monogramm2) j++;
                }
                if (j>0) bi.put(String.valueOf(monogramm1)+String.valueOf(monogramm2),(float)j/lengthBi);
                int p=0, k=0;
                for (int h = 0; h<length-2; h++){
                    if(ourText[h] == ' ' && ourText[h+2] == monogramm1) p++;
                    if (ourText[h] == monogramm1 && ourText[h+2] == ' ') k++;
                }
                if (p>0) bi.put(String.valueOf(' ')+String.valueOf(monogramm1),(float)p/lengthBi);
                if (k>0) bi.put(String.valueOf(monogramm1)+String.valueOf(' '),(float)k/lengthBi);
            }
        }
        //System.out.println(bi);
        FileWriter writer = new FileWriter("C:\\Users\\vadim\\IdeaProjects\\Laba1(git)\\2.txt", false);
        float entropy = 0;
        for (char monogramm1 = 'а'; monogramm1<='я'; monogramm1++)
            writer.write("   "+monogramm1 + "       ");
        writer.write("\n");
        for (char monogramm1 = 'а'; monogramm1<='я'; monogramm1++){
            writer.write(monogramm1+" ");
            for (char monogramm2= 'а'; monogramm2<='я'; monogramm2++) {
                if (bi.get(String.valueOf(monogramm1)+String.valueOf(monogramm2))!=null) {
                    float countity = (float) bi.get(String.valueOf(monogramm1)+String.valueOf(monogramm2));
                    writer.write( String.valueOf(countity)+" ");
                    entropy -= countity*((float)Math.log(countity)/Math.log(2.0));
                }
                else writer.write("0.00000000 ");
            }
            if (bi.get(String.valueOf(monogramm1)+String.valueOf(' '))!=null) {
                float countity = (float) bi.get(String.valueOf(monogramm1)+String.valueOf(' '));
                writer.write( String.valueOf(countity)+" ");
                entropy -= countity*((float)Math.log(countity)/Math.log(2.0));
            }
            if (bi.get(String.valueOf(' ')+String.valueOf(monogramm1))!=null) {
                float countity = (float) bi.get(String.valueOf(' ')+String.valueOf(monogramm1));
                writer.write( String.valueOf(countity)+" ");
                entropy -= countity*((float)Math.log(countity)/Math.log(2.0));
            }
            else writer.write("0.00000000 ");
            writer.write("\n");
            writer.flush();
        }
        writer.write("\n"+ "H2 = " + entropy);
        writer.flush();
        writer.close();
    }


    public static void frequencyOfbigramsAndEntropyWithoutNamespaceStep1(char[] ourText, int length) throws IOException {
        char[] tempText = new char[400000];
        int countNamespace = 0;
        for (int temp = 0; temp < length; temp++) {
            if (ourText[temp] == ' ') countNamespace++;
            else tempText[temp - countNamespace] = ourText[temp];
        }

        int tempLength = length - countNamespace;

        Map bi = new HashMap<String, Float>();
        for (char monogramm1 = 'а'; monogramm1<='я'; monogramm1++){
            for (char monogramm2= 'а'; monogramm2<='я'; monogramm2++) {
                int i, j = 0;
                for (i = 0; i < tempLength - 1; i++) {
                    if (tempText[i] == monogramm1 && tempText[i + 1] == monogramm2) j++;
                }
                if (j>0) bi.put(String.valueOf(monogramm1)+String.valueOf(monogramm2),(float)j/(tempLength-1));
            }
        }

        FileWriter writer = new FileWriter("C:\\Users\\vadim\\IdeaProjects\\Laba1(git)\\3.txt", false);
        float entropy = 0;
        for (char monogramm1 = 'а'; monogramm1<='я'; monogramm1++)
            writer.write("   "+monogramm1 + "       ");
        writer.write("\n");
        for (char monogramm1 = 'а'; monogramm1<='я'; monogramm1++){
            writer.write(monogramm1+" ");
            for (char monogramm2= 'а'; monogramm2<='я'; monogramm2++) {

                if (bi.get(String.valueOf(monogramm1)+String.valueOf(monogramm2))!=null) {
                    float countity = (float) bi.get(String.valueOf(monogramm1)+String.valueOf(monogramm2));
                    writer.write( String.valueOf(countity)+" ");
                    entropy -= countity*((float)Math.log(countity)/Math.log(2.0));
                }
                else writer.write("0.00000000 ");

            }
            writer.write("\n");
            writer.flush();
        }
        writer.write("\n"+ entropy);
        writer.flush();
        writer.close();
    }


    public static void frequencyOfbigramsAndEntropyWithoutNamespaceStep2(char[] ourText, int length) throws IOException {
        char[] tempText = new char[400000];
        int countNamespace = 0;
        for (int temp = 0; temp < length; temp++) {
            if (ourText[temp] == ' ') countNamespace++;
            else tempText[temp - countNamespace] = ourText[temp];
        }

        int tempLength = length - countNamespace;
        int lengthBi;
        if( tempLength % 2 == 0) {
            lengthBi = tempLength/2;
        } else {
            lengthBi = (tempLength-1)/2;
        }
        Map bi = new HashMap<String, Float>();
        for (char monogramm1 = 'а'; monogramm1<='я'; monogramm1++){
            for (char monogramm2= 'а'; monogramm2<='я'; monogramm2++) {
                int i, j = 0;
                for (i = 0; i < tempLength - 2; i++) {
                    if (tempText[i] == monogramm1 && tempText[i + 2] == monogramm2) j++;
                }
                if (j>0) bi.put(String.valueOf(monogramm1)+String.valueOf(monogramm2),(float)j/lengthBi);
            }
        }
        FileWriter writer = new FileWriter("C:\\Users\\vadim\\IdeaProjects\\Laba1(git)\\4.txt", false);
        float entropy = 0;
        for (char monogramm1 = 'а'; monogramm1<='я'; monogramm1++)
            writer.write("   "+monogramm1 + "       ");
        writer.write("\n");
        for (char monogramm1 = 'а'; monogramm1<='я'; monogramm1++){
            writer.write(monogramm1+" ");
            for (char monogramm2= 'а'; monogramm2<='я'; monogramm2++) {

                if (bi.get(String.valueOf(monogramm1)+String.valueOf(monogramm2))!=null) {
                    float countity = (float) bi.get(String.valueOf(monogramm1)+String.valueOf(monogramm2));
                    writer.write( String.valueOf(countity)+" ");
                    entropy -= countity*((float)Math.log(countity)/Math.log(2.0));
                }
                else writer.write("0.00000000 ");

            }
            writer.write("\n");
            writer.flush();
        }
        writer.write("\n"+ entropy);
        writer.flush();
        writer.close();
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