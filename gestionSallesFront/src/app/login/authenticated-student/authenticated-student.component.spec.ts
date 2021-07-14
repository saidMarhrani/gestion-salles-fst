import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuthenticatedStudentComponent } from './authenticated-student.component';

describe('AuthenticatedStudentComponent', () => {
  let component: AuthenticatedStudentComponent;
  let fixture: ComponentFixture<AuthenticatedStudentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AuthenticatedStudentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AuthenticatedStudentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
