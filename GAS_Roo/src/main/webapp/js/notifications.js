$(function(){
        "use strict";
        var socket = atmosphere;
        var subSocket;
        var transport = 'websocket';

        // We are now ready to cut the request
        var request = { url: "http://localhost:8080/not",
            contentType : "application/json",
         //   trackMessageSize: true,
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
            atmosphere.info(errorMsg);
            if (window.EventSource) {
                request.fallbackTransport = "sse";
                transport = "see";
            }
            console.log('Atmosphere Chat. Default transport is WebSocket, fallback is ' + request.fallbackTransport );
        };

        request.onMessage = function (response) {

            var message = response.responseBody;
            try {
                var json = jQuery.parseJSON(message);
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