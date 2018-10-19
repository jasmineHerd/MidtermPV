
package business;

/**
 *
 * @author
 */
public class Annuity extends Financial{
    public static final String AMTDESC = "Monthly Deposit";
    public static final String RESULTDESC = "Final Value of Annuity";
    public static final String BEGBALDESC = "Beg.Annuity Value";
    public static final String PRINCFACTORDSC = "Deposit";
    public static final String INTFACTORDESC = "Int.Earned";
    public static final String ENDBALDESC = "End.Annuity Value";
    public static final String TITLE = "Annuity Detail";

    private double[] bbal, iearn, ebal;
    private boolean built;
    
    public Annuity(){
        super();
        this.built = false;
    }
    public Annuity(double a, double r, int t) {
        super(a,r,t);
        this.built = false;

        if (super.isValid()) {
            buildAnnuity();
        }
    }

    

    private void buildAnnuity() {
        try {
            double a = super.getAmt();
            double r = super.getRate();
            int t = super.getTerm();
            
            this.bbal = new double[t];
            this.iearn = new double[t];
            this.ebal = new double[t];

            this.bbal[0] = 0;
            for (int i=0; i < t; i++) {
               if (i > 0) {
                   this.bbal[i] = this.ebal[i-1];
               }
               this.iearn[i] = (this.bbal[i] + a) * (r / 12.0);
               this.ebal[i] = this.bbal[i] + a + this.iearn[i];
            }
            this.built = true;
        } catch (Exception e) {
            super.setEmsg("Annuity build error."+ e.getMessage());
            this.built = false;
        }
    }

    @Override
    public double getBegBal(int mo) {
        if (!this.built) {
            buildAnnuity();
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
            buildAnnuity();
            if (!this.built) {
                return -1;
            }
        }
        if (mo < 1 || mo > super.getTerm()) {
            return -1;
        }
        return this.iearn[mo-1];
    }

    @Override
    public double getEndBal(int mo){
        if (!this.built) {
            buildAnnuity();
            if (!this.built) {
                return -1;
            }
        }
        if (mo < 1 || mo > super.getTerm()) {
            return -1;
        }
        return this.ebal[mo-1];
    }


//question to self?
    //what is the purpose of the abstract method ?
    //Why not just write the method here and be done with it?
    
    @Override
    public double getResult() {
        if (!this.built) {
            buildAnnuity();
            if (!this.built) {
                return -1;
            }
        }
        return this.ebal[super.getTerm()-1];
    }
    @Override
    public String getTitle(){
        return Annuity.TITLE;
    }
    
    @Override
    public double getPrinFactor(int mo){
        return super.getAmt();//return depoit amount as principle factor
    }


}
