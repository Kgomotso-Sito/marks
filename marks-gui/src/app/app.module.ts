import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { PerfectScrollbarModule } from 'ngx-perfect-scrollbar';
import { AngularFireModule } from '@angular/fire';

import { AppComponent } from './app.component';

// Import containers
import { DefaultLayoutComponent } from './containers';

import { P404Component } from './views/error/404.component';
import { P500Component } from './views/error/500.component';
import { LoginComponent } from './views/login/login.component';
import { RegisterComponent } from './views/register/register.component';

const APP_CONTAINERS = [
  DefaultLayoutComponent
];

import {
  AppAsideModule,
  AppBreadcrumbModule,
  AppHeaderModule,
  AppFooterModule,
  AppSidebarModule,
} from '@coreui/angular';

// Import routing module
import { AppRoutingModule } from './app.routing';

// Import 3rd party components
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { TabsModule } from 'ngx-bootstrap/tabs';
import { ChartsModule } from 'ng2-charts';
import {HTTP_INTERCEPTORS, HttpClientModule, HttpInterceptor} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AuthGuard} from "./guard/auth.guard";
import { TokenInterceptorService} from "./guard/token-interceptor.service";
import { ManualComponent } from './views/manual/manual.component';
import {PdfViewerModule} from "ng2-pdf-viewer";

const firebaseConfig = {
    apiKey: "AIzaSyAj2_PMg04PEWv14ClZ4OIZP6ZlGEB1r-Y",
    authDomain: "marks-authentication.firebaseapp.com",
    databaseURL: "https://marks-authentication.firebaseio.com",
    projectId: "marks-authentication",
    storageBucket: "marks-authentication.appspot.com",
    messagingSenderId: "955619129904",
    appId: "1:955619129904:web:f43154076d4bc6067e4bf5",
    measurementId: "G-HJNLYDJMHJ"
};

@NgModule({
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        AppRoutingModule,
        AppAsideModule,
        AppBreadcrumbModule.forRoot(),
        AppFooterModule,
        AppHeaderModule,
        AppSidebarModule,
        PerfectScrollbarModule,
        BsDropdownModule.forRoot(),
        TabsModule.forRoot(),
        ChartsModule,
        HttpClientModule,
        FormsModule,
        ReactiveFormsModule,
        AngularFireModule.initializeApp(firebaseConfig),
        PdfViewerModule
    ],
  declarations: [
    AppComponent,
    ...APP_CONTAINERS,
    P404Component,
    P500Component,
    LoginComponent,
    RegisterComponent,
    ManualComponent
  ],
  providers: [{
    provide: LocationStrategy,
    useClass: HashLocationStrategy,
  }, {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
  }
  , AuthGuard],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
