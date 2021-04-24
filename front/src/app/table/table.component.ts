import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { formattedDate } from 'src/utils';
import { ValueDTO } from 'src/interfaces';

interface ValueData {
  date: string;
  first_currencyName: string;
  first_course: number;
  second_currencyName: string;
  second_course: number;
  first_isMax: boolean;
  first_isMin: boolean;
  second_isMax: boolean;
  second_isMin: boolean;
}

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss']
})
export class TableComponent implements OnInit {
  private data: ValueDTO[] = [];

  public columns: string[] = ['date', 'first_currencyName', 'first_course', 'second_currencyName', 'second_course'];

  public get values(): ValueData[] {
    const values = this.data.map(elm => elm.course);
    const maxValue = Math.max(...values);
    const minValue = Math.min(...values);

    const timestamps = Array.from(new Set(this.data.map(elm => elm.timestamp)));
    return timestamps.map(timestamp => {
      const [first, second] = this.data.filter(val => val.timestamp === timestamp);
      return {
        date: formattedDate(new Date(timestamp)),
        first_currencyName: first.currencyName,
        first_course: first.course,
        second_currencyName: second.currencyName,
        second_course: second.course,
        first_isMax: first.course === maxValue,
        first_isMin: first.course === minValue,
        second_isMax: second.course === maxValue,
        second_isMin: second.course === minValue
      };
    });
  }

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get<ValueDTO[]>('http://localhost:9090/values', {
      headers: {
        'Access-Control-Allow-Origin': '*'
      }
    }).subscribe(value => {
      this.data = value;
    });
  }
}
