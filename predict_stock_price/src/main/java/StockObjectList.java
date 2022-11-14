import java.util.List;

public class StockObjectList {
    boolean success;
    List<StockObject> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<StockObject> getData() {
        return data;
    }

    public StockObjectList(boolean success, List<StockObject> data) {
        this.success = success;
        this.data = data;
    }

    public void setData(List<StockObject> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "success: " + success;
    }
}
