package hello;

public class CashierRecord {

    private String paymentID;
    private String cashierEID;
    private String PID;
    private String amountDue;
    private String dueDate;
    private String paymentStatus;
    private String paymentDate;

    //Getters

    public String getpaymentID(){
        return paymentID;
    }

    public String getcashierEID(){
        return cashierEID;
    }

    public String getPID(){
        return PID;
    }

    public String getamountDue(){
        return amountDue;
    }

    public String getdueDate(){
        return dueDate;
    }

    public String getpaymentStatus(){
        return paymentStatus;
    }

    public String getpaymentDate(){
        return paymentDate;
    }

    //Setters

    public void setpaymentID(String paymentID){
        this.paymentID = paymentID;
    }

    public void setfName(String cashierEID){
        this.cashierEID = cashierEID;
    }

    public void setPID(String PID){
        this.PID = PID;
    }

    public void setamountDue(String amountDue){
        this.amountDue = amountDue;
    }

    public void setdueDate(String dueDate){
        this.dueDate = dueDate;
    }

    public void setpaymentStatus(String paymentStatus){
        this.paymentStatus = paymentStatus;
    }

    public void setpaymentDate(String paymentDate){
        this.paymentDate = paymentDate;
    }

}
