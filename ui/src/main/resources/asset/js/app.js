import Turbolinks from 'turbolinks'

Turbolinks.start();

var websocket = new WebSocket("ws://127.0.0.1:5105/ws/notify/awesome-topic")

websocket.onmessage = function(msg) {
    let ul = document.getElementById('list');
    ul.style = "display: block";
    let li = document.createElement("li");
    li.appendChild(document.createTextNode(msg.data));
    ul.appendChild(li);
}

websocket.onclose = function() { alert("WebSocket connection closed"); }