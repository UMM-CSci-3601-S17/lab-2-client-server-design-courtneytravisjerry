/**
 * Created by cookx876 on 1/28/17.
 */
/**
 * We use this to make sure we're not trying to do stuff with the
 * elements on the page before the page is even loaded.
 *
 *
 */
window.onload = function() {
    console.log("The page is loaded now!");

    var element = document.getElementById('getAll');
    element.addEventListener("click", getToDo, true);
}


/**
 * Function to get all the users!
 */
var getToDo = function() {
    var httpClient = new HttpClient();
    httpClient.get("/api/todo", function(returned_json){
        document.getElementById('jsonDump').innerHTML = returned_json;
    });
    ////// makeURL();
}
/**
 * takes fields and makes url
 */
function makeURL(){
    var ID = document.getElementById('_ID');
    var Cat = document.getElementById('category');
    var Own = document.getElementById('owner');
    var stat = document.getElementById('status');
    var bodykey = document.getElementById('search');
    var orderKey = document.getElementById('keyword');
    var pageLimit = document.getElementById('limit');
    if(Cat != null){
        var cate = Cat.toString();
        var URL = "/api/todo?category="+Cat.toString();
        document.write(URL);
    }

}
/**
 * runs url when button is pushed
 */


/**
 * p much copied from users.js
 * @constructor
 */
function HttpClient() {
    // We'll take a URL string, and a callback function.
    this.get = function(aUrl, aCallback){
        var anHttpRequest = new XMLHttpRequest();

        // Set a callback to be called when the ready state of our request changes.
        anHttpRequest.onreadystatechange = function(){

            /**
             * Only call our 'aCallback' function if the ready state is 'DONE' and
             * the request status is 200 ('OK')
             *
             * See https://httpstatuses.com/ for HTTP status codes
             * See https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/readyState
             *  for XMLHttpRequest ready state documentation.
             *
             */
            if (anHttpRequest.readyState == 4 && anHttpRequest.status == 200)
                aCallback(anHttpRequest.responseText);
        }

        anHttpRequest.open("GET", aUrl, true);
        anHttpRequest.send(null);
    }

}