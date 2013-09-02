function loadNotifications()
{
    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }

    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
            if (document.getElementById("notification_area") != null)
                document.getElementById("notification_area").innerHTML=xmlhttp.responseText;
        }
    }
    xmlhttp.open("GET","/notification",true);
    xmlhttp.send();
}

function initNotificationPuller()
{
    window.notification_timer =  window.setInterval(function(){loadNotifications()},1000);
}