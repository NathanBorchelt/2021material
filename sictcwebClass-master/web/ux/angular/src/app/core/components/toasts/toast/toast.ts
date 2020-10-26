
export enum ToastStyle {
  GENERAL, SUCCESS, ERROR, WARNING
}

export enum ToastTimeout {
  LONG = 16000, SHORT = 6000
}

export enum ToastColor {

}

export interface ToastOptions {
  title?: string;
  body: string;
  style?: ToastStyle;
  timeout?: number;
  actions?: ToastAction[];
  onShow?: () => void;
  onDismiss?: () => void;
}

export interface ToastAction {
  label: string;
  callback: () => void;
}

export class Toast {

  // Define the length of time in milliseconds a toasts can exist
  // TODO: Make these part of some sort of object
  TIMEOUT_LONG = 16000;
  TIMEOUT_SHORT = 6000;

  // Generate a random 5 digit identifier to simplify identification
    id = (Math.floor(Math.random() * 90000) + 10000);

    title: string;
    body: string;

    style: ToastStyle;
    timeout: number;

    actions: ToastAction[];

    // Callbacks that can be utilized when the ToastService changes the toasts's visibility
    onShow: () => void;
    onDismiss: () => void;

    constructor(toastOptions: ToastOptions) {
      Object.freeze(this.id);
      this.title = toastOptions.title;
      this.body = toastOptions.body;
      this.style = toastOptions.style;
      this.timeout = toastOptions.timeout;
      this.actions = toastOptions.actions;
      this.onShow = toastOptions.onShow;
      this.onDismiss = toastOptions.onDismiss;
    }
}
