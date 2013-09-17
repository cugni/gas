function chatReload(prop)
{

    // se non sono in una pagina dove vi e' la chat
    if ($("chat_area") == null ){
        return;
    }


    // pull
    $("#chat_area").load("/messages?proposal="+prop);

    // se non ho timer, lo setto
    if (window.chatInterval == null)
        window.chatInterval = window.setInterval(function(){chatReload(prop)}, 5000);

}
