
import manager.Managers;

import service.interfaces.TaskManager;
import task.Task;
import task.enums.TaskStatus;
import task.enums.TaskType;
import task.sub.Epic;

public class Main {
    public static void main(String[] args) {

        TaskManager inMemoryTaskManager = Managers.getDefault();


        inMemoryTaskManager.createTask(new Task("Таск 1", TaskType.TASK, TaskStatus.DONE, "Доделать тз"));
        inMemoryTaskManager.getTaskByID(1);
        inMemoryTaskManager.createTask(new Task("Таск 2", TaskType.TASK, TaskStatus.DONE, "Купить еды"));
        inMemoryTaskManager.getTaskByID(2);

        inMemoryTaskManager.getTaskByID(1);
        inMemoryTaskManager.createTask(new Task("Таск 3", TaskType.TASK, TaskStatus.NEW, "Купить еды"));
        inMemoryTaskManager.createTask(new Task("Таск 4", TaskType.TASK, TaskStatus.NEW, "Купить еды"));

        inMemoryTaskManager.getTaskByID(4);
        inMemoryTaskManager.getTaskByID(3);

    //    inMemoryTaskManager.getTaskByID(2);

        inMemoryTaskManager.createEpic(new Epic("Эпик 1", TaskType.EPIC, TaskStatus.NEW, "Покупка квартиры"));
        inMemoryTaskManager.createEpic(new Epic("Эпик 2", TaskType.EPIC, TaskStatus.NEW, "Продажа дачи"));

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