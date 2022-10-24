package dto;

public class Computer {
    private int win;
    private int lose;
    private int tie;
    private double winRate;
    private String tool;
    private final String[] toolList = {"R", "S", "P"};

    public Computer() {
        this.win = 0;
        this.lose = 0;
        this.tie = 0;
        this.winRate = 0;
        this.tool = null;
    }

    public String getComputerDecision() {
        return toolList[(int) (Math.random() * 3)];
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getLose() {
        return lose;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }

    public int getTie() {
        return tie;
    }

    public void setTie(int tie) {
        this.tie = tie;
    }

    public double getWinRate() {
        return winRate;
    }

    public void setWinRate(double winRate) {
        this.winRate = winRate;
    }

    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = tool;
    }
}
