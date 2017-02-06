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



        if(queryParams.containsKey("status")){
            boolean state;
            if ("complete".equals(queryParams.get("status")[0])) {
                state = true;
            } else {
                state =false;
            }

            filteredTodos = returnstatuses(state, filteredTodos);
        }

        if(queryParams.containsKey("contains")){
            String search = queryParams.get("contains")[0];
            filteredTodos = searchBody(search, filteredTodos);
        }

        if(queryParams.containsKey("owner")){
            String owner = queryParams.get("owner")[0];
            filteredTodos = filterOwner(owner, filteredTodos);
        }
        if(queryParams.containsKey("category")){
            String category = queryParams.get("category")[0];
            filteredTodos = filterCategory(category, filteredTodos);
        }
        if(queryParams.containsKey("orderBy")){
            String param = queryParams.get("orderBy")[0];
            if(param.equals("owner")){
                filteredTodos = orderByOwner(param, filteredTodos);
            } else if (param.equals("category")){
                filteredTodos = orderByCategory(param, filteredTodos);
            } else if (param.equals("status")){
                filteredTodos = orderByStatus(param, filteredTodos);
            } else if (param.equals("body")){
                filteredTodos = orderByBody(param, filteredTodos);
            } else {
                return null;
            }
        }
        // page limit
        if(queryParams.containsKey("limit")){
            int limit = Integer.parseInt(queryParams.get("limit")[0]);
            filteredTodos= getPageLimit(limit, filteredTodos);
        }

        return filteredTodos;
    }



    // Get a single Todo_
    public Todo getTodo(String id) {
        return Arrays.stream(todos).filter(x -> x._id.equals(id)).findFirst().orElse(null);
    }

    //Get todo page limit
    public Todo[] getPageLimit(int limit, Todo[] filteredTodos){
        Todo[] toReturn = new Todo[limit];
        int i = 0;
        while(i < limit){
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
        for(i = 0; i < filteredTodos.length; i++){
            if (filteredTodos[i].status.equals(state)) {
                holding[j] = filteredTodos[i];
                j++;
            }
        }
        Todo[] toReturn = new Todo[j];
        for (i=0; i<toReturn.length; i++) {
            toReturn[i] = holding[i];
        }
        return toReturn;
    }

    // returns all todos with search in body
    public Todo[] searchBody(String key, Todo[] filteredTodos){
        Todo[] holding = new Todo[filteredTodos.length];
        int j = 0;
        int i;
        for(i = 0; i < filteredTodos.length; i++){
            if (filteredTodos[i].body.contains(key)) {
                holding[j] = filteredTodos[i];
                j++;
            }
        }
        Todo[] toReturn = new Todo[j];
        for (i=0; i<toReturn.length; i++) {
            toReturn[i] = holding[i];
        }
        return toReturn;
   }
   public Todo[] filterOwner(String owner, Todo[] filteredTodos) {
       Todo[] holding = new Todo[filteredTodos.length];
       int j = 0;
       int i;
       for(i = 0; i < filteredTodos.length; i++){
           if (filteredTodos[i].owner.equals(owner)) {
               holding[j] = filteredTodos[i];
               j++;
           }
       }
       Todo[] toReturn = new Todo[j];
       for (i=0; i<toReturn.length; i++) {
           toReturn[i] = holding[i];
       }
       return toReturn;
   }

    public Todo[] filterCategory(String category, Todo[] filteredTodos) {
        Todo[] holding = new Todo[filteredTodos.length];
        int j = 0;
        int i;
        for(i = 0; i < filteredTodos.length; i++){
            if (filteredTodos[i].category.equals(category)) {
                holding[j] = filteredTodos[i];
                j++;
            }
        }
        Todo[] toReturn = new Todo[j];
        for (i=0; i<toReturn.length; i++) {
            toReturn[i] = holding[i];
        }
        return toReturn;
    }

    //From rosettacode.org :)
    public Todo[] orderByOwner (String owner, Todo[] filteredTodos){
        Todo[] tmp = new Todo[filteredTodos.length];
        int i =1; int j=0;
        tmp[0] = filteredTodos[0];
//        while(i<todos.length){
//            String thing = todos[i].owner;
//            while(thing.compareTo(tmp[j].owner) < 0){
//                j++;
//            }
//            tmp[j].owner = thing;
//        }
        for (i =1; i<tmp.length;i++) {
            for (j = 0; j<i; j++) {
                if (tmp[i].owner.compareTo(tmp[j].owner) < 0) {
                    Todo foo = tmp[i];
                    tmp[i] = tmp[j];
                    tmp[j] = foo;
                }
            }
            i++;
        }
        return tmp;
    }

    public Todo[] orderByCategory(String owner, Todo[] filteredTodos){
        return null;
    }

    public Todo[] orderByBody(String owner, Todo[] filteredTodos){

        return null;
    }

    public Todo[] orderByStatus(String owner, Todo[] filteredTodos){
        return null;
    }
}
