import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {DataTableComponent} from "./components/data-table/data-table-component";
import {ApplicationService} from "./services/application.service"
import {HttpModule} from "@angular/http";
import {RestService} from "./services/rest.service";
import {WebSocketService} from "./services/web-socket.services";
import {DataService} from "./services/data.service";
import {HeaderComponent} from "./components/header/header.component";
import {Ng4FilesModule} from 'angular4-files-upload';

@NgModule({
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    Ng4FilesModule,
    ReactiveFormsModule,
    HttpModule,
    NgbModule.forRoot()
  ],
  providers: [
    RestService,
    WebSocketService,
    DataService,
    ApplicationService
  ],
  declarations: [
    DataTableComponent,
    HeaderComponent
  ],
  entryComponents: [],
  bootstrap: [HeaderComponent, DataTableComponent]
})
export class AppModule {
}
