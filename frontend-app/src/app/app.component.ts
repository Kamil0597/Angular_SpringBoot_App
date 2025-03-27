import { Component, OnInit  } from '@angular/core';
import {NavigationEnd, RouterModule} from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'frontend-app';
  currentPath: string = '';

}
