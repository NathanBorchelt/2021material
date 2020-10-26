import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmailVerificationUpdateComponent } from './email-verification-update.component';

describe('EmailVerificationUpdateComponent', () => {
  let component: EmailVerificationUpdateComponent;
  let fixture: ComponentFixture<EmailVerificationUpdateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmailVerificationUpdateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmailVerificationUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
