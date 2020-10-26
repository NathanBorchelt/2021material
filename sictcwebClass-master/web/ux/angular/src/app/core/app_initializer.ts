import { EndpointsService } from './services/endpoints.service';

export function appInitializerFactory(ep: EndpointsService) {
    // Return a function that returns a function
    // Must be a promise so Angular will pause execution until resolve.
    // Seems to not work with an observable.
    return () => {
        // return ep.points();
    };
}
