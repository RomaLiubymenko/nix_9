import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentGroupEditComponent } from './student-group-edit.component';

describe('StudentGroupEditComponent', () => {
  let component: StudentGroupEditComponent;
  let fixture: ComponentFixture<StudentGroupEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StudentGroupEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentGroupEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
