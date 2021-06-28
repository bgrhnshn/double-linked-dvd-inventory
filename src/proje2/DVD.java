package proje2;

import java.util.ArrayList;

public class DVD {
    private String name,header;
    private int releaseDate,price;
    public ArrayList<String> songs;
    //constructor
public DVD(){
     name="none";
     header="none";
     releaseDate=-1;
     price=-1;
     songs.add("");}
 
 public DVD(String name,String h,int rd,int price,ArrayList<String> a){
     this.name=name;
     header=h;
     releaseDate=rd;
     this.price=price;
     songs=a;}
 //getter & setter
public String getName() {
        return name;}
public void setName(String name) {
        this.name = name;}
public String getHeader() {
        return header;}
public void setHeader(String header) {
        this.header = header;}
public int getReleaseDate() {
        return releaseDate;}
public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;}
public int getPrice() {
        return price;}
public void setPrice(int price) {
        this.price = price;}
 public final String getSongs(){
     //şarkıları listeler
        if(songs.size()>0){
            String temp =songs.get(0)+"\n";
            for (int i=1;i<songs.size();i++){
              temp=temp+songs.get(i)+"\n";
            }
            return temp;
        }
        return "Şarkı yok";}
    public final String printSongs(){
        if(songs.size()>0){
     String temp =songs.get(0);
        for (int i=1;i<songs.size();i++){
          temp=temp+", "+songs.get(i);
        }
        temp=temp+".";  
        return temp;   
    }
return "";}}
