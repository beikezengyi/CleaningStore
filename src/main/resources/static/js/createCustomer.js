function selectCustomer(value) {
	var linkadd = "/chooseCustomer/" + value;
	window.location.href = linkadd;
}

function changeFreeValue(id) {
	var freeAmount = window.document.getElementById(id).value;
	var chargeAmount = window.document.getElementById("accountPayment").value;
	var countAmount = 0;
	var selected;
	if (chargeAmount > 0) {
		if (id == "freeBalanceSe") {
			selected = window.document.getElementById("selectFree").checked;
			if (selected) {
				countAmount = Number(chargeAmount) + Number(freeAmount);
				window.document.getElementById("accountBalance").value = countAmount;
				window.document.getElementById("afterCharge").value = countAmount;
			}
		} else {
			selected = window.document.getElementById("inputFree").checked;
			if (selected) {
				countAmount = Number(chargeAmount) + Number(freeAmount);
				window.document.getElementById("accountBalance").value = countAmount;
				window.document.getElementById("afterCharge").value = countAmount;
			}
		}
	} else {
		window.document.getElementById("accountBalance").value = 0;
		window.document.getElementById("afterCharge").value = 0;
	}
}

function changeFreeType(id) {
	var freeAmount = 0;
	var chargeAmount = window.document.getElementById("accountPayment").value;

	if (chargeAmount > 0) {
		if (id == "selectFree") {
			freeAmount = window.document.getElementById("freeBalanceSe").value;
		} else {
			freeAmount = window.document.getElementById("freeBalanceIn").value;
		}
		countAmount = Number(chargeAmount) + Number(freeAmount);
		window.document.getElementById("accountBalance").value = countAmount;
		window.document.getElementById("afterCharge").value = countAmount;
	} else {
		window.document.getElementById("accountBalance").value = 0;
		window.document.getElementById("afterCharge").value = 0;
	}
}

function changeChargeValue() {
	var chargeAmount = window.document.getElementById("accountPayment").value;

	if (chargeAmount > 0) {
		var selected = window.document.getElementById("selectFree").checked;
		var freeAmount = 0;
		if (selected) {
			freeAmount = window.document.getElementById("freeBalanceSe").value;
		} else {
			freeAmount = window.document.getElementById("freeBalanceIn").value;
		}
		countAmount = Number(chargeAmount) + Number(freeAmount);
		window.document.getElementById("accountBalance").value = countAmount;
		window.document.getElementById("afterCharge").value = countAmount;
	} else {
		window.document.getElementById("accountBalance").value = 0;
		window.document.getElementById("afterCharge").value = 0;
	}

}