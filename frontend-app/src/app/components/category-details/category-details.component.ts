import {Component, OnInit} from '@angular/core';
import {BookCategory} from '../../bookCategory/model/bookCategory';
import {ActivatedRoute} from '@angular/router';
import {BookCategoryService} from '../../bookCategory/service/bookCategory.service';
import {ReaderService} from '../../reader/service/reader.service';
import {ReaderDetails} from '../../reader/model/reader-details';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {ReaderDto} from '../../reader/model/reader-dto';

@Component({
  selector: 'app-category-details',
  imports: [CommonModule, FormsModule],
  templateUrl: './category-details.component.html',
  styleUrl: './category-details.component.css'
})
export class CategoryDetailsComponent implements OnInit
{
  category: BookCategory | null = null;
  readers: ReaderDto[] = [];


  constructor(
    private route: ActivatedRoute,
    private bookCategoryService: BookCategoryService,
    private readerService: ReaderService
  ) {}

  ngOnInit(): void {
    const categoryId = this.route.snapshot.paramMap.get('id');
    if (categoryId) {
      this.loadCategoryDetails(categoryId);
    }
  }

  private loadCategoryDetails(categoryId: string): void {
    this.bookCategoryService.getCategoryById(categoryId).subscribe({
      next: (category) => {
        this.category = category;
        if (this.category?.name) {
          this.loadReadersByCategoryName(this.category.name);
        }
      },
      error: (err) =>
        console.error('Błąd przy ładowaniu szczegółów kategorii:', err),
    });
  }

  private loadReadersByCategoryName(categoryName: string): void {
    this.readerService.getReadersByCategoryName(categoryName).subscribe({
      next: (readers: ReaderDto[]) => {
        this.readers = readers;
        console.log('Czytelnicy przypisani do kategorii:', this.readers);
      },
      error: (err) =>
        console.error('Błąd przy ładowaniu czytelników:', err),
    });
  }

  deleteReader(readerId: string): void
  {
    this.readerService.deleteReader(readerId).subscribe({
      next: () => {
        this.readers = this.readers.filter(
          (reader) => reader.id !== readerId
        );
        console.log('Czytelnik został usunięty.');
      },
      error: (err) =>
        console.error('Błąd przy usuwaniu czytelnika:', err),
    });
  }

}
