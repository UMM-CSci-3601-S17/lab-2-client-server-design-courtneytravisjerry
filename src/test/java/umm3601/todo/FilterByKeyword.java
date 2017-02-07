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
public class FilterByKeyword {

    @Test
    public void GetQuip() throws IOException{

        TodoController todoController = new TodoController();
        Todo[] allTodos = todoController.listTodos(new HashMap<>());
        Todo[] quip = todoController.searchBody("quip cupida", allTodos);
        assertEquals(quip.length, 3);

    }

    @Test
    public void GetBanana() throws IOException{

        TodoController todoController = new TodoController();
        Todo[] allTodos = todoController.listTodos(new HashMap<>());
        Todo[] banana = todoController.searchBody("banana", allTodos);
        assertEquals(banana.length, 0);

    }
}
