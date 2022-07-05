package service.inMemory;

import service.interfaces.HistoryManager;
import task.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager {

    private final Map<Integer, Node> customLinkedList = new HashMap<>(); // новый хэшмэп

    private Node first;
    private Node last;

    @Override
    public void add(Task task) {
        if (customLinkedList.containsKey(task.getId())) {
            removeNode(customLinkedList.get(task.getId()));
            customLinkedList.remove(task.getId());
        }
        Node nodeAdded = linkLast(task); //После добавления задачи не забудьте обновить значение узла в HashMap.
        customLinkedList.put(task.getId(), nodeAdded);
    }


    public Node linkLast(Task task) { // добавлять задачу в конец списка
        final Node secondLast = last;
        final Node newNode = new Node(secondLast, task, null);
        last = newNode;
        if (secondLast != null) {
            secondLast.next = newNode;
        } else {
            first = newNode;
        }
        return newNode;
    }


    public List<Task> getTasks() { // собирать все задачи из списка в обычный ArrayList
        List<Task> allListHistory = new ArrayList<>(); // другой лист???
        Node node = first;
        while (node != null) {
            allListHistory.add(node.value);
            node = node.next;
        }
        return allListHistory;
    }


    public void removeNode(Node value) { //  В качестве параметра этот метод должен принимать объект Node — узел связного списка и вырезать его.

        if (value == first) {
            if (first.next != null)
                first = first.next;
            else
                first = null;
        }

        if (value == last) {
            if (last.previous != null)
                last = last.previous;
            else
                last = null;
        }

        if (value.previous != null) {
            value.previous.next = value.next;
            if (value.next != null) {
                value.next.previous = value.previous;
            }
        }
    }

    @Override
    public List<Task> getHistory() {
        return getTasks();
    }

    public void remove(int id) {
        if (customLinkedList.containsKey(id)) {
            removeNode(customLinkedList.get(id));
            customLinkedList.remove(id);
        }
    }


    private static class Node {
        private Node previous;
        private Task value;
        private Node next;

        public Node(Node previous, Task value, Node next) {
            this.previous = previous;
            this.value = value;
            this.next = next;
        }

    }
}

