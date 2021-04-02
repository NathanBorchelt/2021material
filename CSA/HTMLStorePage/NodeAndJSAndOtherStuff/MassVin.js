const vmap = '0123456789ABCDEFGHJKLMNPRSTUVWXYZ'
const base = '34'

function encodeingCheck(vin) {
    var enWmi = vmapEncode(vin.substring(0, 3), 34)
    var enVds = vmapEncode(vin.substring(3, 8), 34)
    var enSer = vmapEncode(vin.substring(8), 34)
    var c  = ", "
    console.log('INSERT INTO Vins VALUES('+index+c+enWmi+c+enVds+c+enSer+");")
    var decodeWMI = vmapDecode(enWmi, 34, 3)
    var decodeVDS = vmapDecode(enVds, 34, 5)
    var decodeSER = vmapDecode(enSer, 34, 9)
    var frakenstiened = decodeWMI + decodeVDS + decodeSER
    if (frakenstiened === vin) {
        return true
    } else {
        return false
    }
}

function vmapIndexOf(c) {
    return vmap.indexOf(c.toUpperCase())
}

function vmapEncode(data, base) {
    var vrad = 0
    var exp = 0
        //for each letter
    for (var index = 0; index < data.length; index++) {
        //mval is the index of the letter
        var mVal = vmapIndexOf(data[index]) + 1
            //exp shares the sie of the number
        var exp = (data.length - 1) - index
            //vrad is the cummulative number of the indexOfLetter *(base^exp)
        vrad += (Number(mVal) * Math.pow(base, exp))
            //console.log(data[index], mVal, exp, vrad)
    }
    //console.log(vrad)
    return vrad
}

function vmapDecode(vrad, base, length) {
    //out is decoded chars
    var out = ''
        //exp is the exponent will change with every iteration
    exp = 0
    for (var i = 0; i < length; i++) {
        var vmapi = 0
        var vDelta = 0
        exp = (length - 1) - i

        var vradp = base ** exp
        var vmod = vrad % vradp
        vDelta = vrad - vmod
            //console.log(vDelta, "|", vrad, "|", vmod)
        if (vmod > 0) {
            vDelta = vrad - vmod
            vmapi = vDelta / vradp
            vrad = vmod
                //console.log(vDelta, "|", vrad, "|", vmod)
        } else {
            vDelta = vrad
            vmapi = vrad
                //console.log(vDelta, vmapi)
        }
        if (0 < vmapi < 34) {
            //console.log(vmapi)
            //console.log(vmap.substring(vmapi - 1, vmapi))
            out += vmap.substring(vmapi - 1, vmapi)
        }
    }
    return out
}

var file = "vin100.csv"

var fs = require('fs');
const lineReader = require('line-reader')
var lines = fs.readFileSync(file, 'utf8').split('\n').filter(Boolean)

var validVins = []
var thisVin
for (var index = 0; index < lines.length; index++) {
    thisVin = encodeingCheck(lines[index])
    //console.log("VIN: ", lines[index], "\nValid Check: ", thisVin)
    validVins.push(thisVin)
}
var success = 0
var fails = 0
var total = lines.length
validVins.forEach(element => {
    if (element) {
        success++
    } else {
        fails++
    }
})
console.log("Done")
//console.log("Successful VINs: ", success, "\nFailed VINs: ", fails, "\nSuccess Rate: ", (success / total)*100, "%\n Fail Rate: ", (fails / total)*100,"%")