import { Routes } from '@angular/router';

import { BookCategoryListComponent } from './components/book-category-list/book-category-list.component';
import { BookCategoryEditComponent } from './components/book-category-edit/book-category-edit.component';
import { BookCategoryWithReadersComponent } from './components/book-category-with-readers/book-category-with-readers.component';
import { CategoryDetailsComponent } from './components/category-details/category-details.component';
import { AddReaderComponent } from './components/add-reader/add-reader.component';
import {EditReaderComponent} from './components/edit-reader/edit-reader.component';

export const routes: Routes = [
  { path: 'categories/full/add-reader', component: AddReaderComponent},
  { path: 'categories/full/edit-reader/:id', component: EditReaderComponent},
  { path: 'categories/full', component: BookCategoryWithReadersComponent},
  { path: 'categories/edit/:id', component: BookCategoryEditComponent},
  { path: 'categories/:id', component: CategoryDetailsComponent},
  { path: 'categories', component: BookCategoryListComponent},
  { path: '', redirectTo: '/categories', pathMatch: 'full' },
];
