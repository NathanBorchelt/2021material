export interface PrintLabel {
  data: Data;
}

export interface Data {
  appId: 1;
  printerId: number;
  Key: string;
  doc: Doc;
}

export interface Doc {
  vehicle: Vehicle;
}

export interface Vehicle {
  vin: string;
  stock: string;
  year: number;
  make: string;
  model: string;
  color: string;
  mileage: number;
}

