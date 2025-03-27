import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { ReaderDetails } from '../model/reader-details';
import { ReaderForm } from '../model/reader-form';
import {map} from 'rxjs/operators';
import {ReaderDto} from '../model/reader-dto';

@Injectable({
  providedIn: 'root'
})
export class ReaderService
{
  private baseUrl = 'http://localhost:8082/api/readers';
  constructor(private http: HttpClient)
  {

  }

  getReadersDto(): Observable<ReaderDto[]> {
    return this.http.get<ReaderDto[]>(this.baseUrl).pipe(
      map((readers) => {
        console.log('Dane przed mapowaniem:', readers);
        const mappedReaders = readers.map((reader) => ({
          id: reader.id,
          name: reader.name,
          borrowCount: reader.borrowCount,
          bookCategoryName: reader.bookCategoryName,
        }));
        console.log('Dane po mapowaniu:', mappedReaders);
        return mappedReaders;
      })
    );
  }

  getReader(uuid: string): Observable<ReaderDetails> {
    return this.http.get<ReaderDetails>(`${this.baseUrl}/${uuid}`);
  }

  deleteReader(uuid: string): Observable<any> {
    const url = `http://localhost:8082/api/readers/${uuid}`;
    console.log("Sending DELETE request to:", url);
    console.log(this.http.delete(url));
    return this.http.delete(url);
  }

  putReader(uuid: string, request: ReaderForm): Observable<any> {
    return this.http.put(`${this.baseUrl}/${uuid}`, request);
  }

  getReadersByCategoryName(categoryName: string): Observable<ReaderDto[]> {
    return this.getReadersDto().pipe(
      map((readers: ReaderDto[]) =>
        readers.filter((reader) => reader.bookCategoryName === categoryName)
      )
    );
  }
  addReader(reader: ReaderDto): Observable<ReaderDto> {
    const payload = {
      name: reader.name,
      borrowCount: reader.borrowCount,
      bookCategoryName: reader.bookCategoryName,
    };

    return this.http.post<ReaderDto>(`${this.baseUrl}`, payload);
  }
}
