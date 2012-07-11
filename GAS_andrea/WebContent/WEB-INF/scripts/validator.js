function stringMinMax(obj, min, max)
{
	var txt = obj.value;
	if (txt.length < min || txt.length > max)
		obj.style.background = "#ffff00";
	else
		obj.style.background = "#ffffff";
}

function numberMinMax(obj, min, max)
{
	var num = obj.value;
	if (isNaN(num) || num < min || num > max)
		obj.style.background = "#ffff00";
	else
		obj.style.background = "#ffffff";
}