import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmailVerificationQuestionComponent } from './email-verification-question.component';

describe('EmailVerificationQuestionComponent', () => {
  let component: EmailVerificationQuestionComponent;
  let fixture: ComponentFixture<EmailVerificationQuestionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmailVerificationQuestionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmailVerificationQuestionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
