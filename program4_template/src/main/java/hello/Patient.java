package hello;

/*+----------------------------------------------------------------------
||
||  Class Patient
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

public class Patient {

    private int PID;
    private String fName;
    private String lName;
    private String gender;
    private String DOB;
    private String address;
    private String contactNo;

    //Getters

    public int getPID(){
        return PID;
    }

    public String getfName(){
        return fName;
    }

    public String getlName(){
        return lName;
    }

    public String getgender(){
        return gender;
    }

    public String getDOB(){
        return DOB;
    }

    public String getaddress(){
        return address;
    }

    public String getcontactNo(){
        return contactNo;
    }

    //Setters

    public void setPID(int PID){
        this.PID = PID;
    }

    public void setfName(String fName){
        this.fName = fName;
    }

    public void setlName(String lName){
        this.lName = lName;
    }

    public void setgender(String gender){
        this.gender = gender;
    }

    public void setDOB(String DOB){
        this.DOB = DOB;
    }

    public void setaddress(String address){
        this.address = address;
    }

    public void setcontactNo(String contactNo){
        this.contactNo = contactNo;
    }



}
