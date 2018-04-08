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

$(function() {
	$("#includedContent").load("/header");
});

$('.datetimepicker').datetimepicker({
	language : 'zh-CN',
	autoclose : true,
	todayHighlight : true
})

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