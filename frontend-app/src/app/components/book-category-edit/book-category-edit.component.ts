import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BookCategoryService } from '../../bookCategory/service/bookCategory.service';
import { BookCategory } from '../../bookCategory/model/bookCategory';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-book-category-edit',
  templateUrl: '../book-category-edit/book-category-edit.component.html',
  styleUrls: ['../book-category-edit/book-category-edit.component.css'],
  imports: [CommonModule, FormsModule],
})

export class BookCategoryEditComponent implements OnInit
{
  categoryId!: string;
  bookCategory!: BookCategory;
  updatedPopularity: number = 0;
  router: any;

  constructor(
    private route: ActivatedRoute,
    private bookCategoryService: BookCategoryService
  ) {}

  ngOnInit(): void {

    this.categoryId = this.route.snapshot.paramMap.get('id')!;
    this.loadCategory();
  }

  loadCategory(): void {
    this.bookCategoryService.getBookCategory(this.categoryId).subscribe({
      next: (data: BookCategory) => {
        this.bookCategory = data;
      },
      error: (err) => console.error('Błąd przy ładowaniu kategorii:', err),
    });
  }

  saveChanges(): void {
    const updatedCategory: BookCategory = {
      id: this.categoryId,
      name: this.bookCategory.name,
      popularityScore: this.bookCategory.popularityScore,
    };

    this.bookCategoryService.putBookCategory(this.categoryId, updatedCategory).subscribe({
      next: () => {
        console.log('Zapisano zmiany kategorii!');

      },
      error: (err) => {
        console.error('Błąd przy zapisywaniu kategorii:', err);
      },
    });
  }
}
