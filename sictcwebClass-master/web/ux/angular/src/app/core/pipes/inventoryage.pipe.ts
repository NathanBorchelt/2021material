import {PipeTransform, Pipe} from '@angular/core';
import * as moment from 'moment';

@Pipe({
  name: 'inventoryAgePipe'
})
export class InventoryAgePipe implements PipeTransform
{
  transform(value: any): any {
    let today = moment();
    let date = new Date(value);
    let dateUtc = moment(value).utc(true);
    let duration = moment.duration(moment().utc().diff(dateUtc));
    let seconds = duration.asSeconds();

    if(!seconds){
      return "N/A"
    }    

    if (seconds < 120){
      return 'Now';
    }
    if (dateUtc.date() == today.date() && dateUtc.month() == today.month() && dateUtc.year() == today.year()) {
          return 'Today';
    }
    if (dateUtc.date() == today.date() - 1
      && dateUtc.month() == today.month()
      && dateUtc.year() == today.year()) {
      return 'Yesterday';
    }
      return ~~(seconds / 86400) + 1; // Add one to day
  }
}
