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
public class FilterByOwner {

    @Test
    public void GetBlanche() throws IOException{

        TodoController todoController = new TodoController();
        Todo[] allTodos = todoController.listTodos(new HashMap<>());
        Todo[] blanche = todoController.filterOwner("Blanche", allTodos);
        assertEquals(blanche.length, 43);

    }

    @Test
    public void GetBob() throws IOException{

        TodoController todoController = new TodoController();
        Todo[] allTodos = todoController.listTodos(new HashMap<>());
        Todo[] bob = todoController.filterCategory("Bob", allTodos);
        assertEquals(bob.length, 0);

    }
}
