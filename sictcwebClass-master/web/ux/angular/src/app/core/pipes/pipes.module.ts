import { HintReadOnlyPipe } from './hint-read-only.pipe';
import { InventoryAgePipe } from './inventoryage.pipe';
import { OrgPipe } from './org-type.pipe';
import { ClassificationsPipe } from './classifications.pipe';
import { DateAgoPipe } from './dateago.pipe';
import { NgModule } from '@angular/core';

@NgModule({
  declarations: [
    ClassificationsPipe,
    OrgPipe,
    InventoryAgePipe,
    DateAgoPipe,
    HintReadOnlyPipe
  ],
  exports: [
    ClassificationsPipe,
    OrgPipe,
    InventoryAgePipe,
    DateAgoPipe,
    HintReadOnlyPipe
  ]
})
export class PipesModule {}
