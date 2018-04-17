package Core;


public class Street {

    private int number;
    private String name;
    private String quarter;

    public Street(int n, String na, String q) {
        setNumber(n);
        setName(na);
        setQuarter(q);
    }

    public Street() {

    }

    //_______________________________________________________
    public void setNumber(int number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    //_______________________________________________________
    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getQurater() {
        return quarter;
    }

    //_______________________________________________________
    public String toString() {
        return String.format("%s %s, %s", number, name, quarter);
    }

}
