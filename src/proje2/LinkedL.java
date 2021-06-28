package proje2;

public class LinkedL  {
    Node1 head;
    Node1 tail;
public class Node1 {
    Node1 next;
    Node1 previous;
    DVD dvd;
    //constructor
    Node1(){
    next=null;
    previous=null;
    dvd= new DVD();}
    Node1(DVD dvd, Node1 p, Node1 n){
    this.dvd=dvd;
    next=n;
    previous=p;}
    public String toString(){
        return dvd.getName()+", "+dvd.getHeader()+", "+dvd.getReleaseDate()+", "+
                dvd.getPrice()+", "+dvd.printSongs();
                }}
    public LinkedL(){
        head=null;
        tail=null;}
    //liste başına ekleme
    public void addToStart(DVD dvd){
        Node1 newHead = new Node1(dvd,null,head);
        if(head!=null){
            head.previous=newHead;
            newHead.next=head;
            head=newHead;
        } else {
            head = newHead;
            tail=newHead;}}
    //baştaki node'u siler
    public void deleteHeadNode(){      
    if(head!=null){
        head = head.next;
    head.previous=null;}}
    //listenin sonuna ekler
    public void addToLast(DVD dvd){
        Node1 newTail = new Node1(dvd,tail,null);
        if(tail!=null){
            tail.next=newTail;
            newTail.previous=tail;
            tail=newTail;
        } else {
            head=newTail;
            tail=newTail;}}
    //sondaki node'u siler
    public void deleteTailNode(){
        if(tail!=null){
            tail=tail.previous;
            tail.next=null;}}
    //sanatçı adı verilen dvd'i siler
    public boolean deleteByName(String name ,String header){
        Node1 i1=head;
        Node1 i2=head;
        if(!(head==null)){
            if(head==tail){
                if(name.equals(head.dvd.getName()) && header.equals(head.dvd.getHeader())) {deleteHeadNode(); return true;}
            }else
                if(name.equals(head.dvd.getName()) && header.equals(head.dvd.getHeader())){
                    deleteHeadNode();
                    return true;
                }else
                if(name.equals(tail.dvd.getName()) && header.equals(tail.dvd.getHeader())){
                    deleteTailNode();
                    return true;
                }else{
                    while(!(name.equals(i2.dvd.getName()) && header.equals(i2.dvd.getHeader()) || i2.next==null) ){
                        i1=i2;
                        i2=i2.next;
                    }if(header.equals(i2.dvd.getHeader()) && name.equals(i2.dvd.getName())){
                    i1.next=i2.next;
                    i2.next.previous=i1;
                    return true;
                    }}}
        return false;}
    //alfabetik sıraya göre listeye dvd ekler
    public void insertInOrder(DVD d){
        if(head==null) head=tail=new Node1(d,null,null);
        else if(head.dvd.getName().compareTo(d.getName())>0){
            addToStart(d);
        }else if(tail.dvd.getName().compareTo(d.getName())<0){
            addToLast(d);
        }else{
            Node1 current = head;
            while(current.dvd.getName().compareTo(d.getName())<0){
                current=current.next;
            }
            if(current.dvd.getName().compareTo(d.getName())==0){
                while(current.dvd.getReleaseDate()<d.getReleaseDate()){
                    current=current.next;
                }}
            if(current==head){
                addToStart(d);
            }else{
            Node1 p=current.previous;
            Node1 a=new Node1(d,p,current);
            p.next=a;
            current.previous=a;
        }}}}