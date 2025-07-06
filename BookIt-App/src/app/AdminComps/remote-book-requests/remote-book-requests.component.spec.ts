import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RemoteBookRequestsComponent } from './remote-book-requests.component';

describe('RemoteBookRequestsComponent', () => {
  let component: RemoteBookRequestsComponent;
  let fixture: ComponentFixture<RemoteBookRequestsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RemoteBookRequestsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RemoteBookRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
