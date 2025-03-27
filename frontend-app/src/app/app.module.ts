import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AppComponent } from './app.component';
import { BookCategoryListComponent } from './components/book-category-list/book-category-list.component';
import { TestComponent } from './TestComponent';
import { BookCategoryEditComponent } from './components/book-category-edit/book-category-edit.component';
import { BookCategoryWithReadersComponent } from './components/book-category-with-readers/book-category-with-readers.component';
import { CategoryDetailsComponent } from './components/category-details/category-details.component';
import { AddReaderComponent } from './components/add-reader/add-reader.component';
import { AppRoutingModule } from './app-routing.module';
import {EditReaderComponent} from './components/edit-reader/edit-reader.component';

@NgModule({
  declarations: [
    AppComponent,
    BookCategoryListComponent,
    TestComponent,
    BookCategoryEditComponent,
    BookCategoryWithReadersComponent,
    CategoryDetailsComponent,
    AddReaderComponent,
    EditReaderComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    CommonModule,
    FormsModule,
    AppRoutingModule,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
