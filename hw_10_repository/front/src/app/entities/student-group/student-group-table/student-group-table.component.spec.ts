import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentGroupTableComponent } from './student-group-table.component';

describe('StudentGroupTableComponent', () => {
  let component: StudentGroupTableComponent;
  let fixture: ComponentFixture<StudentGroupTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StudentGroupTableComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentGroupTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
