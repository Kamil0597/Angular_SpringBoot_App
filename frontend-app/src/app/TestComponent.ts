import { Component } from '@angular/core';

@Component({
  selector: 'app-test',
  standalone: false,
  template: `
    <ul>
      <li *ngFor="let item of items">{{ item }}</li>
    </ul>
  `,
})
export class TestComponent {
  items = ['Item 1', 'Item 2', 'Item 3'];
}
