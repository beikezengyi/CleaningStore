/**
 * 
 */
var datatable = $('#datatable > tbody > tr');
var color = Chart.helpers.color;
var labels = [];
var priceCount = [];
var totalPayment = [];

$(document).ready(function() {
	draw();
});

//$('#reDraw').click(function() {
//	var canvas=$('#barcanvas')[0];
//	var ctx = canvas.getContext('2d');
//	ctx.clearRect(0, 0, canvas.width, canvas.height);
//	draw();
//});

function draw() {
	if ($('#barcanvas').length > 0) {
		var ctx = $('#barcanvas')[0].getContext('2d');
		$(datatable).each(function() {
			labels.push($(this)[0].children[0].innerHTML);
			priceCount.push($(this)[0].children[3].innerHTML);
			totalPayment.push($(this)[0].children[4].innerHTML);
		});
		var barChartData = {
			labels : labels,
			datasets : [
					{
						label : $('#datatable > thead > tr')[0].children[3].innerHTML,
						backgroundColor : color(window.chartColors.red).alpha(
								0.5).rgbString(),
						borderColor : window.chartColors.red,
						borderWidth : 1,
						data : priceCount
					},
					{
						label : $('#datatable > thead > tr')[0].children[4].innerHTML,
						backgroundColor : color(window.chartColors.blue).alpha(
								0.5).rgbString(),
						borderColor : window.chartColors.blue,
						borderWidth : 1,
						data : totalPayment
					} ]

		};
		window.myBar = new Chart(ctx, {
			type : 'bar',
			data : barChartData,
			options : {
				responsive : true,
				legend : {
					position : 'top',
				},
				title : {
					display : true,
					text : '订单分析'
				}
			}
		});
	}
}

$('#addDataSet').click(function(){
	var index=$('#addType').val();
	var colorNames = Object.keys(window.chartColors);
	var barChartData=window.myBar.data;
	var colorName = colorNames[barChartData.datasets.length % colorNames.length];
	var dsColor = window.chartColors[colorName];
	var newdata=[];
	$(datatable).each(function() {
		newdata.push($(this)[0].children[index].innerHTML);
	});
	var newDataset = {
		label: $('#datatable > thead > tr')[0].children[index].innerHTML,
		backgroundColor: color(dsColor).alpha(0.5).rgbString(),
		borderColor: dsColor,
		borderWidth: 1,
		data: newdata
	};
	barChartData.datasets.push(newDataset);
	window.myBar.update();
});

$('#analysisOrder').click(function(){
	var startValue=$('#startMonth').val();
	var endValue=$('#endMonth').val();
	if(startValue != '' && endValue != ''){
		if(startValue > endValue){
			$('#msg').text("开始月不能晚于终了月。");
			return false;
		}
	}
	return true;
});

