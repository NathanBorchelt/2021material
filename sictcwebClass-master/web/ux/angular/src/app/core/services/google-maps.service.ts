// import { Injectable } from '@angular/core';
// import { Observable, throwError, Observer } from 'rxjs';
// import { HttpClient, HttpResponse, HttpErrorResponse } from '@angular/common/http';
// import { map, catchError, flatMap } from 'rxjs/operators';

// const MAP_API_KEY = 'AIzaSyBcG7ruxu1QBqWJGnc23g143372iJbYqoA';

// @Injectable({ providedIn: 'root' })
// export class GoogleMapsService {
//   // @ts-ignore
//   //const; geocoder = new google.maps.Geocoder();
//   constructor(private http: HttpClient) { }

//   /*geocode(request: any, callback: any) {
//     return this.geocoder.geocode(request, callback);
//   }*/

//   geocode(request: google.maps.GeocoderRequest): Observable<GeocodeResponse> {
//     return Observable.create((observer: Observer<GeocodeResponse>) => {
//       new google.maps.Geocoder().geocode(request, (results, status) => {
//         observer.next({results, status});
//         observer.complete();
//       });
//     });
//   }

//   geocodeWithTimezone(address: string, tzFault = true): Observable<{geocode: GeocodeResponse, timezone: TimeZoneResponse}> {
//     return this.geocode({ address: address }).pipe(flatMap(x => {
//       if (x.status !== 'OK') {
//         throw new Error('Error geocoding address');
//       }
//       const location = x.results[0].geometry.location;
//       return this.getTimezone(location).pipe(map(y => {
//         if (tzFault && y.status !== 'OK') {
//           throw new Error('Error getting timezone');
//         }
//         return { geocode: x, timezone: y };
//       }));
//     }));
//   }

//   getTimezone(latLng: google.maps.LatLng): Observable<TimeZoneResponse> {
//     const time = Math.floor(Date.now() / 1000);
//     let url = 'https://maps.googleapis.com/maps/api/timezone/json';
//     url += '?location=' + latLng.lat() + ',' + latLng.lng();
//     url += '&timestamp=' + time;
//     url += '&key=' + MAP_API_KEY;

//     return this.http.get<TimeZoneResponse>(url, { observe: 'response' })
//     .pipe(map((response) => {
//       return response.body;
//     }), catchError(this.handleError));
//   }

//   handleError(err: HttpErrorResponse) {
//     let message = err.error && err.error.data && err.error.data.Message;
//     message = message || 'Service failed: ' + err.message;
//     return throwError(new Error(message));
//   }
// }

// export interface GeocodeResponse {
//   results: google.maps.GeocoderResult[];
//   status: google.maps.GeocoderStatus | string;
// }

// export interface TimeZoneResponse {
//   dstOffset: number;
//   rawOffset: number;
//   timeZoneId: string;
//   timeZoneName: string;
//   status: string;
//   errorMessage: string;
// }
