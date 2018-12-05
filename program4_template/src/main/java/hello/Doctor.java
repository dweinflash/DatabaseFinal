package hello;

public class Doctor {

    private String DID;
    private String fName;
    private String lName;
    private String DOB;
    private String deptID;
    private String officeNo;
    private String status;

    //Getters

    public String getDID(){
        return DID;
    }

    public String getfName(){
        return fName;
    }

    public String getlName(){
        return lName;
    }

    public String getDOB(){
        return DOB;
    }

    public String getdeptID(){
        return deptID;
    }

    public String getofficeNo(){
        return officeNo;
    }

    public String getstatus(){
        return status;
    }

    //Setters

    public void setDID(String DID){
        this.DID = DID;
    }

    public void setfName(String fName){
        this.fName = fName;
    }

    public void setlName(String lName){
        this.lName = lName;
    }

    public void setDOB(String DOB){
        this.DOB = DOB;
    }

    public void setdeptID(String deptID){
        this.deptID = deptID;
    }

    public void setofficeNo(String officeNo){
        this.officeNo = officeNo;
    }

    public void setstatus(String status){
        this.status = status;
    }



}
