import { Injectable } from '@angular/core';
import { StandardMake } from '../interfaces/StandardMake';
import { StandardModel } from '../interfaces/StandardModel';
import { HttpClient } from '@angular/common/http';
import { EndpointsService, CP_UNISON_API } from './endpoints.service';
import { CPResponse } from '../interfaces/CPResponse';
import { map } from 'rxjs/operators';
import { BehaviorSubject } from 'rxjs';
import { AuthService } from './auth.service';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({ providedIn: 'root' })
export class VehicleService {

    private standardMakes: StandardMake[] = [];
    private standardMakesSubject = new BehaviorSubject(this.standardMakes);
    standardMakes$ = this.standardMakesSubject.asObservable();
    
    private standardModels: StandardModel[] = [];
    private standardModelsSubject = new BehaviorSubject(this.standardModels);
    standardModels$ = this.standardModelsSubject.asObservable();

    constructor(
        private http: HttpClient,
        private endpoints: EndpointsService,
        private jwtHelperService: JwtHelperService) {
        this.init();
    }

    init() {
        const makes = localStorage.getItem('standard_makes');
        if (!makes && this.isAuthenticated()) {
            this.getStandardMakes().subscribe(); //safe-guard
        } else {
            this.standardMakes = JSON.parse(makes);
            this.standardMakesSubject.next(this.standardMakes);
        }

        const models = localStorage.getItem('standard_models');
        if (!models && this.isAuthenticated()) {
            this.getStandardModels().subscribe(); //safe-guard
        } else {
            this.standardModels = JSON.parse(models);
            this.standardModelsSubject.next(this.standardModels);
        }
    }

    getStandardMakes() {
        return this.http.get(this.endpoints.build(CP_UNISON_API, `/v1/vehicles/standardmakes/0`))
            .pipe(map((response: CPResponse<StandardMake[]>) => {
                this.standardMakes = response.data;
                this.standardMakes.sort((a, b) => a.Make.localeCompare(b.Make));
                localStorage.setItem('standard_makes', JSON.stringify(this.standardMakes));
                this.standardMakesSubject.next(this.standardMakes);
                return this.standardMakes;
            }));
    }

    getStandardModels() {
        return this.http.get(this.endpoints.build(CP_UNISON_API, `/v1/vehicles/standardmodels/0`))
            .pipe(map((response: CPResponse<StandardModel[]>) => {
                this.standardModels = response.data;
                this.standardModels.sort((a, b) => a.Model.localeCompare(b.Model));
                localStorage.setItem('standard_models', JSON.stringify(this.standardModels));
                this.standardModelsSubject.next(this.standardModels);
                return this.standardModels;
            }));
    }

    //could not use AuthService due to circular dependancy, will try to improve this later
    isAuthenticated(): boolean {
        try {
            const result = !!localStorage.getItem('id_token') && !this.jwtHelperService.isTokenExpired();
            return result;
        } catch (error) {
            return false;
        }
    }

    /*getStandardYears(): number[] {
        const end = new Date().getFullYear() + 1;
        const start = 1950;
        return Array.from({ length: end - start + 1 }, (_, i) => end - i)
    }*/
}