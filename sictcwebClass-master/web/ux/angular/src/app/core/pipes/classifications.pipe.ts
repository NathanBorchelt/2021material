import {PipeTransform, Pipe} from '@angular/core';

@Pipe({
    name: 'classifications'
})
export class ClassificationsPipe implements PipeTransform
{
  transform(value: string): string {
    if (value == "N"){
        return "New"
    }
    if (value == "U"){
        return "Used"
    }
    if (value == "L"){
        return "Loaner"
    }
    if (!value || !value.trim()){
        return "Unclassified"
    }
    else{
        return value;
    }
 } 
}