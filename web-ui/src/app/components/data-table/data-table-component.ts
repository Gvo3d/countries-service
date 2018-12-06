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

  @Input()
  private responseObs: Observable<Response>;
  private responseSubscription: Subscription;
  isEmpty: boolean;
  responses: Response[];

  constructor(private applicationService: ApplicationService) {
    this.isEmpty = true;
    this.responses = [];
  }

  ngOnDestroy(): void {
    this.responseSubscription.unsubscribe();
    this.applicationService.ws.destroy();
  }

  ngOnInit(): void {
    this.applicationService.data.getMessage().subscribe((value: Response) => {
      if (typeof value == 'undefined'){
        this.responses = [];
        this.isEmpty = true;
      } else {
        console.log("value passed from the data service: " + JSON.stringify(value));
        this.isEmpty = false;
        this.responses.push(value);
      }
    });
  }
}
