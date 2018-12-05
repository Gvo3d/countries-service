export class SubscribedMessage {
  constructor() {}
  private _identity: string;
  private _fileName: string;
  private _fileType: string;
  private _fileContents: string;

  get identity(): string {
    return this._identity;
  }

  set identity(value: string) {
    this._identity = value;
  }

  get fileName(): string {
    return this._fileName;
  }

  set fileName(value: string) {
    this._fileName = value;
  }

  get fileType(): string {
    return this._fileType;
  }

  set fileType(value: string) {
    this._fileType = value;
  }

  get fileContents(): string {
    return this._fileContents;
  }

  set fileContents(value: string) {
    this._fileContents = value;
  }
}
