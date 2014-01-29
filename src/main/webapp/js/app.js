var ws;

var body = document.getElementsByTagName("body");

function init(){
    ws = new WebSocket("ws://localhost:8080/ws");
    ws.onopen = function() {

        test();
    };
    ws.onclose = function() {  };
    ws.onmessage = function(evt) { console.log(evt) };
}

function test(){
    setInterval(a, 1000);
}

var i=0;

function a(){
    ws.send(new Date().getTime());
}

