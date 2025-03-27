import { Component, OnInit } from '@angular/core';
import { BookCategoryService } from '../../bookCategory/service/bookCategory.service';
import { BookCategory } from '../../bookCategory/model/bookCategory';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-book-category-list',
  templateUrl: '../book-category-list/book-category-list.component.html',
  styleUrls: ['../book-category-list/book-category-list.component.css'],
  imports: [CommonModule, FormsModule, HttpClientModule]
})
export class BookCategoryListComponent implements OnInit {
  public bookCategories: BookCategory[] = [];

  newCategoryName = '';
  newCategoryPopularity = 0;
  constructor(private bookCategoryService: BookCategoryService, private router: Router) {}

  ngOnInit(): void {
    this.loadCategories();
  }

  loadCategories(): void {
    this.bookCategoryService.getCategories().subscribe({
      next: (data: BookCategory[]) => {
        console.log('Kategorie załadowane z backendu:', data);
        this.bookCategories = data;
        console.log('Sprawdzam popularityScore:', this.bookCategories.map(cat => cat.popularityScore));
      },
      error: (err) => console.error('Błąd przy ładowaniu kategorii:', err),
    });
  }

  deleteCategory(id: string): void {
    this.bookCategoryService.deleteBookCategory(id).subscribe({
      next: () => {
        this.loadCategories();
      },
      error: (err) => {
        console.error('Error deleting category:', err);
      }
    });
  }

  addCategory(): void {
    if (this.newCategoryName.trim() && this.newCategoryPopularity !== null) {
      const newCategory: Omit<BookCategory, 'id'> = {
        name: this.newCategoryName,
        popularityScore: this.newCategoryPopularity,
      };

      this.bookCategoryService.addBookCategory(newCategory).subscribe({
        next: (response: BookCategory) => {
          console.log('Dodano nową kategorię:', response);
          this.bookCategories.push(response);
          this.newCategoryName = '';
          this.newCategoryPopularity = 0;
        },
        error: (err) => {
          console.error('Błąd przy dodawaniu kategorii:', err);
        },
      });
    }
  }

  navigateToEdit(categoryId: string): void
  {
    this.router.navigate(['/categories/edit', categoryId]);
  }

  categoryWithReaders(): void
  {
    this.router.navigate(['/categories/full']);
  }

  navigateToDetails(categoryId: string): void
  {
    console.log('Nawigacja do kategorii o ID:', categoryId);
    this.router.navigate([`/categories/${categoryId}`]);
  }
}
