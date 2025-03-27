import { bootstrapApplication } from '@angular/platform-browser';
import { provideRouter } from '@angular/router';
import { provideHttpClient } from '@angular/common/http';
import { AppComponent } from './app/app.component';
import { BookCategoryListComponent } from './app/components/book-category-list/book-category-list.component';
import {BookCategoryEditComponent} from './app/components/book-category-edit/book-category-edit.component';
import {
  BookCategoryWithReadersComponent
} from './app/components/book-category-with-readers/book-category-with-readers.component';
import {CategoryDetailsComponent} from './app/components/category-details/category-details.component';
import {AddReaderComponent} from './app/components/add-reader/add-reader.component';
import {EditReaderComponent} from './app/components/edit-reader/edit-reader.component';

bootstrapApplication(AppComponent, {
  providers: [
    provideRouter([
      { path: 'categories/full/add-reader', component: AddReaderComponent},
      { path: 'categories/full/edit-reader/:id', component: EditReaderComponent},
      { path: 'categories/full', component: BookCategoryWithReadersComponent},
      { path: 'categories/edit/:id', component: BookCategoryEditComponent},
      { path: 'categories/:id', component: CategoryDetailsComponent},
      { path: 'categories', component: BookCategoryListComponent},
      { path: '', redirectTo: '/categories', pathMatch: 'full' },
    ]),
    provideHttpClient(), // Zapewnij HttpClient
  ],
});
