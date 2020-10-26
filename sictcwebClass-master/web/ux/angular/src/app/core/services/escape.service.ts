import { Injectable } from '@angular/core';


export class ESCHandler {
  id: number;
  willExecute: () => boolean;
  onESC: () => void;
}

@Injectable({providedIn: 'root'})
export class EscapeService {

  handlers: ESCHandler[] = []; // FILO

  constructor() {
    window.addEventListener('keydown', (event: KeyboardEvent) => {
      if (event.code === 'Escape') {
        this.remove(this.handlers[this.handlers.length - 1]);
      }
    });
  }

  create(onEsc: () => void, willExecute: () => boolean): ESCHandler {
    const _escHandler = new ESCHandler();
    _escHandler.id = this.handlers.length;
    _escHandler.onESC = onEsc;
    _escHandler.willExecute = willExecute;
    this.handlers.push(_escHandler);
    return _escHandler;
  }

  remove(handler: ESCHandler) {
    if (!handler) { return; }
    const removeHandlerIdx = this.handlers.findIndex((_handler: ESCHandler) => {
      return _handler.id === handler.id;
    });
    if (handler.willExecute()) {
      handler.onESC();
      this.handlers.splice(removeHandlerIdx, 1);
    } else {
      this.handlers.splice(removeHandlerIdx, 1);
      this.remove(this.handlers[this.handlers.length - 1]);
    }
  }
}
