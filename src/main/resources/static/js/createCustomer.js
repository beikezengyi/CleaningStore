function parse(str) {
	return parseInt(str) || 0;
}

$('#accountPayment').change(function() {
	var accountPayment = parse($(this).val());
	var accountBalance = parse($('#accountBalance').val());
	var freetype = $("input[id*='freetype']").filter(":checked");
	var giveAmount = 0;
	if (freetype.length == 1) {
		giveAmount = parse($(freetype).next().val());
	}
	setCommonFunction(accountPayment, accountBalance, giveAmount);
});

$("[id*='freetype']").change(function() {
	var id = $(this).attr('id');
	if (id.replace("freetype", "") == '1') {
		$('#freetype2').next().attr('readonly', 'readonly');
		$('#freetype1').next().attr('disabled', false);
	} else {
		$('#freetype2').next().attr('readonly', false);
		$('#freetype1').next().attr('disabled', 'disabled');
	}
	var accountPayment = parse($('#accountPayment').val());
	var accountBalance = parse($('#accountBalance').val());
	var giveAmount = parse($(this).next().val());
	setCommonFunction(accountPayment, accountBalance, giveAmount);
});

function setCommonFunction(accountPayment, accountBalance, giveAmount) {
	if (accountPayment > 0) {
		var thistime = giveAmount + accountPayment;
		$('#getthistime').val(thistime);
		$('#afterCharge').val(thistime + accountBalance);
	} else {
		$('#getthistime').val('0');
		$('#afterCharge').val(accountBalance);
	}
}

$("[id*='freeBalance']").change(function() {
	var freetype = $("input[id*='freetype']").filter(":checked");
	var giveAmount = 0;
	if (freetype.length == 1) {
		giveAmount = parse($(this).val());
	}
	var accountPayment = parse($('#accountPayment').val());
	var accountBalance = parse($('#accountBalance').val());
	setCommonFunction(accountPayment, accountBalance, giveAmount);
});

$('#alertCharge').click(
		function() {
			var accountPayment = parse($('#accountPayment').val());
			if (accountPayment > 0) {
				var freetype = $("input[id*='freetype']").filter(":checked");
				var giveAmount = 0;
				if (freetype.length == 1) {
					giveAmount = parse($(freetype).next().val());
				}
				if (window.confirm('是否充值' + accountPayment + '元,赠送金额为'
						+ giveAmount + '元')) {
					if (giveAmount >= accountPayment) {
						if (window.confirm("警告:赠送金额大于或等于充值额，是否继续?")) {
							return true;
						} else {
							window.alert('充值已取消，顾客信息未提交。');
							return false;
						}
					}
					return true;
				} else {
					window.alert('充值已取消，顾客信息未提交。');
					return false;
				}
			} else {
				// 仅做修改信息不充值
				return true;
			}
		});

function selectCustomer(value) {
	var linkadd = "/chooseCustomer/" + value;
	window.location.href = linkadd;
}