package hello;

public class DepartmentRecord {

    private String deptID;
    private String name;
    private String bldgName;
    private String deptOffNum;

    //Getters

    public String getdeptID(){
        return deptID;
    }

    public String getname(){
        return name;
    }

    public String getbldgName(){
        return bldgName;
    }

    public String getdeptOffNum(){
        return deptOffNum;
    }

    //Setters

    public void setdeptID(String deptID){
        this.deptID = deptID;
    }

    public void setname(String name){
        this.name = name;
    }

    public void setbldgName(String bldgName){
        this.bldgName = bldgName;
    }

    public void setdeptOffNum(String deptOffNum){
        this.deptOffNum = deptOffNum;
    }

}
