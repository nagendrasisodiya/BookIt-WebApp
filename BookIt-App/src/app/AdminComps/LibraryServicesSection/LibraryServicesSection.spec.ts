import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LibraryServicesSection } from './LibraryServicesSection';

describe('AssignAndSubmitComponent', () => {
  let component: LibraryServicesSection;
  let fixture: ComponentFixture<LibraryServicesSection>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LibraryServicesSection]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LibraryServicesSection);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
