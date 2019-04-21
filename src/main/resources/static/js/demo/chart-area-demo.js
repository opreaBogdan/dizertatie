google.charts.load('current', {'packages':['corechart']});

let priceData = {data : null};
let smaChartData = {data : null};
let numSeries = {value:1};
let smaSeries = {value:1};

var options = {
  hAxis: {title: 'Year',  titleTextStyle: {color: '#333'},
           slantedText:true, slantedTextAngle:60},
  height:300,
  vAxis: {minValue: 0},
  explorer: {
  axis: 'horizontal',
  keepInBounds: true,
  maxZoomIn: 0.01},
  series: {
      0: {
          visibleInLegend: false
      }
  }
};

function initializeCharts() {
    numSeries.value = 1;
    smaSeries.value = 1;
    priceData.data = new google.visualization.DataTable();
    priceData.data.addColumn('number', 'X');
    priceData.data.addColumn('number', 'Y');

    var chart = new google.visualization.LineChart(document.getElementById('chart_price'));
    chart.draw(priceData.data, options);

    smaChartData.data = new google.visualization.DataTable();
    smaChartData.data.addColumn('number', 'X');
    smaChartData.data.addColumn('number', 'Y');

    var chart = new google.visualization.LineChart(document.getElementById('chart_sma'));
    chart.draw(smaChartData.data, options);
}

function updateChart(newData, previousChartData, X, Y, HTMLelementID, numberOfSeries) {
    var chartData = new google.visualization.DataTable();
    chartData.addColumn('number', X);
    chartData.addColumn('number', Y);
    rows = [];
    for (i = 0; i <= newData.predicted.length; i++) {
        currentElement = [];
        currentElement.push(newData.time[i]);
        currentElement.push(newData.predicted[i]);
        rows.push(currentElement);
    }
    chartData.addRows(rows);

    series = [];
    for (i = 1; i <= numberOfSeries.value; i++)
        series.push(i);

    numberOfSeries.value++;
    joinedData = google.visualization.data.join(previousChartData.data, chartData, 'full', [[0, 0]], series, [1]);
    previousChartData.data = joinedData;
    var chart = new google.visualization.LineChart(document.getElementById(HTMLelementID));
    chart.draw(previousChartData.data, options);
}

window.onload = function predict(){
    updateAllCharts("Apple");
}

function updateAllCharts(companyName) {
    initializeCharts();

    $.ajax({
        type: "POST",
        url: "/predict",
        data: ({'SYM': companyName}),
        success: function(predictedData)
        {
            updateChart(predictedData, priceData, "TIME", "Price", "chart_price", numSeries);
        },
        error: function (xhr, ajaxOptions, thrownError) {
            alert(xhr.status);
            alert(thrownError);
        }
    })

    $.ajax({
        type: "POST",
        url: "/sma",
        data: ({'SYM': companyName, 'period': 20}),
        success: function(smaData)
        {
           updateChart(smaData, smaChartData, "Time", "SMA20", "chart_sma", smaSeries);
        },
        error: function (xhr, ajaxOptions, thrownError) {
            alert(xhr.status);
            alert(thrownError);
        }
    })

     $.ajax({
         type: "POST",
         url: "/sma",
         data: ({'SYM': companyName, 'period': 100}),
         success: function(smaData)
         {
            updateChart(smaData, smaChartData, "Time", "SMA100", "chart_sma", smaSeries);
         },
         error: function (xhr, ajaxOptions, thrownError) {
             alert(xhr.status);
             alert(thrownError);
         }
     })

     $.ajax({
          type: "POST",
          url: "/sma",
          data: ({'SYM': companyName, 'period': 50}),
          success: function(smaData)
          {
             updateChart(smaData, smaChartData, "Time", "SMA50", "chart_sma", smaSeries);
          },
          error: function (xhr, ajaxOptions, thrownError) {
              alert(xhr.status);
              alert(thrownError);
          }
      })

      $.ajax({
           type: "POST",
           url: "/sma",
           data: ({'SYM': companyName, 'period': 10}),
           success: function(smaData)
           {
              updateChart(smaData, smaChartData, "Time", "SMA10", "chart_sma", smaSeries);
           },
           error: function (xhr, ajaxOptions, thrownError) {
               alert(xhr.status);
               alert(thrownError);
           }
       })

       $.ajax({
          type: "POST",
          url: "/sma",
          data: ({'SYM': companyName, 'period': 200}),
          success: function(smaData)
          {
             updateChart(smaData, smaChartData, "Time", "SMA200", "chart_sma", smaSeries);
          },
          error: function (xhr, ajaxOptions, thrownError) {
              alert(xhr.status);
              alert(thrownError);
          }
      })
}