public class Snake {
    int startPos;
    int endPositon;

    public Snake(int startPos, int endPositon) {
        this.startPos = startPos;
        this.endPositon = endPositon;
    }

    public int getStartPos() {
        return startPos;
    }

    public void setStartPos(int startPos) {
        this.startPos = startPos;
    }

    public int getEndPositon() {
        return endPositon;
    }

    public void setEndPositon(int endPositon) {
        this.endPositon = endPositon;
    }
}
