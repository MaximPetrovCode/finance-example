import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {TableComponent} from './table/table.component';
import {ChartComponent} from './chart/chart.component';

const OriginComponent = TableComponent;

const routes: Routes = [
  {
    path: '',
    component: OriginComponent
  },
  {
    path: 'table',
    component: TableComponent,
  },
  {
    path: 'chart',
    component: ChartComponent
  },
  {
    path: '**',
    redirectTo: ''
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
