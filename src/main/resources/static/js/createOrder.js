function search() {
	window.location.href = '/allCustomer';
}

function submitOrder() {
	var cuid = window.document.getElementById("customerNumber").value;
	var createform = window.document.getElementById("createform");
	var link = createform.action;
	window.location.href = '/createOrderwithid/' + cuid;
}

function goOrderDetails(ordernumber) {
	var linkadd = "/orderDetails?ordernumber=" + ordernumber;
	window.location.href = linkadd;
}

$(document).ready(function() {
	checkCutomer();
});

$('#customerName').change(function() {
	checkCutomer();
});

function checkCutomer() {
	var name = $('#customerName').val();
	if (name != '') {
		$.ajax({
			url : "/checkCustomer?name=" + name,
			dataType : "json",
			method : "post",
			success : function(response) {
				success(name, response);
			},
			error : function(response) {
				success(name, response);
			}
		})
	} else {
		$('#customerNameMsg').text("请点击放大镜查询会员名或输入会员名。");
		seterror();
	}
}

function success(name, response) {
	if (response == '0') {
		$('#customerNameMsg').text(name + "还不是会员，请先为他注册。");
		seterror(name);
	} else {
		$('#customerNameMsg').attr("class", "successText");
		$('#customerNameMsg').text(name + "的会员号为：" + response);
		$('#customerNumber').val(response);
	}
}

function seterror(name) {
	$('#customerNameMsg').attr("class", "failText");
	$('#customerNumber').val('');
	$('#customerName').focus();
}