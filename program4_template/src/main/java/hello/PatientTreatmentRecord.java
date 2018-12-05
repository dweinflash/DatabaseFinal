package hello;

/*+----------------------------------------------------------------------
||
||  Class PatientTreatmentRecord
||
||
||        Purpose:  Resposible for the creation of a Model object.
||                Contains the correct fields that correspond to
||                the form fields by using getter and setter methods.
||                Will be used to capture information in the forms
||                and placed into the proper JDBC sql statements.
||                The variable types will have to work with the
||                database tables by having the same type.
||
++-----------------------------------------------------------------------*/


public class PatientTreatmentRecord {

    private int PID;
    private int DID;
    private int apptNo;
    private String visitReason;
    private String visitDate;
    private String dateHospitalized;
    private String expectedDischarge;
    private String actualDischarge;
    private int roomNo;
    private String treatmentMethod;

    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public int getDID() {
        return DID;
    }

    public void setDID(int DID) {
        this.DID = DID;
    }

    public int getApptNo() {
        return apptNo;
    }

    public void setApptNo(int apptNo) {
        this.apptNo = apptNo;
    }

    public String getVisitReason() {
        return visitReason;
    }

    public void setVisitReason(String visitReason) {
        this.visitReason = visitReason;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getDateHospitalized() {
        return dateHospitalized;
    }

    public void setDateHospitalized(String dateHospitalized) {
        this.dateHospitalized = dateHospitalized;
    }

    public String getExpectedDischarge() {
        return expectedDischarge;
    }

    public void setExpectedDischarge(String expectedDischarge) {
        this.expectedDischarge = expectedDischarge;
    }

    public String getActualDischarge() {
        return actualDischarge;
    }

    public void setActualDischarge(String actualDischarge) {
        this.actualDischarge = actualDischarge;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public String getTreatmentMethod() {
        return treatmentMethod;
    }

    public void setTreatmentMethod(String treatmentMethod) {
        this.treatmentMethod = treatmentMethod;
    }
}
