package hello;

/*+----------------------------------------------------------------------
||
||  Class DepartmentRecord
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

public class DepartmentRecord {

    private int deptID;
    private String name;
    private String bldgName;
    private int deptOffNum;

    public int getDeptID() {
        return deptID;
    }

    public void setDeptID(int deptID) {
        this.deptID = deptID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBldgName() {
        return bldgName;
    }

    public void setBldgName(String bldgName) {
        this.bldgName = bldgName;
    }

    public int getDeptOffNum() {
        return deptOffNum;
    }

    public void setDeptOffNum(int deptOffNum) {
        this.deptOffNum = deptOffNum;
    }
}
