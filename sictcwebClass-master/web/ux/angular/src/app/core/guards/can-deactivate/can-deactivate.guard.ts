// import { Injectable } from '@angular/core';
// import { Observable } from 'rxjs';
// import { CanDeactivate } from '@angular/router';
// import { ComponentCanDeactivate } from './component-can-deactivate';
// import { ConfirmationModalService } from '../../services/confirmation-modal.service';


// @Injectable()
// export class CanDeactivateGuard implements CanDeactivate<ComponentCanDeactivate> {

//     constructor(private confirmationService: ConfirmationModalService) { }

//     canDeactivate(component: ComponentCanDeactivate): boolean | Observable<boolean> {
//         if (!component.canDeactivate()) {
//             return this.confirmationService.confirm('You have unsaved changes.', 'Do you want to navigate away and lose your changes?')
//                 .map((deactivate: boolean) => {
//                     return deactivate;
//                 });
//         } else {
//             return true;
//         }
//     }
// }
