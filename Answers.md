What is the purpose of .gitIgnore?
    It's as list of files that Git ignores.

Explain what a route is.
    THINGS, that will reroute the user's desired path to a more appropriate path that fits with our website.
    Will redirect a user's extension to the desired path

What is the purpose of umm3601.Server class? What is the purpose of the umm3601.user.UserController class?
    Redirects urls and decides how the users will be returned

Explain what happens when a user accesses each of the following URLs:
The page users
    page returns the users in a giant mess
The page api/users
    page returns the users in an orderly fashion
The page api/users?age=25
    page returns only the users age 25 in an orderly fashion
The page api/users/588935f5de613130e931ffd5
    page reutrns the user with the ID 588935f5de613130e931ffd5, in an easy-to-read manner

What happens when the user accesses the page "kittens"? Modify the server code so accessing the
page "kittens" results in the text "Meow". Describe what you did and how it worked.
    404 page not found
    created a /kittens.html page, added a redirect from /kittens to /kittens.html in the Server class

What are the contents of the public folder? What is the purpose of each of the HTML files there?
    It consists of assets.images, css, javascript and html files.

Describe what happens when you filter users by age in the client? What is read from the web page,
and what request is sent to the server? What is received, and how/where is it displayed?
    It only shows the user that fit the age. The web page adds a query string. A filter of age is sent
    the server. The client gets a list of users whose age matches. It's displayed in the body of the page.

Where is the client-side JavaScript defined? Name the file(s) in which it is used.
    It's defined in the src/main/java/user folder. User and UserController are used.