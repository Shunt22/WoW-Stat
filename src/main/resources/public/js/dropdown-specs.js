$(function() {
	
	var watching;
	var vars=getUrlVars();
	
	if(!("bracket" in vars)){
		$(document).find("a[href=\"/talents?bracket=3v3\"]").addClass("active");
	}else{
		switch(vars["bracket"]){
			case "2v2":
				$(document).find("a[href=\"/talents?bracket=2v2\"]").addClass("active");
			break;
			case "3v3":
				$(document).find("a[href=\"/talents?bracket=3v3\"]").addClass("active");
			break;
			case "rbg":
				$(document).find("a[href=\"/talents?bracket=rbg\"]").addClass("active");
			break;
		}
	}
	if(!("class" in vars)){
		watching="<img width=\"20\" height=\"20\" src=\"/images/classes/warrior.jpg\" />&nbsp; Warrior";
	}else{
		if("bracket" in vars){
			var classElement=$(document).find("a[href=\"/talents?bracket="+vars["bracket"]+"&class="+vars["class"]+"\"]");
		}else{
			var classElement=$(document).find("a[href=\"/talents?bracket=3v3&class="+vars["class"]+"\"]");
		}
		clazz=classElement.text();
		clazz=clazz.trim();
	
		var picture=classElement.html();
		var pictureSplit=picture.split("\n");
		watching=pictureSplit[1]+" "+clazz;
	}
	watching+="&nbsp;<span class=\"caret\"></span>";
	
	$("#dropdownMenu1").html(watching);
	$(document).find("div[role=\"tabpanel\"]:eq(1)").addClass("tab-pane active");
	$(document).find("a[role=\"tab\"]:first").parent().addClass("active");

});
function getUrlVars()
{
    var vars = [], hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for(var i = 0; i < hashes.length; i++)
    {	
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
    return vars;
}
