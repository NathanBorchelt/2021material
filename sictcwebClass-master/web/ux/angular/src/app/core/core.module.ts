import { NgModule, Optional, SkipSelf, APP_INITIALIZER } from '@angular/core';
import { appInitializerFactory } from './app_initializer';
import { JWT_OPTIONS, JwtModule } from '@auth0/angular-jwt';
import { HttpClientModule } from '@angular/common/http';
import { EndpointsService } from './services/endpoints.service';
import { AuthService } from './services/auth.service';
import { Angulartics2Module } from 'angulartics2';
import { Angulartics2GoogleTagManager } from 'angulartics2/gtm';
import { CommonModule } from '@angular/common';
// import { TypeToConfirmModalComponent } from './components/type-to-confirm-modal/type-to-confirm-modal.component';
import { FormsModule } from '@angular/forms';
// import { ConfirmationModalComponent } from './components/confirmation-modal/confirmation-modal.component';
import { CookieService } from 'ngx-cookie-service';

export function jwtOptionsFactory() {
  return {
    tokenGetter: () => {
      const token = localStorage.getItem('id_token');
      return token;
    },
    whitelistedDomains: [
      'localhost:5000',
      'orion.cpht.io',
      'api.cphandheld.io',
      'api.stage-cphandheld.io',
      'demo.cpht.io',
      'api.dev-cphandheld.io',
      'newapi.cphandheld.io',
      'aajshzvpkh.execute-api.us-east-1.amazonaws.com',
      '7aqffypike.execute-api.us-east-1.amazonaws.com',
      'ca9j3beie5.execute-api.us-east-1.amazonaws.com',
      '8qls8x6lv7.execute-api.us-east-1.amazonaws.com',
      '787vuqtivc.execute-api.us-east-1.amazonaws.com'],
    blacklistedRoutes: [/.*\/integration-api.*/]
  };
}

@NgModule({
  imports: [
    FormsModule,
    JwtModule.forRoot({
      jwtOptionsProvider: {
        provide: JWT_OPTIONS,
        useFactory: jwtOptionsFactory
      }
    }),
    CommonModule,
    HttpClientModule,
    Angulartics2Module.forRoot(),
  ],
  providers: [
    {
      provide: APP_INITIALIZER,
      useFactory: appInitializerFactory,
      deps: [EndpointsService],
      multi: true
    },
    CookieService,
    AuthService
  ],
  // entryComponents: [ConfirmationModalComponent, TypeToConfirmModalComponent],
  // declarations: [TypeToConfirmModalComponent, ConfirmationModalComponent]
})
export class CoreModule {

  constructor(@Optional() @SkipSelf() parentModule: CoreModule) {
    if (parentModule) {
      throw new Error(
        'CoreModule is already loaded. Import it in the AppModule only.');
    }
  }
}
