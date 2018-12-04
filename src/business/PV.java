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
public class PV extends Financial {
    public static final String AMTDESC = "Lump Sum Amount";
    public static final String RESULTDESC = "Present Value";
    public static final String TITLE = "Present Value";
    public static final String BEGBALDESC = "Present Value";
    public static final String PRINCFACTORDESC = "Value Changed";
    public static final String INTFACTORDESC = "Discount";
    public static final String ENDBALDESC = "Present Value";
   // public static final String PRINPAIDDESC = "Prin.Paid";
    
    
    

    private boolean built;
    private double[] bbal, ifactor;
    
    public PV(){
        super();
    }
    public PV(double a, double r, int t) {
       super(a, r, t);
        this.built = false;

        if (super.isValid()) {
            buildPV();
        }
    }



    private void buildPV () {
       // double denom, morate;
        

        try {


            this.bbal = new double[super.getTerm()+1];
            this.ifactor = new double[super.getTerm()+1];
            double morate = super.getRate()/12.0;

            

            for (int i=0; i <=super.getTerm(); i++){
                 this.bbal[i] = super.getAmt()/
                         Math.pow((1+morate),(super.getTerm()-i));
                 this.ifactor[i]  = super.getAmt() - this.bbal[i];
               
            }

            this.built = true;
        } catch (Exception e) {
            super.setEmsg("PV build error: " + e.getMessage());
            this.built = false;
        }
    }

    @Override
    public double getBegBal(int mo) {
        if (!this.built) {
            buildPV();
            if (!this.built) {
                return -1;
            }
        }
        if (mo < 0 || mo > super.getTerm()) {
            return -1;
        }
        return this.bbal[mo];
    }

    @Override
    public double getIntFactor(int mo) {
        if (!this.built) {
            buildPV();
            if (!this.built) {
                return -1;
            }
        }
        if (mo < 0 || mo > super.getTerm()) {
            return -1;
        }
        return this.ifactor[mo];
    }

    @Override
    public double getEndBal(int mo) {
        if (!this.built) {
            buildPV();
            if (!this.built) {
                return -1;
            }
        }
        if (mo < 1 || mo > super.getTerm()) {
            return -1;
        }
        return this.bbal[mo-1];
    }


    @Override
    public double getResult() {
        if (!this.built) {
            buildPV();
            if (!this.built) {
                return -1;
            }
        }
        return this.bbal[0];
    }
    
    @Override
    public double getPrinFactor(int mo){
            if (!this.built) {
            buildPV();
            if (!this.built) {
                return -1;
            }
        }
        if (mo < 0 || mo > super.getTerm()+1) {
            return -1;
        }
        return this.bbal[mo+1]-this.bbal[mo];
    }
    
    @Override
    public String getTitle(){
        return PV.TITLE;
    }
    /*
    public double getPrinPaid(int mo){
         if (!this.built) {
            buildPV();
            if (!this.built) {
                return -1;
            }
        }
        if (mo < 1 || mo > super.getTerm()) {
            return -1;
        }
        return this.ebal[mo-1]-this.bbal[mo-1];
    }*/
}
