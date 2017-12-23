$(function(){	
	var pathname = window.location.pathname; // Returns path only
	var elem=$(document).find("a[href=\""+pathname+"\"]:first");
	elem.append("<span class=\"sr-only\">(current)</span>");
	elem.parent().addClass("active");
});