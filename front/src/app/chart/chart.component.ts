import {Component, OnInit} from '@angular/core';
import * as Chart from 'chart.js';
import { ChartDataSets, ChartOptions, ChartType } from 'chart.js';
import {Label} from 'ng2-charts';
import {HttpClient} from '@angular/common/http';
import { formattedDate } from 'src/utils';
import { ValueDTO } from 'src/interfaces';

const defaultOptions: ChartOptions = {
  responsive: true,
  legend: {
    labels: {
      fontColor: 'white'
    }
  },
  scales: {
    yAxes: [{
      ticks: {
        beginAtZero: true,
        fontColor: ['white']
      }
    }],
    xAxes: [{
      ticks: {
        fontColor: ['white']
      }
    }]
  }
};

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.scss']
})
export class ChartComponent implements OnInit {
  constructor(private http: HttpClient) { }

  public values: ValueDTO[] = [];

  public options: ChartOptions = defaultOptions;

  public chartType: ChartType = 'bar';
  public legend = true;
  public plugins = [];
  public datasets: ChartDataSets[] = [];
  public labels: Label[] = [];

  private setLabels(): void {
    this.labels = Array.from(new Set(this.values.map(elm => formattedDate(new Date(elm.timestamp)))));
  }

  private setData(): void {
    this.datasets = [
      { data: this.values.filter(elm => elm.currencyName.toUpperCase() === 'USD').map(val => val.course), label: 'USD' },
      { data: this.values.filter(elm => elm.currencyName.toUpperCase() === 'EUR').map(val => val.course), label: 'EUR' }
    ];
  }

  public fetch(): void {
    this.http.get<ValueDTO[]>('http://localhost:9090/values', {
      headers: {
        'Access-Control-Allow-Origin': '*'
      }
    })
      .subscribe(value => {
        this.values = value;
      })
      .add(() => {
        this.setLabels();
        this.setData();
      });
  }

  ngOnInit(): void {
    this.fetch();
  }

}
