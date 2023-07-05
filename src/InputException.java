public class InputException extends Exception {
    private String detail;
    public InputException(String detail, String message) {
        super(message);
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "InputException{" +
                "detail='" + detail + '\'' +
                ", message=" + getMessage()
                + "} ";
    }
}
