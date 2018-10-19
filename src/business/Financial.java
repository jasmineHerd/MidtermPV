/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author jasmineherd
 */
abstract public class Financial {

    private double amt, rate;
    private int term;
    private String emsg;

    public Financial() {
        this.amt = 0;
        this.rate = 0;
        this.term = 0;
        this.emsg = "";

    }

    public Financial(double a, double r, int t) {
        this.amt = a;
        this.rate = r;
        this.term = t;
        this.emsg = "";
    }
    protected boolean isValid() {
        this.emsg = "";
        boolean valid = true;
        if (this.getAmt() <= 0) {
            valid = false;
            this.emsg += "Ammount must be positive. ";
        }
        if (this.getRate() <= 0) {
            valid = false;
            this.emsg += "Rate must be positive. ";
        }
        if (this.getTerm() <= 0) {
            valid = false;
            this.emsg += "Term must be positive. ";
        }
        return valid;
    }
   
    public double getAmt() {
        return amt;
    }

    public void setAmt(double amt) {
        this.amt = amt;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public String getEmsg() {
        return emsg;
    }
    
    protected void setEmsg(String emsg){
        this.emsg = emsg;
    }
    
    
    
     abstract public double getResult();
     abstract public double getBegBal(int mo);
     abstract public double getPrinFactor(int mo);
     abstract public double getIntFactor(int mo);
     abstract public double getEndBal(int mo);
     abstract public String getTitle();

    
    
    
    
}
