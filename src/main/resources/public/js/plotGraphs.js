$(function() {
	// Tooltip for hovering plot
	$("<div id='tooltip'></div>").css({
		position: "absolute",
		display: "none",
		border: "1px solid #fdd",
		padding: "2px",
		"background-color": "#fee",
		"pointer-events": "none",
		opacity: 0.80
	}).appendTo("body"); 


	
	
	specGraph("2v2");
	raceGraph("2v2");
	

	$("#specsTabs li a").click(function (e) {
		//e.preventDefault()
		//$(this).tab('show')
		specGraph($(this).text().trim());
	});
	$("#racesTabs li a").click(function (e) {
		//e.preventDefault()
		//$(this).tab('show')
		raceGraph($(this).text().trim());
	
	});
	
	$(document).find("div[role=\"tabpanel\"]:eq(1)").addClass("tab-pane active");
	$(document).find("a[role=\"tab\"]:first").parent().addClass("active");
	
	$(document).find("a[href=\"#2v2r\"]:first").parent().addClass("active");
	$(document).find("div[id=\"2v2r\"]").addClass("tab-pane active");
	
});
function raceGraph(url){
	var dataSets={}; // Map for graph data
	
	var plotId;
	switch(url){
		case "2v2":
			plotId="#race-placeholder2v2";
			break;
		case "3v3":
			plotId="#race-placeholder3v3";
			break;
		case "rbg":
			plotId="#race-placeholderrbg";
			break;
	}
	$.getJSON("/racesRest?bracket="+url)
	.done(function(data) {
		$.each( data, function( index, value ) {
			dataSets[value.name]={
				data: [["<img src="+value.url+" width=\"25\" height=\"25\">",value.percent]],
				label: value.name,
				amount: value.amount
			}
			if(value.faction=="Alliance"){
				dataSets[value.name].faction="Alliance";
				dataSets[value.name].color="blue";
			}else{
				dataSets[value.name].faction="Horde";
				dataSets[value.name].color="red";
			}

		});	
		
		// insert checkboxes 
		var choiceContainer = $("p.racesChoices");
		
		choiceContainer.find("input").click(plotAccordingToChoices);
		
		plotAccordingToChoices();
		
		function plotAccordingToChoices() {
			
			var data = [];
			choiceContainer.find("input:checked").each(function () {
				var key = $(this).attr("name");
				$.each(dataSets, function(key1,val1){
					if(dataSets[key1].faction==key){
						data.push(dataSets[key1]);
					}
				});
			});
			$.plot(plotId,  data , {
				series: {
					bars: {
						show: true,
						barWidth: 0.9,
						align: "center"
					}
				},
				xaxis: {
					mode: "categories",
					tickLength: 0
				},
				yaxis: {
					ticks: 10
				},
				grid: {
					hoverable: true
				},
				legend: {
					show: false
				}
			});
			$(plotId).bind("plothover", function (event, pos, item) {
					if (item) {
						var y = item.series.data[item.dataIndex][1];
						var x = item.series.label;
						$("#tooltip").html("<b>"+x+"</b> is <b>"+y+"%</b> ("+item.series.amount+" players)")
							.css({top: item.pageY+5, left: item.pageX+5})
							.fadeIn(200);
					} else {
						$("#tooltip").hide();
					}
				
			});
		} // end of plotAccordingToChoices funct
	}).fail(function() {
		$("#race-placeholder").text("Some Error Happened... Try to refresh this page");
	}); // End of Json Done
}
function specGraph(url){
	var dataSets={}; //main data Object
	
	var plotId;
	switch(url){
		case "2v2":
			plotId="#spec-placeholder2v2";
			break;
		case "3v3":
			plotId="#spec-placeholder3v3";
			break;
		case "rbg":
			plotId="#spec-placeholderrbg";
			break;
	}
	$.getJSON("/specsRest?bracket="+url)
	.done(function(data) {
		
		$.each( data, function(index,value) {
			dataSets[value.name]={
				label: value.name,
				data: [["<img src="+value.url+" width=\"20\" height=\"20\">",value.percent]],
				amount: value.amount,
				color: value.color
			}
		});	

		var choiceContainer = $("p.specsChoices");
		choiceContainer.find("input").click(plotAccordingToChoices);
		plotAccordingToChoices();
		
		var turnOnButton = $(".turnOn");
		turnOnButton.click(turnOn);
		var turnOffButton = $(".turnOff");
		turnOffButton.click(turnOff);
		
		function turnOff(){
			choiceContainer.find(':checkbox').each(function (){
				$(this).prop("checked", false);
			});
			plotAccordingToChoices();
		}
		function turnOn(){
			choiceContainer.find(':checkbox').each(function (){
				$(this).prop("checked", true);
			});
			plotAccordingToChoices();
		}
		
		function plotAccordingToChoices() {
		
			
			var data = [];
			choiceContainer.find("input:checked").each(function () {
				var key = $(this).attr("name");

				$.each( dataSets, function( key1, val1 ) {
				    var lastIndex=key1.lastIndexOf("-");
					var arr = key1.substr(0,lastIndex-1);
					if(arr==key){
						data.push(dataSets[key1])
					}
				});	
			});

			$.plot(plotId,  data , {
				series: {
					bars: {
						show: true,
						barWidth: 0.3,
						align: "center",
						fill: 1
					}
				},
				xaxis: {
					mode: "categories",
					tickLength: 0
				},
				yaxis: {
					ticks: 10
				},
				grid: {
					hoverable: true
				},
				legend: {
					show:false
				}
				
			});
			$(plotId).bind("plothover", function (event, pos, item) {
			
					if (item) {
						var y = item.series.data[item.dataIndex][1];
						var x = item.series.label;
						$("#tooltip").html("<b>"+x+"</b> is <b>"+y+"%</b> ("+item.series.amount+" players)")
							.css({top: item.pageY+5, left: item.pageX+5})
							.fadeIn(200);
					} else {
						$("#tooltip").hide();
					}
				
			});
			
		} // end of funct
				
	
	}).fail(function() {
		$("#spec-placeholder").text("Some Error Happened... Try to refresh this page");
	});
}