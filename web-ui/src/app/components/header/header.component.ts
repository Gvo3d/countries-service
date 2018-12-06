import {Component, ElementRef, OnInit, Renderer2, TemplateRef, ViewChild} from '@angular/core';
import {ApplicationService} from "../../services/application.service";
import {Constants} from "../../utils/constants";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'header',
  templateUrl: './header.component.html'
})

export class HeaderComponent {
  private onLoad;
  form: FormGroup;
  @ViewChild('file') file: ElementRef;

  constructor(public applicationService: ApplicationService) {
    this.onLoad = this.applicationService.data.session;
    this.form = new FormGroup({
      file: new FormControl()
    });
  }

  handleFileInput(event) {
    this.applicationService.data.clearMessage();
    let body = new FormData();
    let file = event.target.files[0];
    body.set(Constants.getFileStatement(), file);
    this.applicationService.rest.doPost(Constants.getFileUploadUrl()+this.applicationService.data.session, body).subscribe(x=> {
      const result :string = x.text();
       console.log("Operation status: "+result);
      this.clearFile();
    })
  }

  clearFile() {
    console.log("Clearing input!");
    this.form.get('file').setValue(null);
    this.file.nativeElement.value = '';
  }
}
