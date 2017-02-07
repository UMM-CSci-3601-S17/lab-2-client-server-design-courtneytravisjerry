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
public class FilterByCategory {

    @Test
    public void GetHomework() throws IOException{

        TodoController todoController = new TodoController();
        Todo[] allTodos = todoController.listTodos(new HashMap<>());
        Todo[] homework = todoController.filterCategory("homework", allTodos);
        assertEquals(homework.length, 79);

    }

    @Test
    public void GetEcon() throws IOException{

        TodoController todoController = new TodoController();
        Todo[] allTodos = todoController.listTodos(new HashMap<>());
        Todo[] econ = todoController.filterCategory("economics", allTodos);
        assertEquals(econ.length, 0);

    }
}
