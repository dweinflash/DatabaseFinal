package hello;

/*+----------------------------------------------------------------------
||
||  Class QueryTwo
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

public class QueryTwo {

    private String name;

    //Getters

    public String getname(){
        return name;
    }

    //Setters

    public void setname(String name){
        this.name = name;
    }


}
