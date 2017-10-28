package go_solution.memory;

/**
 * Created by ralphniederer on 28.10.17.
 */

public class Saver {
    private String path = "";
    private String code = "";

    public Saver() {}

    public String getpath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
