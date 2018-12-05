package hello;

public class Nurse {

    private String nurseID;
    private String fName;
    private String lName;
    private String DOB;
    private String roomNum;
    private String deptID;

    //Getters

    public String getnurseID(){
        return nurseID;
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

    public String getroomNum(){
        return roomNum;
    }

    public String getdeptID(){
        return deptID;
    }

    //Setters

    public void setnurseID(String nurseID){
        this.nurseID = nurseID;
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

    public void setroomNum(String roomNum){
        this.roomNum = roomNum;
    }

    public void setdeptID(String deptID){
        this.deptID = deptID;
    }


}
