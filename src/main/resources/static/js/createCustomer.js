function selectCustomer(value) {
	var linkadd = "/chooseCustomer/" + value;
	window.location.href = linkadd;
}

function changeFreeValue(id, flg) {
	var nowbal = 0;
	if (flg == true) {
		nowbal = window.document.getElementById("nowbal").value;
	}
	var freeAmount = window.document.getElementById(id).value;
	var chargeAmount = window.document.getElementById("accountPayment").value;
	var countAmount = 0;
	var selected;
	if (chargeAmount > 0) {
		if (id == "freeBalanceSe") {
			selected = window.document.getElementById("freetype1").checked;
			if (selected) {
				countAmount = Number(chargeAmount) + Number(freeAmount);
				window.document.getElementById("accountBalance").value = countAmount;
				window.document.getElementById("afterCharge").value = countAmount
						+ Number(nowbal);
			}
		} else {
			selected = window.document.getElementById("freetype2").checked;
			if (selected) {
				countAmount = Number(chargeAmount) + Number(freeAmount);
				window.document.getElementById("accountBalance").value = countAmount;
				window.document.getElementById("afterCharge").value = countAmount
						+ Number(nowbal);
			}
		}
	} else {
		window.document.getElementById("accountBalance").value = nowbal;
		window.document.getElementById("afterCharge").value = nowbal;
	}
}

function changeFreeType(id, flg) {
	var nowbal = 0;
	if (flg == true) {
		nowbal = window.document.getElementById("nowbal").value;
	}
	var freeAmount = 0;
	var chargeAmount = window.document.getElementById("accountPayment").value;

	if (chargeAmount > 0) {
		if (id == "freetype1") {
			freeAmount = window.document.getElementById("freeBalanceSe").value;
		} else {
			freeAmount = window.document.getElementById("freeBalanceIn").value;
		}
		countAmount = Number(chargeAmount) + Number(freeAmount);
		window.document.getElementById("accountBalance").value = countAmount;
		window.document.getElementById("afterCharge").value = countAmount
				+ Number(nowbal);
	} else {
		window.document.getElementById("accountBalance").value = nowbal;
		window.document.getElementById("afterCharge").value = nowbal;
	}
}

function changeChargeValue(flg) {
	var nowbal = 0;
	if (flg == true) {
		nowbal = window.document.getElementById("nowbal").value;
	}
	var chargeAmount = window.document.getElementById("accountPayment").value;

	if (chargeAmount > 0) {
		var selected = window.document.getElementById("freetype1").checked;
		var freeAmount = 0;
		if (selected) {
			freeAmount = window.document.getElementById("freeBalanceSe").value;
		} else {
			freeAmount = window.document.getElementById("freeBalanceIn").value;
		}
		countAmount = Number(chargeAmount) + Number(freeAmount);
		window.document.getElementById("accountBalance").value = countAmount;
		window.document.getElementById("afterCharge").value = Number(countAmount)
				+ Number(nowbal);
	} else {
		window.document.getElementById("accountBalance").value = 0;
		window.document.getElementById("afterCharge").value = nowbal;
	}
}

function alertCharge() {
	var accountPayment=window.document.getElementById("accountPayment").value;
	var accountBalance=window.document.getElementById("accountBalance").value;
	if (accountPayment > 0){
		if (window.confirm('是否充值'+accountPayment+'元,赠送金额为'
				+(Number(accountBalance)-Number(accountPayment))+'元')) {
			return true; 
		} else {
			window.alert('充值取消，未充值。');
			return false;
		}
	}else{
		return true; 
	}
}