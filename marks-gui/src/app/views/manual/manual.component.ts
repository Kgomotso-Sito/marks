import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-manual',
  templateUrl: './manual.component.html'
})
export class ManualComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  pdfUrl: string = 'assets/Marks_User_Manual.pdf';
}
