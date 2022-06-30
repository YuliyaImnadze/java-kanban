package manager;

import service.InFile.FileBackedTasksManager;
import service.interfaces.HistoryManager;
import service.InMemory.InMemoryHistoryManager;
import service.interfaces.TaskManager;

import java.io.File;

public final class Managers {
    private static TaskManager taskManager;
    private static HistoryManager historyManager;

    static {
        historyManager = new InMemoryHistoryManager();
        taskManager = new FileBackedTasksManager(new File("src/service/InFile/saveReports.csv"));
    }

    public static TaskManager getDefault() {
        return taskManager;
    }

    public static HistoryManager getDefaultHistory() {
        return historyManager;
    }

}

