package utc2.itk62.sneaker.common;

public class Paging {
    private int limit;
    private int offset;

    public Paging(int limit, int page) {
        this.limit = limit;
        this.offset = (page - 1) * limit;
        checkPageLimit();
    }

    public void checkPageLimit() {
        if (limit <= 0) {
            this.limit = 100;
        }
        if (offset < 0) {
            offset = 0;
        }
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }
}
