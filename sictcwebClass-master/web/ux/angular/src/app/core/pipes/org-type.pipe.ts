import {PipeTransform, Pipe} from '@angular/core';

@Pipe({
    name: 'orgtype'
})
export class OrgPipe implements PipeTransform
{
  transform(value: string): string {
    if (value == "V"){
        return "Vendor"
        }
    if (value == "D"){
        return "Dealer"
        }
    }   
}