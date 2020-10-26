import {PipeTransform, Pipe} from '@angular/core';
import {AuthService} from '../services/auth.service';

@Pipe({
  name: 'hintReadOnlyPipe'
})
export class HintReadOnlyPipe implements PipeTransform {

  constructor(private auth: AuthService) {
  }

  transform(value: string): string {
    if (this.auth.isReadOnly()) {
      return value + ' - Unauthorized';
    } else {
      return value;
    }
  }
}
