package go_solution.memory;

/**
 * Created by ralphniederer on 28.10.17.
 */

public class Saver {
    private String path = "";
    private String code = "";

    public void setPath(String path) {
        this.path = path;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getPath() {
        return this.path;
    }

    public String getCode() {
        return this.code;
    }
}
