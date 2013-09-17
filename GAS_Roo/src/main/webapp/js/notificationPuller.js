function loadNotifications()
{
    // se non sono in una pagina dove servono le modifiche
    if ($("notification_area").length == 0 && window.notificationInterval != null)
    {
        window.clearInterval(window.notificationInterval);
        window.notificationInterval = null;
    }

    // pull
    $("#notification_area").load("/notification");

    // se non ho timer, lo setto
    if (window.notificationInterval == null)
        window.notificationInterval = window.setInterval(function(){loadNotifications()}, 5000);
}