
package business;

/**
 *
 * @author
 */
public class Loan extends Financial{
    public static final String AMTDESC = "Loan Amount";
    public static final String RESULTDESC = "Monthly Payment";
    public static final String TITLE = "Loan Schedule";
    public static final String BEGBALDESC = "Beg.Loan Value";
    public static final String PRINCFACTORDESC = "Payment";
    public static final String INTFACTORDESC = "Int.Charge";
    public static final String ENDBALDESC = "End.Loan Bal";
    public static final String PRINPAIDDESC = "Prin.Paid";
    

    private double  mopmt;
    private boolean built;
    private double[] bbal, ichg, ebal;
    
    public Loan(){
        super();
        this.built = false;
    }
    public Loan(double a, double r, int t) {
       super(a, r, t);
        this.mopmt = 0;
        this.built = false;

        if (super.isValid()) {
            buildLoan();
        }
    }



    private void buildLoan () {
        double denom, morate;
        

        try {
             double a = super.getAmt();
            double r = super.getRate();
            int t = super.getTerm();
            
            morate = r / 12.0;
            denom = Math.pow(1+morate, t) -1;
            this.mopmt = (morate + morate / denom) *a;

            this.bbal = new double[t];
            this.ichg = new double[t];
            this.ebal = new double[t];

            this.bbal[0] = a;

            for (int i=0; i < t; i++) {
                if (i > 0) {
                    this.bbal[i] = this.ebal[i-1];
                }
                this.ichg[i] = this.bbal[i] * morate;
                this.ebal[i] = this.bbal[i] + this.ichg[i] - this.mopmt;
            }

            this.built = true;
        } catch (Exception e) {
            super.setEmsg("Build fail on Loan: " + e.getMessage());
            this.built = false;
        }
    }

    @Override
    public double getBegBal(int mo) {
        if (!this.built) {
            buildLoan();
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
    public double getIntFactor(int mo) {
        if (!this.built) {
            buildLoan();
            if (!this.built) {
                return -1;
            }
        }
        if (mo < 1 || mo > super.getTerm()) {
            return -1;
        }
        return this.ichg[mo-1];
    }

    @Override
    public double getEndBal(int mo) {
        if (!this.built) {
            buildLoan();
            if (!this.built) {
                return -1;
            }
        }
        if (mo < 1 || mo > super.getTerm()) {
            return -1;
        }
        return this.ebal[mo-1];
    }


    @Override
    public double getResult() {
        if (!this.built) {
            buildLoan();
            if (!this.built) {
                return -1;
            }
        }
        return this.mopmt;
    }
    
    @Override
    public double getPrinFactor(int mo){
        return getResult();//extra but set up for next assignment
    }
    
    @Override
    public String getTitle(){
        return Loan.TITLE;
    }
    
    public double getPrinPaid(int mo){
         if (!this.built) {
            buildLoan();
            if (!this.built) {
                return -1;
            }
        }
        if (mo < 1 || mo > super.getTerm()) {
            return -1;
        }
        return this.ebal[mo-1]-this.bbal[mo-1];
    }

  
}

