public class CSVStock {
    private String ds;
    private double y;

    public String getDs() {
        return ds;
    }

    public void setDs(String ds) {
        this.ds = ds;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public CSVStock(String ds, double y) {
        this.ds = ds;
        this.y = y;
    }
}
