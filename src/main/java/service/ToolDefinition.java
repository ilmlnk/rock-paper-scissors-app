package service;

public class ToolDefinition {
    protected static int defineTool(String tool) {
        switch (tool.toUpperCase()) {
            case "R" -> {
                return 0;
            }
            case "S" -> {
                return 1;
            }
            case "P" -> {
                return 2;
            }
        }
        return -1;
    }
}
