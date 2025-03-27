import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookCategoryWithReadersComponent } from './book-category-with-readers.component';

describe('BookCategoryWithReadersComponent', () => {
  let component: BookCategoryWithReadersComponent;
  let fixture: ComponentFixture<BookCategoryWithReadersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BookCategoryWithReadersComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BookCategoryWithReadersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
