package service.InFile;

import service.InMemory.InMemoryTaskManager;
import service.interfaces.HistoryManager;
import service.interfaces.TaskManager;
import task.Task;
import task.enums.TaskStatus;
import task.enums.TaskType;
import task.sub.Epic;
import task.sub.SubTask;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class FileBackedTasksManager extends InMemoryTaskManager implements TaskManager {

    public static void main(String[] args) throws IOException {

        File fileForExample = new File("src/service/InFile/saveReports2.csv");
        FileBackedTasksManager fileBackedTasksManager = new FileBackedTasksManager(fileForExample);
        fileBackedTasksManager.createTask(new Task("Таск 1", TaskStatus.DONE, "Доделать тз"));
        fileBackedTasksManager.getTaskByID(1);
        fileBackedTasksManager.createTask(new Task("Таск 2", TaskStatus.DONE, "Купить еды"));
        fileBackedTasksManager.getTaskByID(2);

        fileBackedTasksManager.getTaskByID(1);
        fileBackedTasksManager.createTask(new Task("Таск 3", TaskStatus.NEW, "Купить еды"));
        fileBackedTasksManager.createTask(new Task("Таск 4", TaskStatus.NEW, "Купить еды"));

        fileBackedTasksManager.getTaskByID(4);
        fileBackedTasksManager.getTaskByID(3);


        fileBackedTasksManager.createEpic(new Epic("Эпик 1", TaskStatus.NEW, "Покупка квартиры"));
        fileBackedTasksManager.createEpic(new Epic("Эпик 2", TaskStatus.NEW, "Продажа дачи"));
        File fileForExmaple2 = new File("src/service/InFile/saveReports2.csv");
        FileBackedTasksManager fileBackedTasksManager2 = loadFromFile(fileForExmaple2);
        System.out.println(fileBackedTasksManager2.getTaskByID(1));

    }

    File file;

    public FileBackedTasksManager(File file) {
        super();
        this.file = file;
    }

    public static String toString(HistoryManager manager) { // для сохранения и восстановления менеджера истории из CSV.
        StringBuilder line = new StringBuilder();
        if (manager.getHistory().size() != 0) {
            for (Task task : manager.getHistory()) {
                line.append(task.getId()).append(",");

            }
        } else {
            line.append("История просмотров пуста  ");
        }
        line.deleteCharAt(line.length() - 1);
        return line.toString();
    }

    public static List<Integer> historyFromString(String value) { // для сохранения и восстановления менеджера истории из CSV.
        List<Integer> history = new ArrayList<>();
        if (value.isEmpty()) {
            System.out.println("Передана пустая строка");
            return Collections.emptyList();
        }
        String[] line = value.split(",");
        for (String str : line) {
            history.add(Integer.parseInt(str));
        }
        return history;
    }

    public Task fromString(String value) { // метод создания задачи из строки
        String name;
        String description;
        TaskStatus status;
        TaskType type;
        int id;

        String[] line = value.split(",");
        name = line[2];
        description = line[4];
        id = Integer.parseInt(line[0]);
        status = TaskStatus.valueOf(line[3]);
        type = TaskType.valueOf(line[1]);
        switch (type) {
            case EPIC:
                Epic epic = new Epic(name, status, description);
                epic.setId(id);
                return epic;
            case SUB_TASK:
                SubTask subTask = new SubTask(name, status, description, Integer.parseInt(line[5]));
                subTask.setId(id);
                return subTask;
            case TASK:
                Task task = new Task(name, status, description);
                task.setId(id);
                return task;
        }
        return null;
    }

    public void save() { // сохранять текущее состояние менеджера в указанный файл
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("id,type,name,status,description,epic\n");

            Collection<Task> tasks = super.getTasks();
            Collection<Epic> epics = super.getEpics();
            Collection<SubTask> subTasks = super.getAllSubtasks();

            for (Task task : tasks) {
                writer.write(task.toString());
            }
            for (Epic epic : epics) {
                writer.write(epic.toString());
            }
            for (SubTask sub : subTasks) {
                writer.write(sub.toString());
            }
            writer.write("\n");
            writer.write(toString(getHistoryManager()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static FileBackedTasksManager loadFromFile(File file) throws IOException { // восстанавливать данные менеджера из файла при запуске программы
        FileBackedTasksManager fileBackedTasksManager = new FileBackedTasksManager(file);
        String s = Files.readString(Path.of(String.valueOf(file)));
        if (s.isEmpty())
            return fileBackedTasksManager;
        String[] lines = s.split("\n");
        int i = 1;
        while (!lines[i].isEmpty()) {
            Task task = fileBackedTasksManager.fromString(lines[i]);
            ++i;
            fileBackedTasksManager.createTask(task);

            if (lines.length == i)
                break;
        }
        if (i == lines.length) {
            return fileBackedTasksManager;
        } else {
            List<Integer> history = historyFromString(lines[lines.length - 1]);
            for (Integer idHistory : history) {
                if (fileBackedTasksManager.getEpicByID(idHistory) != null) {
                    fileBackedTasksManager.getHistoryManager().add(fileBackedTasksManager.getEpicByID(idHistory));

                }
                if (fileBackedTasksManager.getSubtaskByID(idHistory) != null) {
                    fileBackedTasksManager.getHistoryManager().add(fileBackedTasksManager.getSubtaskByID(idHistory));

                }
                if (fileBackedTasksManager.getTaskByID(idHistory) != null) {
                    fileBackedTasksManager.getHistoryManager().add(fileBackedTasksManager.getTaskByID(idHistory));
                }

                return fileBackedTasksManager;
            }
        }
        return fileBackedTasksManager;
    }

    @Override
    public void createTask(Task task) {
        super.createTask(task);
        save();
    }

    @Override
    public Collection<Task> getTasks() {
        return super.getTasks();
    }

    @Override
    public Collection<SubTask> getAllSubtasks() {
        return super.getAllSubtasks();
    }

    @Override
    public Task getTaskByID(int taskId) {
        return super.getTaskByID(taskId);
    }

    @Override
    public Epic getEpicByID(int epicId) {
        return super.getEpicByID(epicId);
    }

    @Override
    public SubTask getSubtaskByID(int subtaskId) {
        return super.getSubtaskByID(subtaskId);
    }

    @Override
    public List<Task> getHistory() {
        return super.getHistory();
    }

    @Override
    public void createEpic(Epic epic) {
        super.createEpic(epic);
        save();
    }

    @Override
    public Collection<Epic> getEpics() {
        return super.getEpics();
    }

    @Override
    public void addSubTask(SubTask subTask) {
        super.addSubTask(subTask);
        save();
    }

    @Override
    public List<SubTask> getSubTask(int epicId) {
        return super.getSubTask(epicId);
    }

    @Override
    public void deleteAllTasks() {
        super.deleteAllTasks();
        save();
    }

    @Override
    public void deleteSubtaskById(int subtaskId) {
        super.deleteSubtaskById(subtaskId);
        save();
    }

    @Override
    public void deleteAllSubtasks() {
        super.deleteAllSubtasks();
        save();
    }

    @Override
    public void deleteAllEpics() {
        super.deleteAllEpics();
        save();
    }

    @Override
    public void updateTask(Task task) {
        super.updateTask(task);
        save();
    }

    @Override
    public void updateEpic(Epic epic) {
        super.updateEpic(epic);
        save();
    }

    @Override
    public void updateSubtask(SubTask subTask) {
        super.updateSubtask(subTask);
        save();
    }

    @Override
    public void deleteTaskById(int taskId) {
        super.deleteTaskById(taskId);
        save();
    }

    @Override
    public void deleteEpicById(int epicId) {
        super.deleteEpicById(epicId);
        save();
    }
}
