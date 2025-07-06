import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignedBooksComponent } from './assigned-books.component';

describe('AssignedBooksComponent', () => {
  let component: AssignedBooksComponent;
  let fixture: ComponentFixture<AssignedBooksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AssignedBooksComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AssignedBooksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
