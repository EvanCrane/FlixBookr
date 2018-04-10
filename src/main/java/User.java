package main.java;

public class User
{
    //Instance Variables
    private String uName;
    private String pWord;
    
    //Constructor
    public User(String uName, String pWord) {
        this.uName = uName;
        this.pWord = pWord;
    }
    
    //Properties
    ///uName
    public String getuName() {
        return this.uName;
    }
    
    public void setuName(String uName) {
        this.uName = uName;
    }
    
    ///pWord
    public String getpWord() {
        return this.pWord;
    }
    
    public void setpWord(String pWord) {
        this.pWord = pWord;
    }
}
