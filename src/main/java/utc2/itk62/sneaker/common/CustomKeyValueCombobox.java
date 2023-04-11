package utc2.itk62.sneaker.common;

public class CustomKeyValueCombobox<T> {
    private T key;
    private String value;

    public CustomKeyValueCombobox() {
    }

    public CustomKeyValueCombobox(T key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public T getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
