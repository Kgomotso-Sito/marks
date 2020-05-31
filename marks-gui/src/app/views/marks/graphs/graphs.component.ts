import { Component, OnInit } from '@angular/core';
import {FormBuilder} from "@angular/forms";
import {GraphsService} from "./graphs.service";

@Component({
  selector: 'app-graphs',
  templateUrl: './graphs.component.html'
})
export class GraphsComponent implements OnInit{

  // lineChart
  //Projected line of over other
  public lineChartData : Array<any>;
  private isDataAvailable:boolean=false;

  public lineChartLabels: Array<any> = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
  public lineChartOptions: any = {
    animation: false,
    responsive: true
  };

  public lineChartLegend = true;
  public lineChartType = 'line';


  constructor(private graphsService: GraphsService) { }

  ngOnInit(): void {
    this.getSubjects();
  }

  getSubjects() {
    this.graphsService.getMarks().subscribe(
        data => {
          this.lineChartData = data;
          this.isDataAvailable=true;

        },
        err => console.error(err),
        () => console.log('Done getting marks')
    );
  }

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
