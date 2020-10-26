import {PipeTransform, Pipe} from '@angular/core';
import * as moment from 'moment';

@Pipe({
  name: 'dateAgoPipe'
})

export class DateAgoPipe implements PipeTransform
{
  transform(value: string): string {
    if (value == null) {
      return 'Never';
    }

    let dateUtc = moment(value).utc(true);
    return moment(dateUtc).fromNow();
  }
}
