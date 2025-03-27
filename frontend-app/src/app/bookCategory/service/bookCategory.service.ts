import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BookCategory } from '../model/bookCategory';
import { BookCategoryReadDTO } from '../model/bookCategoryReadDTO';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class BookCategoryService {
  private baseUrl = 'http://localhost:8082/api/categories'; // Poprawny URL

  constructor(private http: HttpClient) {}

  getCategories(): Observable<BookCategory[]> {
    return this.http.get<BookCategory[]>(this.baseUrl).pipe(
      map((categories) =>
        categories.map((category) => ({
          ...category,
          popularityScore: category.popularityScore // Przykład przekształcenia
        }))
      )
    );
  }

  getBookCategory(uuid: string): Observable<BookCategory> {
    return this.http.get<BookCategory>(`${this.baseUrl}/${uuid}`);
  }

  deleteBookCategory(uuid: string): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${uuid}`);
  }

  putBookCategory(uuid: string, category: Partial<BookCategory>): Observable<BookCategory> {
    return this.http.put<BookCategory>(`${this.baseUrl}/${uuid}`, category);
  }

  addBookCategory(category: Omit<BookCategory, 'id'>): Observable<BookCategory> {
    return this.http.post<BookCategory>(this.baseUrl, category);
  }

  getCategoriesWithReaders(): Observable<BookCategory[]> {
    return this.http.get<BookCategory[]>('/api/categories/with-readers');
  }

  getCategoryById(id: string): Observable<BookCategory> {
    return this.http.get<BookCategory>(`${this.baseUrl}/${id}`);
  }

}
