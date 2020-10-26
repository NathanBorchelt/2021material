import { Injectable } from '@angular/core';
import { StandardColor } from '../interfaces/StandardColor';

@Injectable({ providedIn: 'root' })
export class ColorService {

    constructor() {
        this._colors = this._colors.sort((a, b) => a.GridOrderBy - b.GridOrderBy);
    }

    getStandardColors(): StandardColor[] {
        return this._colors;
    }

    getStandardColorByName(color: string): StandardColor {
        if (!color) return undefined;
        const key = color && color.trim().toLowerCase();
        return this._colors.find(x => x.Color.trim().toLowerCase() == key);
    }

    //Per John C, let's keep it simple and hardcode for now.
    _colors: StandardColor[] = [
        {
            RecordId: 1,
            Color: "White",
            Hex: "#FFF",
            GridOrderBy: 0
        },
        {
            RecordId: 2,
            Color: "Black",
            Hex: "#000",
            GridOrderBy: 3
        },
        {
            RecordId: 3,
            Color: "Yellow",
            Hex: "#F6E05E",
            GridOrderBy: 4
        },
        {
            RecordId: 4,
            Color: "Red",
            Hex: "#AA3532",
            GridOrderBy: 9
        },
        {
            RecordId: 5,
            Color: "Silver",
            Hex: "#E7EAF2",
            GridOrderBy: 1
        },
        {
            RecordId: 6,
            Color: "Gray",
            Hex: "#848B97",
            GridOrderBy: 2
        },
        {
            RecordId: 7,
            Color: "Purple",
            Hex: "#9F7AEA",
            GridOrderBy: 8
        },
        {
            RecordId: 8,
            Color: "Green",
            Hex: "#2F855A",
            GridOrderBy: 11
        },
        {
            RecordId: 9,
            Color: "Blue",
            Hex: "#2B6CB0",
            GridOrderBy: 10
        },
        {
            RecordId: 10,
            Color: "Tan",
            Hex: "#DBC992",
            GridOrderBy: 6
        },
        {
            RecordId: 11,
            Color: "Orange",
            Hex: "#FFA400",
            GridOrderBy: 5
        },
        {
            RecordId: 12,
            Color: "Brown",
            Hex: "#744210",
            GridOrderBy: 7
        }
    ];
}