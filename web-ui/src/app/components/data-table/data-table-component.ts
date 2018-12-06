import {Component, EventEmitter, Input, OnChanges, OnDestroy, OnInit, Output, SimpleChanges} from '@angular/core';
import {Subscription} from "rxjs/Subscription";
import {Response} from "../../model/response-dto";
import {Observable} from "rxjs/Observable";
import {ApplicationService} from "../../services/application.service";
import {filter, map} from "rxjs/operators";

@Component({
  selector: 'data',
  templateUrl: './data-table.component.html'
})
export class DataTableComponent implements OnInit, OnDestroy {

  @Input()
  private responseObs: Observable<Response>;
  private responseSubscription: Subscription;
  private isEmpty: boolean;
  responses: Response[];

  constructor(private applicationService: ApplicationService) {
    this.isEmpty = true;
    this.responses = [];
    //this.initWsContext();
  }

  // initWsContext(){
  //   this.applicationService.ws.messageReceived.pipe(filter((response) => {
  //     console.log("response from server: "+JSON.stringify(response));
  //     return true;
  //     //return response.countryCode !=null;
  //   }),map((response) => {
  //     this.responses.push(response);
  //     //return response.data;
  //   }));
  // }

  ngOnDestroy(): void {
    this.responseSubscription.unsubscribe();
    this.applicationService.ws.destroy();
  }

  ngOnInit(): void {
    //this.applicationService.ws.init();
    this.applicationService.data.getMessage().subscribe((value: Response) => {
      console.log("value passed from the data service: "+JSON.stringify(value));
      this.isEmpty = false;
      this.responses.push(value);
    });
  }


  //
  // ngOnChanges(changes: SimpleChanges): void {
  //   this.applicationService.ws.getMessageSourceAsObservable().subscribe((value: Response) => {
  //     this.isEmpty = false;
  //     console.log("data - on changes");
  //     this.responses.push(value);
  //   })
  // }
}
