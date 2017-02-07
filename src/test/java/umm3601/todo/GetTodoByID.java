package umm3601.todo;

import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class GetTodoByID {

    @Test
    public void getBlancheSoftD() throws IOException {
        TodoController todoController = new TodoController();
        Todo todo = todoController.getTodo("58895985a22c04e761776d54");
        assertEquals("software design", todo.category);
    }

    @Test
    public void getFryHomework() throws IOException {
        TodoController todoController = new TodoController();
        Todo todo = todoController.getTodo("588959856601f6a77b6a2862");
        assertEquals( "homework", todo.category);
    }
}