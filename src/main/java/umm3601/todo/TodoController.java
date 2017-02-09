package umm3601.todo;

/**
 * Created by cookx876 on 1/28/17.
 */
import com.google.gson.Gson;

        import java.io.FileReader;
        import java.io.IOException;
        import java.util.Arrays;
        import java.util.Map;

public class TodoController {

    private Todo[] todos;

    public TodoController() throws IOException {
        Gson gson = new Gson();
        FileReader reader = new FileReader("src/main/data/todos.json");
        todos = gson.fromJson(reader, Todo[].class);
    }

    // List Todos
    public Todo[] listTodos(Map<String, String[]> queryParams) {
        Todo[] filteredTodos = todos;

        // filter by status
        if (queryParams.containsKey("status")) {
            boolean state;
            if ("complete".equals(queryParams.get("status")[0])) {
                filteredTodos = returnstatuses(true, filteredTodos);
            } else if ("incomplete".equals(queryParams.get("status")[0])) {
                filteredTodos = returnstatuses(false, filteredTodos);
            } else {
                filteredTodos = new Todo[0];
            }
        }

        // filter by keyword
        if (queryParams.containsKey("contains")) {
            String search = queryParams.get("contains")[0];
            filteredTodos = searchBody(search, filteredTodos);
        }

        // filter by owner
        if (queryParams.containsKey("owner")) {
            String owner = queryParams.get("owner")[0];
            filteredTodos = filterOwner(owner, filteredTodos);
        }

        // filter by category
        if (queryParams.containsKey("category")) {
            String category = queryParams.get("category")[0];
            filteredTodos = filterCategory(category, filteredTodos);
        }

        // order by keyword
        if (queryParams.containsKey("orderBy")) {
            String param = queryParams.get("orderBy")[0];
            if (param.equals("owner")) {
                filteredTodos = orderByOwner(filteredTodos);
            } else if (param.equals("category")) {
                filteredTodos = orderByCategory(filteredTodos);
            } else if (param.equals("status")) {
                filteredTodos = orderByStatus(filteredTodos);
            } else if (param.equals("body")) {
                filteredTodos = orderByBody(filteredTodos);
            } else {
                filteredTodos = new Todo[0];
            }
        }

        // page limit
        if (queryParams.containsKey("limit")) {
            int limit = Integer.parseInt(queryParams.get("limit")[0]);
            filteredTodos = getPageLimit(limit, filteredTodos);
        }

        return filteredTodos;
    }


    // Get a single todo_
    public Todo getTodo(String id) {
        return Arrays.stream(todos).filter(x -> x._id.equals(id)).findFirst().orElse(null);
    }

    //Get todo_ page limit
    public Todo[] getPageLimit(int limit, Todo[] filteredTodos) {
        Todo[] toReturn = new Todo[limit];
        int i = 0;
        while (i < limit) {
            toReturn[i] = filteredTodos[i];
            i++;
        }
        return toReturn;
    }

    //Return All todos of a status.
    public Todo[] returnstatuses(boolean state, Todo[] filteredTodos) {
        Todo[] holding = new Todo[filteredTodos.length];
        int j = 0;
        int i;
        for (i = 0; i < filteredTodos.length; i++) {
            if (filteredTodos[i].status.equals(state)) {
                holding[j] = filteredTodos[i];
                j++;
            }
        }
        Todo[] toReturn = new Todo[j];
        for (i = 0; i < toReturn.length; i++) {
            toReturn[i] = holding[i];
        }
        return toReturn;
    }

    // returns all todos with search in body
    public Todo[] searchBody(String key, Todo[] filteredTodos) {
        Todo[] holding = new Todo[filteredTodos.length];
        int j = 0;
        int i;
        for (i = 0; i < filteredTodos.length; i++) {
            if (filteredTodos[i].body.contains(key)) {
                holding[j] = filteredTodos[i];
                j++;
            }
        }
        Todo[] toReturn = new Todo[j];
        for (i = 0; i < toReturn.length; i++) {
            toReturn[i] = holding[i];
        }
        return toReturn;
    }

    // returns all todos with a given owner
    public Todo[] filterOwner(String owner, Todo[] filteredTodos) {
        Todo[] holding = new Todo[filteredTodos.length];
        int j = 0;
        int i;
        for (i = 0; i < filteredTodos.length; i++) {
            if (filteredTodos[i].owner.equals(owner)) {
                holding[j] = filteredTodos[i];
                j++;
            }
        }
        Todo[] toReturn = new Todo[j];
        for (i = 0; i < toReturn.length; i++) {
            toReturn[i] = holding[i];
        }
        return toReturn;
    }

    // returns all todos with a given category
    public Todo[] filterCategory(String category, Todo[] filteredTodos) {
        Todo[] holding = new Todo[filteredTodos.length];
        int j = 0;
        int i;
        for (i = 0; i < filteredTodos.length; i++) {
            if (filteredTodos[i].category.equals(category)) {
                holding[j] = filteredTodos[i];
                j++;
            }
        }
        Todo[] toReturn = new Todo[j];
        for (i = 0; i < toReturn.length; i++) {
            toReturn[i] = holding[i];
        }
        return toReturn;
    }

    // returns all todos, sorted by owner
    public Todo[] orderByOwner(Todo[] filteredTodos) {
        Todo[] tmp = new Todo[filteredTodos.length];
        int i;
        int j;
        for (i = 0; i < filteredTodos.length; i++) {
            for (j = i; j < filteredTodos.length; j++) {
                if (filteredTodos[i].owner.compareTo(filteredTodos[j].owner) > 0) {
                    Todo temp = filteredTodos[i];
                    filteredTodos[i] = filteredTodos[j];
                    filteredTodos[j] = temp;
                }
            }
        }
        return filteredTodos;
    }

    // returns all todos, sorted by category
    public Todo[] orderByCategory(Todo[] filteredTodos) {
        Todo[] tmp = new Todo[filteredTodos.length];
        int i;
        int j;
        for (i = 0; i < filteredTodos.length; i++) {
            for (j = i; j < filteredTodos.length; j++) {
                if (filteredTodos[i].category.compareTo(filteredTodos[j].category) > 0) {
                    Todo temp = filteredTodos[i];
                    filteredTodos[i] = filteredTodos[j];
                    filteredTodos[j] = temp;
                }
            }
        }
        return filteredTodos;
    }

    // returns all todos, sorted by body
    public Todo[] orderByBody(Todo[] filteredTodos) {
        Todo[] tmp = new Todo[filteredTodos.length];
        int i;
        int j;
        for (i = 0; i < filteredTodos.length; i++) {
            for (j = i; j < filteredTodos.length; j++) {
                if (filteredTodos[i].body.compareTo(filteredTodos[j].body) > 0) {
                    Todo temp = filteredTodos[i];
                    filteredTodos[i] = filteredTodos[j];
                    filteredTodos[j] = temp;
                }
            }
        }
        return filteredTodos;
    }


    // returns all todos, sorted by status
    public Todo[] orderByStatus(Todo[] filteredTodos) {
        Todo[] tmp = new Todo[filteredTodos.length];
        int i;
        int j;
        for (i = 0; i < filteredTodos.length; i++) {
            for (j = i; j < filteredTodos.length; j++) {
                if (filteredTodos[i].status.compareTo(filteredTodos[j].status) < 0) {
                    Todo temp = filteredTodos[i];
                    filteredTodos[i] = filteredTodos[j];
                    filteredTodos[j] = temp;
                }
            }
        }
        return filteredTodos;
    }
}
