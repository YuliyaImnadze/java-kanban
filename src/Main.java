
import manager.Managers;

import service.InFile.FileBackedTasksManager;
import service.interfaces.TaskManager;
import task.Task;
import task.enums.TaskStatus;
import task.sub.Epic;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        TaskManager inMemoryTaskManager = Managers.getDefault();


        inMemoryTaskManager.createTask(new Task("Таск 1", TaskStatus.DONE, "Доделать тз"));
        inMemoryTaskManager.getTaskByID(1);
        inMemoryTaskManager.createTask(new Task("Таск 2", TaskStatus.DONE, "Купить еды"));
        inMemoryTaskManager.getTaskByID(2);

        inMemoryTaskManager.getTaskByID(1);
        inMemoryTaskManager.createTask(new Task("Таск 3", TaskStatus.NEW, "Купить еды"));
        inMemoryTaskManager.createTask(new Task("Таск 4", TaskStatus.NEW, "Купить еды"));

        inMemoryTaskManager.getTaskByID(4);
        inMemoryTaskManager.getTaskByID(3);

    //    inMemoryTaskManager.getTaskByID(2);

        inMemoryTaskManager.createEpic(new Epic("Эпик 1", TaskStatus.NEW, "Покупка квартиры"));
        inMemoryTaskManager.createEpic(new Epic("Эпик 2", TaskStatus.NEW, "Продажа дачи"));

//        inMemoryTaskManager.addSubTask(new SubTask("Сабтаск 1", TaskStatus.NEW, "---", 3));
//        inMemoryTaskManager.addSubTask(new SubTask("Сабтаск 2", TaskStatus.NEW, "---", 3));
//        inMemoryTaskManager.addSubTask(new SubTask("Сабтаск 3", TaskStatus.NEW, "---", 3));*/


//        inMemoryTaskManager.getTaskByID(1);
//        inMemoryTaskManager.getTaskByID(1);
       /* inMemoryTaskManager.getTaskByID(2);
        inMemoryTaskManager.getEpicByID(4);
        inMemoryTaskManager.getSubtaskByID(5);
        inMemoryTaskManager.getSubtaskByID(5);
        inMemoryTaskManager.getSubtaskByID(7);

        inMemoryTaskManager.deleteEpicById(3);*/

    //    System.out.println(inMemoryTaskManager.getHistory());

    }
}