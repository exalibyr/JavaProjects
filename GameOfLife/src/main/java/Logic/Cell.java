package Logic;

public class Cell {
	
    private boolean isAlive;

    public boolean isAlive() {
        return isAlive;
    }

    public void setState(boolean state) {
        isAlive = state;
    }
}
