package com.vadymandartem;

 import java.io.*;
 import java.nio.charset.StandardCharsets;
 import java.util.HashMap;
 import java.util.Map;


public class Main {

    public static void main(String[] args) throws UnsupportedEncodingException {
        char[] ourText = new char[4000000];
        // Read in mass
        try(BufferedReader buf = new BufferedReader (new InputStreamReader(new FileInputStream("C:\\Users\\Tempuser\\IdeaProjects\\Laba1\\src\\com\\vadymandartem\\11.txt"), "UTF-8"))){
            int c;
            int i=0;
            while ( (c = buf.read()) != -1){
                char symbol = ((char)c);
                if (Character.isUpperCase(symbol)) symbol=Character.toLowerCase(symbol);
                if (symbol >= 'а' && symbol<= 'я'){
                    ourText[i] = symbol;
                    i++;
                }
                if ((symbol==' ' || symbol=='\n' || symbol=='\t') && i>0 && ourText[i-1]!=' ' ){
                    ourText[i] = ' ';
                    i++;
                }
                if ((symbol==' ' || symbol=='\n' || symbol=='\t') && i==0){
                    ourText[i] = ' ';
                    i++;
                }
            }
            int length = i;
            buf.close();

            // Read monogramm
            Map mono = new HashMap<String,Integer>();
            for (char monogramm = 'а'; monogramm<='я'; monogramm++){
                int j=0;
                for (i=0;i<length;i++){
                    if (ourText[i]==monogramm) j++;
                }
                if (j>0) mono.put(monogramm,j);
            }
            int j=0;
            for (i=0;i<length;i++){
                if (ourText[i]==' ') j++;
            }
            if (j>0) mono.put(' ',j);
            System.out.println(mono);

            // Read entro monogramm
            float entro = 0;
            for (char monogramm = 'а'; monogramm<='я'; monogramm++){
                if (mono.get(monogramm)!=null) {
                    int countity = (int) mono.get(monogramm);
                    float prob = (float)countity/(float)length;
                    entro -= prob*((float)Math.log(prob)/Math.log(2.0));
                }
            }
            int countity = (int) mono.get(' ');
            float prob = (float)countity/(float)length;
            entro -= prob*((float)Math.log(prob)/Math.log(2.0));
            System.out.println("entro=" + entro);

            //Read bigramm
            Map bi = new HashMap<String, Integer>();
            for (char monogramm1 = 'а'; monogramm1<='я'; monogramm1++){
                for (char monogramm2= 'а'; monogramm2<='я'; monogramm2++) {
                    j = 0;
                    for (i = 0; i < length - 1; i++) {
                        if (ourText[i] == monogramm1 && ourText[i + 1] == monogramm2) j++;
                    }
                    if (j>0) bi.put(String.valueOf(monogramm1)+String.valueOf(monogramm2),j);
                }
            }
            System.out.println(bi);

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
