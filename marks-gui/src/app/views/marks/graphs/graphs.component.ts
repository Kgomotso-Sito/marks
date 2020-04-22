import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-graphs',
  templateUrl: './graphs.component.html'
})
export class GraphsComponent {
  // lineChart
  //Projected line of over other
  public lineChartData: Array<any> = [
    {data: [65, 59, 80, 81, 56, 55, 40, 87, 43, 65, 69, 78, 87, 87], label: 'Maths'},
    {data: [28, 48, 19, 86, 27, 90, 67, 65, 76, 98, 87, 98, 43], label: 'Physics'},
    {data: [28, 48, 40, 19, 86, 27, 90, 45, 87, 45, 49, 89, 27, 54], label: 'Life Sc'},
    {data: [80, 48, 77, 96, 100, 76, 40, 77, 89, 90, 80, 90, 90, 57], label: 'CAT'},
    {data: [77, 89, 90, 80, 90, 90, 57, 19, 86, 27, 90, 67, 65, 76], label: 'LO'},
    {data: [45, 87, 45, 49, 89, 27, 54, 90, 80, 90, 90, 57, 19, 86], label: 'FAL'},
  ];
  public lineChartLabels: Array<any> = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
  public lineChartOptions: any = {
    animation: false,
    responsive: true
  };

  public lineChartLegend = true;
  public lineChartType = 'line';

  // barChart
  public barChartOptions: any = {
    scaleShowVerticalLines: false,
    responsive: true
  };
  public barChartLabels: string[] = ['Math', 'Physics', 'Life Sc.', 'CAT', 'LO', 'FAL', 'SAL'];
  public barChartType = 'bar';
  public barChartLegend = true;

  public barChartData: any[] = [
    {data: [65, 59, 80, 81, 56, 55, 40], label: '2020'},
    {data: [28, 48, 40, 19, 86, 27, 90], label: '2019'},
    {data: [82, 84, 40, 91, 86, 72, 49], label: '2018'},
    {data: [44, 66, 88, 55, 66, 77, 56], label: '2017'}
  ];

  // Radar
  public radarChartLabels: string[] = ['Math', 'Physics', 'Life Sc.', 'CAT', 'LO', 'FAL', 'SAL'];

  public radarChartData: any = [
    {data: [65, 59, 80, 81, 56, 55, 40], label: '2020'},
    {data: [28, 48, 40, 19, 86, 27, 90], label: '2019'},
    {data: [82, 84, 40, 91, 86, 72, 49], label: '2018'},
    {data: [44, 66, 88, 55, 66, 77, 56], label: '2017'}
  ];
  public radarChartType = 'radar';

  // events
  public chartClicked(e: any): void {
    console.log(e);
  }

  public chartHovered(e: any): void {
    console.log(e);
  }

}
