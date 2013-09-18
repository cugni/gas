$(function(){
        "use strict";
        var socket = atmosphere;
        var subSocket;
        var transport = 'websocket';
        var not_area=$('#notification_area');
        if($(not_area).length==0)return;

        // We are now ready to cut the request
        var request = { url:window.baseURL+"ws/"+window.userId,
            contentType : "application/json",
            trackMessageSize: true,
            trackMessageLength: true,
            logLevel:'debug',
            shared : true,
            transport : transport ,
            fallbackTransport: 'long-polling'};

        request.onOpen = function(response) {
            console.log('Atmosphere connected using ' + response.transport);
            transport = response.transport;
            /*if (response.transport == "local") {
                subSocket.pushLocal("Name?");
            } */
        };

        request.onTransportFailure = function(errorMsg, request) {
            console.log(errorMsg);
            if (window.EventSource) {
                request.fallbackTransport = "sse";
                transport = "see";
            }
            console.log('Atmosphere Chat. Default transport is WebSocket, fallback is ' + request.fallbackTransport );
        };

        request.parseMessage=function(json){
            var url= window.baseURL+"notification/"
            if(json.type=="NEW_USER") {
                $(not_area).prepend("<p class='not_"+json.type+"'><a href='"+url+json.id+"'><b>"+json.type+"</b>: "+json.user.username+"</a></p>") ;
            } else if (json.type=="NEW_PRODUCT") {
                $(not_area).prepend("<p class='not_"+json.type+"'><a href='"+url+json.id+"'><b>"+json.type+"</b>: "+json.product.name+"</a></p>") ;

            } else if (json.type=="NEW_PROPOSAL") {
                $(not_area).prepend("<p class='not_"+json.type+"'><a href='"+url+json.id+"'><b>"+json.type+"</b>: "+json.proposal.product.name+"</a></p>") ;

            }  else if (json.type=="NEW_MESSAGE") {
                $(not_area).prepend("<p class='not_"+json.type+"'><a href='"+url+json.id+"'><b>"+json.type+"</b>: "+json.message.product.name+"</a></p>") ;

            }else {
                $(not_area).prepend("<p class='not_gen not_"+json.type+"'><a href='"+url+json.id+"'><b>"+json.type+"</b></a></p>") ;

            }
        }
    $.ajax(window.baseURL+"notification/lasts").done(function(msg){
        var m=$.parseJSON(msg);
        for(var i=0;i< m.length;i++){
            request.parseMessage(m[i]);
        }


    });
        request.onMessage = function (response) {

            var message = response.responseBody;
           // $(not_area).append("<p>",message);

            try {
                var json = jQuery.parseJSON(message);
                request.parseMessage(json);
               console.log(json);
            } catch (e) {
                console.log(e);
                console.log('This doesn\'t look like a valid JSON: ', message.data);
                return;
            }


           console.log(message);
        };

        request.onClose = function(response) {
            console.log("closed");
        }       ;

        subSocket = socket.subscribe(request);
//        input.keydown(function(e) {
//            if (e.keyCode === 13) {
//                var msg = $(this).val();
//                if (author == null) {
//                    author = msg;
//                }
//
//                subSocket.push(jQuery.stringifyJSON({ author: author, message: msg }));
//                $(this).val('');
//
//                input.attr('disabled', 'disabled');
//                if (myName === false) {
//                    myName = msg;
//                }
//            }
//        });
//
//        function addMessage(author, message, color, datetime) {
//            content.append('<p><span style="color:' + color + '">' + author + '</span> @ ' +
//                + (datetime.getHours() < 10 ? '0' + datetime.getHours() : datetime.getHours()) + ':'
//                + (datetime.getMinutes() < 10 ? '0' + datetime.getMinutes() : datetime.getMinutes())
//                + ': ' + message + '</p>');
//        }
//    });
});