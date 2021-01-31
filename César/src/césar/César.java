/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package césar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class César {

  static  char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
  static String fileContent;
  static String txt_crypté=" ";
  static String txt_decrypté=" ";
  static int K ;
    
    public static void main(String[] args) {
        
         //Appel au fonctions
       création_ficher();
        //Lire la valeur du clé de cryptage
        Scanner sc = new Scanner(System.in);  
       System.out.println("***********************************************");
       System.out.println("Cryptage du contenu de fichier créer");
       System.out.println("Donner la valeur de clé de cryptage");
       K = sc.nextInt();
       Cryptage();
       création_ficherA();           
       System.out.println("***********************************************");
       System.out.println("Décryptage du contenu de fichier créer");
       décryptage();  
       création_ficherB();  

       

    }
    
    //Fonctions:
    //1
//Fonction pour la création et la lecture du fichier principale
    public static String création_ficher(){ 
        Scanner sc = new Scanner(System.in);
       // String directory = System.getProperty("D:\\mémoire\\"); ne fonctionne pas 
        System.out.println("Proposé un nom pour ton fichier");//pr qu'il etre dynamique
        String fn = sc.nextLine();
        String fileName = fn+".txt";   
        System.out.println("Le nom du fichier est: "+fileName); //pr qu'il etre dynamique
        String absolutePath = "C:\\Users\\USER\\" + fileName;

// Write the content in file 
   System.out.println("remplir ton fichier: "+fileName);
        String fc = sc.nextLine();
   try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(absolutePath))) {
    fileContent = fc;
    bufferedWriter.write(fileContent);
    System.out.println("Le fichier: "+fileName+" est créer");//pour suivre le déroulement du prgrm
} catch (IOException e) {
    // Exception handling
}

// Read the content from file
try(BufferedReader bufferedReader = new BufferedReader(new FileReader(absolutePath))) {
    String line = bufferedReader.readLine();
    while(line != null) {
        System.out.println("lecture de contenu du fichier: "+line);//pour suivre le déroulement du prgrm
        line = bufferedReader.readLine();
    }
} catch (FileNotFoundException e) {
    // Exception handling
} catch (IOException e) {
    // Exception handling
}
        return null;}
    
    
    //2
    //Fonction pour le cryptage du contenu de fichier
    public static String Cryptage(){
        int ch;
        char  c = 0;
        int e;
        String L; 
       L = fileContent.toLowerCase(); //transformé le contenu en miniscule pour qu'on peut le comparer car les champs  
       System.out.println("transformé le contenu du fichier en miniscule: "+ L); //du tableau écritent en miniscule
      for(int i=0; i< L.length();i++){
          e = 0;
         for(int j=0; j<alphabet.length; j++){ 
            if(L.charAt(i)== alphabet[j]){ //Si la caractère de text = une alphabet 
                ch =(j+K) % 26; //k la clé et j la position de l'alphabet dans le tableau
                c = alphabet[ch]; //c prend la nouvelle position calculer
                e = 1;//confirme que la caractère traiter n'est pas un espace
            }
          }
         if(e == 0){
         c = ' ';}
       txt_crypté = txt_crypté+ c; //remplir txt_crypté caractère par caractère
       c= ' ';  //initialisé le c 
      }
      System.out.println("le text crypté est:"+txt_crypté); //pour suivre le déroulement du prgrm
      return txt_crypté;}
    
    //3
    //Fonction pour décrypté le contenu 
    public static String décryptage(){
        int ch = 0;
        char  c = 0;
        int e;
        int value;
        String L; 
       L = txt_crypté.toLowerCase(); //travallant avec le text crypter
      for(int i=0; i< L.length();i++){//meme étapes sauf la méthode de décryptage
          e = 0;
         for(int j=0; j<alphabet.length; j++){
            if(L.charAt(i)== alphabet[j]){
                value =j-K;
                if (value < 0 ){ 
                ch = value + 26;
                } else { ch = value;}
                c = alphabet[ch];
                e = 1;
            }
          }
         if(e == 0){
         c = ' ';}
       txt_decrypté = txt_decrypté+ c;
       c= ' ';  
      }
      System.out.println("le text décrypté est: "+txt_decrypté);//pour suivre le déroulement du prgrm
      return txt_decrypté;}
    
    
    //4
//Fonction pour la création et la lecture du fichier A (qui contient le text crypté)
    public static String création_ficherA(){
        Scanner sc = new Scanner(System.in);
        //String directory = System.getProperty("C:\\Users\\USER\\Desktop\\"); ne fonctionne pas
        String fileName = "A.txt";   
        String absolutePath = "C:\\Users\\USER\\" + fileName;

// Write the content in file 
   try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(absolutePath))) {
    fileContent = txt_crypté;
    bufferedWriter.write(fileContent);
    System.out.println("Le fichier: "+fileName+" est créer");//pour suivre le déroulement du prgrm
} catch (IOException e) {
    // Exception handling
}
// Read the content from file
try(BufferedReader bufferedReader = new BufferedReader(new FileReader(absolutePath))) {
    String line = bufferedReader.readLine();
    while(line != null) {
        System.out.println("lecture de contenu du fichier: "+line);//pour suivre le déroulement du prgrm
        line = bufferedReader.readLine();
    }
} catch (FileNotFoundException e) {
    // Exception handling
} catch (IOException e) {
    // Exception handling
}
        return null;}
    
    
    //5
//Fonction pour la création et la lecture du fichier B (qui contient le text décrypté)
    public static String création_ficherB(){
        Scanner sc = new Scanner(System.in);
        //String directory = System.getProperty("C:\\Users\\USER\\Desktop\\"); ne fonctionne pas
        String fileName = "B.txt";   
        String absolutePath = "C:\\Users\\USER\\" + fileName;

// Write the content in file 
   try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(absolutePath))) {
    fileContent = txt_decrypté;
    bufferedWriter.write(fileContent);
    System.out.println("Le fichier: "+fileName+" est créer");
} catch (IOException e) {
    // Exception handling
}
// Read the content from file
try(BufferedReader bufferedReader = new BufferedReader(new FileReader(absolutePath))) {
    String line = bufferedReader.readLine();
    while(line != null) {
        System.out.println("lecture de contenu du fichier: "+line);
        line = bufferedReader.readLine();
    }
} catch (FileNotFoundException e) {
    // Exception handling
} catch (IOException e) {
    // Exception handling
}
        return null;}
        

    
}
