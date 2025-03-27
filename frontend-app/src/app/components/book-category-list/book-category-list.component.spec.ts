import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookCategoryListComponent } from './book-category-list.component';

describe('BookCategoryListComponent', () => {
  let component: BookCategoryListComponent;
  let fixture: ComponentFixture<BookCategoryListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BookCategoryListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BookCategoryListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
