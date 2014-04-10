var ServerObject = function(){
    var instance =  {

        init: function(){
            this.requestMap = {};
            var self = this;
            var ws = new WebSocket("ws://localhost:8080/ws");
            this.ws = ws;
            ws.onopen = this.onOpen;
            ws.onclose = function() {};
            ws.onmessage = function(a){
                self.onMessage(a);
            };
        },

        onOpen: function(){
            //
        },

        onMessage: function(a){
            var received = JSON.parse(a.data);
            if(received.methods){
                this.defineMethods(received.methods);
            }
            if(received.response){
                this.elaborateResponse(received.response);
            }
        },

        elaborateResponse: function(response){
            var cb = this.requestMap[response.id-0];

            if(cb === "waitReturn"){
                this.requestMap[response.id-0] = {result: response.result}
            } else {
                delete this.requestMap[response.id-0];
                cb(response.result);
            }
        },

        defineMethods: function(listMethods){
            for(var i=0; i< listMethods.length; i++){
                var methodName = listMethods[i];


                this[methodName] = function(){

                    var args = [];
                    var cb;

                    for(var t=0; t< arguments.length; t++){
                        if(arguments[t] instanceof Function){
                            cb = arguments[t];
                        } else {
                            args[t] = arguments[t];
                        }
                    }

                    var id = new Date().getTime();
                    var obj = {
                        args: args,
                        id: id,
                        method: methodName
                    };
                    var str = JSON.stringify(obj);
                    this.requestMap[id] = cb? cb : "waitReturn";
                    this.ws.send(str);


                    if(!cb){

                        while (!this.requestMap[id].result){}

                        return this.requestMap[id].result;
                    }
                }
            }
        }
    };
    instance.init();
    return instance;
};
