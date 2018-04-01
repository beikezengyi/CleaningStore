function search() {
	window.location.href = '/allCustomer';
}

function submitOrder() {
	var cuid = window.document.getElementById("customerNumber").value;
	var createform = window.document.getElementById("createform");
	var link = createform.action;
	window.location.href = '/createOrderwithid/' + cuid;
}

function goOrderDetails(ordernumber){
	var linkadd="/orderDetails?ordernumber="+ordernumber;
	window.location.href=linkadd;
}