package main.java;

public class User
{
    //Instance Variables
    private String uName;
    private String pWord;
    //true if admin
    private boolean priv;
    
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

    //Privilege
    public boolean getPriv() {
        return this.priv;
    }

    public void setPriv(boolean priv) {
        this.priv = priv;
    }
}
