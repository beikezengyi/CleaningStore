function order() {
	window.location.href='/order';
}

function createOrder(){
	window.location.href='/createOrder';
}


function welcome(){
	window.location.href='/';
}

function createCustomer(){
	window.location.href='/createCustomer';
}

function selectCustomer(){
	window.location.href='/selectCustomer';
}

$('#updateWashWay').click(function(){
	window.location.href='/washWaySetting';
});
$('#createWashWay').click(function(){
	window.location.href='/createWashWaySetting';
});
$('#updateThing').click(function(){
	window.location.href='/thingSetting';
});
$('#createThing').click(function(){
	window.location.href='/createThingSetting';
});
$('#analysisOrder').click(function(){
	window.location.href='/analysisOrder';
});
$('#analysisMoney').click(function(){
	window.location.href='/payment';
});



function changeColor(id, selectValue) {
	var index = id.substring(11, 12);

	var divid = "div" + index;
	if (selectValue == "t") {
		$("#" + divid).attr("style", "background-color: #d7dde5;");
	} else {
		$("#" + divid).attr("style", "background-color: #FFF;");
	}
}

$(document).ready(function() {
    $('.readonly').find('input, textarea, select').attr('readonly', 'readonly');
});



