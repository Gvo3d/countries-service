import {Component, EventEmitter, Input, OnChanges, OnDestroy, OnInit, Output, SimpleChanges} from '@angular/core';
import {Subscription} from "rxjs/Subscription";
import {Response} from "../../model/response-dto";
import {Observable} from "rxjs/Observable";
import {ApplicationService} from "../../services/application.service";

@Component({
  selector: 'data',
  templateUrl: './data-table.component.html'
})
export class DataTableComponent implements OnInit, OnDestroy {

  private responseSubscription: Subscription;
  private isEmpty: boolean;
  responses: Response[];

  constructor(private applicationService: ApplicationService) {
    this.isEmpty = true;
  }

  ngOnDestroy(): void {
    this.responseSubscription.unsubscribe();
    this.applicationService.ws.ngOnDestroy();
  }

  ngOnInit(): void {
    this.applicationService.ws.init();
  }
}
