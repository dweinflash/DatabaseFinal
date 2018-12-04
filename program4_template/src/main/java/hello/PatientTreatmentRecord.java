package hello;

public class PatientTreatmentRecord {

    private String PID;
    private String DID;
    private String apptNo;
    private String visitReason;
    private String visitDate;
    private String dateHospitalized;
    private String expectedDischarge;
    private String actualDischarge;
    private String roomNo;
    private String treatmentMethod;

    //Getters

    public String getPID(){
        return PID;
    }

    public String getDID(){
        return DID;
    }

    public String getapptNo(){
        return apptNo;
    }

    public String getvisitReason(){
        return visitReason;
    }

    public String getvisitDate(){
        return visitDate;
    }

    public String getdateHospitalized(){
        return dateHospitalized;
    }

    private String expectedDischarge(){
        return expectedDischarge;
    }

    private String actualDischarge(){
        return actualDischarge;
    }

    private String roomNo(){
        return roomNo;
    }

    private String treatmentMethod(){
        return treatmentMethod;
    }

    //Setters

    public void setPID(String PID){
        this.PID = PID;
    }

    public void setDID(String DID){
        this.DID = DID;
    }

    public void setapptNo(String apptNo){
        this.apptNo = apptNo;
    }

    public void setvisitReason(String visitReason){
        this.visitReason = visitReason;
    }

    public void setvisitDate(String visitDate){
        this.visitDate = visitDate;
    }

    public void setdateHosiptalized(String dateHospitalized){
        this.dateHospitalized = dateHospitalized;
    }

    public void setexpectedDischarge(String expectedDischarge){
        this.expectedDischarge = expectedDischarge;
    }

    public void setactualDischarge(String actualDischarge){
        this.actualDischarge = actualDischarge;
    }

    public void setroomNo(String roomNo){
        this.roomNo = roomNo;
    }

    public void settreatmentMethod(String treatmentMethod){
        this.treatmentMethod = treatmentMethod;
    }


}
