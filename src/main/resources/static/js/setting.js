/**
 * 
 */
$("input[id*='thingName']").change(function() {
	var changevalue = $(this).val();
	var id = $(this).attr('id');
	var index = id.split(".")[0].replace("thlist", "");
	if (changevalue != '') {
		$.ajax({
			url : "/checkThingSetting?thing=" + changevalue,
			method : "post",
			dataType : "json",
			success : function(res) {
				checkWashway(res, index);
			},
			error : function(res) {
				checkWashway(res, index);
			}
		})
	} else {
		clearErrorMsg(index);
	}
});

$("input[id*='washWayName']").change(function() {
	var changevalue = $(this).val();
	var id = $(this).attr('id');
	var index = id.split(".")[0].replace("wylist", "");
	if (changevalue != '') {
		$.ajax({
			url : "/checkWashWaySetting?washway=" + changevalue,
			method : "post",
			dataType : "json",
			success : function(res) {
				checkWashway(res, index);
			},
			error : function(res) {
				checkWashway(res, index);
			}
		})
	} else {
		clearErrorMsg(index);
	}
});

function checkWashway(res, index) {
	var msg = $('#msg' + index);
	if (res == null || res == '' 
		|| res == 0 || res.responseText == '') {
		
		$(msg).text("OK");
		$(msg).attr("class", "successText");
	} else {
		$(msg).text("已存在");
		$(msg).attr("class", "failText");
	}
}

function clearErrorMsg(index) {
	var msg = $('#msg' + index);
	$(msg).text("");
}
