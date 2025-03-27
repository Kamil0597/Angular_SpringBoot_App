import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ReaderService } from '../../reader/service/reader.service';
import { BookCategoryService } from '../../bookCategory/service/bookCategory.service';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {BookCategory} from '../../bookCategory/model/bookCategory';
import {ReaderDto} from '../../reader/model/reader-dto';

@Component({
  selector: 'app-add-reader',
  templateUrl: '../add-reader/add-reader.component.html',
  styleUrls: ['../add-reader/add-reader.component.css'],
  imports: [CommonModule, FormsModule],
})
export class AddReaderComponent implements OnInit {

  public bookCategories: BookCategory[] = [];
  public readers: ReaderDto[] = [];

  newReader = {
    name: '',
    borrowCount: 0,
    bookCategoryId: '',
    bookCategoryName:'',
  };
  readerId: string = '';

  constructor(
    private bookCategoryService: BookCategoryService,
    private readerService: ReaderService,
    private router: Router
  ) {}

  ngOnInit(): void {
    console.log('AddReaderComponent został załadowany.');
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

  private loadReaders(): void {
    this.readerService.getReadersDto().subscribe({
      next: (readers: ReaderDto[]) => {
        console.log('Czytelnicy załadowani:', readers);
        this.readers = readers;
      },
      error: (err) => console.error('Błąd przy ładowaniu czytelników:', err),
    });
  }

  addReader(): void {

    if (this.newReader.name.trim() && this.newReader.bookCategoryName.trim()) {

      this.readerService.addReader({
        id: '',
        name: this.newReader.name,
        borrowCount: this.newReader.borrowCount || 0,
        bookCategoryName: this.newReader.bookCategoryName,
      }).subscribe({
        next: (response) => {
          console.log('Nowy czytelnik został dodany:', response);
          this.loadReaders(); // Odśwież listę czytelników
        },
        error: (err) => {
          console.error('Błąd przy dodawaniu czytelnika:', err);
        },
      });
    } else {
      console.error('Wprowadź poprawne dane czytelnika.');
    }
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
}
