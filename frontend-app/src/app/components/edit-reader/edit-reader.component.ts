import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ReaderService } from '../../reader/service/reader.service';
import { ReaderDto } from '../../reader/model/reader-dto';
import {ReaderDetails} from '../../reader/model/reader-details';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {ReaderForm} from '../../reader/model/reader-form';

@Component({
  selector: 'app-edit-reader',
  templateUrl: './edit-reader.component.html',
  styleUrls: ['./edit-reader.component.css'],
  imports: [CommonModule, FormsModule, HttpClientModule]
})
export class EditReaderComponent implements OnInit {
  reader: ReaderDetails | null = null;
  isLoading: boolean = false;
  errorMessage: string | null = null;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private readerService: ReaderService
  ) {}

  ngOnInit(): void {
    const readerId = this.route.snapshot.paramMap.get('id');
    if (readerId) {
      this.loadReader(readerId);
    } else {
      this.errorMessage = 'Nie znaleziono ID czytelnika.';
    }
  }

  loadReader(id: string): void {
    this.isLoading = true;
    this.readerService.getReader(id).subscribe({
      next: (reader) => {
        this.reader = reader;
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Błąd przy ładowaniu czytelnika:', err);
        this.errorMessage = 'Błąd przy ładowaniu czytelnika. Spróbuj ponownie później.';
        this.isLoading = false;
      }
    });
  }

  saveChanges(): void {
    if (this.reader) {
      const readerForm: ReaderForm = {
        id: this.reader.id,
        name: this.reader.name,
        borrowCount: this.reader.borrowCount,
        bookCategoryName: this.reader.bookCategoryName,
        bookCategoryId: this.reader.bookCategoryId, // Include category ID if needed
      };

      this.readerService.putReader(readerForm.id, readerForm).subscribe({
        next: () => {
          console.log('Reader updated successfully');
          this.router.navigate(['/categories/full']);
        },
        error: (err) => {
          console.error('Error updating reader:', err);
          this.errorMessage = 'Error saving changes. Please try again later.';
        },
      });
    }
  }


  cancel(): void {
    this.router.navigate(['/readers']); // Powrót do listy czytelników
  }
}
