package hello;

/*+----------------------------------------------------------------------
||
||  Class Doctor
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

public class Doctor {

    private int DID;
    private String fName;
    private String lName;
    private String DOB;
    private int deptID;
    private int officeNo;
    private String status;

    public int getDID() {
        return DID;
    }

    public void setDID(int DID) {
        this.DID = DID;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public int getDeptID() {
        return deptID;
    }

    public void setDeptID(int deptID) {
        this.deptID = deptID;
    }

    public int getOfficeNo() {
        return officeNo;
    }

    public void setOfficeNo(int officeNo) {
        this.officeNo = officeNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
