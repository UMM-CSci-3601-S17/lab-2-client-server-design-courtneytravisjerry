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
public class SortByKey {
    @Test
    public void SortNotFilter() throws IOException{

        TodoController todoController = new TodoController();
        Todo[] allTodos = todoController.listTodos(new HashMap<>());
        Todo[] sorted = todoController.orderByCategory(allTodos);
        assertEquals(sorted.length, 300);
        System.out.println("First to be sorted = " + sorted[1].category + " "+ sorted[1].owner);
    }

    @Test
    public void SortbyStatus() throws IOException{

        TodoController todoController = new TodoController();
        Todo[] allTodos = todoController.listTodos(new HashMap<>());
        Todo[] state = todoController.orderByStatus(allTodos);
        assertEquals(state[0].status.toString(), "true");
        assertEquals(state[299].status.toString() , "false");

    }

    @Test
    public void SortbyBody() throws IOException{

        TodoController todoController = new TodoController();
        Todo[] allTodos = todoController.listTodos(new HashMap<>());
        Todo[] body = todoController.orderByBody(allTodos);
        assertEquals(body[0].body.charAt(0), 'A');

    }

    @Test
    public void SortbyOwner() throws IOException{

        TodoController todoController = new TodoController();
        Todo[] allTodos = todoController.listTodos(new HashMap<>());
        Todo[] owner = todoController.orderByOwner(allTodos);
        assertEquals(owner[0].owner, "Barry");

    }

    @Test
    public void SortbyCategory() throws IOException{

        TodoController todoController = new TodoController();
        Todo[] allTodos = todoController.listTodos(new HashMap<>());
        Todo[] category = todoController.orderByCategory(allTodos);
        assertEquals(category[0].category, "groceries");

    }
}
