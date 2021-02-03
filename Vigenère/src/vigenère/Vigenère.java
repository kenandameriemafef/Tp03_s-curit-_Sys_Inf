
package vigenère;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Vigenère {

   
    
    
    
static  char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
static char[][] alphabetMatrice = new char [26][26];
static String K,text,Clé;
static String text_crypté="";
static String text_décrypté = "";
static int lt,lk;
static String fileContent;


    public static void main(String[] args) {
        
       création_ficher(); 
       remplissage_Matrice(); 
       Scanner sc = new Scanner(System.in);         
       System.out.println("Donner la valeur de clé de cryptage");
       Clé = sc.nextLine();
       Clé = Clé.toLowerCase();   
       text = text.toLowerCase();
       nv_clé();
       //Appel fonction décryptage:
       cryptage();
       création_ficherA();
       //Appel fonction décryptage:
       décryptage();
       création_ficherB();
       
    }
    
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
         text = sc.nextLine();
   try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(absolutePath))) {
    fileContent = text;
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
    
  //*****  1 ************
 //Fonction remplissage de la matrice 
 public static String remplissage_Matrice(){ 
     int l,remplit;
     int li=0;
     
    //premier remplissage jsute pour le premier tour d'alphabet 
    for(int c=0; c<26; c++){  
        l=0;
       for(int i=li; i<26; i++){
         if( l<26 ){
           alphabetMatrice[c][l]=alphabet[i];
           l++;
            }
     }
       li++;
    }
    
 //remplissage des champs vide le tour pour remplir tt les champs
    for(int c=0; c<26; c++){
        remplit=0;
        for( l=0; l<26; l++){
            if(alphabetMatrice[c][l] != 0){
                remplit++;
            }
            if(alphabetMatrice[c][l] == 0){
                int m = 26-remplit;
                for(int i=0; i<m;i++){
                    alphabetMatrice[c][l]=alphabet[i];
                    l++;}            
    }}  }
 //affichage du matrice complete
    for( l=0; l<26; l++){
        for(int c=0; c<26; c++){
        System.out.print(alphabetMatrice[c][l]+ "\t");}
        System.out.println();  
    }
     return null;}
 
 //********** 2 *************
 //fonction pour aue la taille du text et du clé etre égaux (répitition du clé selon la taille du text)
 public static String nv_clé(){
      int diff; // le différence 
      int j=0;
       K="";
      diff=text.length()-Clé.length();
      if(diff != 0){ // si existe une différence entre eux donc: 
      for(int i=0;i<text.length();i++){ //selon la taille du text
          if(text.charAt(i)!=' '){  //si le champ n'est un espace  
          if(j<Clé.length()){ //compter la taille du clé pour la répitition
          K=K+Clé.charAt(j);
          j++;
          if(j==Clé.length()){ //quand le compteur égale la taille du clé il faut le réinitialisé à 0
              j=0;
          }}
          }else{K=K+' ';} //lorque le champs est un espace la clé reçoit un espace 
      }}
      System.out.println("la nouvelle clé est: "+K);
 return null;}

    
 //fonction de cryptage 
 public static String cryptage(){
     int e;
     for(int i=0;i<text.length();i++){
         e=0;
       for(int j=0; j<alphabet.length;j++){
         if(text.charAt(i)== alphabet[j]){ //chercher l'index des alphabets du text
           lt=j; e++;}} //effectuer à lt
       for(int j=0; j<alphabet.length;j++){ 
         if(K.charAt(i)== alphabet[j]){ //chercher l'index des alphabets du clé
           lk=j; e++;}} //effectuer à lk
       if(e==2){ //Si le champ n'est pas un espace donc: ajouter l'intersection du deux index au text crypté, si non il faut l'ajouter un espace
       text_crypté = text_crypté+alphabetMatrice[lk][lt];}else{text_crypté = text_crypté+' ';} 
     }
     System.out.println("le message crypté est: "+text_crypté);
    return text_crypté;}
 
 //fonction de décryptage
 public static String décryptage(){
     int I=0;
     int c=0;
     for(int i=0; i<K.length();i++){
       if(K.charAt(i) != ' '){ //tjrs vérirification si le champ n'est pas un espace
       for(int l=0; l<26;l++){
          if(K.charAt(i)== alphabetMatrice[0][l]){ //chercher la ligne de la n eme lettre du clé dans la colone 0 
             // System.out.println(K.charAt(i)+" = "+ alphabetMatrice[0][l]);
          for( c=0; c<26;c++){ //aprés la trouver chercher dans la ligne détecter la colone de la lettre du text_crypté 
              if(text_crypté.charAt(i)== alphabetMatrice[l][c]){ 
                //  System.out.println(text_crypté.charAt(i)+" = "+ alphabetMatrice[c][l]);
                I=c; //System.out.println("l'index est "+I); //l'index du colone stocké dans I
                 }}
          text_décrypté = text_décrypté + alphabetMatrice[0][I]; //Ajouter au text décrypter la lettre du premiere ligne (0) de l'intersection entre la Clé et le text crypter
          }
       }}else {text_décrypté = text_décrypté +' ';} //Si un espace il fait qu'il reçoit un espace 
     }
     System.out.println("le message décrypté est: "+text_décrypté);
     
    return text_décrypté;}
 
 //**************************
 //Fonctions pour la cration des fichiers=> A.txt reçoit le text crypté et B.txt reçoit le text décrypté
 //Fonction pour la création et la lecture du fichier A (qui contient le text crypté)
    public static String création_ficherA(){
        Scanner sc = new Scanner(System.in);
        //String directory = System.getProperty("C:\\Users\\USER\\Desktop\\"); ne fonctionne pas
        String fileName = "A.txt";   
        String absolutePath = "C:\\Users\\USER\\" + fileName;

// Write the content in file 
   try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(absolutePath))) {
    fileContent = text_crypté;
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
    

 //Fonction pour la création et la lecture du fichier B (qui contient le text décrypté)
    public static String création_ficherB(){
        Scanner sc = new Scanner(System.in);
        //String directory = System.getProperty("C:\\Users\\USER\\Desktop\\"); ne fonctionne pas
        String fileName = "B.txt";   
        String absolutePath = "C:\\Users\\USER\\" + fileName;
// Write the content in file 
   try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(absolutePath))) {
    fileContent = text_décrypté;
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
}return null;}
        
    
}
