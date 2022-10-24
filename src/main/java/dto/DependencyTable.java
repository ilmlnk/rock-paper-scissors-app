package dto;

public class DependencyTable {
    public static final String[][] dependencyTable = {
            {"R=R", "R=S", "R=P"},
            {"S=R", "S=S", "S=P"},
            {"P=R", "P=S", "P=P"}
    };
}

// +_____________________________________________________________+
// |    Rock-Rock    |      Rock-Scissors    |    Rock-Paper     |
// |  Scissors-Rock  |   Scissors-Scissors   |   Scissors-Paper  |
// |    Paper-Rock   |     Paper-Scissors    |    Paper-Paper    |
// +_____________________________________________________________+