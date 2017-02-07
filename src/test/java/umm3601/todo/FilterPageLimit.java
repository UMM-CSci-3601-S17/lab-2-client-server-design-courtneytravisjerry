package umm3601.todo;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;
/**
 * Created by cookx876 on 2/7/17.
 */

public class FilterPageLimit {

    @Test
    public void Get3() throws IOException{

        TodoController todoController = new TodoController();
        Todo[] allTodos = todoController.listTodos(new HashMap<>());
        Todo[] Lim3 = todoController.getPageLimit(3, allTodos);
        assertEquals(Lim3.length, 3);

    }

    @Test
    public void Get7() throws IOException{

        TodoController todoController = new TodoController();
        Todo[] allTodos = todoController.listTodos(new HashMap<>());
        Todo[] Lim3 = todoController.getPageLimit(7, allTodos);
        assertEquals(Lim3.length, 7);

    }
}