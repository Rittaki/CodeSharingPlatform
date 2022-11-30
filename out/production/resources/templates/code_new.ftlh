<html>
    <head>
        <style>
            textarea {margin: 3;
            width: 500px;
            height: 150px;}
            button {margin: 3;}
        </style>
        <title>Create</title>
        <script>
            function send() {
                let object = {
                    "code": document.getElementById("code_snippet").value
                };
                
                let json = JSON.stringify(object);
                
                let xhr = new XMLHttpRequest();
                xhr.open("POST", '/api/code/new', false)
                xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
                xhr.send(json);
                
                if (xhr.status == 200) {
                  alert("Success!");
                }
            }
        </script>
    </head>
    <body>
    <textarea id="code_snippet">// write your code here</textarea>
    <br>
    <button id="send_snippet" type="submit" onclick="send()">Submit</button>
    </body>
</html>