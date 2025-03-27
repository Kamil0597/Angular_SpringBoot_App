import { Component, OnInit } from '@angular/core';
import { BookCategory } from '../../bookCategory/model/bookCategory';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BookCategoryService } from '../../bookCategory/service/bookCategory.service';
import { Router } from '@angular/router';
import { ReaderService } from '../../reader/service/reader.service';
import { ReaderDto } from '../../reader/model/reader-dto';
import {HttpClientModule} from '@angular/common/http';

@Component({
  selector: 'app-book-category-with-readers',
  templateUrl: '../book-category-with-readers/book-category-with-readers.component.html',
  styleUrls: ['../book-category-with-readers/book-category-with-readers.component.css'],
  imports: [CommonModule, FormsModule, HttpClientModule]
})
export class BookCategoryWithReadersComponent implements OnInit
{
  public bookCategories: BookCategory[] = [];
  public readers: ReaderDto[] = [];

  constructor(
    private bookCategoryService: BookCategoryService,
    private readerService: ReaderService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    console.log('BookCategoryWithReadersComponent został załadowany.');

    this.loadCategories();
    this.loadReaders();
  }

  private loadCategories(): void {
    this.bookCategoryService.getCategories().subscribe({
      next: (categories: BookCategory[]) => {
        console.log('Kategorie załadowane z backendu:', categories);
        this.bookCategories = categories;
      },
      error: (err) => console.error('Błąd przy ładowaniu kategorii:', err),
    });
  }


  newCategoryName: any;
  newCategoryPopularity: any;

  private loadReaders(): void {
    this.readerService.getReadersDto().subscribe({
      next: (readers: ReaderDto[]) => {
        console.log('Czytelnicy załadowani:', readers);
        this.readers = readers;
      },
      error: (err) => console.error('Błąd przy ładowaniu czytelników:', err),
    });
  }

  addCategory(): void {
    if (this.newCategoryName.trim() && this.newCategoryPopularity !== null) {
      const newCategory: Omit<BookCategory, 'id'> = {
        name: this.newCategoryName,
        popularityScore: this.newCategoryPopularity, // Pobranie wartości z formularza
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

  deleteCategory(id: string): void {
    this.bookCategoryService.deleteBookCategory(id).subscribe({
      next: () => {
        this.loadCategories();
      },
      error: (err) => {
        console.error('Error deleting category:', err); // Obsłuż błędy
      }
    });
  }

  navigateToEdit(categoryId: string): void
  {
    this.router.navigate(['/categories/edit', categoryId]);
  }

  navigateToDetails(categoryId: string): void
  {
    console.log('Nawigacja do kategorii o ID:', categoryId);
    this.router.navigate([`/categories/${categoryId}`]);
  }

  deleteReader(readerId: string): void {
    if (confirm('Czy na pewno chcesz usunąć tego czytelnika?')) {
      this.readerService.deleteReader(readerId).subscribe({
        next: () => {
          console.log(`Czytelnik o ID ${readerId} został usunięty`);
          this.loadReaders();
        },
        error: (err) => {
          console.error('Błąd przy usuwaniu czytelnika:', err);
        },
      });
    }
  }

  navigateToAddReader(): void {
    console.log("navigateToAddReader method invoked");
    this.router.navigate(['categories/full/add-reader']);
  }

  navigateToEditReader(readerId: string): void {
    this.router.navigate([`/categories/full/edit-reader`, readerId]);
  }
}
