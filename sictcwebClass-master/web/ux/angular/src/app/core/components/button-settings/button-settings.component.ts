import { Component, EventEmitter, Input, OnInit, Output, ɵConsole } from '@angular/core';
import { Observable } from 'rxjs';
import { ToastService } from '../toasts/toast.service';
import { Toast, ToastStyle, ToastTimeout } from '../toasts/toast/toast';

enum ResultIcon {
  Success = 'fas fa-check-circle',
  Failure = 'fas fa-exclamation-circle'
}

@Component({
  selector: 'app-button-settings',
  templateUrl: './button-settings.component.html',
  styleUrls: ['./button-settings.component.scss']
})
export class ButtonSettingsComponent implements OnInit {

  // None, Primary and Danger, add these in the SCSS file
  @Input()
  style = '';

  @Input()
  disabled = false;

  @Input()
  disableFinishLoadingAnimation = false;

  @Input()
  type = 'button';

  isShowingLoadingAnimation = false;
  isShowingFinishLoadingAnimation = false;
  finishLoadingAnimationTimeout = 2000;

  @Output()
  onClick: EventEmitter<null> = new EventEmitter<null>();

  @Output()
  onLoadingCompleted: EventEmitter<null> = new EventEmitter<null>();

  @Output()
  onAnimationCompleted: EventEmitter<null> = new EventEmitter<null>();

  resultIcon: ResultIcon;

  constructor() { }

  ngOnInit() {}

  watchObservable(watch: Observable<any>) {
    this.startLoading();
    watch.subscribe((success: any) => {
      this.resultIcon = ResultIcon.Success;
      this.stopLoading();
    }, (failure: any) => {
      this.resultIcon = ResultIcon.Failure;
      this.stopLoading();
    });
  }

  private startLoading() {
    this.isShowingLoadingAnimation = true;
  }

  private stopLoading() {
    if (this.disableFinishLoadingAnimation) {
      this.isShowingLoadingAnimation = false;
      this.onLoadingCompleted.emit();
      this.isShowingFinishLoadingAnimation = false;
      this.onAnimationCompleted.emit();
      return;
    }
    this.isShowingLoadingAnimation = false;
    this.onLoadingCompleted.emit();
    setTimeout(() => {
      this.isShowingFinishLoadingAnimation = false;
      this.onAnimationCompleted.emit();
    }, this.finishLoadingAnimationTimeout);
    this.isShowingFinishLoadingAnimation = true;
  }

  click() {
    if (this.isShowingLoadingAnimation || this.isShowingFinishLoadingAnimation) return;
    this.onClick.emit();
  }
}
