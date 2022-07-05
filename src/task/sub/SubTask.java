package task.sub;

import task.Task;
import task.enums.TaskStatus;
import task.enums.TaskType;

import java.util.Objects;

public class SubTask extends Task {
    private int epicId;

    public SubTask(String name, TaskType type, TaskStatus status, String description, int epicId) {
        super(name, type, status, description);
        this.epicId = epicId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SubTask subTask = (SubTask) o;
        return epicId == subTask.epicId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), epicId);
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", epicId=" + epicId +
                '}';
    }
}