package hello;

/*+----------------------------------------------------------------------
||
||  Class Staff
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

public class Staff {

    private int EID;
    private String fName;
    private String lName;
    private String DOB;
    private int deptID;
    private int salary;
    private String contactNo;
    private String title;
    private String gender;
    private int officeNo;

    public int getEID() {
        return EID;
    }

    public void setEID(int EID) {
        this.EID = EID;
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

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getOfficeNo() {
        return officeNo;
    }

    public void setOfficeNo(int officeNo) {
        this.officeNo = officeNo;
    }
}
