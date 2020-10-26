import * as moment from 'moment';

export interface Journal {
  Class: string;
  SubClass: string;
  Note: string;
  Data: any;
  CreatedDate: string;
  CreatedMoment: moment.Moment;
  CreatedUserName: string;
  isCollapsed: boolean;
}
