package hello;

public class Pharmacist {

    private String pharmID;
    private String fName;
    private String lName;
    private String DOB;
    private String deptID;
    private String officeNum;
    private String status;

    //Getters

    public String getpharmID(){
        return pharmID;
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

    public String getofficeNum(){
        return officeNum;
    }

    public String getdeptID(){
        return deptID;
    }

    //Setters

    public void setpharmID(String pharmID){
        this.pharmID = pharmID;
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

    public void setofficeNo(String officeNum){
        this.officeNum = officeNum;
    }

    public void setdeptID(String deptID){
        this.deptID = deptID;
    }


}
