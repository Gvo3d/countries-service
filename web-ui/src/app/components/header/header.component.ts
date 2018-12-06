import {Component, ElementRef, OnInit, Renderer2, TemplateRef, ViewChild} from '@angular/core';
import {ApplicationService} from "../../services/application.service";
import {Ng4FilesStatus, Ng4FilesSelected} from 'angular4-files-upload';
import {Http, Response, Headers, RequestOptions} from '@angular/http';
import {Constants} from "../../utils/constants";
import {SubscribedMessage} from "../../model/socket-message-dto";
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
    //this.applicationService.data.clearMessage();
    let body = new FormData();
    let file = event.target.files[0];
    body.set(Constants.getFileStatement(), file);
    this.applicationService.rest.doPost(Constants.getFileUploadUrl()+this.applicationService.data.session, body).subscribe(x=> {
      const result :string = x.text();
       console.log("Operation status: "+result);
      this.clearFile();
    })
  }

  // private processFile(event: any): FormData {
  //   let files = event.target.files;
  //   let fData: FormData = new FormData;
  //
  //   for (var i = 0; i < files.length; i++) {
  //     fData.append("file[]", files[i]);
  //   }
  //   var _data = {
  //     filename: 'Sample File',
  //     id: '0001'
  //   };
  //
  //   fData.append("data", JSON.stringify(_data));
  //   return fData;
  // }

  private processFile(event: any): SubscribedMessage {
    let files = event.target.files;

    const message: SubscribedMessage = new SubscribedMessage;
    if(event.target.files && event.target.files.length > 0) {
      let reader = new FileReader();
          let file = event.target.files[0];
          reader.readAsDataURL(file);
          reader.onload = () => {
            this.form.get('file').setValue({
              filename: file.name,
              filetype: file.type,
              value: reader.result.split(',')[1]
            })
          };
        }

    return message;
  }

  // private readFile(event){
  //   let reader = new FileReader();
  //   if(event.target.files && event.target.files.length > 0) {
  //     let file = event.target.files[0];
  //     reader.readAsDataURL(file);
  //     reader.onload = () => {
  //       this.form.get('avatar').setValue({
  //         filename: file.name,
  //         filetype: file.type,
  //         value: reader.result.split(',')[1]
  //       })
  //     };
  //   }
  // }

  clearFile() {
    console.log("Clearing input!");
    this.form.get('file').setValue(null);
    this.file.nativeElement.value = '';
  }
}
