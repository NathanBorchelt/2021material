import { Injectable } from '@angular/core';
import { Observable ,  BehaviorSubject } from 'rxjs';

import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';

export const CP_UNISON_API = 'unisonApi';
export const CP_PRINTER_API = 'printerApi';
export const CP_SCANNER_API = 'scannerApi';
export const CP_INTEGRATION_API = 'integrationApi';
export const CP_BATCH_API = 'batchApi';
export const CP_SOCK_ROOT = 'sockRoot';
export const CP_SOCK_PATH = 'sockPath';
export const CP_SOCK_NS = 'sockNs';
export const STRIPE_PUBLISHABLE_KEY = 'stripePublishableKey';
export const CP_STORE_EMAIL_API = 'storeEmailApi';

// export function endpointsProviderFactory(ep: EndpointsService) {
//     return () => ep.getEndpoints();
// }

@Injectable({providedIn: 'root'})
export class EndpointsService {

    values: any;

    public isReady = new BehaviorSubject<boolean>(false);

    constructor(private http: HttpClient) { }

    get isReady$(): Observable<boolean> {
        return this.isReady.asObservable();
    }

    // getEndpoints() {
    //     return this.http.get('assets/endpoints.json')
    //         .toPromise()
    //         .then(endpoints => {
    //             this.values = endpoints;
    //             if (this.values[CP_UNISON_API] === 'CP_UNISON_API') {
    //                 if (environment.production) {
    //                     throw new Error('envars missing in production environment! Make sure endpoints.json has been updated properly with CP_ environment variables.');
    //                 }
    //                 else {
    //                     console.log('endpoints.json not configured, but since this isn\'t production, using relevant environment.ts settings');
    //                     this.copyEnvironment();
    //                 }
    //             }
    //             this.isReady.next(true);
    //         });
    // }

    build(endpoint: string, path: string) {
        return `${window.location.protocol}//${this.values[endpoint]}${path}`;
    }

    buildSecure(endpoint: string, path: string) {
        return `https://${this.values[endpoint]}${path}`;
    }

    getEndpoint(endpoint: string) {
        return this.values[endpoint];
    }

    private copyEnvironment() {
        this.values[CP_UNISON_API] = environment.apiEndpoint;
        this.values[CP_PRINTER_API] = environment.printer_apiEndpoint;
        this.values[CP_SCANNER_API] = environment.scanner_apiEndpoint;
        this.values[CP_INTEGRATION_API] = environment.integration_apiEndpoint;
        this.values[CP_BATCH_API] = environment.batch_apiEndpoint;
        this.values[CP_SOCK_ROOT] = environment.sockRoot;
        this.values[CP_SOCK_PATH] = environment.sockPath;
        this.values[CP_SOCK_NS] = environment.sockNs;
        this.values[STRIPE_PUBLISHABLE_KEY] = environment.stripePublishableKey;
        this.values[CP_STORE_EMAIL_API] = environment.storeEmailApi;
    }
}
